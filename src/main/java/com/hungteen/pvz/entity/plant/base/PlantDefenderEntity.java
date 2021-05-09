package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.item.tool.card.ImitaterCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IDefender;

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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class PlantDefenderEntity extends PVZPlantEntity implements IDefender{

	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.defineId(PlantDefenderEntity.class, DataSerializers.FLOAT);
	
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
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, getAttractRange(), this.getAttractRange()) {
			@Override
			protected boolean checkOther(LivingEntity entity) {
				if(entity instanceof MobEntity) {
					return ! (((MobEntity) entity).getTarget() instanceof PlantDefenderEntity);
				}
				return false;
			}
		});
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getTarget() != null) {
				this.attract();
				this.setTarget(null);
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setDefenceLife(this.getSuperLife());
		this.attract();
	}
	
	@Override
	public void attract() {
		float range = getAttractRange();
		for(LivingEntity target:EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range))) {
			this.attract(target);
		}
	}
	
	public void attract(LivingEntity target) {
		if(target instanceof MobEntity) {
			if(!(((MobEntity) target).getTarget() instanceof PlantDefenderEntity)) {
				((MobEntity) target).setTarget(this);
			}
		}
	}
	
	@Override
	public float getCurrentHealth() {
		return super.getCurrentHealth() + this.getDefenceLife();
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		super.interactAt(player, vec3d, hand);
		ItemStack stack = player.getItemInHand(hand);
		if(stack.getItem() instanceof PlantCardItem && this.getHealth() != this.getMaxHealth()) {
			PlantCardItem cardItem = (PlantCardItem) stack.getItem();
			if(cardItem.plantType == this.getPlantEnumName()) { // nut heal 
				if(! level.isClientSide) {
					PlantCardItem.checkSunAndHealPlant(player, this, cardItem, stack);
				} else {
					for(int i = 0; i < 4; ++ i) {
						this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight(), this.getZ(), (this.getRandom().nextFloat()-0.5f)/8, 0.05f, (this.getRandom().nextFloat()-0.5f)/8);
					}
				}
				return ActionResultType.CONSUME;
			} else if(cardItem instanceof ImitaterCardItem && ((ImitaterCardItem) cardItem).isPlantTypeEqual(stack, this.getPlantEnumName())) {
				if(! level.isClientSide) {
					PlantCardItem.checkSunAndHealPlant(player, this, cardItem, stack);
				} else {
					for(int i = 0; i < 4; ++ i) {
						this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY() + this.getBbHeight(), this.getZ(), (this.getRandom().nextFloat()-0.5f)/8, 0.05f, (this.getRandom().nextFloat()-0.5f)/8);
					}
				}
				return ActionResultType.CONSUME;
			}
		}
		return ActionResultType.FAIL;
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		amount = this.pumpkinReduceDamage(source, amount);
		if(! level.isClientSide && this.getDefenceLife() > 0) {
			if(this.getDefenceLife() > amount) { // damage armor health first
				this.setDefenceLife(this.getDefenceLife() - amount);
				amount = 0;
			} else {
				amount -= this.getDefenceLife();
				this.setDefenceLife(0f);
			}
		}
		if(amount == 0) amount = 0.001F;
		return super.hurt(source, amount);
	}
	
	public float getAttractRange() {
		return 3;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
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
