package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.special.GoldTileBlock;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.impl.plant.OtherPlants;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class GoldLeafEntity extends PlantBomberEntity {

	public static final int GOLD_GEN_CD = 400;
	
	public GoldLeafEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
	}

	@Override
	public void startBomb(boolean server) {
		Block block = level.getBlockState(this.blockPosition().below()).getBlock();
		final int lvl = getBlockGoldLevel(block);
		if(server && lvl >= 0 && lvl < this.getTileLevel()) {
			level.setBlockAndUpdate(blockPosition().below(), getGoldTileByLvl(this.getTileLevel()).defaultBlockState());
		}
	}
	
	public static int getBlockGoldLevel(Block block) {
		if(block instanceof GoldTileBlock) {
			return ((GoldTileBlock) block).lvl;
		}
		if(block == Blocks.GOLD_BLOCK) return 0;
		return - 1;
	}
	
	public static int getGoldGenAmount(int lvl) {
		return lvl == 1 ? 25 : lvl == 2 ? 50 : 75;
	}
	
	/**
	 * {@link #startBomb(boolean)}
	 */
	public static Block getGoldTileByLvl(int lvl) {
		return lvl == 1 ? BlockRegister.GOLD_TILE1.get() : lvl == 2 ? BlockRegister.GOLD_TILE2.get() : BlockRegister.GOLD_TILE3.get();
	}
	
	public int getTileLevel() {
		return this.getThreeStage(1, 2, 3);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6F, 1F);
	}
	
	@Override
	public int getReadyTime() {
		return 60;
	}

	@Override
	public IPlantType getPlantType() {
		return OtherPlants.GOLD_LEAF;
	}

}
