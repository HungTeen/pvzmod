package com.hungteen.pvz.common.world.structure;

import com.hungteen.pvz.PVZMod;
import com.mojang.serialization.Codec;

import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public abstract class PVZStructureBase<T extends IFeatureConfig> extends Structure<T>{

	public PVZStructureBase(Codec<T> p_i231997_1_) {
		super(p_i231997_1_);
	}
	
	@Override
	public String getFeatureName() {
		return PVZMod.MOD_ID + ":" + getPVZStructureName();
	}
	
	@Override
	public Decoration step() {
		return Decoration.SURFACE_STRUCTURES;
	}
	
	public abstract String getPVZStructureName();

}
