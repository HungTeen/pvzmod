package com.hungteen.pvz.world.data;

import java.util.HashSet;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.utils.enums.InvasionEvents;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class PVZInvasionData extends WorldSavedData {

	private static final String DATA_NAME = "WorldEventData";
	private HashSet<InvasionEvents> events = new HashSet<>(InvasionEvents.values().length);
	private HashSet<Zombies> zombies = new HashSet<>(Zombies.values().length);
	private HashSet<Plants> plants = new HashSet<>(Plants.values().length);
	private boolean changed = false;
	private int countDownDay = 0;
	private int currentDifficulty = 0;

	public PVZInvasionData() {
		super(DATA_NAME);
	}

	public PVZInvasionData(String name) {
		super(name);
	}

	// getter setter for zombie spawns.
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

	// getter setter for plant spawns.
	public boolean hasPlantSpawnEntry(Plants plant) {
		return plants.contains(plant);
	}

	public void addPlantSpawnEntry(Plants plant) {
		plants.add(plant);
		this.setDirty();
	}

	public void removePlantSpawnEntry(Plants plant) {
		plants.remove(plant);
		this.setDirty();
	}

	// getter setter for invasion events.
	public boolean hasEvent(InvasionEvents ev) {
		return events.contains(ev);
	}

	public void addEvent(InvasionEvents ev) {
		events.add(ev);
		this.setDirty();
	}

	public void removeEvent(InvasionEvents ev) {
		events.remove(ev);
		this.setDirty();
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
			this.countDownDay = PVZConfig.COMMON_CONFIG.WorldSettings.WorldInvasionSettings.InvasionIntervalLength.get();
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

	// getter setter for count down day.
	public void setCurrentDifficulty(int dif) {
		this.currentDifficulty = dif;
		this.setDirty();
	}

	public int getCurrentDifficulty() {
		return this.currentDifficulty;
	}

	@Override
	public void load(CompoundNBT nbt) {
		// restore event.
		events.clear();
		ListNBT list = (ListNBT) nbt.get("event");
		if (list != null) {
			for (INBT tmp : list) {
				CompoundNBT tag = (CompoundNBT) tmp;
				events.add(InvasionEvents.values()[tag.getInt("id")]);
			}
		}
		// restore zombie spawn entry.
		zombies.clear();
		if (nbt.contains("zombie_spawn_entries")) {
			CompoundNBT list2 = nbt.getCompound("zombie_spawn_entries");
			for (Zombies zombie : Zombies.values()) {
				if (list2.contains("type_" + zombie.toString()) && list2.getBoolean("type_" + zombie.toString())) {
					this.addZombieSpawnEntry(zombie);
				}
			}
		}
		// restore zombie spawn entry.
		plants.clear();
		if (nbt.contains("plant_spawn_entries")) {
			CompoundNBT list2 = nbt.getCompound("plant_spawn_entries");
			for (Plants plant : Plants.values()) {
				if (list2.contains("type_" + plant.toString()) && list2.getBoolean("type_" + plant.toString())) {
					this.addPlantSpawnEntry(plant);
				}
			}
		}
		//others
		this.changed = nbt.getBoolean("changed");
		this.countDownDay = nbt.getInt("count_down_day");
		this.currentDifficulty = nbt.getInt("pvz_current_difficulty");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		ListNBT list = new ListNBT();
		//events
		events.stream().forEach((event) -> {
			CompoundNBT tag = new CompoundNBT();
			tag.putInt("id", event.ordinal());
			list.add(tag);
		});
		nbt.put("event", list);
		//zombies.
		CompoundNBT list2 = new CompoundNBT();
		for (Zombies zombie : Zombies.values()) {
			list2.putBoolean("type_" + zombie.toString(), this.hasZombieSpawnEntry(zombie));
		}
		nbt.put("zombie_spawn_entries", list2);
		//plants.
		CompoundNBT list3 = new CompoundNBT();
		for (Plants plant : Plants.values()) {
			list3.putBoolean("type_" + plant.toString(), this.hasPlantSpawnEntry(plant));
		}
		nbt.put("plant_spawn_entries", list3);
		//others
		nbt.putBoolean("changed", this.changed);
		nbt.putInt("count_down_day", this.countDownDay);
		nbt.putInt("pvz_current_difficulty", this.currentDifficulty);
		return nbt;
	}

	/*
	 * there is no world has invasion except overworld.
	 */
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
		ServerWorld world = worldIn.getServer().getLevel(World.OVERWORLD);
		DimensionSavedDataManager storage = world.getDataStorage();
		return storage.computeIfAbsent(PVZInvasionData::new, DATA_NAME);
	}

}
