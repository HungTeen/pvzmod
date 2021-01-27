package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MournerZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Boolean> RIGHT_SHAKE = EntityDataManager.createKey(MournerZombieEntity.class, DataSerializers.BOOLEAN);
	public static final int SHAKE_CD = 10;
	
	public MournerZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setRightShake(this.getRNG().nextInt(2) == 0 ? true : false);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(RIGHT_SHAKE, true);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_SLOW);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.VERY_LOW);
	}
	
	@Override
	public float getLife() {
		return 48;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.setAttackTime(SHAKE_CD);
		float scale = 3;
		entityIn.setMotion(0, Math.sqrt(this.getRNG().nextFloat()) * scale, 0);
		return super.attackEntityAsMob(entityIn);
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeNormalDamage(this, this);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
		}
	}
	
	@Override
	protected void onDeathUpdate() {
		remove();
		if(!world.isRemote) {
			TombStoneEntity tomb = EntityRegister.TOMB_STONE.get().create(world);
			EntityUtil.onMobEntitySpawn(world, tomb, getPosition());
		}
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("is_right_shake")) {
			this.setRightShake(compound.getBoolean("is_right_shake"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_right_shake", this.isRightShake());
	}
	
	public boolean isRightShake() {
		return this.dataManager.get(RIGHT_SHAKE);
	}
	
	public void setRightShake(boolean is) {
		this.dataManager.set(RIGHT_SHAKE, is);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.MOURNER_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.MOURNER_ZOMBIE;
	}

}
