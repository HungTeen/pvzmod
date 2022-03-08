package com.hungteen.pvz.common.world.challenge;

import com.google.common.collect.Maps;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.utils.ConfigUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListNBT;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.storage.WorldSavedData;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PVZChallengeData extends WorldSavedData {

	private static final String DATA_NAME = "ChallengeData";
	private final Map<Integer, Challenge> challengeMap = Maps.newHashMap();
	private final ServerLevel world;
	private int currentChallengeId = 1;
	private int tick = 0;

	public PVZChallengeData(ServerLevel world) {
		super(DATA_NAME);
		this.world = world;
	}

	/**
	 * tick all raid in running.
	 * {@link ChallengeManager#tickChallenges(Level)}
	 */
	public void tick() {
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
	
	public Optional<Challenge> createChallenge(ServerLevel world, ResourceLocation res, Mth pos) {
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

	@Override
	public void load(CompoundTag nbt) {
		if (nbt.contains("current_id")) {
			this.currentChallengeId = nbt.getInt("current_id");
		}
		final ListNBT raidList = nbt.getList("challenges", 10);
		for (int i = 0; i < raidList.size(); ++i) {
			final CompoundTag tmp = raidList.getCompound(i);
			final Challenge raid = new Challenge(world, tmp);
			this.challengeMap.put(raid.getId(), raid);
		}
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		nbt.putInt("current_id", this.currentChallengeId);

		final ListNBT raidList = new ListNBT();
		for (Challenge raid : this.challengeMap.values()) {
			final CompoundTag tmp = new CompoundTag();
			raid.save(tmp);
			raidList.add(tmp);
		}
		nbt.put("challenges", raidList);

		return nbt;
	}

	public static PVZChallengeData getInvasionData(Level worldIn) {
		if (!(worldIn instanceof ServerLevel)) {
			throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
		}
		return ((ServerLevel) worldIn).getDataStorage().computeIfAbsent(() -> new PVZChallengeData((ServerLevel) worldIn), DATA_NAME);
	}

}
