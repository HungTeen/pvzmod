package com.hungteen.pvz.common.entity.zombie.custom;

import com.hungteen.pvz.common.entity.ai.navigator.LavaZombiePathNavigator;
import com.hungteen.pvz.common.entity.zombie.base.SwimmerZombieEntity;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.fluid.Fluid;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class LavaZombieEntity extends SwimmerZombieEntity {

	public LavaZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.LAVA, 0.0F);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_FAST);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_LOW);
	}

	@Override
	public void zombieTick() {
		super.zombieTick();
		this.floatInLava();
	}

	private void floatInLava() {
		if (this.isInLava()) {
			ISelectionContext iselectioncontext = ISelectionContext.of(this);
			if (iselectioncontext.isAbove(FlowingFluidBlock.STABLE_SHAPE, this.blockPosition(), true)
					&& !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)) {
				this.onGround = true;
			} else {
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.06D, 0.0D));
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entityIn) {
		entityIn.setSecondsOnFire(5);
		return super.doHurtTarget(entityIn);
	}

	@Override
	public float getLife() {
		return 360;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.935f;
	}

	@Override
	public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
		if (p_205022_2_.getBlockState(p_205022_1_).getFluidState().is(FluidTags.LAVA)) {
			return 10.0F;
		} else {
			return this.isInLava() ? Float.NEGATIVE_INFINITY : 0.0F;
		}
	}

	@Override
	public boolean canStandOnFluid(Fluid p_230285_1_) {
		return p_230285_1_.is(FluidTags.LAVA);
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new LavaZombiePathNavigator(this, world);
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.LAVA_ZOMBIE;
	}

	@Override
	public ZombieType getZombieType() {
		return CustomZombies.LAVA_ZOMBIE;
	}

}
