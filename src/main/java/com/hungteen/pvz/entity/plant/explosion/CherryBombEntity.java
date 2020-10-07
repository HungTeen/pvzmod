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
import net.minecraft.entity.LivingEntity;
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
			float len=(this.getPlantLvl()<=10)?2f:2.5f;
			AxisAlignedBB aabb=EntityUtil.getEntityAABB(this, len, len);
			for(LivingEntity entity:EntityUtil.getEntityAttackableTarget(this, aabb)) {
				 entity.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			}
			this.playSound(SoundRegister.CHERRY_BOMB.get(), 1, 1);
		}
		for(int i=1;i<=5;i++) {
//			System.out.println("222");
		    this.world.addParticle(ParticleRegister.RED_BOMB.get(), this.getPosX(),this.getPosY(),this.getPosZ(), 0,0,0);
		}
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 150+now*25;
		}
		return 150;
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
