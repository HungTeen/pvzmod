package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.IPlantEntity;
import com.hungteen.pvz.api.IPlantInfo;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.PlantType;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;

import java.util.Optional;

public class PlantInfo implements IPlantInfo {

	protected IPlantType type;
	protected boolean needSyncLevel = true;
	protected int sunCost;
	protected int level = 1;
	
	public PlantInfo() {
	}
	
	public PlantInfo(IPlantType type) {
		this.type = type;
	}

	@Override
	public void placeOn(IPlantEntity plantEntity, int lvl, int sunCost) {
		this.setLevel(lvl);
		this.setSunCost(sunCost);
		if(plantEntity instanceof Entity) {
			EntityUtil.playSound((Entity) plantEntity, SoundRegister.PLANT_ON_GROUND.get());
		}
	}

	@Override
	public void onSuper() {}
	
	public static void read(IPlantInfo info, CompoundNBT compound, String flag) {
		if (compound.contains(flag)) {
			CompoundNBT nbt = compound.getCompound(flag);
			if(nbt.contains("plant_type")) {
				final String string = compound.getString("plant_type");
				Optional<IPlantType> op = PlantType.getPlantByName(string);
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
	
	public static void write(IPlantInfo info, CompoundNBT compound, String flag) {
	    if(info != null) {
	    	CompoundNBT nbt = new CompoundNBT();
		    info.write(nbt);
		    compound.put(flag, nbt);
	    }
	}

	@Override
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

	@Override
	public void write(CompoundNBT nbt) {
		nbt.putString("outer_plant_type", this.type.getIdentity());
		nbt.putInt("sun_cost", this.getSunCost());
		nbt.putInt("plant_level", this.getLevel());
		nbt.putBoolean("need_sync", this.needSyncLevel);
	}

	@Override
	public void setType(IPlantType type) {
		this.type = type;
	}

	@Override
	public IPlantType getType() {
		return this.type;
	}

	@Override
	public void setSunCost(int cost) {
		this.sunCost = cost;
	}

	@Override
	public int getSunCost() {
		return this.sunCost;
	}

	@Override
	public boolean needSyncLevel() {
		return this.needSyncLevel;
	}

	@Override
	public void setSyncLevel(boolean is) {
		this.needSyncLevel = is;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getLevel() {
		return this.level;
	}
	
}
