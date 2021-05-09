package com.hungteen.pvz.entity.plant.explosion;

import java.util.List;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class ExplodeONutEntity extends WallNutEntity {

	public ExplodeONutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		if(! this.level.isClientSide) {
			this.explode(this);
		} else {
			for(int i = 0; i < 5; ++ i) {
		        this.level.addParticle(ParticleRegister.RED_BOMB.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
	}
	
	private void explode(Entity entity) {
		float len = 2F;
		AxisAlignedBB aabb = EntityUtil.getEntityAABB(entity, len, len);
		EntityUtil.getAttackEntities(this, aabb).forEach((target) -> {
			target.hurt(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
		});
		EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.explode(this);
		EntityUtil.spawnParticle(this, 0);
		if(this.getExtraAttackChance() > 0) {
			List<Entity> list = EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, 40, 40));
			if(list.isEmpty()) return ;
			for(int i = 0; i < this.getExtraAttackChance(); ++ i) {
//				System.out.println(i);
				Entity target = list.get(this.getRandom().nextInt(list.size()));
				this.explode(target);
				EntityUtil.spawnParticle(target, 0);
			}
		}
	}
	
	public int getExtraAttackChance() {
		if(this.isPlantInStage(1)) return 0;
		if(this.isPlantInStage(2)) return 1;
		return 2;
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			return 140 + 5 * lvl;
		}
		return 240;
	}
	
	@Override
	public float getSuperLife() {
		return 0;
	}

	@Override
	public Plants getUpgradePlantType() {
		return null;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.EXPLODE_O_NUT;
	}

}
