package com.hungteen.pvzmod.entities.ai;

import com.hungteen.pvzmod.util.AIUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class EntityAIDestroyBlocks extends EntityAIBase {

	private EntityLivingBase target;
	private EntityLiving attacker;
	private BlockPos block;
	private int scanTick;
	private int digTick;

	public EntityAIDestroyBlocks(EntityLiving attacker) {
		this.attacker = attacker;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		this.target = this.attacker.getAttackTarget();
		if (this.target != null && this.target.isEntityAlive() && this.attacker.getNavigator().noPath()
				&& this.attacker.getDistance(this.target) > 1D) {//&&this.target.posY<=this.attacker.posY+2) {

			if (!(this.block != null && this.attacker.getDistanceSq(block) <= (4d * 4d)
					&& canHarvest(this.attacker, block)))
				this.block = getNextBlock(this.attacker, target, 2D);
			return this.block != null;
		}
		return false;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		this.attacker.getNavigator().clearPath();
	}

	@Override
	public void resetTask() {
		this.block = null;
		this.digTick = 0;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.block != null && this.attacker.getDistanceSq(block) <= (4D * 4D)
				&& canHarvest(this.attacker, block);
	}

	@Override
	public void updateTask() {
		if (!this.shouldContinueExecuting())
			return;

		this.attacker.getLookHelper().setLookPosition(this.target.posX,
				this.target.posY + (double) target.getEyeHeight(), target.posZ,
				(float) this.attacker.getHorizontalFaceSpeed(), (float) this.attacker.getVerticalFaceSpeed());
		this.attacker.getNavigator().clearPath();

		digTick++;
		float str = AIUtil.getBlockStrength(this.attacker, this.attacker.world, block) * (digTick + 1F);
		ItemStack heldItem = this.attacker.getHeldItem(EnumHand.MAIN_HAND);
		IBlockState state = this.attacker.world.getBlockState(block);

		if (this.attacker.world.isAirBlock(block)) {
			this.resetTask();
		} else if (str >= 1F) // Block has been broken.
		{
			boolean canHarvest = state.getMaterial().isToolNotRequired()
					|| (!heldItem.isEmpty() && heldItem.canHarvestBlock(state));
			this.attacker.world.destroyBlock(block, canHarvest);
			this.attacker.getNavigator().setPath(this.attacker.getNavigator().getPathToEntityLiving(target),
					this.attacker.getMoveHelper().getSpeed()); // This is fine. We only run it after a block breaks
			this.resetTask();
		} else if (digTick % 5 == 0) // Just keeping digging...
		{
			this.attacker.world.playSound(null, block,
					state.getBlock().getSoundType(state, this.attacker.world, block, this.attacker).getHitSound(),
					SoundCategory.BLOCKS, 1F, 1F);
			this.attacker.swingArm(EnumHand.MAIN_HAND);
			this.attacker.world.sendBlockBreakProgress(this.attacker.getEntityId(), block, (int) (str * 10F));
		}
	}

	private BlockPos getNextBlock(EntityLiving digger, EntityLivingBase target, double dis) {
		int digWidth = MathHelper.ceil(digger.width);
		int digHeight = MathHelper.ceil(digger.height);

		int passMax = digWidth * digHeight * digWidth;
		// (x,y,z) similar to 10 bit transfer [w h w]
		int x = scanTick / (digWidth * digHeight);
		int y = scanTick % (digWidth * digHeight) / digWidth;
		int z = scanTick % digWidth;

		double rayX = x + digger.posX - digWidth / 2;
		double rayY = y + digger.posY + 0.5d;
		double rayZ = z + digger.posZ - digWidth / 2;
		Vec3d rayS = new Vec3d(rayX, rayY, rayZ);
		Vec3d rayT = this.target.getPositionVector();
		rayT = rayS.add(rayT.subtract(rayS).normalize().scale(dis));// a vector point to target

		BlockPos p1 = digger.getPosition();
		BlockPos p2 = target.getPosition();

		if (p1.getDistance(p2.getX(), p1.getY(), p2.getZ()) < 4) {
			if (p2.getY() - p1.getY() > 2D) {
				rayT = rayS.addVector(0D, dis, 0D);
			} else if (p2.getY() - p1.getY() < 2D) {
				rayT = rayS.addVector(0D, -dis, 0D);
			}
		}
		RayTraceResult ray = digger.world.rayTraceBlocks(rayS, rayT, false, true, false);
		scanTick = (scanTick + 1) % passMax;

		if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos pos = ray.getBlockPos();
			IBlockState state = digger.world.getBlockState(pos);

			if (canHarvest(digger, pos)) {
				return pos;
			}
		}

		return null;
	}

	private boolean canHarvest(EntityLiving entity, BlockPos pos) {
		IBlockState state = entity.world.getBlockState(pos);

		if (!state.getMaterial().isSolid() || state.getBlockHardness(entity.world, pos) < 0F)// is water or can't
																								// destroy
			return false;
		else if (state.getMaterial().isToolNotRequired())// not need a tool
			return true;

		return true;// wait to add tool
	}
}
