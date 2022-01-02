package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.common.item.tool.plant.BowlingGloveItem;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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
		EssenceType.registerEssence(type);
	}

	@Override
	public void registerEssenceTypes(Collection<IEssenceType> types) {
		EssenceType.registerEssences(types);
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
		return EssenceType.getEssences();
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
