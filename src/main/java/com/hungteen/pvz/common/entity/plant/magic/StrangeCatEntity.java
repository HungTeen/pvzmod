package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.common.impl.plant.MemePlants;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;

import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.Arrays;
import java.util.List;

public class StrangeCatEntity extends PVZPlantEntity {

	public static final int REST_CD = 1000;
	public static final int ANIM_CD = 10;
	private int restTick = REST_CD;
	
	public StrangeCatEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.isImmuneToWeak = true;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 5, 5));
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (! level.isClientSide) {
			if(EntityUtil.isEntityValid(this.getTarget())) {
				this.lookControl.setLookAt(this.getTarget(), 30f, 30f);
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else if(this.restTick == 0){
				this.setAttackTime(0);
				if(EntityUtil.isEntityValid(this.getTarget())) {
					this.performAttack(getTarget());
				}
			} else {
				this.setAttackTime(- 1);
			}
			this.restTick = Math.max(0, this.restTick - 1);
		}
	}
	
	/**
	 * deal damage
	 */
	protected void performAttack(LivingEntity target) {
		target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage());
		EntityUtil.playSound(this, SoundRegister.BRUH.get());
		this.setAttackTime(ANIM_CD);
		this.restTick = REST_CD;
	}
	
	/**
	 * {@link PVZLivingEvents#onLivingDeath(LivingDeathEvent)}
	 */
	public static void handleCopyCat(final LivingDeathEvent ev) {
		if(! ev.getEntity().level.isClientSide && ev.getSource().getEntity() instanceof StrangeCatEntity) {
			final float range = 10F;
			final int count = ev.getEntity().level.getEntitiesOfClass(StrangeCatEntity.class, EntityUtil.getEntityAABB(ev.getEntity(), range, range)).size();
			if(count < PVZConfig.COMMON_CONFIG.EntitySettings.PlantSetting.StrangeCatCount.get()) {
				((StrangeCatEntity) ev.getSource().getEntity()).onSelfCopy(ev.getEntityLiving());
			}
		}
	}
	
	public void onSelfCopy(LivingEntity target) {
		StrangeCatEntity cat = EntityRegister.STRANGE_CAT.get().create(level);
		PlantUtil.copyPlantData(cat, this);
		EntityUtil.onEntitySpawn(level, cat, target.blockPosition());
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		EntityUtil.playSound(this, SoundRegister.BRUH.get());
		this.setAttackTime(ANIM_CD);
		EntityUtil.getRandomLivingInRange(level, this, EntityUtil.getEntityAABB(this, 20, 20), getSuperAttackCount()).forEach((target) ->{
			target.hurt(PVZEntityDamageSource.normal(this), this.getAttackDamage());
		});
	}
	
	public boolean isResting() {
		return this.getAttackTime() < 0;
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage()),
				Pair.of(PAZAlmanacs.REST_TIME, REST_CD)
		));
	}

	/**
	 * max damage to target
	 */
	public float getAttackDamage() {
		return 200;
	}
	
	public int getSuperAttackCount() {
		return 4;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 30;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.8f, 1f, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("rest_tick", this.restTick);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("rest_tick")) {
			this.restTick = compound.getInt("rest_tick");
		}
	}
	
	@Override
	public IPlantType getPlantType() {
		return MemePlants.STRANGE_CAT;
	}

}
