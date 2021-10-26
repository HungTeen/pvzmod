package com.hungteen.pvz.api.types;

import com.hungteen.pvz.api.IPlantEntity;
import com.hungteen.pvz.api.IPlantInfo;
import com.hungteen.pvz.api.IPlantModel;
import net.minecraft.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

/**
 * define a plant type.
 */
public interface IPlantType extends IPAZType {

	/**
	 * get (plant type, plant entity interface) pair.
	 */
//	Optional<Pair<MobEntity, IPlantEntity>> getPlantEntityType();

	/**
	 * get the essence type that the plant belongs to.
	 */
	IEssenceType getEssence();
	
	/**
	 * get the entity model of plant (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	Optional<IPlantModel<? extends IPlantEntity>> getPlantModel();
	
	/**
	 * what type the plant upgrade from.
	 */
	Optional<IPlantType> getUpgradeFrom();
	
	/**
	 * what type the plant upgrade to.
	 */
	Optional<IPlantType> getUpgradeTo();
	
	/**
	 * can plant card place on.
	 */
	ICardPlacement getPlacement();
	
	/**
	 * the shroom type : it need sleep at night. <br>
	 * such as Puff Shroom.
	 */
	boolean isShroomPlant();
	
	/**
	 * the block type : it's not an entity, but a block. <br>
	 * such as Lily Pad.
	 */
	boolean isBlockPlant();
	
	/**
	 * get corresponding block.
	 */
	Optional<Block> getPlantBlock();
	
	/**
	 * the water type : it only lives in water. <br>
	 * such as Tangle Kelp.
	 */
	boolean isWaterPlant();
	
	/**
	 * the outer type : it's not an entity, but a render layer. <br>
	 * such as Pumpkin.
	 */
	boolean isOuterPlant();
	
	/**
	 * get corresponding outer plant info.
	 */
	Optional<IPlantInfo> getOuterPlant();
	
}
