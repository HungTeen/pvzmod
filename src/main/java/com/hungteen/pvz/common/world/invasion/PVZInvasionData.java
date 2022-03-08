package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class PVZInvasionData extends WorldSavedData {

	private static final String DATA_NAME = "InvasionEventData";
	private boolean changed = false;
	private int countDownDay = PVZConfig.COMMON_CONFIG.InvasionSettings.InvasionIntervalLength.get();

	public PVZInvasionData() {
		super(DATA_NAME);
	}

	// getter setter for change flag.
	public void setChanged(boolean is) {
		this.changed = is;
		this.setDirty();
	}

	public boolean hasChanged() {
		return this.changed;
	}

	// getter setter for count down day.
	public void setCountDownDay(int day) {
		this.countDownDay = day;
		this.setDirty();
	}

	public void decCountDownDay() {
		if(this.countDownDay <= 0) {
			this.countDownDay = PVZConfig.COMMON_CONFIG.InvasionSettings.InvasionIntervalLength.get();
		} else {
			-- this.countDownDay;
		}
		this.setDirty();
	}

	public int getCountDownDay() {
		return this.countDownDay;
	}
	
	public boolean hasCountDownDay() {
		return this.getCountDownDay() > 0;
	}


	@Override
	public void load(CompoundTag nbt) {
		this.changed = nbt.getBoolean("changed");
		this.countDownDay = nbt.getInt("count_down_day");
	}

	@Override
	public CompoundTag save(CompoundTag nbt) {
		nbt.putBoolean("changed", this.changed);
		nbt.putInt("count_down_day", this.countDownDay);
		return nbt;
	}

//	public static PVZInvasionData getInvasionData(World worldIn) {
//		if (!(worldIn instanceof ServerWorld)) {
//			throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
//		}
//		ServerWorld world = (ServerWorld) worldIn;
//		DimensionSavedDataManager storage = world.getDataStorage();
//		return storage.computeIfAbsent(PVZInvasionData::new, DATA_NAME);
//	}
	
	public static PVZInvasionData getOverWorldInvasionData(Level worldIn) {
		if (!(worldIn instanceof ServerLevel)) {
			throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
		}
		final ServerLevel world = worldIn.getServer().getLevel(Level.OVERWORLD);
		DimensionSavedDataManager storage = world.getDataStorage();
		return storage.computeIfAbsent(PVZInvasionData::new, DATA_NAME);
	}

}
