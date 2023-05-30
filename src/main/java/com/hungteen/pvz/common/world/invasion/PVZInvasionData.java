package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.utils.ConfigUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
		return this.countDownDay; //reflect actual count only when ConfigUtil.scatteredInvasions() set false
	}

	public int getCountDownDay(PlayerEntity player) {
		return ConfigUtil.scatteredInvasions() ? (int)(player.getUUID().getMostSignificantBits()+countDownDay) % PVZConfig.COMMON_CONFIG.InvasionSettings.InvasionIntervalLength.get() : getCountDownDay();
	}
	
	public boolean hasCountDownDay() {
		return this.getCountDownDay() <= 0; //reflect actual count only when ConfigUtil.scatteredInvasions() set false
	}

	public boolean hasCountDownDay(PlayerEntity player){
		return ConfigUtil.scatteredInvasions() ? (player.getUUID().getMostSignificantBits()+countDownDay) % PVZConfig.COMMON_CONFIG.InvasionSettings.InvasionIntervalLength.get() <= 0 : hasCountDownDay() ;
	}


	@Override
	public void load(CompoundNBT nbt) {
		this.changed = nbt.getBoolean("changed");
		this.countDownDay = nbt.getInt("count_down_day");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
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
	
	public static PVZInvasionData getOverWorldInvasionData(World worldIn) {
		if (!(worldIn instanceof ServerWorld)) {
			throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
		}
		final ServerWorld world = worldIn.getServer().getLevel(World.OVERWORLD);
		DimensionSavedDataManager storage = world.getDataStorage();
		return storage.computeIfAbsent(PVZInvasionData::new, DATA_NAME);
	}

}
