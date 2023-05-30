package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;
import com.hungteen.pvz.api.raid.*;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.item.tool.plant.BowlingGloveItem;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;
import com.hungteen.pvz.common.world.challenge.Challenge;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.*;
import java.util.function.Supplier;

;

public class PVZAPIImpl implements IPVZAPI{

	@Override
	public void registerPlantType(IPlantType type) {
		PlantType.registerPlant(type);
	}

	@Override
	public void registerPlantTypes(Collection<IPlantType> types) {
		PlantType.registerPlants(types);
	}

	@Override
	public void registerZombieType(IZombieType type) {
		ZombieType.registerZombie(type);
	}

	@Override
	public void registerZombieTypes(Collection<IZombieType> types) {
		ZombieType.registerZombies(types);
	}

	@Override
	public void registerEssenceType(IEssenceType type) {
		EssenceTypes.registerEssence(type);
	}

	@Override
	public void registerEssenceTypes(Collection<IEssenceType> types) {
		types.forEach(type -> EssenceTypes.registerEssence(type));
	}

	@Override
	public void registerRankType(IRankType type) {
		RankTypes.registerRankType(type);
	}

	@Override
	public void registerSkillType(ISkillType type) {
		SkillTypes.registerSkillType(type);
	}

	@Override
	public void registerSkillTypes(Collection<ISkillType> types) {
		types.forEach(type -> registerSkillType(type));
	}

	@Override
	public void registerCD(ICoolDown type) {
		CoolDowns.registerCD(type);
	}

	@Override
	public void registerCDs(Collection<ICoolDown> types) {
		CoolDowns.registerCDs(types);
	}

	@Override
	public void registerSpawnAmount(String name, Class<? extends IAmountComponent> c) {
		ChallengeManager.registerAmountComponent(name, c);
	}

	@Override
	public void registerSpawnPlacement(String name, Class<? extends IPlacementComponent> c) {
		ChallengeManager.registerPlacementComponent(name, c);
	}

	@Override
	public void registerReward(String name, Class<? extends IRewardComponent> c) {
		ChallengeManager.registerRewardComponent(name, c);
	}

	@Override
	public void registerRaidType(String name, Class<? extends IChallengeComponent> c) {
		ChallengeManager.registerChallengeComponent(name, c);
	}

	@Override
	public void registerWaveType(String name, Class<? extends IWaveComponent> c) {
		ChallengeManager.registerWaveComponent(name, c);
	}

	@Override
	public void registerSpawnType(String name, Class<? extends ISpawnComponent> c) {
		ChallengeManager.registerSpawnComponent(name, c);
	}

	@Override
	public boolean createRaid(ServerWorld world, ResourceLocation res, BlockPos pos) {
		if(! ChallengeManager.hasChallengeNearby(world, pos)) {
			return ChallengeManager.createChallenge(world, res, pos);
		}
		return false;
	}

	@Override
	public boolean isRaider(ServerWorld world, Entity entity) {
		return ChallengeManager.isRaider(world, entity);
	}

	public Challenge getEntityChallenge(ServerWorld world, Entity entity){
		return ChallengeManager.getEntityChallenge(world, entity);
	}

	@Override
	public Optional<Challenge> getNearByRaid(ServerWorld world, BlockPos pos) {
		return ChallengeManager.getChallengeNearBy(world, pos);
	}

	@Override
	public Map<ResourceLocation, IChallengeComponent> getRaidTypes() {
		return ChallengeManager.getChallengeTypes();
	}

	@Override
	public List<IPlantType> getPlants() {
		return PlantType.getPlants();
	}

	@Override
	public List<IZombieType> getZombies() {
		return ZombieType.getZombies();
	}

	@Override
	public List<IPAZType> getPAZs() {
		final List<IPAZType> list = new ArrayList<>();
		list.addAll(getPlants());
		list.addAll(getZombies());
		return list;
	}

	@Override
	public List<IEssenceType> getEssences() {
		return EssenceTypes.getEssences();
	}

	@Override
	public Optional<IPAZType> getTypeByID(String id) {
		final Optional<IPlantType> opt1 = getPlantTypeByID(id);
		final Optional<IZombieType> opt2 = getZombieTypeByID(id);
		return opt1.isPresent() ? Optional.ofNullable(opt1.get()) : Optional.ofNullable(opt2.get());
	}

	@Override
	public Optional<IPlantType> getPlantTypeByID(String id) {
		return PlantType.getPlantByName(id);
	}

	@Override
	public Optional<IZombieType> getZombieTypeByID(String id) {
		return ZombieType.getZombieByName(id);
	}

	@Override
	public void registerPeaGunMode(IPlantType type) {
		PeaGunItem.registerPeaGunShootMode(type);
	}

	@Override
	public void registerBowlingMode(IPlantType type, Supplier<EntityType<? extends Entity>> supplier, float size) {
		BowlingGloveItem.registerBowling(type, supplier, size);
	}

}
