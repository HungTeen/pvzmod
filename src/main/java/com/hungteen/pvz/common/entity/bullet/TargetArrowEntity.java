package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity.BungeeTypes;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class TargetArrowEntity extends AbstractArrowEntity {

	public TargetArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public TargetArrowEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.TARGET_ARROW.get(), living, worldIn);
	}
	
	@Override
	public void tick() {
		if(! level.isClientSide) {
			if(! EntityUtil.isEntityValid(this.getOwner())) { // shooter died
				this.remove();
				return ;
			} else {
				if(this.getOwner() instanceof BungeeZombieEntity) {
					BungeeZombieEntity bungee = (BungeeZombieEntity) this.getOwner();
					if(EntityUtil.isEntityValid(bungee.getStealTarget())) {
						this.shoot(bungee.getStealTarget());
					} else {
						this.remove();
						return ;
					}
				}
			}
		}
		super.tick();
	}
	
	@Override
	protected void onHitEntity(EntityRayTraceResult result) {
		if(result.getEntity() instanceof LivingEntity && this.getOwner() instanceof PlayerEntity) {// summon bungee
			if(! BungeeZombieEntity.canBungeeSteal(result.getEntity())) {
				super.onHitEntity(result);
				return ;
			}
			BungeeZombieEntity zombie = EntityRegister.BUNGEE_ZOMBIE.get().create(level);
			zombie.setBungeeType(BungeeTypes.HELP);
			zombie.setCharmed(true);
			zombie.setStealTarget((LivingEntity) result.getEntity());
			EntityUtil.onEntitySpawn(level, zombie, blockPosition().above(20));
			super.onHitEntity(result);
			this.remove();
		}
	}
	
	public void shoot(LivingEntity target) {
		Vector3d speed = target.position().subtract(this.position()).normalize();
		double multi = 1.3D;
		this.setDeltaMovement(speed.multiply(multi, multi, multi));
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5F, 0.5F);
	}

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(ItemRegister.TARGET_ARROW.get());
	}
	
	@Override
	public byte getPierceLevel() {
		return 0;
	}
	
	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
