package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IDefender;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class PlantDefenderEntity extends PVZPlantEntity implements IDefender{

	private static final DataParameter<Float> DEFENCE_LIFE = EntityDataManager.createKey(PlantDefenderEntity.class, DataSerializers.FLOAT);
	
	public PlantDefenderEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(DEFENCE_LIFE, 0f);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, getAttractRange(), 3f) {
			@Override
			protected boolean checkPlant(Entity entity) {
				return entity instanceof MobEntity;
			}
		});
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
		for(Entity target:EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, range, range))) {
			if(target instanceof MobEntity) {
				if(!(((MobEntity) target).getAttackTarget() instanceof PlantDefenderEntity)) {
					((MobEntity) target).setAttackTarget(this);
				}
			}
		}
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if(player.getHeldItem(hand).getItem() instanceof PlantCardItem && this.getHealth() != this.getMaxHealth()) {
			PlantCardItem item = (PlantCardItem) player.getHeldItem(hand).getItem();
			if(!item.isEnjoyCard()&&item.getPlant() == this.getPlantEnumName()) { // nut heal 
				if(!world.isRemote) {
					player.getCooldownTracker().setCooldown(item, this.getCoolDownTime());
				    this.heal(this.getMaxHealth());
				}else {
					for(int i=0;i<4;i++) {
						this.world.addParticle(ParticleTypes.HEART, this.getPosX(), this.getPosY()+this.getEyeHeight(), this.getPosZ(), (this.getRNG().nextFloat()-0.5f)/8, 0.05f, (this.getRNG().nextFloat()-0.5f)/8);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(!world.isRemote) {
			if(this.getDefenceLife()>amount) { // damage armor health first
				this.setDefenceLife(this.getDefenceLife()-amount);
				amount=0;
			}else {
				amount-=this.getDefenceLife();
				this.setDefenceLife(0f);
			}
		}
		return super.attackEntityFrom(source, amount);
	}
	
	protected float getAttractRange() {
		return 3;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	public float getDefenceLife() {
		return dataManager.get(DEFENCE_LIFE);
	}
	
	public void setDefenceLife(float life) {
		dataManager.set(DEFENCE_LIFE, life);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setDefenceLife(compound.getFloat("defence_life"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putFloat("defence_life", this.getDefenceLife());
	}
	
}
