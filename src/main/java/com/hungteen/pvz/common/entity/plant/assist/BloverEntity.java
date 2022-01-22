package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BloverEntity extends PVZPlantEntity {

	public BloverEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide && this.getExistTick() == 5) {
			this.blow();
		}
	}
	
	public void blow() {
		if(! this.level.isClientSide) {
			final float len = this.getBlowRange();
			//deal damage.
			EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, len, len)).forEach(target -> {
				if(EntityUtil.isEntityInSky(target)) {
					target.hurt(PVZDamageSource.normal(this).setMustHurt(), this.getAttackDamage());
					final Vector3d speed = target.getDeltaMovement();
					final double lvl = this.getForceLevel() * 2.5F;
					final Vector3d delta = MathUtil.getHorizontalNormalizedVec(this.position(), target.position()).scale(lvl);
					target.setDeltaMovement(speed.x + delta.x, speed.y, speed.z + delta.z);
				}
			});
			//dispel fog.
//			level.getEntitiesOfClass(PlayerEntity.class, EntityUtil.getEntityAABB(this, len, len), player -> ! EntityUtil.canTargetEntity(this, player)).forEach((player) -> {
//				PlayerUtil.addResource(player, Resources.NO_FOG_TICK, 2400 * this.getForceLevel());
//			});
		}
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.ATTACK_DAMAGE, this.getAttackDamage()),
				Pair.of(PAZAlmanacs.ATTACK_RANGE, this.getBlowRange())
		));
	}

	@Override
	public boolean canPlantTarget(Entity entity) {
		if(entity instanceof BalloonZombieEntity) {
			return true;
		}
		return super.canPlantTarget(entity);
	}
	
	@Override
	public Optional<SoundEvent> getSpawnSound() {
		return Optional.ofNullable(SoundRegister.BLOVER.get());
	}
	
	public float getAttackDamage(){
		return this.getSkillValue(SkillTypes.BLOW_STRENGTH);
	}
	
	public int getForceLevel() {
		return 2;
	}

	public float getBlowRange(){
		return 30;
	}

	public int getReadyTime() {
		return 40;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5F, 1.5F);
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.BLOVER;
	}

}
