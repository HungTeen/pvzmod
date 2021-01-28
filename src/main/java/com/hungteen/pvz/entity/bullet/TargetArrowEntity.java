package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity.BungeeTypes;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
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
import net.minecraft.util.math.Vec3d;
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
		if(! world.isRemote) {
			if(! EntityUtil.isEntityValid(this.getShooter())) { // shooter died
				this.remove();
				return ;
			} else {
				if(this.getShooter() instanceof BungeeZombieEntity) {
					BungeeZombieEntity bungee = (BungeeZombieEntity) this.getShooter();
					if(bungee.getStealTarget() != null) {
						this.shoot(bungee.getStealTarget());
					}
				}
			}
		}
		super.tick();
	}
	
	@Override
	protected void onEntityHit(EntityRayTraceResult result) {
		if(result.getEntity() instanceof LivingEntity && this.getShooter() instanceof PlayerEntity) {// summon bungee
			if(! BungeeZombieEntity.canBungeeSteal(result.getEntity())) {
				super.onEntityHit(result);
				return ;
			}
			BungeeZombieEntity zombie = EntityRegister.BUNGEE_ZOMBIE.get().create(world);
			zombie.setBungeeType(BungeeTypes.HELP);
			zombie.setCharmed(true);
			zombie.setStealTarget((LivingEntity) result.getEntity());
			EntityUtil.onMobEntitySpawn(world, zombie, getPosition().up(20));
			this.remove();
		}
	}
	
	public void shoot(LivingEntity target) {
		Vec3d speed = target.getPositionVec().subtract(this.getPositionVec()).normalize();
		double multi = 1D;
		this.setMotion(speed.mul(multi, multi, multi));
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.5F, 0.5F);
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ItemRegister.TARGET_ARROW.get());
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
