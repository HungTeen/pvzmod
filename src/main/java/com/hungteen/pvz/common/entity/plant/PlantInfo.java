package com.hungteen.pvz.common.entity.plant;

import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.paz.IPlantInfo;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PlantType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;

import java.util.Optional;

public class PlantInfo implements IPlantInfo {

	protected IPlantType type;
	protected int sunCost;
	
	public PlantInfo() {
	}
	
	public PlantInfo(IPlantType type) {
		this.type = type;
	}

	@Override
	public void placeOn(IPlantEntity plantEntity, int sunCost) {
		this.setSunCost(sunCost);
		if(plantEntity instanceof Entity) {
			EntityUtil.playSound((Entity) plantEntity, SoundRegister.PLACE_PLANT_GROUND.get());
		}
	}

	@Override
	public void onSuper(IPlantEntity plantEntity) {}

	/**
	 * read nbt from plant entity.
	 */
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

	/**
	 * write nbt to plant entity.
	 */
	public static void write(IPlantInfo info, CompoundNBT compound, String flag) {
	    if(info != null) {
	    	CompoundNBT nbt = new CompoundNBT();
		    info.write(nbt);
		    compound.put(flag, nbt);
	    }
	}

	@Override
	public void read(CompoundNBT nbt) {
		// no need to read plant type again.
		if(nbt.contains("sun_cost")) {
			this.setSunCost(nbt.getInt("sun_cost"));
		}
	}

	@Override
	public void write(CompoundNBT nbt) {
		nbt.putString("plant_type", this.type.getIdentity());
		nbt.putInt("sun_cost", this.getSunCost());
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
	public void onHeal(IPlantEntity plantEntity, float percent) {
	}
	
}
