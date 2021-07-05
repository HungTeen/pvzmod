package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.common.entity.bullet.itembullet.PotatoEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PotatoMineEntity extends PlantCloserEntity{

	public static final int RISING_ANIM_CD = 20;
	public static final int PREPARE_CD = 240;
	
	public PotatoMineEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! this.level.isClientSide) {
			if(this.getExistTick() == PREPARE_CD - RISING_ANIM_CD + 1) {
				EntityUtil.playSound(this, SoundRegister.DIRT_RISE.get());
			}
		} else {
			if(this.isRisingFromDirt()) {
				for(int i = 0; i < 10; ++ i) {
					Vector3d offset = new Vector3d(MathUtil.getRandomFloat(getRandom()), 0, MathUtil.getRandomFloat(getRandom())).normalize();
					WorldUtil.spawnRandomSpeedParticle(level, ParticleRegister.DIRT_BURST_OUT.get(), this.position().add(offset), MathUtil.getRandomFloat(getRandom()) / 8, 0.06F);
				}
			}
		}
	}
	
	@Override
	public boolean canStartSuperMode() {
		return super.canStartSuperMode() && this.getAttackTime() <= 0;
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		shootPotatos();
		if(! this.isRisingFromDirt() && ! this.isMineReady()) {
			this.setRisingFromDirt();
		}
	}
	
	/**
	 * shoot some potato to the sky
	 */
	protected void shootPotatos() {
		int num = this.getShootNum();
		for(int i = 1; i <= num; ++ i) {
			PotatoEntity potato = new PotatoEntity(level, this);
			potato.setPos(this.getX(), this.getY() + 1, this.getZ());
		    final float dx = MathUtil.getRandomFloat(getRandom()) * i / 2;
		    final float dy = 1;
		    final float dz = MathUtil.getRandomFloat(getRandom()) * i / 2;
		    potato.shoot(dx, dy, dz);
		    this.level.addFreshEntity(potato);
		}
	}
	
	public int getShootNum() {
		return this.isPlantInStage(1) ? 3 : this.isPlantInStage(2) ? 4 : 5;
	}
	
	/**
	 * {@link #canBeImmuneToEnforce()}
	 */
	public boolean isMineReady() {
		return this.getExistTick() > PREPARE_CD;
	}
	
	/**
	 * {@link #canBeImmuneToEnforce()}
	 */
	public boolean isRisingFromDirt() {
		return this.getAttackTime() >= PREPARE_CD - RISING_ANIM_CD && this.getAttackTime() <= PREPARE_CD;
	}
	
	/**
	 * {@link PotatoEntity#onImpact(net.minecraft.util.math.RayTraceResult)}
	 */
	public void setRisingFromDirt() {
		this.setExistTick(PREPARE_CD - RISING_ANIM_CD - 2);
	}
	
	@Override
	public void performAttack(LivingEntity target1) {
		if(! this.level.isClientSide) {
			final float range = 1.6F;
			EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach(target -> {
				target.hurt(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			});
			EntityUtil.playSound(this, SoundRegister.POTATO_MINE.get());
			for(int i = 1; i <= 5; ++ i) {
				EntityUtil.spawnParticle(this, 3);
				EntityUtil.spawnParticle(this, 4);
			}
			this.remove();
		}
	}
	
	@Override
	protected boolean canBeImmuneToEnforce() {
		return this.isMineReady() || this.isRisingFromDirt();
	}
	
	@Override
	public boolean canCheckDistance() {
		return this.isMineReady();
	}
	
	public float getAttackDamage(){
		return MathUtil.getProgressAverage(this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 120, 320);
	}
	
	/**
	 * use to change Signal String's color.
	 * {@link PotatoMineModel#setupAnim(PotatoMineEntity, float, float, float, float, float)}
	 */
	public int getSignChangeCD(){
		return this.isMineReady() ? 10 : 20;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.6f, 0.4f, false);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.POTATO_MINE;
	}

}
