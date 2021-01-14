package com.hungteen.pvz.entity.plant.light;

import com.hungteen.pvz.block.special.GoldTileBlock;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.enums.Plants;

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
	}

	@Override
	public void startBomb() {
		Block block = world.getBlockState(this.getPosition().down()).getBlock();
		int lvl = getBlockGoldLevel(block);
		if(lvl == - 1 || lvl >= this.getTileLevel()) return ;
		if(! world.isRemote) {
			world.setBlockState(getPosition().down(), this.getGoldTileByLvl(this.getTileLevel()).getDefaultState());
		} else {
			
		}
	}
	
	public static int getBlockGoldLevel(Block block) {
		if(block instanceof GoldTileBlock) return ((GoldTileBlock) block).lvl;
		if(block == Blocks.GOLD_BLOCK) return 0;
		return - 1;
	}
	
	public static int getGoldGenAmount(int lvl) {
		if(lvl == 1) return 25;
		if(lvl == 2) return 35;
		return 50;
	}
	
	private Block getGoldTileByLvl(int lvl) {
		if(lvl == 1) return BlockRegister.GOLD_TILE1.get();
		if(lvl == 2) return BlockRegister.GOLD_TILE2.get();
		if(lvl == 3) return BlockRegister.GOLD_TILE3.get();
		System.out.println("Warn : Wrong use for gold tile !");
		return Blocks.GOLD_BLOCK;
	}
	
	public int getTileLevel() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6F, 1F);
	}
	
	@Override
	public int getReadyTime() {
		return 60;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.GOLD_LEAF;
	}

}
