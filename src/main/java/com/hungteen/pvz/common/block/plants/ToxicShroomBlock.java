package com.hungteen.pvz.common.block.plants;

import java.util.Random;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ToxicShroomBlock extends BushBlock implements IGrowable {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape[] STAGES = { Block.box(3.0D, 0.0D, 3.0D, 13.0D, 5.0D, 13.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 10.0D, 15.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 11.0D, 15.0D) };
	private static final int VALID_LIGHT_LEVEL = 7;
	private static final int POISON_TICK = 100;
	private static final int POISON_LVL = 1;

	public ToxicShroomBlock(Block.Properties p_i49971_1_) {
		super(p_i49971_1_);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	}

	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(ItemRegister.SPORE.get());
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.MYCELIUM;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		final BlockPos blockpos = pos.below();
		final BlockState blockstate = worldIn.getBlockState(blockpos);
		final Block block = blockstate.getBlock();
		return block == Blocks.MYCELIUM && worldIn.getBrightness(LightType.SKY, pos) < VALID_LIGHT_LEVEL
				&& blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int age = state.getValue(AGE);
		if (age >= 0 && age <= 3) {
			return STAGES[age];
		}
		return STAGES[0];
	}

	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		int i = state.getValue(AGE);
		if (i < 3 && worldIn.getRawBrightness(pos, 0) <= VALID_LIGHT_LEVEL
				&& net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(10) == 0)) {
			worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
		if (worldIn.isClientSide && i == 3) { // spawn particles
			for (int j = 0; j < 3; j++) {
				worldIn.addParticle(ParticleTypes.PORTAL, pos.getX(), pos.getY(), pos.getZ(),
						(rand.nextFloat() - 0.5) / 5, (rand.nextFloat() - 0.5) / 10, (rand.nextFloat() - 0.5) / 5);
			}
		}
	}

	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof MonsterEntity)) {
			entityIn.makeStuckInBlock(state, new Vector3d((double) 0.8F, 0.75D, (double) 0.8F));
			if (!worldIn.isClientSide && state.getValue(AGE) == 3
					&& (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
					LivingEntity living = (LivingEntity) entityIn;
					living.addEffect(new EffectInstance(Effects.POISON, POISON_TICK, POISON_LVL, false, false));
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 3;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (i > 1) {
			int j = 1 + worldIn.random.nextInt(1);
			popResource(worldIn, pos, new ItemStack(ItemRegister.SPORE.get(), j));
			worldIn.playSound((PlayerEntity) null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
					SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(1)), 2);
			return ActionResultType.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	/**
	 * Whether this IGrowable can grow
	 */
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 3;
	}

	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = Math.min(3, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
	}
}