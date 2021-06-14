package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class ImpEntity extends PVZZombieEntity {

	public ImpEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! level.isClientSide) {
			int now = this.getRandom().nextInt(10);
			if(now == 0) this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 600, 1));
			else if(now == 1) this.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 600, 1));
			else if(now == 2) this.addEffect(new EffectInstance(Effects.JUMP, 600, 1));
			else if(now == 3) this.addEffect(new EffectInstance(Effects.HEALTH_BOOST, 600, 1));
			else if(now == 3) this.addEffect(new EffectInstance(Effects.ABSORPTION, 600, 1));
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_FAST);
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if(source.getMsgId() == DamageSource.FALL.msgId) return true;
		return super.isInvulnerableTo(source);
	}
	
	@Override
	public float getLife() {
		return 10;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.2F, 0.45F);
		return EntitySize.scalable(0.6F, 1.2F);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.IMP;
	}

}
