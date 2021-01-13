package com.hungteen.pvz.entity.plant.assist;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.MetalItemEntity;
import com.hungteen.pvz.entity.bullet.MetalItemEntity.MetalStates;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class MagnetShroomEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> METAL_TYPE = EntityDataManager.createKey(MagnetShroomEntity.class,
			DataSerializers.VARINT);
	
	public MagnetShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(METAL_TYPE, MetalTypes.EMPTY.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 12, 12));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! world.isRemote) {
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else if(this.getAttackTime() == 0) {
				this.setMetalType(MetalTypes.EMPTY);
			}
			LivingEntity target = this.getAttackTarget();
			if(target != null) {
				if(! this.checkCanPlantTarget(target)) {
					this.setAttackTarget(null);
					return ;
				}
				if(this.isPlantActive()) {
					this.dragMetal(target);
				}
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		int range = 20;
		int cnt = this.getSuperDragCnt();
		EntityUtil.playSound(this, SoundRegister.MAGNET.get());
		for(LivingEntity target : world.getEntitiesWithinAABB(LivingEntity.class, EntityUtil.getEntityAABB(this, range, range), (entity) -> {
			return this.checkCanPlantTarget(entity);
		})){
			if(! (target instanceof IHasMetal)) continue;
			((IHasMetal) target).decreaseMetal();
			MetalItemEntity metal = new MetalItemEntity(EntityRegister.METAL.get(), world, this, ((IHasMetal) target).getMetalType());
			metal.setMetalState(MetalStates.BULLET);
			metal.setPosition(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ());
			world.addEntity(metal);
			if(-- cnt == 0) return ;
		};
	}
	
	private void dragMetal(LivingEntity target) {
		if(target instanceof IHasMetal) {
			EntityUtil.playSound(this, SoundRegister.MAGNET.get());
			((IHasMetal) target).decreaseMetal();
			this.setAttackTime(this.getAttackCD());
			MetalItemEntity metal = new MetalItemEntity(EntityRegister.METAL.get(), world, this, ((IHasMetal) target).getMetalType());
			metal.setPosition(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ());
			world.addEntity(metal);
		} else {
			System.out.println("Error : wrong target !");
		}
	}
	
	public int getSuperDragCnt() {
		if(this.isPlantInStage(1)) return 3;
		if(this.isPlantInStage(2)) return 4;
		return 5;
	}
	
	public ItemStack getMetalRenderItem() {
		if(this.getMetalType() == null) return ItemStack.EMPTY;
		return new ItemStack(MetalTypes.getMetalItem(getMetalType()));
	}
	
	public boolean isPlantActive() {
		return ! this.isPlantSleeping() && this.getAttackTime() == 0;
	}
	
	public int getAttackCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) return 615 - 15 * lvl;
		return 300;
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		return entity instanceof IHasMetal && ((IHasMetal) entity).hasMetal();
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("metal_type", this.getMetalType().ordinal());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("metal_type")) {
			this.setMetalType(MetalTypes.values()[compound.getInt("metal_type")]);
		}
	}

	public MetalTypes getMetalType() {
		return MetalTypes.values()[dataManager.get(METAL_TYPE)];
	}

	public void setMetalType(MetalTypes type) {
		dataManager.set(METAL_TYPE, type.ordinal());
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.MAGNET_SHROOM;
	}

	@Override
	public Plants getUpgradePlantType() {
		return Plants.GOLD_MAGNET;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.5f, 1.3f);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 60;
	}

}
