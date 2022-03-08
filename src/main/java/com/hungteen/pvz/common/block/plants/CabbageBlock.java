package com.hungteen.pvz.common.block.plants;

import java.util.Random;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.CropsBlock;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Mth;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;

public class CabbageBlock extends CropsBlock {

	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape[] SHAPE = new VoxelShape[] {
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D) };

	public CabbageBlock(Properties properties) {
		super(properties);
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return 3;
	}

	protected IItemProvider getBaseSeedId() {
		return ItemRegister.CABBAGE_SEEDS.get();
	}

	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerLevel worldIn, Mth pos, Random rand) {
		if (rand.nextInt(4) != 0) {
			super.tick(state, worldIn, pos, rand);
		}
	}

	protected int getBonemealAgeIncrease(Level worldIn) {
		return super.getBonemealAgeIncrease(worldIn) / 3;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, Mth pos, ISelectionContext context) {
		return SHAPE[state.getValue(this.getAgeProperty())];
	}

}
