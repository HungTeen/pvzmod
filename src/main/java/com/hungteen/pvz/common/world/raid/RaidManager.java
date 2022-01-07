package com.hungteen.pvz.common.world.raid;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.events.RaidEvent;
import com.hungteen.pvz.api.raid.*;
import com.hungteen.pvz.common.impl.raid.RaidComponent;
import com.hungteen.pvz.common.impl.raid.SpawnComponent;
import com.hungteen.pvz.common.impl.raid.WaveComponent;
import com.hungteen.pvz.common.impl.raid.amount.ConstantAmount;
import com.hungteen.pvz.common.impl.raid.placement.CenterPlacement;
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

public class RaidManager {

	private static final Map<ResourceLocation, IRaidComponent> RAID_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IRaidComponent>> RAID_TYPE_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IWaveComponent>> WAVE_TYPE_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends ISpawnComponent>> SPAWN_TYPE_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IAmountComponent>> AMOUNT_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IPlacementComponent>> PLACEMENT_MAP = Maps.newHashMap();
	private static final Map<String, Class<? extends IRewardComponent>> REWARD_MAP = Maps.newHashMap();
	
	/**
	 * {@link RaidLoader#apply(Map, net.minecraft.resources.IResourceManager, net.minecraft.profiler.IProfiler)}
	 */
	public static void finishRaidMap(Map<ResourceLocation, IRaidComponent> map) {
		for(Entry<ResourceLocation, IRaidComponent> entry : map.entrySet()) {
			RAID_MAP.put(entry.getKey(), entry.getValue());
		}
	}
	
	public static void tickRaids(World world) {
		if(! world.isClientSide) {
			final PVZRaidData data = PVZRaidData.getInvasionData(world);
			data.tick();
		}
	}
	
	public static boolean hasRaidNearby(ServerWorld world, BlockPos pos) {
		return getRaidNearBy(world, pos).isPresent();
	}

	public static Optional<Raid> getRaidNearBy(ServerWorld world, BlockPos pos){
		final List<Raid> list = getRaids(world);
		for(Raid r : list) {
			if(Math.abs(r.getCenter().getX() - pos.getX()) <= ConfigUtil.getRaidRange()
					|| Math.abs(r.getCenter().getY() - pos.getY()) <= ConfigUtil.getRaidRange()
					|| Math.abs(r.getCenter().getZ() - pos.getZ()) <= ConfigUtil.getRaidRange()) {
				return Optional.ofNullable(r);
			}
		}
		return Optional.empty();
	}
	
	public static boolean createRaid(ServerWorld world, ResourceLocation res, BlockPos pos) {
		final PVZRaidData data = PVZRaidData.getInvasionData(world);
		Optional<Raid> opt = data.createRaid(world, res, pos);
		opt.ifPresent(r -> {
				MinecraftForge.EVENT_BUS.post(new RaidEvent.RaidStartEvent(r));
		});
		return opt.isPresent();
	}
	
	public static List<Raid> getRaids(ServerWorld world) {
		return PVZRaidData.getInvasionData(world).getRaids();
	}

	public static Map<ResourceLocation, IRaidComponent> getRaidTypes() {
		return Collections.unmodifiableMap(RAID_MAP);
	}
	
	public static boolean isRaider(ServerWorld world, Entity entity) {
		final PVZRaidData data = PVZRaidData.getInvasionData(world);
		for(Raid raid : data.getRaids()) {
			if(raid.isRaider(entity)) {
				return true;
			}
		}
		return false;
	}
	
	@Nullable
	public static IRaidComponent getRaidComponent(ResourceLocation res) {
		return RAID_MAP.getOrDefault(res, null);
	}

	public static void registerSpawnAmount(String name, Class<? extends IAmountComponent> c) {
		if(AMOUNT_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Spawn Amount : duplicate name, overwrited.");
		}
		AMOUNT_MAP.put(name, c);
	}

	public static void registerSpawnPlacement(String name, Class<? extends IPlacementComponent> c) {
		if(PLACEMENT_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Spawn Placement : duplicate name, overwrited.");
		}
		PLACEMENT_MAP.put(name, c);
	}

	public static void registerReward(String name, Class<? extends IRewardComponent> c) {
		if(REWARD_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Reward : duplicate name, overwrited.");
		}
		REWARD_MAP.put(name, c);
	}

	public static void registerRaidType(String name, Class<? extends IRaidComponent> c) {
		if(RAID_TYPE_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Raid Type : duplicate name, overwrited.");
		}
		RAID_TYPE_MAP.put(name, c);
	}

	public static void registerWaveType(String name, Class<? extends IWaveComponent> c) {
		if(WAVE_TYPE_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Wave Type : duplicate name, overwrited.");
		}
		WAVE_TYPE_MAP.put(name, c);
	}

	public static void registerSpawnType(String name, Class<? extends ISpawnComponent> c) {
		if(SPAWN_TYPE_MAP.containsKey(name)) {
			PVZMod.LOGGER.warn("Register Spawn Type : duplicate name, overwrited.");
		}
		SPAWN_TYPE_MAP.put(name, c);
	}
	
	/**
	 * get amount component by type name.
	 */
	public static IAmountComponent getSpawnAmount(String name) {
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
	public static IPlacementComponent getSpawnPlacement(String name) {
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
	public static IRewardComponent getReward(String name) {
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
	public static IRaidComponent getRaidType(String name) {
		if(RAID_TYPE_MAP.containsKey(name)) {
			try {
				return RAID_TYPE_MAP.get(name).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if(! name.equals("")){
			PVZMod.LOGGER.warn("Raid Type Missing : can not find {}", name);
		}
		return new RaidComponent();
	}
	
	/**
	 * get wave component by type name.
	 */
	@Nullable
	public static IWaveComponent getWaveType(String name) {
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
	public static ISpawnComponent getSpawnType(String name) {
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
				final IPlacementComponent tmp = RaidManager.getSpawnPlacement(entry.getKey());
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
	
}
