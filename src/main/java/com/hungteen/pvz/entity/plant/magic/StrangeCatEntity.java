package com.hungteen.pvz.entity.plant.magic;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class StrangeCatEntity extends PVZPlantEntity {

	public static final int ANIM_CD = 10;
	private int restTick = 0;
	
	public StrangeCatEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.isImmuneToWeak = true;
		this.restTick = this.getRestCD();
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.STRANGE_CAT;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 4, 2));
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (! level.isClientSide && this.getTarget() != null) {
			this.lookControl.setLookAt(this.getTarget(), 30f, 30f);
			if(! this.isSuitableTarget(getTarget())) {
				this.setTarget(null);
			}
		}
		if (! level.isClientSide) {
			if(this.getTarget() == null) {
				this.setAttackTime(ANIM_CD);
			} else {
				if(this.restTick <= 0) {
					if(this.getAttackTime() > 0) {
				        this.setAttackTime(this.getAttackTime() - 1);
				    } else {
						this.performAttack(getTarget());
					}
				}
			}
			this.restTick = Math.max(0, this.restTick - 1);
		}
	}
	
	public void onSelfCopy(LivingEntity target) {
		StrangeCatEntity cat = EntityRegister.STRANGE_CAT.get().create(level);
		PlantUtil.copyPlantData(cat, this);
		if(this.getRandom().nextInt(PVZConfig.COMMON_CONFIG.EntitySettings.StrangeCatNameChance.get()) == 0) {
			List<String> list = Arrays.asList("芦苇", "Rua猫");
			int pos = this.getRandom().nextInt(list.size());
			cat.setCustomName(new StringTextComponent(list.get(pos)));
		}
		EntityUtil.onMobEntitySpawn(level, cat, target.blockPosition());
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		return super.canPlantTarget(entity) && this.isSuitableTarget(entity);
	}
	
	/**
	 * deal damage
	 */
	private void performAttack(LivingEntity target) {
		target.hurt(PVZDamageSource.causeNormalDamage(this, this).setCopyDamage(), getAttackDamage());
		EntityUtil.playSound(this, SoundRegister.BRUH.get());
		this.restTick = this.getRestCD();
	}

	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		EntityUtil.playSound(this, SoundRegister.BRUH.get());
		EntityUtil.getRandomLivingInRange(level, this, EntityUtil.getEntityAABB(this, 20, 20), getSuperAttackCount()).forEach((target) ->{
			target.hurt(PVZDamageSource.causeNormalDamage(this, this).setCopyDamage(), getAttackDamage());
		});
	}
	
	public boolean isSuitableTarget(LivingEntity target) {
		return EntityUtil.getCurrentHealth(target) <= this.getAttackDamage();
	}
	
	/**
	 * max damage to target
	 */
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if (lvl <= 19) return 156 + 4 * lvl;
		return 240;
	}
	
	public int getSuperAttackCount() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	public int getRestCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 1210 - 10 * lvl;
		}
		return 1000;
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
	public int getSuperTimeLength() {
		return 30;
	}

}
