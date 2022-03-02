package com.hungteen.pvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.zombie.pool.DiggerZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class SplitPeaEntity extends PeaShooterEntity{

	private static final DataParameter<Integer> ROUND_TICK = EntityDataManager.defineId(SplitPeaEntity.class, DataSerializers.INT);  
	public static final int MAX_ROUND_TIME = 20;
	
	public SplitPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ROUND_TICK, 0);
	}
	
	@Override
	protected void plantTick() {
		super.plantTick();
		if(! level.isClientSide) {
			if(this.getRoundTick() != 0 && this.getRoundTick() != MAX_ROUND_TIME / 2) {
				this.rotateFacing();
			}
		}
	}
	
	@Override
	public void shootBullet() {
		if(this.isPlantInSuperMode()) {
			final int cnt = this.getSuperShootCount();
			for(int i = 0; i < cnt; ++ i) {
				final float offset = MathUtil.getRandomFloat(getRandom()) / 3;
				final float offsetH = MathUtil.getRandomFloat(getRandom()) / 3;
				this.performShoot(SHOOT_OFFSET, offset, offsetH, this.getExistTick() % 10 == 0, FORWARD_SHOOT_ANGLE);
				this.performShoot(SHOOT_OFFSET, offset, offsetH, false, BACK_SHOOT_ANGLE);
			}
		} else {
			final int frontNum = this.isFacingFront() ? 1 : 2;
			final int backNum = this.isFacingFront() ? 2 : 1;
			if(this.getAttackTime() <= frontNum) {
				this.performShoot(SHOOT_OFFSET, 0, 0, this.getAttackTime() == 1, FORWARD_SHOOT_ANGLE);
			}
			if(this.getAttackTime() <= backNum) {
			    this.performShoot(SHOOT_OFFSET, 0, 0, false, BACK_SHOOT_ANGLE);
			}
			this.checkAndChangeFacing();//change head.
		}
	}
	
	/**
	 * deal with change facing
	 */
	private void checkAndChangeFacing() {
		if(this.getAttackTime() == 1 && ! this.isPlantInSuperMode()) {
			final float chance = this.isFacingFront() ? this.getDoubleChance() : 1 - this.getDoubleChance();
			if(this.getRandom().nextFloat() < chance) {
				this.rotateFacing();
			}
		}
	}
	
	private void rotateFacing() {
		this.setRoundTick((this.getRoundTick() + 1) % MAX_ROUND_TIME);
	}
	
	/**
	 * Repeater head stay front chance. 
	 */
	public float getDoubleChance() {
		return this.getSkillValue(SkillTypes.SPLIT_DOUBLE_CHANCE);
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
	}
	
	public boolean isFacingFront() {
		return this.getRoundTick() == 0;
	}

	@Override
	public double getMaxShootAngle() {
		return 5;
	}

	@Override
	public boolean canPAZTarget(Entity entity) {
		if(entity instanceof DiggerZombieEntity){
			return this.checkY(entity);
		}
		return super.canPAZTarget(entity);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("round_tick")) {
			this.setRoundTick(compound.getInt("round_tick"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("round_tick", this.getRoundTick());
	}
	
	public int getRoundTick() {
		return this.entityData.get(ROUND_TICK);
	}
	
	public void setRoundTick(int tick) {
		this.entityData.set(ROUND_TICK, tick);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.SPLIT_PEA;
	}
	
}
