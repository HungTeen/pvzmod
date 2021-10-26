package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
		//TODO 根据id获取type
		return Optional.empty();
	}

}
