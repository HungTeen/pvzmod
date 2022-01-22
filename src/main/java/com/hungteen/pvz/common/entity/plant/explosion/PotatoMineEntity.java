package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.common.entity.bullet.itembullet.PotatoEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantCloserEntity;
import com.hungteen.pvz.common.entity.zombie.pool.DiggerZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class PotatoMineEntity extends PlantCloserEntity{

	public static final int RISING_ANIM_CD = 20;
	
	public PotatoMineEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! this.level.isClientSide) {
			if(this.getExistTick() == this.getPrepareCD() - RISING_ANIM_CD + 1) {
				EntityUtil.playSound(this, SoundRegister.DIRT_RISE.get());
			}
		} else {
			if(this.isRisingFromDirt()) {
				for(int i = 0; i < 1; ++ i) {
					Vector3d offset = new Vector3d(MathUtil.getRandomFloat(getRandom()), 0, MathUtil.getRandomFloat(getRandom())).normalize();
					WorldUtil.spawnRandomSpeedParticle(level, ParticleRegister.DIRT_BURST_OUT.get(), this.position().add(offset), MathUtil.getRandomFloat(getRandom()) / 8, 0.06F);
				}
			}
		}
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.EXPLODE_DAMAGE, this.getExplodeDamage()),
				Pair.of(PAZAlmanacs.EXPLODE_DAMAGE, this.getExplodeRange()),
				Pair.of(PAZAlmanacs.PREPARE_CD, this.getPrepareCD())
		));
	}

	@Override
	public void performAttack(LivingEntity target1) {
		if(! this.level.isClientSide) {
			final float range = 1.6F;
			final AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, range, range);
			EntityUtil.getWholeTargetableEntities(this, aabb).forEach(target -> {
				target.hurt(PVZDamageSource.explode(this), this.getExplodeDamage());
			});
			PVZPlantEntity.clearLadders(this, aabb);
			EntityUtil.playSound(this, SoundRegister.POTATO_MINE.get());
			for(int i = 1; i <= 10; ++ i) {
				EntityUtil.spawnParticle(this, 3);
				EntityUtil.spawnParticle(this, 4);
			}
			this.remove();
		}
	}
	
	@Override
	public boolean canPlantTarget(Entity target) {
		if(target instanceof DiggerZombieEntity) {
			return true;
		}
		return super.canPlantTarget(target);
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
		    final float dy = 0.5F;
		    final float dz = MathUtil.getRandomFloat(getRandom()) * i / 2;
		    potato.shoot(dx, dy, dz);
		    potato.summonByOwner(this);
		    this.level.addFreshEntity(potato);
		}
	}
	
	public int getShootNum() {
		return 3;
	}

	public float getExplodeRange(){
		return 1.8F;
	}

	public float getExplodeDamage(){
		return this.getSkillValue(SkillTypes.NORMAL_BOMB_DAMAGE);
	}

	public int getPrepareCD(){
		return (int) this.getSkillValue(SkillTypes.MINE_FAST_PREPARE);
	}

	/**
	 */
	public boolean isMineReady() {
		return this.getExistTick() > this.getPrepareCD();
	}
	
	/**
	 */
	public boolean isRisingFromDirt() {
		return this.getExistTick() >= this.getPrepareCD() - RISING_ANIM_CD && this.getExistTick() <= this.getPrepareCD();
	}
	
	/**
	 * {@link PotatoEntity#onImpact(net.minecraft.util.math.RayTraceResult)}
	 */
	public void setRisingFromDirt() {
		this.setExistTick(this.getPrepareCD() - RISING_ANIM_CD - 2);
	}
	
	@Override
	protected boolean canBeImmuneToEnforce(Entity entity) {
		return super.canBeImmuneToEnforce(entity) && (this.isMineReady() || this.isRisingFromDirt());
	}
	
	@Override
	public boolean canCheckDistance() {
		return this.isMineReady();
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
	public IPlantType getPlantType() {
		return PVZPlants.POTATO_MINE;
	}

}
