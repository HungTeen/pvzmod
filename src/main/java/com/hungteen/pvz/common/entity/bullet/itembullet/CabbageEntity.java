package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class CabbageEntity extends PultBulletEntity implements IRendersAsItem {

	private static final DataParameter<Integer> CABBAGE_TYPE = EntityDataManager.defineId(CabbageEntity.class, DataSerializers.INT);
	
	public CabbageEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public CabbageEntity(World worldIn, LivingEntity shooter) {
		super(EntityRegister.CABBAGE.get(), worldIn, shooter);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CABBAGE_TYPE, CabbageTypes.NORMAL.ordinal());
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.CABBAGE.get());
	}

	protected void dealDamage(Entity target) {
		target.hurt(PVZDamageSource.cabbage(this, this.getThrower()), this.getFixDamage());
	}
	
	private float getFixDamage() {
		float damage = this.attackDamage;
		if(this.getCabbageType() == CabbageTypes.POWER) {
			damage += 20;
		}
		return damage;
	}
	
	protected float getAttackDamage() {
		if(this.getThrower() instanceof CabbagePultEntity) return ((CabbagePultEntity) this.getThrower()).getAttackDamage();
		return 0;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5F, 0.5F);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("cabbage_type")) {
			this.setCabbageType(CabbageTypes.values()[compound.getInt("cabbage_type")]);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("cabbage_type", this.getCabbageType().ordinal());
	}
	
	public void setCabbageType(CabbageTypes type) {
		this.entityData.set(CABBAGE_TYPE, type.ordinal());
	}
	
	public CabbageTypes getCabbageType() {
		return CabbageTypes.values()[this.entityData.get(CABBAGE_TYPE)];
	}
	
	public static enum CabbageTypes {
		NORMAL,
		POWER,
	}

}
