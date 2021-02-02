package com.hungteen.pvz.entity.plant.arma;

import java.util.Optional;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.ButterEntity;
import com.hungteen.pvz.entity.bullet.KernelEntity;
import com.hungteen.pvz.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class KernelPultEntity extends PlantPultEntity {

	private static final DataParameter<Integer> CURRENT_BULLET = EntityDataManager.createKey(KernelPultEntity.class, DataSerializers.VARINT);
	private static final int BUTTER_CHANCE = 10;
	
	public KernelPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(CURRENT_BULLET, CornTypes.KERNEL.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getPultRange(), 11, 10));
	}
	
	@Override
	public void startPultAttack() {
		super.startPultAttack();
		this.changeBullet();
	}
	
	protected void changeBullet() {
		if(this.isPlantInSuperMode() && ! this.isSuperOut) {
			this.setCurrentBullet(CornTypes.BUTTER);
			return ;
		}
		this.setCurrentBullet(this.getRNG().nextInt(BUTTER_CHANCE) == 0 ? CornTypes.BUTTER : CornTypes.KERNEL);
	}
	
	@Override
	public void pultBullet() {
		LivingEntity target = this.getAttackTarget();
		this.pultKernel(target, false);
	}
	
	@Override
	protected void doSuperAttackToTarget(LivingEntity target) {
		this.pultKernel(target, true);
	}
	
	protected Optional<PultBulletEntity> pultKernel(LivingEntity target, boolean isSuper) {
		if(target == null) return Optional.empty();
		PultBulletEntity bullet = null;
		if(isSuper || this.getCurrentBullet() == CornTypes.BUTTER) {
			bullet = new ButterEntity(world, this);
		} else if(this.getCurrentBullet() == CornTypes.KERNEL) {
			bullet = new KernelEntity(world, this);
		} 
		if(bullet == null) {
			System.out.println("ERROR : Wrong kernel Pult Throw Bullet Type !");
			return Optional.empty();
		}
		bullet.setPosition(this.getPosX(), this.getPosY() + 1.7f, this.getPosZ());
		bullet.shootPultBullet(target);
        this.world.addEntity(bullet);
        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
        this.setCurrentBullet(CornTypes.KERNEL); 
        return Optional.of(bullet);
	}
	
	public EffectInstance getButterEffect() {
		return new EffectInstance(EffectRegister.BUTTER_EFFECT.get(), this.getButterDuration(), 1, false, false);
	}
	
	public int getButterDuration() {
		return 100;
	}
	
	public float getKernelDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 18) {
			int now = (lvl -1) / 2;
			return 2 + 0.25f * now;
		}
		if(lvl <= 19) return 4.25f;
		return 4.5f;
	}
	
	public float getButterDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 3.75F + 0.25F * lvl;
		return 9;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8F, 1F);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("current_bullet_type")) {
			this.setCurrentBullet(CornTypes.values()[compound.getInt("current_bullet_type")]);
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("current_bullet_type", this.getCurrentBullet().ordinal());
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.KERNEL_PULT;
	}
	
	public void setCurrentBullet(CornTypes type) {
		this.dataManager.set(CURRENT_BULLET, type.ordinal());
	}
	
	public CornTypes getCurrentBullet() {
		return CornTypes.values()[this.dataManager.get(CURRENT_BULLET)];
	}

	public static enum CornTypes{
		KERNEL,
		BUTTER,
		ROCKET
	}
	
}
