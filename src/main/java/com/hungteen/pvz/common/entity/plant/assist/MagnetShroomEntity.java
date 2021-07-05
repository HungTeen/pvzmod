package com.hungteen.pvz.common.entity.plant.assist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.MetalItemEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.MetalItemEntity.MetalStates;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class MagnetShroomEntity extends PVZPlantEntity {

	private static final DataParameter<Integer> METAL_TYPE = EntityDataManager.defineId(MagnetShroomEntity.class,
			DataSerializers.INT);
	
	public MagnetShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(METAL_TYPE, MetalTypes.EMPTY.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false,  12, 12));
		this.targetSelector.addGoal(1, new MagnetShroomTargetLadder(this, true, 20, 20));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			} else if(this.getAttackTime() == 0) {
				this.setMetalType(MetalTypes.EMPTY);
			}
			LivingEntity target = this.getTarget();
			if(target != null) {
				if(! this.checkCanPlantTarget(target)) {
					if(! EntityUtil.canTargetEntity(this, target) && target instanceof PVZPlantEntity && ((PVZPlantEntity) target).hasMetal()) {
						
					} else {
						this.setTarget(null);
					    return ;
					}
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
		for(LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, EntityUtil.getEntityAABB(this, range, range), (entity) -> {
			return this.checkCanPlantTarget(entity);
		})){
			if(! (target instanceof IHasMetal)) continue;
			((IHasMetal) target).decreaseMetal();
			MetalItemEntity metal = new MetalItemEntity(level, this, ((IHasMetal) target).getMetalType());
			metal.setMetalState(MetalStates.BULLET);
			metal.setPos(target.getX(), target.getY() + target.getEyeHeight(), target.getZ());
			level.addFreshEntity(metal);
			if(-- cnt == 0) return ;
		};
	}
	
	private void dragMetal(LivingEntity target) {
		if(target instanceof IHasMetal) {
			EntityUtil.playSound(this, SoundRegister.MAGNET.get());
			((IHasMetal) target).decreaseMetal();
			this.setAttackTime(this.getAttackCD());
			MetalItemEntity metal = new MetalItemEntity(level, this, ((IHasMetal) target).getMetalType());
			metal.setPos(target.getX(), target.getY() + target.getEyeHeight(), target.getZ());
			level.addFreshEntity(metal);
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
	public boolean canPlantTarget(Entity entity) {
		return entity instanceof IHasMetal && ((IHasMetal) entity).hasMetal();
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("metal_type", this.getMetalType().ordinal());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("metal_type")) {
			this.setMetalType(MetalTypes.values()[compound.getInt("metal_type")]);
		}
	}

	public MetalTypes getMetalType() {
		return MetalTypes.values()[entityData.get(METAL_TYPE)];
	}

	public void setMetalType(MetalTypes type) {
		entityData.set(METAL_TYPE, type.ordinal());
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
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 1.3f);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 60;
	}
	
	private static class MagnetShroomTargetLadder extends TargetGoal {

		protected final AlgorithmUtil.EntitySorter sorter;
		private final int targetChance = 5;
		private final float upperHeight;
		private final float lowerHeight;
		private final float width;

		public MagnetShroomTargetLadder(MobEntity mobIn, boolean checkSight, float w, float h) {
			super(mobIn, checkSight);
			this.width = w;
			this.upperHeight = h;
			this.lowerHeight = h;
			this.sorter = new AlgorithmUtil.EntitySorter(mob);
			this.setFlags(EnumSet.of(Goal.Flag.TARGET));
		}

		@Override
		public boolean canUse() {
			if (this.targetChance > 0 && this.mob.getRandom().nextInt(this.targetChance) != 0) {
				return false;
			}
			List<LivingEntity> list1 = new ArrayList<LivingEntity>();
			this.mob.level.getEntitiesOfClass(PVZPlantEntity.class, getAABB()).forEach((plant) -> {
				if(! EntityUtil.canTargetEntity(mob, plant) && this.checkSenses(plant)) {
					if(plant.hasMetal()) {
						list1.add(plant);
					}
				}
			});
			if (list1.isEmpty()) {
				return false;
			}
			Collections.sort(list1, this.sorter);
			this.targetMob = list1.get(0);
			return true;
		}

		@Override
		public void start() {
			this.mob.setTarget(this.targetMob);
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity entity = this.mob.getTarget();
			if (entity == null) {
				entity = this.targetMob;
			}
			if(entity == null || ! entity.isAlive()) {
				return false;
			}
			if(! EntityUtil.canTargetEntity(mob, entity) && this.checkSenses(entity)) {
				this.mob.setTarget(entity);
				return true;
			}
			return false;
		}

		protected boolean checkSenses(Entity entity) {
			return this.mob.getSensing().canSee(entity);
		}

		private AxisAlignedBB getAABB() {
			return new AxisAlignedBB(this.mob.getX() + width, this.mob.getY() + this.upperHeight,
					this.mob.getZ() + width, this.mob.getX() - width,
					this.mob.getY() - this.lowerHeight, this.mob.getZ() - width);
		}

	}

}
