package com.hungteen.pvz.world.data;

import java.util.HashSet;

import com.hungteen.pvz.utils.enums.Events;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class WorldEventData extends WorldSavedData{

	private static final String DATA_NAME = "WorldEventData";
	private HashSet<Events> events = new HashSet<>(Events.values().length);
	private boolean isZomBossDefeated = false;//is zomboss defeated
	private boolean mustStartNextDay = false;//nextDay must start a event
	private boolean mustNotStartNextDay = false;//nextDay must not start a event
	
	public WorldEventData() {
		super(DATA_NAME);
	}

	@Override
	public void read(CompoundNBT nbt) {
		events.clear();
		ListNBT list = (ListNBT) nbt.get("event");
		if(list==null) {
			list = new ListNBT();
		}
		for(int i=0;i<list.size();i++) {
			CompoundNBT tmp = list.getCompound(i);
			events.add(Events.values()[tmp.getInt("id")]);
		}
		this.isZomBossDefeated=nbt.getBoolean("is_zomboss_defeated");
		this.mustStartNextDay=nbt.getBoolean("must_start_next_day");
		this.mustNotStartNextDay=nbt.getBoolean("must_not_start_next_day");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		ListNBT list = new ListNBT();
		for(Events ev:events) {
			CompoundNBT tmp = new CompoundNBT();
			tmp.putInt("id", ev.ordinal());
			list.add(tmp);
		}
		nbt.put("event", list);
		nbt.putBoolean("is_zomboss_defeated",this.isZomBossDefeated);
		nbt.putBoolean("must_start_next_day",this.mustStartNextDay);
		nbt.putBoolean("must_not_start_next_day",this.mustNotStartNextDay);
		return nbt;
	}
	
	public static WorldEventData getOverWorldEventData(World worldIn) {
		if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
		ServerWorld world = worldIn.getServer().getWorld(DimensionType.OVERWORLD);
		DimensionSavedDataManager storage = world.getSavedData();
		return storage.getOrCreate(WorldEventData::new,DATA_NAME);
	}

}
