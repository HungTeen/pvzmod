package com.hungteen.pvz.api;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;
import com.hungteen.pvz.api.raid.IAmountComponent;
import com.hungteen.pvz.api.raid.IPlacementComponent;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.api.raid.IRewardComponent;
import com.hungteen.pvz.api.raid.ISpawnComponent;
import com.hungteen.pvz.api.raid.IWaveComponent;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.common.world.challenge.Challenge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void registerRankType(IRankType type) {

    }

    @Override
    public void registerSkillType(ISkillType type) {

    }

    @Override
    public void registerSkillTypes(Collection<ISkillType> types) {

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

	@Override
	public void registerSpawnAmount(String name, Class<? extends IAmountComponent> c) {
	}

	@Override
	public void registerSpawnPlacement(String name, Class<? extends IPlacementComponent> c) {
	}

	@Override
	public void registerRaidType(String name, Class<? extends IChallengeComponent> c) {
	}

	@Override
	public void registerWaveType(String name, Class<? extends IWaveComponent> c) {
	}

	@Override
	public void registerSpawnType(String name, Class<? extends ISpawnComponent> c) {
	}

	@Override
	public void registerReward(String name, Class<? extends IRewardComponent> c) {
	}

	@Override
	public boolean createRaid(ServerWorld world, ResourceLocation res, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isRaider(ServerWorld world, Entity entity) {
		return false;
	}

    @Override
    public Challenge getEntityChallenge(ServerWorld world, Entity entity) {
        return null;
    }

	@Override
	public Optional<Challenge> getNearByRaid(ServerWorld world, BlockPos pos) {
		return Optional.empty();
	}

	@Override
	public Map<ResourceLocation, IChallengeComponent> getRaidTypes() {
		return new HashMap<>();
	}

}
