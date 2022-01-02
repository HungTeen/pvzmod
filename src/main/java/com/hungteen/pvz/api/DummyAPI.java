package com.hungteen.pvz.api;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;
import com.hungteen.pvz.api.types.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * fake dummy API when there is no Custom Raid mod.
 */
public class DummyAPI implements IPVZAPI {

    public static final IPVZAPI INSTANCE = new DummyAPI();

    @Override
    public void registerPlantType(IPlantType type) {
    }

    @Override
    public void registerPlantTypes(Collection<IPlantType> types) {
    }

    @Override
    public void registerZombieType(IZombieType type) {
    }

    @Override
    public void registerZombieTypes(Collection<IZombieType> types) {
    }

    @Override
    public void registerEssenceType(IEssenceType type) {

    }

    @Override
    public void registerEssenceTypes(Collection<IEssenceType> types) {
    }

    @Override
    public void registerCD(ICoolDown type) {
    }

    @Override
    public void registerCDs(Collection<ICoolDown> types) {

    }

    @Override
    public List<IPlantType> getPlants() {
        return new ArrayList<>();
    }

    @Override
    public List<IZombieType> getZombies() {
        return new ArrayList<>();
    }

    @Override
    public List<IPAZType> getPAZs() {
        return new ArrayList<>();
    }

    @Override
    public List<IEssenceType> getEssences() {
        return new ArrayList<>();
    }

    @Override
    public Optional<IPAZType> getTypeByID(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<IPlantType> getPlantTypeByID(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<IZombieType> getZombieTypeByID(String id) {
        return Optional.empty();
    }

	@Override
	public void registerPeaGunMode(IPlantType type) {
	}

	@Override
	public void registerBowlingMode(IPlantType type, Supplier<EntityType<? extends Entity>> supplier, float size) {
	}

}
