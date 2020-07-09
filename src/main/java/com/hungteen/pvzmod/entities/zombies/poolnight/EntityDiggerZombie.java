package com.hungteen.pvzmod.entities.zombies.poolnight;

import com.hungteen.pvzmod.entities.ai.target.PVZAIZombieGlobalTarget;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDiggerZombie extends EntityZombieBase{

    public EntityDiggerZombie(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 2F);
    }

    @Override
    protected void initAITargetTask() {
    	this.targetTasks.addTask(2, new PVZAIZombieGlobalTarget(this, 100, 100){
    		@Override
    		protected boolean checkSenses(Entity entity) {
    			return true;
    		}
    	});
    }
    /**
     * Tries to move the entity towards the specified location.
     */
    public void move(MoverType type, double x, double y, double z)
    {
        super.move(type, x, y, z);
        this.doBlockCollisions();
    }

//    
//    @Override
//    public void onEntityUpdate() {
//    	super.onEntityUpdate();
//    	
//    }
    
    boolean checkIsInBlock()
    {
    	return this.world.getBlockState(new BlockPos(this)).getMaterial()!=Material.AIR;
    }
    
    @Override
    public boolean hasNoGravity() {
    	return this.checkIsInBlock();
    }
    
    @Override
    protected void onNormalZombieUpdate() {
    	super.onNormalZombieUpdate();
    	if(!this.world.isRemote&&this.getAttackTarget()!=null) {
    		EntityLivingBase target=this.getAttackTarget();
    	    Vec3d v3=new Vec3d(target.posX-this.posX,target.posY-this.posY,target.posZ-this.posZ).normalize();
    	    double speed=this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue()/2;
    	    this.motionX=v3.x*speed;
    	    if(target.posY-this.getEyeHeight()>=0.5f) {
    	        this.motionY=v3.y*speed;
    	    }
    	    else {
    	    	this.motionY=0;
    	    }
    	    this.motionZ=v3.z*speed;
    	    this.faceEntity(target, 30,30);
    	}
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
    }

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DIGGER_ZOMBIE;
	}

	@Override
	public float getLife() {
		return 65;
	}
}
