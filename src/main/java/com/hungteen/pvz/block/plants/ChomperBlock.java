package com.hungteen.pvz.block.plants;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ChomperBlock extends BushBlock{

	private static final VoxelShape SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	
	public ChomperBlock() {
		super(Block.Properties.from(Blocks.PUMPKIN).doesNotBlockMovement().notSolid().harvestTool(ToolType.AXE).harvestLevel(2));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof AnimalEntity||entityIn instanceof PlayerEntity) {
			if(!worldIn.isRemote) {
				if(this.RANDOM.nextInt(50)==0) {
					entityIn.attackEntityFrom(PVZDamageSource.CHOMPER_PLANT, 12);
				}
			}
		}
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(player.getHeldItem(handIn).getItem()==Items.BONE_MEAL) {
			for(int i=-2;i<=2;i++) {
				if(worldIn.isRemote) break;
				for(int j=-1;j<=1;j++) {
					for(int k=-2;k<=2;k++) {
						BlockPos tmp = pos.add(i, j, k);
						if(isValidGround(worldIn.getBlockState(tmp), worldIn, tmp)) {
							if(worldIn.isAirBlock(tmp.up())){
								int chance=PVZConfig.COMMON_CONFIG.BlockSettings.ChomperGrowChance.get();
								if(this.RANDOM.nextInt(chance)==0) {
									worldIn.setBlockState(tmp.up(), BlockRegister.CHOMPER.get().getDefaultState());
								}
							}
						}
					}
				}
			}
			
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
