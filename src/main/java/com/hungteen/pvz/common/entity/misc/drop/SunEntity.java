package com.hungteen.pvz.common.entity.misc.drop;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 09:16
 **/
public class SunEntity extends DropEntity {

	private static final float SUN_FALL_SPEED = 0.03F;
	public LivingEntity controller = null;
	public Vector3d ColorBase = new Vector3d(255,230,15);
	public Vector3d ColorChange = new Vector3d(0,25,15);
	private Entity following;

	public SunEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		setAmount(this.getDefaultAmount());
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();

		if(! this.onGround && ! this.isInWater()) {
			double speedY = this.getDeltaMovement().y;
			if(speedY > - SUN_FALL_SPEED){
				speedY -= SUN_FALL_SPEED / 2;
			} else{
				speedY = -SUN_FALL_SPEED;
			}
			this.setDeltaMovement(this.getDeltaMovement().x * 0.94, speedY, this.getDeltaMovement().z * 0.94);
		} else{
			this.setDeltaMovement(Vector3d.ZERO);
		}

		if ((this.tickCount+this.getId()) % ((this.following instanceof PlayerEntity) ? 200 : 50) == 0 || (this.following != null && this.following.distanceToSqr(this) > 64.0D)) {
			this.following = this.level.getNearestPlayer(this, 6.0D);
		}
		if (this.following == null && getAmount() < 150 && (this.tickCount+this.getId()) % 50 == 0){
			List<Entity> list = this.level.getEntities(this, this.getBoundingBox().inflate(2D, 2D, 2D));
			for (Entity entity : list) {
				if (entity instanceof SunEntity && entity.getId() > this.getId()) {
					this.following = entity;
					break;
				}
			}
		}

		if (this.following != null) {
			if (following instanceof SunEntity){
				if (this.distanceTo(following) < 0.5F){
					((SunEntity) following).setAmount(((SunEntity) following).getAmount()+this.getAmount());
					this.remove();
				}
			}
			Vector3d vec3 = new Vector3d(this.following.getX() - this.getX(), this.following.getY() + (double)this.following.getEyeHeight() / 2.0D - this.getY(), this.following.getZ() - this.getZ());
			double d0 = vec3.lengthSqr();
			if (d0 < 25.0D) {
				double d1 = 1.0D - Math.sqrt(d0) / 5.0D;
				this.setDeltaMovement(this.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * (following instanceof SunEntity ? 0.1D: 0.3D))));
			}
		}

		if (this.controller != null && (this.controller.isSpectator() || (!this.controller.isAlive()))) {
			this.controller = null;
		}

		if (this.controller == null){
			ColorBase = new Vector3d(255,230,15);
			ColorChange = new Vector3d(0,25,15);
		}
	}

	public int getIcon() {
		final int value = this.getAmount();
		return value < 6 ? 0 : value < 16 ? 1 : value < 26 ? 2 : 3;
	}

	protected int getDefaultAmount() {
		return 50;
	}

	@Override
	public void onCollectedByPlayer(PlayerEntity living) {
		if(! level.isClientSide) {
			PlayerUtil.addResource(living, Resources.SUN_NUM, this.getAmount());
			PlayerUtil.playClientSound(living, SoundRegister.SUN_PICK.get());
		}
		this.remove();
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		int amount = this.getAmount();
		float w = amount * 1f / 200 + 0.2f;
		return EntitySize.scalable(w, w);
	}

	public static void spawnSunsByAmount(World world, BlockPos pos, int amount) {
		spawnSunsByAmount(world, pos, amount, 75, 1);
	}

	/**
	 * spawn sun in range, each is set to a specific amount.
	 */
	public static void spawnSunsByAmount(World world, BlockPos pos, int amount, int each, double speed) {
		while(amount >= each) {
			amount -= each;
			dropSunRandomly(world, pos, each, speed);
		}
		if(amount != 0) {
			dropSunRandomly(world, pos, amount, speed);
		}
	}

	/**
	 * spawn random speed sun entity with specific amount.
	 */
	public static void dropSunRandomly(World world, BlockPos pos, int amount, double speed) {
		final SunEntity sun = EntityRegister.SUN.get().create(world);
		sun.setAmount(amount);
		speed *= 0.15;
		EntityUtil.onEntitySpawn(world, sun, pos);
		final double dy = speed * 0.2 + 0.2;
		final double dx = MathUtil.getRandomFloat(world.getRandom()) + speed * 0.2;
		final double dz = MathUtil.getRandomFloat(world.getRandom()) + speed * 0.2;
		sun.setDeltaMovement(new Vector3d(dx, 0, dz).scale(speed).add(0, dy, 0));
	}

	public static boolean canSunSpawn(EntityType<? extends SunEntity> zombieType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random rand) {
		if(worldIn instanceof ServerWorld) {
			return ! ((ServerWorld) worldIn).isRainingAt(pos) && ((ServerWorld) worldIn).isDay() && worldIn.getBrightness(LightType.SKY, pos) >= 15;
		}
		return worldIn.getBrightness(LightType.SKY, pos) >= 15;
	}

	@Override
	public int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.SunLiveTick.get();
	}


}