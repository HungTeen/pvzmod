package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class CornEntity extends PultBulletEntity {

	public int cornCnt;
	
	public CornEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
		this.height = 20;
	}
	
	public CornEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.CORN.get(), worldIn, living);
		this.height = 20;
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		super.tick();
	}
	
	@Override
	protected void dealDamage(Entity target) {
		this.dealExplosionDamage();
	}
	
	@Override
	protected void onHitBlock() {
		this.dealExplosionDamage();
	}
	
	private void dealExplosionDamage() {
		final float range = 4F;
		int killCnt = 0;
		for(Entity entity : EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), EntityUtil.getEntityAABB(this, range, range))) {
			entity.hurt(PVZDamageSource.corn(this, this.getThrower()), this.attackDamage);
			if(! EntityUtil.isEntityValid(entity) && entity instanceof GargantuarEntity) {
				++ killCnt;
			}
		};
		if(this.getThrower() instanceof PVZPlantEntity) {
			PlayerEntity player = EntityUtil.getEntityOwner(level, getThrower());
			if(player != null && player instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, getThrower(), killCnt);
			}
		}
		ItemEntity item = new ItemEntity(level, getX(), getY() + 1, getZ(), new ItemStack(ItemRegister.POP_CORN.get(), this.cornCnt));
		item.setDefaultPickUpDelay();
		level.addFreshEntity(item);
		this.cornCnt = 0;
		for(int i = 0; i < 3; ++ i) {
			EntityUtil.spawnParticle(this, 8);
		}
		for(int i = 0; i < 20; ++ i) {
			EntityUtil.spawnParticle(this, 9);
		}
		EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1F, 1F);
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 300;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("cannon_pop_corn_cnt")) {
			this.cornCnt = compound.getInt("cannon_pop_corn_cnt");
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("cannon_pop_corn_cnt", this.cornCnt);
	}

}
