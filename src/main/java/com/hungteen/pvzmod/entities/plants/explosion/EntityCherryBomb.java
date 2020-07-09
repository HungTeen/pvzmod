package com.hungteen.pvzmod.entities.plants.explosion;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityBombBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityCherryBomb extends EntityBombBase{

	public EntityCherryBomb(World worldIn) {
		super(worldIn);
	}

	@Override
	public void startBoom() {
		if(!this.world.isRemote) {
			float len=(this.getPlantLvl()<=10)?1.5f:2.5f;
			AxisAlignedBB aabb=new AxisAlignedBB(this.posX+len, this.posY+len, this.posZ+len,this.posX-len, this.posY-len, this.posZ-len);
			for(Entity entity:EntityUtil.getEntityAttackableTarget(this, aabb)) {
				 entity.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			}
			this.playSound(SoundsHandler.CHERRY_BOMB, 4, 1);
		}
		for(int i=1;i<=5;i++) {
			Main.proxy.spawnParticle(PVZParticleType.DARK_RED_BOMB, this.posX, this.posY, this.posZ, 0, 0, 0);
		}
	}

	@Override
	public int getReadyTime() {
		return 30;
	}
	
	@Override
	public float getAttackDamage() {
		int lvl=this.getPlantLvl();
		if(lvl<=5) return 150;
		else if(lvl<=10) return 175;
		else if(lvl<=15) return 200;
		else if(lvl<=20) return 225;
		return 150;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CHERRY_BOMB;
	}
}
