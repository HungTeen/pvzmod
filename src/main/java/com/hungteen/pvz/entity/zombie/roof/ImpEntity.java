package com.hungteen.pvz.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ImpEntity extends PVZZombieEntity {

	public ImpEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			int now = this.getRNG().nextInt(10);
			if(now == 0) this.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 1));
			else if(now == 1) this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, 1));
			else if(now == 2) this.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 1));
			else if(now == 3) this.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 600, 1));
			else if(now == 3) this.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 600, 1));
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if(source.getDamageType() == DamageSource.FALL.damageType) return true;
		return super.isInvulnerableTo(source);
	}
	
	@Override
	public float getLife() {
		return 10;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.flexible(0.2F, 0.45F);
		return EntitySize.flexible(0.6F, 1.2F);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.IMP;
	}

}
