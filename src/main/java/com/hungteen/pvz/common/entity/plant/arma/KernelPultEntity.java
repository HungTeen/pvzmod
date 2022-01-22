package com.hungteen.pvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.ButterEntity;
import com.hungteen.pvz.common.entity.bullet.KernelEntity;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.List;

public class KernelPultEntity extends PlantPultEntity {

	private static final DataParameter<Integer> CURRENT_BULLET = EntityDataManager.defineId(KernelPultEntity.class, DataSerializers.INT);
	private static final int BUTTER_CHANCE = 10;
	private KernelPultEntity upgradeEntity;
	
	public KernelPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CURRENT_BULLET, CornTypes.KERNEL.ordinal());
	}
	
	@Override
	public void onPlantUpgrade(PVZPlantEntity plantEntity) {
		super.onPlantUpgrade(plantEntity);
		if(this.upgradeEntity != null) {
			this.upgradeEntity.remove();
		}
	}
	
	@Override
	public boolean canBeUpgrade(PlayerEntity player) {
		this.upgradeEntity = this.getNearByPult(player);
		return super.canBeUpgrade(player) && EntityUtil.isEntityValid(this.upgradeEntity);
	}
	
	private KernelPultEntity getNearByPult(PlayerEntity player){
		final float range = 1.5F;
		List<KernelPultEntity> list = level.getEntitiesOfClass(KernelPultEntity.class, EntityUtil.getEntityAABB(this, range, range), pult -> {
			return ! pult.is(this) && pult.getPlantType() == PVZPlants.KERNEL_PULT && ! EntityUtil.canAttackEntity(pult, player);
		});
		return list.size() == 0 ? null : list.get(0);
	}
	
	@Override
	public void startPultAttack() {
		super.startPultAttack();
		this.changeBullet();
	}
	
	/**
	 * switch butter or kernel.
	 */
	protected void changeBullet() {
		if(this.isPlantInSuperMode() && ! this.isSuperOut) {
			this.setCurrentBullet(CornTypes.BUTTER);
			return ;
		}
		this.setCurrentBullet(this.getRandom().nextInt(BUTTER_CHANCE) == 0 ? CornTypes.BUTTER : CornTypes.KERNEL);
	}
	
	@Override
	public void performPult(LivingEntity target1) {
		super.performPult(target1);
		this.setCurrentBullet(CornTypes.KERNEL); 
	}
	
	@Override
	protected PultBulletEntity createBullet() {
		if(this.isPlantInSuperMode() || this.getCurrentBullet() == CornTypes.BUTTER) {
			return new ButterEntity(level, this);
		}
		return new KernelEntity(level, this);
	}
	
	@Override
	public float getSuperDamage() {
		return 2 * this.getAttackDamage();
	};

	@Override
	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.MORE_KERNEL_DAMAGE);
	}

	public EffectInstance getButterEffect() {
		return new EffectInstance(EffectRegister.BUTTER_EFFECT.get(), this.getButterDuration(), 1, false, false);
	}
	
	public int getButterDuration() {
		return 100;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1F);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("current_bullet_type")) {
			this.setCurrentBullet(CornTypes.values()[compound.getInt("current_bullet_type")]);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("current_bullet_type", this.getCurrentBullet().ordinal());
	}
	
	public void setCurrentBullet(CornTypes type) {
		this.entityData.set(CURRENT_BULLET, type.ordinal());
	}
	
	public CornTypes getCurrentBullet() {
		return CornTypes.values()[this.entityData.get(CURRENT_BULLET)];
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.KERNEL_PULT;
	}

	public static enum CornTypes{
		KERNEL,
		BUTTER,
		ROCKET
	}
	
}
