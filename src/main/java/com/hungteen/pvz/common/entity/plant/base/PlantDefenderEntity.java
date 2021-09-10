package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.ICanBeAttracted;
import com.hungteen.pvz.common.entity.ai.goal.misc.PlantAttractGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.defence.PumpkinEntity;
import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.utils.interfaces.ICanAttract;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class PlantDefenderEntity extends PVZPlantEntity implements ICanAttract {

	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.defineId(PlantDefenderEntity.class, DataSerializers.FLOAT);
	protected float damageMultiple = 1F;//use to reduce damage by tall nut.
	
	public PlantDefenderEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(DEFENCE_LIFE, 0f);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PlantAttractGoal(this, this, 15));
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setDefenceLife(this.getSuperLife());
	}
	
	@Override
	public boolean canAttract(LivingEntity entity) {
		if(entity instanceof ICanBeAttracted && ! ((ICanBeAttracted) entity).canBeAttractedBy(this)) {
			return false;
		}
		if(! this.getSensing().canSee(entity)) {
			return false;
		}
		return true;
	}
	
	@Override
	public void attract(LivingEntity target) {
		if(target instanceof MobEntity && (! (((MobEntity) target).getTarget() instanceof ICanAttract))) {
			((MobEntity) target).setTarget(this);
		}
		if(target instanceof ICanBeAttracted) {
			((ICanBeAttracted) target).attractBy(this);
		}
	}
	
	@Override
	protected float getCurrentDefenceHealth() {
		return super.getCurrentDefenceHealth() + this.getDefenceLife();
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		super.interactAt(player, vec3d, hand);
		ItemStack stack = player.getItemInHand(hand);
		if(stack.getItem() instanceof PlantCardItem && this.getHealth() != this.getMaxHealth()) {
//			PlantCardItem cardItem = (PlantCardItem) stack.getItem();
//			if(cardItem.plantType == this.getPlantType()) { // nut heal 
//				if(! level.isClientSide) {
//					PlantCardItem.checkSunAndHealPlant(player, this, cardItem, stack);
//				} else {
//					for(int i = 0; i < 4; ++ i) {
//						this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight(), this.getZ(), (this.getRandom().nextFloat()-0.5f)/8, 0.05f, (this.getRandom().nextFloat()-0.5f)/8);
//					}
//				}
//				return ActionResultType.CONSUME;
//			} else if(cardItem instanceof ImitaterCardItem && ((ImitaterCardItem) cardItem).isPlantTypeEqual(stack, this.getPlantType())) {
//				if(! level.isClientSide) {
//					PlantCardItem.checkSunAndHealPlant(player, this, cardItem, stack);
//				} else {
//					for(int i = 0; i < 4; ++ i) {
//						this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight(), this.getZ(), (this.getRandom().nextFloat()-0.5f)/8, 0.05f, (this.getRandom().nextFloat()-0.5f)/8);
//					}
//				}
//				return ActionResultType.CONSUME;
//			}
		}
		return ActionResultType.FAIL;
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		amount = PumpkinEntity.PumpkinInfo.pumpkinReduceDamage(this, source, amount);
		amount *= this.damageMultiple;
		if(! level.isClientSide && this.getDefenceLife() > 0) {
			if(this.getDefenceLife() > amount) { // damage armor health first
				this.setDefenceLife(this.getDefenceLife() - amount);
				amount = 0;
			} else {
				amount -= this.getDefenceLife();
				this.setDefenceLife(0f);
			}
		}
		return super.hurt(source, amount == 0 ? 0.0001F : amount);
	}
	
	@Override
	public float getAttractRange() {
		return 2.5F;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	/**
	 * for extra life.
	 */
	public abstract float getSuperLife();
 
	
	public float getDefenceLife() {
		return entityData.get(DEFENCE_LIFE);
	}
	
	public void setDefenceLife(float life) {
		entityData.set(DEFENCE_LIFE, life);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("defence_life")) {
			this.setDefenceLife(compound.getFloat("defence_life"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putFloat("defence_life", this.getDefenceLife());
	}
	
}
