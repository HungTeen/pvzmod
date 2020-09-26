package com.hungteen.pvz.entity.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.util.math.AxisAlignedBB;

public class PVZNearestTargetGoal extends TargetGoal{

	protected final PVZNearestTargetGoal.Sorter sorter;
	private final int targetChance;
    private final float upperHeight;
    private final float lowerHeight;
    private final float width;
    
    public PVZNearestTargetGoal(MobEntity mobIn, boolean checkSight, float w, float h) {
		this(mobIn, checkSight, 5, w, h);
	}
    
	public PVZNearestTargetGoal(MobEntity mobIn, boolean checkSight, int chance, float w, float h) {
		this(mobIn, checkSight, chance, w, h, h);
	}
	
	public PVZNearestTargetGoal(MobEntity mobIn, boolean checkSight, int chance, float w, float h1, float h2) {
		super(mobIn, checkSight);
		this.targetChance=chance;
		this.width=w;
		this.upperHeight=h1;
		this.lowerHeight=h2;
		this.sorter=new PVZNearestTargetGoal.Sorter(goalOwner);
		this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.targetChance > 0 && this.goalOwner.getRNG().nextInt(this.targetChance) != 0){
            return false;
        }
		List<Entity> list = this.goalOwner.world.getEntitiesWithinAABB(Entity.class, this.getAABB());
		List<Entity> list1 =new ArrayList<Entity>();
		for(Entity entity:list) {
			if(EntityUtil.checkCanEntityTarget(this.goalOwner,entity)) {
				if(!this.shouldCheckSight||checkSenses(entity)) {
					if(checkPlant(entity)) {
				        list1.add(entity);
					}
				}
			}
		}
		if(list1.isEmpty()) {
			return false;
		}
		Collections.sort(list1, this.sorter);
        this.target= (LivingEntity) list1.get(0);
        return true;
	}
	
	@Override
	public void startExecuting() {
		this.goalOwner.setAttackTarget(this.target);
	}
	
	protected boolean checkSenses(Entity entity){
		return this.goalOwner.getEntitySenses().canSee(entity);
	}
	
	protected boolean checkPlant(Entity entity){
		if(this.goalOwner instanceof IShooter) {
			return ((IShooter) this.goalOwner).checkY(entity);
		}
		return true;
	}
	
	private AxisAlignedBB getAABB(){
		return new AxisAlignedBB(this.goalOwner.getPosX()+width, this.goalOwner.getPosY() + this.upperHeight, this.goalOwner.getPosZ()+width, this.goalOwner.getPosX()-width, this.goalOwner.getPosY() - this.lowerHeight, this.goalOwner.getPosZ()-width);
	}
	
	public static class Sorter implements Comparator<Entity>{
        private final Entity entity;

        public Sorter(Entity entityIn){
            this.entity = entityIn;
        }
        
        public int compare(Entity p_compare_1_, Entity p_compare_2_){
            double d0 = this.entity.getDistanceSq(p_compare_1_);
            double d1 = this.entity.getDistanceSq(p_compare_2_);
            if (d0 < d1){
                return -1;
            }
            else{
                return d0 > d1 ? 1 : 0;
            }
        }
    }
}
