package com.hungteen.pvz.common.world.invasion;

import com.hungteen.pvz.PVZConfig;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashSet;
import java.util.Set;

public class PVZInvasionData extends WorldSavedData {

	private static final String DATA_NAME = "InvasionEventData";
	private final Set<ResourceLocation> invasionResources = new HashSet<>();
	private ResourceLocation spawnInvasion;
	private boolean isRunning = false;
	private boolean changed = false;
	private int countDownDay = PVZConfig.COMMON_CONFIG.InvasionSettings.InvasionIntervalLength.get();
	private int currentDifficulty = 0;
	private int tick = 0;

	public PVZInvasionData() {
		super(DATA_NAME);
	}

	public void setSpawnInvasion(ResourceLocation resourceLocation){
		if(this.spawnInvasion != null){
			this.invasionResources.remove(this.spawnInvasion);
			InvasionManager.activeResources.remove(this.spawnInvasion);
		}
		this.spawnInvasion = resourceLocation;
		this.invasionResources.add(this.spawnInvasion);

		InvasionManager.spawnResource = this.spawnInvasion;
		InvasionManager.activeResources.add(this.spawnInvasion);
		this.setRunning(true);

		this.setDirty();
	}

	public void addAssistInvasion(ResourceLocation resourceLocation){
		if(! this.invasionResources.contains(resourceLocation)){
			this.invasionResources.add(resourceLocation);
			InvasionManager.activeResources.add(resourceLocation);

			this.setDirty();
		}
	}

	public void removeAssistInvasion(ResourceLocation resourceLocation){
		if(this.invasionResources.contains(resourceLocation)){
			this.invasionResources.remove(resourceLocation);
			InvasionManager.activeResources.remove(resourceLocation);

			this.setDirty();
		}
	}

	public void clearInvasion(){
		this.spawnInvasion = null;
		this.invasionResources.clear();

		InvasionManager.spawnResource = null;
		InvasionManager.activeResources.clear();

		this.setDirty();
	}

	public ResourceLocation getSpawnInvasion() {
		return spawnInvasion;
	}

	public Set<ResourceLocation> getInvasionResources() {
		return invasionResources;
	}

	public void setRunning(boolean flag){
		if(this.isRunning != flag){
			this.isRunning = flag;
			InvasionManager.isRunning = flag;
			this.setDirty();
		}
	}

	public boolean isRunning() {
		return isRunning;
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

	// getter setter for count down day.
	public void setCurrentDifficulty(int dif) {
		this.currentDifficulty = Math.max(0, dif);
		InvasionManager.invasionDifficulty = this.currentDifficulty;
		this.setDirty();
	}
	
	public void addCurrentDifficulty(int dif) {
		this.currentDifficulty = Math.max(0, this.currentDifficulty + dif);
		InvasionManager.invasionDifficulty = this.currentDifficulty;
		this.setDirty();
	}

	public int getCurrentDifficulty() {
		return this.currentDifficulty;
	}

	@Override
	public void load(CompoundNBT nbt) {
		/* restore event */
		if(nbt.contains("invasion_resources")){
			this.invasionResources.clear();
			final ListNBT list = (ListNBT) nbt.get("invasion_resources");
			for(int i = 0; i < list.size(); ++ i){
				final CompoundNBT tmp = (CompoundNBT) list.get(i);
				this.invasionResources.add(new ResourceLocation(tmp.getString("type")));
			}
		}
		if(nbt.contains("spawn_resource")){
			this.spawnInvasion = new ResourceLocation(nbt.getString("spawn_resource"));
		}
		/* others */
		this.changed = nbt.getBoolean("changed");
		this.isRunning = nbt.getBoolean("isRunning");
		this.countDownDay = nbt.getInt("count_down_day");
		this.currentDifficulty = nbt.getInt("pvz_current_difficulty");
	}

	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		{
			ListNBT list = new ListNBT();
			this.invasionResources.forEach(res -> {
				final CompoundNBT tmp = new CompoundNBT();
				tmp.putString("type", res.toString());
				list.add(tmp);
			});
			nbt.put("invasion_resources", list);
		}
		if(this.spawnInvasion != null){
			nbt.putString("spawn_resource", this.spawnInvasion.toString());
		}
		/* others */
		nbt.putBoolean("changed", this.changed);
		nbt.putBoolean("isRunning", this.isRunning);
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
		final ServerWorld world = worldIn.getServer().getLevel(World.OVERWORLD);
		DimensionSavedDataManager storage = world.getDataStorage();
		return storage.computeIfAbsent(PVZInvasionData::new, DATA_NAME);
	}

}
