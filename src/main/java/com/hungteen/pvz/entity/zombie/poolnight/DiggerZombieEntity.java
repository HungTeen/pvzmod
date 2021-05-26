package com.hungteen.pvz.entity.zombie.poolnight;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DiggerZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_PICKAXE = EntityDataManager.defineId(DiggerZombieEntity.class, DataSerializers.BOOLEAN);
	public static final int MAX_ANIM_TIME = 30;
	
	public DiggerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPickaxe(true);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_PICKAXE, true);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			LivingEntity target = this.getTarget();
			if(this.hasPickaxe()) {
				if(target != null) {
				    if(this.distanceToSqr(target) <= 8) {
				    	this.setAttackTime(MathHelper.clamp(this.getAttackTime() + 1, 0, MAX_ANIM_TIME));
				    } else {
				    	this.setAttackTime(MathHelper.clamp(this.getAttackTime() - 1, 0, MAX_ANIM_TIME));
				    }
			    } else {
			    	this.setAttackTime(MathHelper.clamp(this.getAttackTime() - 1, 0, MAX_ANIM_TIME));
			    }
			} else {
				this.setAttackTime(MathHelper.clamp(this.getAttackTime() + 1, 0, MAX_ANIM_TIME));
			}
			this.updateAttributes(this.hasPickaxe());
		}
		this.refreshDimensions();
	}
	
	private void updateAttributes(boolean has){
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(has ? ZombieUtil.NORMAL_DAMAGE : ZombieUtil.LOW);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(has ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
	}
	
	@Override
	public float getLife() {
		return 40;
	}
	
	@Override
	public boolean hasMetal() {
		return this.hasPickaxe();
	}

	@Override
	public void decreaseMetal() {
		this.setPickaxe(false);
	}

	@Override
	public void increaseMetal() {
		this.setPickaxe(true);
	}
	
	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.IRON_PICKAXE;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.4f, this.getAttackTime() * 0.02F + 0.1F);
		return EntitySize.scalable(0.8f, this.getAttackTime() * 0.06F + 0.2F);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("digger_has_pickaxe")) {
			this.setPickaxe(compound.getBoolean("digger_has_pickaxe"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("digger_has_pickaxe", this.hasPickaxe());
	}
	
	public void setPickaxe(boolean has) {
		this.entityData.set(HAS_PICKAXE, has);
	}
	
	public boolean hasPickaxe() {
		return this.entityData.get(HAS_PICKAXE);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.DIGGER_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DIGGER_ZOMBIE;
	}

}
