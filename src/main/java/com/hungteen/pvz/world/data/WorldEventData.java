package com.hungteen.pvz.world.data;

import java.util.HashSet;

import com.hungteen.pvz.utils.enums.Events;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class WorldEventData extends WorldSavedData{

	private static final String DATA_NAME = "WorldEventData";
	private HashSet<Events> events = new HashSet<>(Events.values().length);
	private boolean changed = false;
	private boolean isZomBossDefeated = false;//is zomboss defeated
	private boolean mustStartNextDay = false;//nextDay must start a event
	private boolean mustNotStartNextDay = false;//nextDay must not start a event
	
	public WorldEventData() {
		super(DATA_NAME);
	}
	
	public boolean hasEvent(Events ev) {
		return events.contains(ev);
	}
	
	public void addEvent(Events ev) {
		events.add(ev);
//		System.out.println(events.contains(ev));
		this.markDirty();
	}
	
	public void removeEvent(Events ev) {
		events.remove(ev);
		this.markDirty();
	}
	
	public void setChanged(boolean is) {
		this.changed=is;
		this.markDirty();
	}
	
	public boolean getChanged() {
		return this.changed;
	}
	
	public void setIsZomBossDefeated(boolean is) {
		this.isZomBossDefeated=is;
		this.markDirty();
	}
	
	public boolean getIsZomBossDefeated() {
		return this.isZomBossDefeated;
	}
	
	public void setMustStartNextDay(boolean is) {
		this.mustStartNextDay=is;
		this.markDirty();
	}
	
	public boolean getMustStartNextDay() {
		return this.mustStartNextDay;
	}
	
	public void setMustNotStartNextDay(boolean is) {
		this.mustNotStartNextDay=is;
		this.markDirty();
	}
	
	public boolean getMustNotStartNextDay() {
		return this.mustNotStartNextDay;
	}
	
	@Override
	public void read(CompoundNBT nbt) {
		events.clear();
		System.out.println("1");
		ListNBT list = (ListNBT) nbt.get("event");
		if(list!=null) {
			for(INBT tmp:list) {
				CompoundNBT tag = (CompoundNBT) tmp;
				events.add(Events.values()[tag.getInt("id")]);
			}
		}
		this.changed=nbt.getBoolean("changed");
		this.isZomBossDefeated=nbt.getBoolean("is_zomboss_defeated");
		this.mustStartNextDay=nbt.getBoolean("must_start_next_day");
		this.mustNotStartNextDay=nbt.getBoolean("must_not_start_next_day");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		ListNBT list = new ListNBT();
//		System.out.println("1");
		events.stream().forEach((event)->{
			CompoundNBT tag = new CompoundNBT();
			tag.putInt("id", event.ordinal());;
			list.add(tag);
		});
		nbt.put("event", list);
		nbt.putBoolean("changed", this.changed);
		nbt.putBoolean("is_zomboss_defeated",this.isZomBossDefeated);
		nbt.putBoolean("must_start_next_day",this.mustStartNextDay);
		nbt.putBoolean("must_not_start_next_day",this.mustNotStartNextDay);
		return nbt;
	}
	
	public static WorldEventData getOverWorldEventData(World worldIn) {
		if (!(worldIn instanceof ServerWorld)) {
//			System.out.println("pp");
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
		ServerWorld world = worldIn.getServer().getWorld(DimensionType.OVERWORLD);
		DimensionSavedDataManager storage = world.getSavedData();
//		if(storage.get(WorldEventData::new,DATA_NAME)==null) {
//			System.out.println("null");
//		}
		return storage.getOrCreate(WorldEventData::new,DATA_NAME);
	}

}
