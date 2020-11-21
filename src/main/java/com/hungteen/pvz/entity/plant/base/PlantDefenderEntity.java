package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Resources;
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
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, getAttractRange(), this.getAttractRange()) {
			@Override
			protected boolean checkOther(LivingEntity entity) {
				if(entity instanceof MobEntity) {
					return ! (((MobEntity) entity).getAttackTarget() instanceof PlantDefenderEntity);
				}
				return false;
			}
		});
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! world.isRemote) {
			if(this.getAttackTarget() != null) {
				this.attract();
				this.setAttackTarget(null);
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
			if(!(((MobEntity) target).getAttackTarget() instanceof PlantDefenderEntity)) {
				((MobEntity) target).setAttackTarget(this);
			}
		}
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		super.processInteract(player, hand);
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() instanceof PlantCardItem && this.getHealth() != this.getMaxHealth()) {
			PlantCardItem item = (PlantCardItem) stack.getItem();
			if(item.getPlant() == this.getPlantEnumName() && !player.getCooldownTracker().hasCooldown(item)) { // nut heal 
				if(!world.isRemote) {
					player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
						int cost = item.getSunCost(stack);
						int sun = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.SUN_NUM);
						if(sun >= cost) {
							l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - cost);
							PlantCardItem.onUsePlantCard(player, stack, item, l.getPlayerData().getPlantStats().getPlantLevel(getPlantEnumName()));
						    this.heal(this.getMaxHealth());
						}
					});
				}else {
					for(int i = 0;i < 4; ++ i) {
						this.world.addParticle(ParticleTypes.HEART, this.getPosX(), this.getPosY() + this.getEyeHeight(), this.getPosZ(), (this.getRNG().nextFloat()-0.5f)/8, 0.05f, (this.getRNG().nextFloat()-0.5f)/8);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		amount = this.pumpkinReduceDamage(source, amount);
		if(!world.isRemote) {
			if(this.getDefenceLife() > amount) { // damage armor health first
				this.setDefenceLife(this.getDefenceLife() - amount);
				amount = 0;
			}else {
				amount -= this.getDefenceLife();
				this.setDefenceLife(0f);
			}
		}
		return super.attackEntityFrom(source, amount);
	}
	
	public float getAttractRange() {
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
