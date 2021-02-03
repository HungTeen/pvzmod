package com.hungteen.pvz.entity.bullet.itembullet;

import com.hungteen.pvz.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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

	private static final DataParameter<Integer> CABBAGE_TYPE = EntityDataManager.createKey(CabbageEntity.class, DataSerializers.VARINT);
	
	public CabbageEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public CabbageEntity(World worldIn, LivingEntity shooter) {
		super(EntityRegister.CABBAGE.get(), worldIn, shooter);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(CABBAGE_TYPE, CabbageTypes.NORMAL.ordinal());
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.CABBAGE.get());
	}

	protected void dealDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeThrowDamage(this, this.getThrower()), this.getFixDamage());
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.5F, 0.5F);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("cabbage_type")) {
			this.setCabbageType(CabbageTypes.values()[compound.getInt("cabbage_type")]);
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("cabbage_type", this.getCabbageType().ordinal());
	}
	
	public void setCabbageType(CabbageTypes type) {
		this.dataManager.set(CABBAGE_TYPE, type.ordinal());
	}
	
	public CabbageTypes getCabbageType() {
		return CabbageTypes.values()[this.dataManager.get(CABBAGE_TYPE)];
	}
	
	public static enum CabbageTypes {
		NORMAL,
		POWER,
	}

}
