package com.hungteen.pvz.api;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public Optional<IPAZType> getTypeByID(String id) {
        return Optional.empty();
    }

}
