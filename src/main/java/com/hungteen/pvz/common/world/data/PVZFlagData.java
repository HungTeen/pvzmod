package com.hungteen.pvz.common.world.data;

import com.hungteen.pvz.common.cache.FlagCache;
import com.hungteen.pvz.common.entity.zombie.roof.Edgar090505Entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class PVZFlagData extends WorldSavedData {

	private static final String DATA_NAME = "PVZFlagData";
	private int zomboss1DefeatedCount = 0; //how many times zomboss1 was defeated
	
	public PVZFlagData() {
		super(DATA_NAME);
	}
	
	public PVZFlagData(String name) {
		super(name);
	}
	
	/**
	 * {@link Edgar090505Entity#die(net.minecraft.util.DamageSource)}
	 */
	public void addAdgarDefeatedCount() {
		++ this.zomboss1DefeatedCount;
		FlagCache.isEdgar090505Defeated = (this.zomboss1DefeatedCount > 0);
		setDirty();
	}
	
	public void setZomboss1DefeatedCount(int cnt) {
		this.zomboss1DefeatedCount = cnt;
		FlagCache.isEdgar090505Defeated = (this.zomboss1DefeatedCount > 0);
		setDirty();
	}
	
	public int getZomboss1DefeatedCount() {
		return this.zomboss1DefeatedCount;
	}
	
	public boolean isZombossDefeated() {
		return this.zomboss1DefeatedCount > 0;
	}

	@Override
	public void load(CompoundNBT nbt) {
		if(nbt.contains("zomboss1_defeat_count")) {
			this.setZomboss1DefeatedCount(nbt.getInt("zomboss1_defeat_count"));
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		nbt.putInt("zomboss1_defeat_count", this.getZomboss1DefeatedCount());
		return nbt;
	}
	
	public static PVZFlagData getGlobalFlagData(World worldIn) {
		if (! (worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
		ServerWorld world = worldIn.getServer().getLevel(World.OVERWORLD);
		DimensionSavedDataManager storage = world.getDataStorage();
		return storage.computeIfAbsent(PVZFlagData::new, DATA_NAME);
	}
	
}
