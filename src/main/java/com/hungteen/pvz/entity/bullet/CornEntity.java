package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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
		this.noClip = true;
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
		EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
		float range = 2.6F;
		EntityUtil.getAttackEntities(this.getThrower(), EntityUtil.getEntityAABB(this, range, range)).forEach((entity) -> {
			if((! (entity instanceof LivingEntity) || EntityUtil.checkCanEntityTarget(this.getThrower(), (LivingEntity) entity))) {
				entity.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), this.attackDamage);
			}
		});
		ItemEntity item = new ItemEntity(world, getPosX(), getPosY() + 1, getPosZ(), new ItemStack(ItemRegister.POP_CORN.get(), this.cornCnt));
		item.setDefaultPickupDelay();
		world.addEntity(item);
		this.cornCnt = 0;
		for(int i = 0; i < 3; ++ i) {
			EntityUtil.spawnParticle(this, 8);
		}
		for(int i = 0; i < 20; ++ i) {
			EntityUtil.spawnParticle(this, 9);
		}
	}

	@Override
	protected float getAttackDamage() {
		if(this.getThrower() instanceof CobCannonEntity) return ((CobCannonEntity) this.getThrower()).getAttackDamage();
		return 0;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1F, 1F);
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 300;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("cannon_pop_corn_cnt")) {
			this.cornCnt = compound.getInt("cannon_pop_corn_cnt");
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("cannon_pop_corn_cnt", this.cornCnt);
	}

}
