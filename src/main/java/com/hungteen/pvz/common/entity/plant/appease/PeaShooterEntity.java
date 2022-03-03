package com.hungteen.pvz.common.entity.plant.appease;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class PeaShooterEntity extends PlantShooterEntity{

	protected static final double SHOOT_OFFSET = 0.2D;//pea position offset
	
	public PeaShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void shootBullet() {
		if(this.isPlantInSuperMode()) {
			final int cnt = this.getSuperShootCount();
			for(int i = 0; i < cnt; ++ i) {
				final float offset = MathUtil.getRandomFloat(getRandom()) / 3;
				final float offsetH = MathUtil.getRandomFloat(getRandom()) / 3;
				this.performShoot(SHOOT_OFFSET, offset, offsetH, this.getExistTick() % 10 == 0, FORWARD_SHOOT_ANGLE);
			}
		} else {
			this.performShoot(SHOOT_OFFSET, 0, 0, this.getAttackTime() == 1, FORWARD_SHOOT_ANGLE);
		}
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		final PeaEntity pea = new PeaEntity(this.level, this, this.getShootType(), this.getShootState());
//		if(this.getRandom().nextFloat() < this.getKBChance()){
//		}
		return pea;
	}

	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.PEA_DAMAGE);
	}

//	public float getBigChance(){
//		return this.getSkillValue(SkillTypes.BIG_PEA);
//	}
//
//	public float getKBChance(){
//		return this.getSkillValue(SkillTypes.KB_PEA);
//	}

	/**
	 * get how many peas need shoot per tick, when super.
	 */
	public int getSuperShootCount() {
		return MathUtil.getRandomMinMax(getRandom(), 1, 2);
	}

	protected PeaEntity.Type getShootType(){
		return PeaEntity.Type.NORMAL;
	}
	
	protected PeaEntity.State getShootState(){
		return PeaEntity.State.NORMAL;
	}

	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}

	@Override
	public int getSuperTimeLength() {
		return 100;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7F, 1.3F);
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.PEA_SHOOTER;
	}

}
