package com.hungteen.pvzmod.entities.plants.magic;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.ai.target.PVZAIPlantGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityStrangeCat extends EntityPlantBase{

	public EntityStrangeCat(World worldIn) {
		super(worldIn);
	}

	@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 1.50F));
        this.targetTasks.addTask(10, new PVZAIPlantGlobalTarget(this,1,10));
    }
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(!this.world.isRemote) {
			if(this.getAttackTime()<this.getAttackCD()) {
				this.setAttackTime(this.getAttackTime()+1);
			}
			if(this.getAttackTime()>=this.getAttackCD()) {
				if(this.getAttackTarget()!=null) {
					EntityLivingBase target=this.getAttackTarget();
					if(this.getDistanceSq(target)<=EntityUtil.getAttackRange(target, this, 3)) {//在范围内
						if(!target.isDead&&target.getHealth()<=this.getAttackHP()) {//血量合适
							performAttack((EntityLiving) target);
							this.setAttackTime(0);
						}
					}
				}
			}
		}
		if(this.isPlantInSuperMode()) {
			if(!this.world.isRemote) {
				int len=30;
				AxisAlignedBB aabb=new AxisAlignedBB(posX+len, posY+len, posZ+len, posX-len, posY-len, posZ-len);
				int cnt=this.getSuperChance();
				for(Entity target:EntityUtil.getEntityAttackableTarget(this, aabb)) {
						if(((EntityLivingBase) target).getHealth()<=this.getAttackHP()) {
							cnt--;
							this.performAttack((EntityLiving) target);
							if(cnt==0) break;
						}
				}
			}
		}
	}
	
	private void performAttack(EntityLiving target)
	{
		target.attackEntityFrom(PVZDamageSource.causeMagicDamage(this, this), target.getHealth());
		EntityStrangeCat cat=new EntityStrangeCat(world);
		cat.setPosition(target.posX, target.posY, target.posZ);
		this.world.spawnEntity(cat);
	}
	
	@Override
	protected boolean checkWeak() {
		return false;
	}
	
	public int getAttackHP()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 200+now*100;
		}
		return 200;
	}
	
	public int getSuperChance()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 3;
		else if(lvl<=13) return 4;
		else if(lvl<=20) return 5;
		return 3;
	}
	
	public int getAttackCD()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 1200-now*100;
		}
		return 1200;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.STRANGE_CAT;
	}

}
