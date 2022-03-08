package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.level.Level;

public class CherryBombEntity extends PlantBomberEntity{

	public CherryBombEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb(boolean server) {
		if(server) {
			int deathCnt = 0;
			final float range = getExplodeRange();
			final AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, range, range);
			for(Entity target : EntityUtil.getWholeTargetableEntities(this, aabb)) {
				target.hurt(PVZEntityDamageSource.explode(this), this.getExplodeDamage());
				if(! EntityUtil.isEntityValid(target)) {
					++ deathCnt;
				}
			}
			PVZPlantEntity.clearLadders(this, aabb);
			EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
			//trigger advancement.
			Player owner = EntityUtil.getEntityOwner(level, this);
			if(owner != null && owner instanceof ServerPlayer) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayer) owner, this, deathCnt);
			}
		} else {
			for(int i = 0; i < 5; ++ i) {
		        this.level.addParticle(ParticleRegister.RED_BOMB.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
	}
	
	/**
	 * explosion damage.
	 * {@link #startBomb(boolean server)}
	 */
	@Override
	public float getExplodeDamage(){
		return this.getSkillValue(SkillTypes.NORMAL_BOMB_DAMAGE);
	}

	@Override
	public float getExplodeRange(){
		return 4.5F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.9f, 1f, false);
	}
	
	@Override
	public int getReadyTime() {
		return 30;
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.CHERRY_BOMB;
	}

}
