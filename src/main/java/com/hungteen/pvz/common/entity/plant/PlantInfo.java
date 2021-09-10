package com.hungteen.pvz.common.entity.plant;

import java.util.Optional;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.nbt.CompoundNBT;

public class PlantInfo {

	protected PlantType type;
	protected boolean needSyncLevel = true;
	protected int sunCost;
	protected int level = 1;
	
	public PlantInfo() {
	}
	
	public PlantInfo(PlantType type) {
		this.type = type;
	}
	
	public void placeOn(PVZPlantEntity plantEntity, int lvl, int sunCost) {
		this.setLevel(lvl);
		this.setSunCost(sunCost);
		EntityUtil.playSound(plantEntity, SoundRegister.PLANT_ON_GROUND.get());
	}
	
	/**
	 * trigger when inner plants be super.
	 */
	public void onSuper() {
		
	}
	
	public static void read(PlantInfo info, CompoundNBT compound, String flag) {
		if (compound.contains(flag)) {
			CompoundNBT nbt = compound.getCompound(flag);
			if(nbt.contains("plant_type")) {
				final String string = compound.getString("plant_type");
				Optional<PlantType> op = PlantType.getPlantByName(string);
				if(op.isPresent()) {// choose plant info type.
					if(op.get().isOuterPlant()) {
						info = op.get().getOuterPlant().get();
					} else {
						info = new PlantInfo(op.get());
					}
					info.read(nbt);
				}
			}
		}
	}
	
	public static void write(PlantInfo info, CompoundNBT compound, String flag) {
	    if(info != null) {
	    	CompoundNBT nbt = new CompoundNBT();
		    info.write(nbt);
		    compound.put(flag, nbt);
	    }
	}
	
	public void read(CompoundNBT nbt) {
		if(nbt.contains("sun_cost")) {
			this.setSunCost(nbt.getInt("sun_cost"));
		}
		if(nbt.contains("plant_level")) {
			this.setLevel(nbt.getInt("plant_level"));
		}
		if(nbt.contains("need_sync")) {
			this.setSyncLevel(nbt.getBoolean("need_sync"));
		}
	}
	
	public void write(CompoundNBT nbt) {
		nbt.putString("outer_plant_type", this.type.getIdentity());
		nbt.putInt("sun_cost", this.getSunCost());
		nbt.putInt("plant_level", this.getLevel());
		nbt.putBoolean("need_sync", this.needSyncLevel);
	}
	
	public void setType(PlantType type) {
		this.type = type;
	}
	
	public PlantType getType() {
		return this.type;
	}
	
	public void setSunCost(int cost) {
		this.sunCost = cost;
	}
	
	public int getSunCost() {
		return this.sunCost;
	}
	
	public boolean needSyncLevel() {
		return this.needSyncLevel;
	}
	
	public void setSyncLevel(boolean is) {
		this.needSyncLevel = is;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
	
}
