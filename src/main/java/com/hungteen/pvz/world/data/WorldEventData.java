package com.hungteen.pvz.world.data;

import java.util.HashSet;

import com.hungteen.pvz.utils.enums.Events;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class WorldEventData extends WorldSavedData{

	private static final String DATA_NAME = "WorldEventData";
	private HashSet<Events> events = new HashSet<>(Events.values().length);
	private HashSet<Zombies> zombies = new HashSet<>(Zombies.values().length);
	private boolean changed = false;
	private boolean isZomBossDefeated = false;//is zomboss defeated
	private boolean mustStartNextDay = false;//nextDay must start a event
	private boolean mustNotStartNextDay = false;//nextDay must not start a event
	
	public WorldEventData() {
		super(DATA_NAME);
	}
	
	public WorldEventData(String name) {
		super(name);
	}
	
	public boolean hasZombieSpawnEntry(Zombies zombie) {
		return zombies.contains(zombie);
	}
	
	public void addZombieSpawnEntry(Zombies zombie) {
		zombies.add(zombie);
		this.setDirty();
	}
	
	public void removeZombieSpawnEntry(Zombies zombie) {
		zombies.remove(zombie);
		this.setDirty();
	}
	
	public boolean hasEvent(Events ev) {
		return events.contains(ev);
	}
	
	public void addEvent(Events ev) {
		events.add(ev);
		this.setDirty();
	}
	
	public void removeEvent(Events ev) {
		events.remove(ev);
		this.setDirty();
	}
	
	public void setChanged(boolean is) {
		this.changed=is;
		this.setDirty();
	}
	
	public void setIsZomBossDefeated(boolean is) {
		this.isZomBossDefeated=is;
		this.setDirty();
	}
	
	public void setMustStartNextDay(boolean is) {
		this.mustStartNextDay=is;
		this.setDirty();
	}
	
	public void setMustNotStartNextDay(boolean is) {
		this.mustNotStartNextDay=is;
		this.setDirty();
	}
	
	public boolean hasChanged() {
		return this.changed;
	}
	
	public boolean getIsZomBossDefeated() {
		return this.isZomBossDefeated;
	}
	
	public boolean getMustStartNextDay() {
		return this.mustStartNextDay;
	}
	
	public boolean getMustNotStartNextDay() {
		return this.mustNotStartNextDay;
	}
	
	@Override
	public void load(CompoundNBT nbt) {
		//restore event.
		events.clear();
		ListNBT list = (ListNBT) nbt.get("event");
		if(list != null) {
			for(INBT tmp:list) {
				CompoundNBT tag = (CompoundNBT) tmp;
				events.add(Events.values()[tag.getInt("id")]);
			}
		}
		//restore zombie spawn entry.
		zombies.clear();
		if(nbt.contains("zombie_spawn_entries")) {
			CompoundNBT list2 = nbt.getCompound("zombie_spawn_entries");
			for(Zombies zombie : Zombies.values()) {
				if(list2.contains("type_" + zombie.toString()) && list2.getBoolean("type_" + zombie.toString())) {
					this.addZombieSpawnEntry(zombie);
				}
			}
		}
		this.changed=nbt.getBoolean("changed");
		this.isZomBossDefeated=nbt.getBoolean("is_zomboss_defeated");
		this.mustStartNextDay=nbt.getBoolean("must_start_next_day");
		this.mustNotStartNextDay=nbt.getBoolean("must_not_start_next_day");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		ListNBT list = new ListNBT();
		events.stream().forEach((event)->{
			CompoundNBT tag = new CompoundNBT();
			tag.putInt("id", event.ordinal());
			list.add(tag);
		});
		nbt.put("event", list);
		CompoundNBT list2 = new CompoundNBT();
		for(Zombies zombie : Zombies.values()) {
			list2.putBoolean("type_" + zombie.toString(), this.hasZombieSpawnEntry(zombie));
		}
		nbt.put("zombie_spawn_entries", list2);
		nbt.putBoolean("changed", this.changed);
		nbt.putBoolean("is_zomboss_defeated",this.isZomBossDefeated);
		nbt.putBoolean("must_start_next_day",this.mustStartNextDay);
		nbt.putBoolean("must_not_start_next_day",this.mustNotStartNextDay);
		return nbt;
	}
	
	public static WorldEventData getOverWorldEventData(World worldIn) {
		if (! (worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
		ServerWorld world = worldIn.getServer().getLevel(World.OVERWORLD);
		DimensionSavedDataManager storage = world.getDataStorage();
		return storage.computeIfAbsent(WorldEventData::new, DATA_NAME);
	}

}
