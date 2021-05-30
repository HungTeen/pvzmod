package com.hungteen.pvz.common.entity.plant.appease;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SplitPeaEntity extends PeaShooterEntity{

	public static final int MAX_ROUND_TIME = 20;
	private static final DataParameter<Integer> ROUND_TICK = EntityDataManager.defineId(SplitPeaEntity.class, DataSerializers.INT);  
	
	public SplitPeaEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ROUND_TICK, 0);
	}
	
	@Override
	protected void plantBaseTick() {
		super.plantBaseTick();
		if(! level.isClientSide) {
			if(this.getRoundTick() != 0 && this.getRoundTick() != MAX_ROUND_TIME / 2) {
				this.rotateFacing();
			}
		}
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target = this.getTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY() + this.getDimensions(getPose()).height * 0.7f;
        double dis =MathHelper.sqrt(dx * dx + dz * dz);
        double tmp = this.LENTH / dis;
        double deltaX = tmp * dx * (this.isFacingFront() ? 1 : -1);
        double deltaZ = tmp * dz * (this.isFacingFront() ? 1 : -1);
        //shoot front
        if(this.getAttackTime() >= (this.isFacingFront() ? 2 : 1)) {
        	PeaEntity pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
            pea.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
            pea.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());      
            this.level.addFreshEntity(pea);
        }
        //shoot back
        if(this.getAttackTime() >= (this.isFacingFront() ? 1 : 2)) {
        	PeaEntity pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
            pea.setPos(this.getX() - deltaX, y, this.getZ() - deltaZ);
            pea.shootPeaAnti(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());      
            this.level.addFreshEntity(pea);
        }
        this.playSound(getShootSound(), 1.0F, 1.0F);
        this.checkAndChangeFacing();
	}
	
	/**
	 * deal with change facing
	 */
	private void checkAndChangeFacing() {
		if(this.getAttackTime() == 1 && ! this.isPlantInSuperMode()) {
			int chance = this.isFacingFront() ? this.getDoubleChance() : 100 - this.getDoubleChance();
			if(this.getRandom().nextInt(100) < chance) {
				this.rotateFacing();
			}
		}
	}
	
	private void rotateFacing() {
		this.setRoundTick((this.getRoundTick() + 1) % MAX_ROUND_TIME);
	}
	
	public int getDoubleChance() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 20 + 10 * now;
		}
		return 60;
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(2);
	}
	
	public boolean isFacingFront() {
		return this.getRoundTick() == 0;
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
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SPLIT_PEA;
	}
	
	public int getRoundTick() {
		return this.entityData.get(ROUND_TICK);
	}
	
	public void setRoundTick(int tick) {
		this.entityData.set(ROUND_TICK, tick);
	}
	
}
