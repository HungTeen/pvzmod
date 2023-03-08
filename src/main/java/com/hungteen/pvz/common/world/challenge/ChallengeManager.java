package com.hungteen.pvz.common.world.challenge;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.impl.challenge.reward.ItemRewardComponent;
import com.hungteen.pvz.common.impl.challenge.reward.UnLockRewardComponent;
import com.hungteen.pvz.RegistryHandler;
import com.hungteen.pvz.api.events.RaidEvent;
import com.hungteen.pvz.api.raid.*;
import com.hungteen.pvz.common.datapack.ChallengeTypeLoader;
import com.hungteen.pvz.common.impl.challenge.ChallengeComponent;
import com.hungteen.pvz.common.impl.challenge.SpawnComponent;
import com.hungteen.pvz.common.impl.challenge.WaveComponent;
import com.hungteen.pvz.common.impl.challenge.amount.ConstantAmount;
import com.hungteen.pvz.common.impl.challenge.amount.RandomAmount;
import com.hungteen.pvz.common.impl.challenge.placement.CenterPlacement;
import com.hungteen.pvz.common.impl.challenge.placement.OffsetPlacement;
import com.hungteen.pvz.common.impl.challenge.placement.OuterPlacement;
import com.hungteen.pvz.common.impl.challenge.reward.AdvancementRewardComponent;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

public class ChallengeManager {

	private static final Map<String, Class<? extends IChallengeComponent>> CHALLENGE_TYPE_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IWaveComponent>> WAVE_TYPE_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends ISpawnComponent>> SPAWN_TYPE_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IAmountComponent>> AMOUNT_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IPlacementComponent>> PLACEMENT_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IRewardComponent>> REWARD_MAP = Maps.newHashMap();

	/**
	 * {@link RegistryHandler#coreRegister()}
	 */
	public static void registerChallengeStuff(){
		/* challenge */
		registerChallengeComponent(ChallengeComponent.NAME, ChallengeComponent.class);
		/* wave */
		registerWaveComponent(WaveComponent.NAME, WaveComponent.class);
		/* spawn */
		registerSpawnComponent(SpawnComponent.NAME, SpawnComponent.class);
		/* placement */
		registerPlacementComponent(CenterPlacement.NAME, CenterPlacement.class);
		registerPlacementComponent(OffsetPlacement.NAME, OffsetPlacement.class);
		registerPlacementComponent(OuterPlacement.NAME, OuterPlacement.class);
		/* amount */
		registerAmountComponent(ConstantAmount.NAME, ConstantAmount.class);
		registerAmountComponent(RandomAmount.NAME, RandomAmount.class);
		/* reward */
		registerRewardComponent(AdvancementRewardComponent.NAME, AdvancementRewardComponent.class);
		registerRewardComponent(ItemRewardComponent.NAME, ItemRewardComponent.class);
		registerRewardComponent(UnLockRewardComponent.NAME, UnLockRewardComponent.class);
	}
	
	public static void tickChallenges(World world) {
		if(! world.isClientSide) {
			final PVZChallengeData data = PVZChallengeData.getInvasionData(world);
			data.tick();
		}
	}
	
	public static boolean hasChallengeNearby(ServerWorld world, BlockPos pos) {
		return getChallengeNearBy(world, pos).isPresent();
	}

	public static Optional<Challenge> getChallengeNearBy(ServerWorld world, BlockPos pos){
		final List<Challenge> list = getChallenges(world);
		for(Challenge r : list) {
			if(Math.abs(r.getCenter().getX() - pos.getX()) <= ConfigUtil.getRaidRange()
					&& Math.abs(r.getCenter().getY() - pos.getY()) <= ConfigUtil.getRaidRange()
					&& Math.abs(r.getCenter().getZ() - pos.getZ()) <= ConfigUtil.getRaidRange()) {
				return Optional.ofNullable(r);
			}
		}
		return Optional.empty();
	}
	
	public static boolean createChallenge(ServerWorld world, ResourceLocation res, BlockPos pos) {
		final PVZChallengeData data = PVZChallengeData.getInvasionData(world);
		Optional<Challenge> opt = data.createChallenge(world, res, pos);
		opt.ifPresent(r -> {
				MinecraftForge.EVENT_BUS.post(new RaidEvent.RaidStartEvent(r));
		});
		return opt.isPresent();
	}
	
	public static List<Challenge> getChallenges(ServerWorld world) {
		return PVZChallengeData.getInvasionData(world).getChallenges();
	}

	public static Map<ResourceLocation, IChallengeComponent> getChallengeTypes() {
		return Collections.unmodifiableMap(ChallengeTypeLoader.CHALLENGE_MAP);
	}
	
	public static boolean isRaider(ServerWorld world, Entity entity) {
		final PVZChallengeData data = PVZChallengeData.getInvasionData(world);
		for(Challenge raid : data.getChallenges()) {
			if(raid.isRaider(entity)) {
				return true;
			}
		}
		return false;
	}

	public static Challenge getEntityChallenge(ServerWorld world, Entity entity) {
		final PVZChallengeData data = PVZChallengeData.getInvasionData(world);
		for(Challenge raid : data.getChallenges()) {
			if(raid.isRaider(entity)) {
				return raid;
			}
		}
		return null;
	}
	
	@Nullable
	public static IChallengeComponent getChallengeByResource(ResourceLocation res) {
		return ChallengeTypeLoader.CHALLENGE_MAP.getOrDefault(res, null);
	}

	@Nullable
	public static ResourceLocation getResourceByChallenge(IChallengeComponent challengeComponent) {
		return ChallengeTypeLoader.RES_MAP.getOrDefault(challengeComponent, null);
	}

	public static Stream<ResourceLocation> getIds() {
		return ChallengeTypeLoader.CHALLENGE_MAP.keySet().stream();
	}

	/*
	register stuff.
	 */

	public static void registerAmountComponent(String name, Class<? extends IAmountComponent> c) {
		if(AMOUNT_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Spawn Amount : duplicate name, overwrited.");
		}
		AMOUNT_MAP.put(name, c);
	}

	public static void registerPlacementComponent(String name, Class<? extends IPlacementComponent> c) {
		if(PLACEMENT_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Spawn Placement : duplicate name, overwrited.");
		}
		PLACEMENT_MAP.put(name, c);
	}

	public static void registerRewardComponent(String name, Class<? extends IRewardComponent> c) {
		if(REWARD_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Reward : duplicate name, overwrited.");
		}
		REWARD_MAP.put(name, c);
	}

	public static void registerChallengeComponent(String name, Class<? extends IChallengeComponent> c) {
		if(CHALLENGE_TYPE_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Challenge Type : duplicate name, overwrited.");
		}
		CHALLENGE_TYPE_MAP.put(name, c);
	}

	public static void registerWaveComponent(String name, Class<? extends IWaveComponent> c) {
		if(WAVE_TYPE_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Wave Type : duplicate name, overwrited.");
		}
		WAVE_TYPE_MAP.put(name, c);
	}

	public static void registerSpawnComponent(String name, Class<? extends ISpawnComponent> c) {
		if(SPAWN_TYPE_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Spawn Type : duplicate name, overwrited.");
		}
		SPAWN_TYPE_MAP.put(name, c);
	}

	/*
	get stuff.
	 */
	
	/**
	 * get amount component by type name.
	 */
	public static IAmountComponent getAmountComponent(String name) {
		if(AMOUNT_MAP.containsKey(name)) {
			try {
				return AMOUNT_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			PVZMod.LOGGER.warn("Spawn Amount Missing : can not find {}", name);
		}
		return new ConstantAmount();
	}
	
	/**
	 * get placement component by type name.
	 */
	@Nullable
	public static IPlacementComponent getPlacementComponent(String name) {
		if(PLACEMENT_MAP.containsKey(name)) {
			try {
				return PLACEMENT_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			PVZMod.LOGGER.warn("Spawn Placement Missing : can not find {}", name);
		}
		return null;
	}
	
	/**
	 * get placement component by type name.
	 */
	@Nullable
	public static IRewardComponent getRewardComponent(String name) {
		if(REWARD_MAP.containsKey(name)) {
			try {
				return REWARD_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			PVZMod.LOGGER.warn("Reward Missing : can not find {}", name);
		}
		return null;
	}
	
	/**
	 * get raid component by type name.
	 */
	@Nullable
	public static IChallengeComponent getChallengeComponent(String name) {
		if(CHALLENGE_TYPE_MAP.containsKey(name)) {
			try {
				return CHALLENGE_TYPE_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if(! name.equals("")){
			PVZMod.LOGGER.warn("Raid Type Missing : can not find {}", name);
		}
		return new ChallengeComponent();
	}
	
	/**
	 * get wave component by type name.
	 */
	@Nullable
	public static IWaveComponent getWaveComponent(String name) {
		if(WAVE_TYPE_MAP.containsKey(name)) {
			try {
				return WAVE_TYPE_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if(! name.equals("")){
			PVZMod.LOGGER.warn("Wave Type Missing : can not find {}", name);
		}
		return new WaveComponent();
	}
	
	/**
	 * get spawn component by type name.
	 */
	@Nullable
	public static ISpawnComponent getSpawnComponent(String name) {
		if(SPAWN_TYPE_MAP.containsKey(name)) {
			try {
				return SPAWN_TYPE_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if(! name.equals("")){
			PVZMod.LOGGER.warn("Spawn Type Missing : can not find {}", name);
		}
		return new SpawnComponent();
	}

	/*
	read method.
	 */

	/**
	 * flag set false to make default placement as null.
	 */
	@Nullable
	public static IPlacementComponent readPlacement(JsonObject jsonObject, boolean flag) {
		/* spawn placement */
		IPlacementComponent placement = flag ? new CenterPlacement() : null;
		JsonObject obj = JSONUtils.getAsJsonObject(jsonObject, StringUtil.SPAWN_PLACEMENT, null);
		if(obj != null && ! obj.entrySet().isEmpty()) {
			for(Entry<String, JsonElement> entry : obj.entrySet()) {
				final IPlacementComponent tmp = ChallengeManager.getPlacementComponent(entry.getKey());
				if(tmp != null) {
					tmp.readJson(entry.getValue());
					placement = tmp;
				} else {
					PVZMod.LOGGER.warn("Placement Component : Read Spawn Placement Wrongly");
				}
				break;
			}
		}
		return placement;
	}

	public static IAmountComponent readAmount(JsonObject json, String amountTag){
		JsonObject obj = JSONUtils.getAsJsonObject(json, amountTag);
		if(obj != null && ! obj.entrySet().isEmpty()) {
			for(Entry<String, JsonElement> entry : obj.entrySet()) {
				final IAmountComponent tmp = getAmountComponent(entry.getKey());
				if(tmp != null) {
					tmp.readJson(entry.getValue());
					return tmp;
				} else {
					PVZMod.LOGGER.warn("Amount Component : Read Spawn Amount Wrongly");
				}
				break;
			}
		}
		return new ConstantAmount();
	}
	
}
