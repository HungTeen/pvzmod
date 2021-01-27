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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DiggerZombieEntity extends PVZZombieEntity implements IHasMetal {

	private static final DataParameter<Boolean> HAS_PICKAXE = EntityDataManager.createKey(DiggerZombieEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ANIM_TIME = EntityDataManager.createKey(DiggerZombieEntity.class, DataSerializers.VARINT);
	public static final int MAX_ANIM_TIME = 30;
	
	public DiggerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPickaxe(true);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HAS_PICKAXE, true);
		this.dataManager.register(ANIM_TIME, 0);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! world.isRemote) {
			LivingEntity target = this.getAttackTarget();
			if(this.hasPickaxe()) {
				if(target != null) {
				    if(this.getDistanceSq(target) <= 8) {
				    	this.setAnimTime(MathHelper.clamp(this.getAnimTime() + 1, 0, MAX_ANIM_TIME));
				    } else {
				    	this.setAnimTime(MathHelper.clamp(this.getAnimTime() - 1, 0, MAX_ANIM_TIME));
				    }
			    } else {
			    	this.setAnimTime(MathHelper.clamp(this.getAnimTime() - 1, 0, MAX_ANIM_TIME));
			    }
			} else {
				this.setAnimTime(MathHelper.clamp(this.getAnimTime() + 1, 0, MAX_ANIM_TIME));
			}
			this.updateAttributes(this.hasPickaxe());
		}
		this.recalculateSize();
	}
	
	private void updateAttributes(boolean has){
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(has ? ZombieUtil.NORMAL_DAMAGE : ZombieUtil.LOW);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(has ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
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
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, this.getAnimTime() * 0.06F + 0.2F);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("digger_has_pickaxe")) {
			this.setPickaxe(compound.getBoolean("digger_has_pickaxe"));
		}
		if(compound.contains("digger_anim_time")) {
			this.setAnimTime(compound.getInt("digger_anim_time"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("digger_has_pickaxe", this.hasPickaxe());
		compound.putInt("digger_anim_time", this.getAnimTime());
	}
	
	public void setPickaxe(boolean has) {
		this.dataManager.set(HAS_PICKAXE, has);
	}
	
	public boolean hasPickaxe() {
		return this.dataManager.get(HAS_PICKAXE);
	}
	
	public void setAnimTime(int time) {
		this.dataManager.set(ANIM_TIME, time);
	}
	
	public int getAnimTime() {
		return this.dataManager.get(ANIM_TIME);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.DIGGER_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DIGGER_ZOMBIE;
	}

}
