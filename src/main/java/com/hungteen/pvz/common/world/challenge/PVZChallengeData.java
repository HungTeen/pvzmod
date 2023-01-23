package com.hungteen.pvz.common.world.challenge;

import com.google.common.collect.Maps;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.challenge.RaiderDataProvider;
import com.hungteen.pvz.utils.ConfigUtil;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PVZChallengeData extends WorldSavedData {

	private static final String DATA_NAME = "ChallengeData";
	private final Map<Integer, Challenge> challengeMap = Maps.newHashMap();
	private final ServerWorld world;
	private int currentChallengeId = 1;
	private int tick = 0;

	public PVZChallengeData(ServerWorld world) {
		super(DATA_NAME);
		this.world = world;
	}

	/**
	 * tick all raid in running.
	 * {@link ChallengeManager#tickChallenges(World)}
	 */
	public void tick() {
		for (Object entity: world.getEntities().toArray()){
			((Entity) entity).getCapability(CapabilityHandler.RAIDER_DATA_CAPABILITY).ifPresent(x -> {
				if (x.getChallengeID() == 0){
					for(Challenge tmp: this.getChallenges()){
						if (tmp.raiders.contains(entity)){
							x.setChallengeID(tmp.getId());
						};
					}
					if (x.getChallengeID() == 0){
						x.setChallengeID(-1);
					}
				}
				else if (matchID(x.getChallengeID()) == null) {
					x.setChallengeID(0);
				}
				else if (x.getChallengeID() != -1) {
					matchID(x.getChallengeID()).raiders.add((Entity) entity);
				}
			});
		}

		Iterator<Challenge> iterator = this.challengeMap.values().iterator();
		while (iterator.hasNext()) {
			Challenge raid = iterator.next();
			if (! ConfigUtil.isRaidEnable()) {
				raid.remove();
			}
			if (raid.isRemoving()) {
				iterator.remove();
				this.setDirty();
			} else {
				this.world.getProfiler().push("Challenge Tick");
				raid.tick();
				this.world.getProfiler().pop();
			}
		}

		if (++ this.tick % 200 == 0) {
			this.setDirty();
		}
	}
	
	public Optional<Challenge> createChallenge(ServerWorld world, ResourceLocation res, BlockPos pos) {
		final int id = this.getUniqueId();
		IChallengeComponent tmp = ChallengeManager.getChallengeByResource(res);
		if(tmp != null && tmp.isSuitableDimension(world.dimension())){
			final Challenge challenge = new Challenge(id, world, res, pos);
			this.addChallenge(id, challenge);
			return Optional.ofNullable(challenge);
		}
		PVZMod.LOGGER.error("Challenge Create : Wrong ResourceLocation or Dimension for {} !", res);
		return Optional.empty();
	}
	
	public void addChallenge(int id, Challenge challenge) {
		this.challengeMap.put(id, challenge);
		this.setDirty();
	}

	public int getUniqueId() {
		this.setDirty();
		return this.currentChallengeId++;
	}

	public List<Challenge> getChallenges() {
		return this.challengeMap.values().stream().collect(Collectors.toList());
	}

	public Challenge matchID(int id) {
		for (Challenge challenge : this.getChallenges()) {
			if (challenge.getId() == id) {
				return challenge;
			}
		}
		return null;
	}
	@Override
	public void load(CompoundNBT nbt) {
		if (nbt.contains("current_id")) {
			this.currentChallengeId = nbt.getInt("current_id");
		}
		final ListNBT raidList = nbt.getList("challenges", 10);
		for (int i = 0; i < raidList.size(); ++i) {
			final CompoundNBT tmp = raidList.getCompound(i);
			final Challenge raid = new Challenge(world, tmp);
			this.challengeMap.put(raid.getId(), raid);
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		nbt.putInt("current_id", this.currentChallengeId);

		final ListNBT raidList = new ListNBT();
		for (Challenge raid : this.challengeMap.values()) {
			final CompoundNBT tmp = new CompoundNBT();
			raid.save(tmp);
			raidList.add(tmp);
		}
		nbt.put("challenges", raidList);

		return nbt;
	}

	public static PVZChallengeData getInvasionData(World worldIn) {
		if (!(worldIn instanceof ServerWorld)) {
			throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
		}
		return ((ServerWorld) worldIn).getDataStorage().computeIfAbsent(() -> new PVZChallengeData((ServerWorld) worldIn), DATA_NAME);
	}

}
