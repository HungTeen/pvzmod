package com.hungteen.pvz.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class CherryBombEntity extends PlantBomberEntity{

	public CherryBombEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
//		System.out.println(this.world.isRemote);
		if(!this.world.isRemote) {
			float len = this.getExpRange();
			AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, len, len);
			EntityUtil.getAttackEntities(this, aabb).forEach((target) -> {
				target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			});
			EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
		}
		for(int i = 0; i < 5; ++ i) {
		    this.world.addParticle(ParticleRegister.RED_BOMB.get(), this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
		}
	}
	
	public float getExpRange() {
		return this.getPlantLvl() <= 10 ? 2f : 2.5f;
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 145 + 5 * lvl;
		}
		return 250;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.9f, 1f, false);
	}
	
	@Override
	public int getReadyTime() {
		return 30;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CHERRY_BOMB;
	}

}
