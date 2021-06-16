package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PuffShroomEntity extends PlantShooterEntity {

	protected final double LENTH = 0.1d;
	
	public PuffShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 2, 0));
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target=this.getTarget();
		if(target==null) {
			//System.out.println("no target at all!");
			return ;
		}
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY()+this.getDimensions(getPose()).height*0.7f;
        double dis = MathHelper.sqrt(dx * dx + dz * dz);
        double tmp = this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
        SporeEntity spore = new SporeEntity(this.level, this);
        spore.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
        spore.shootPea(dx, target.getY() + target.getBbHeight() - y, dz, this.getBulletSpeed());      
        EntityUtil.playSound(this, SoundRegister.PUFF.get());
        this.level.addFreshEntity(spore);
	}

	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(first) {
			int x = this.getHelpRange();
			int cnt = 1;
			for(PuffShroomEntity shroom : level.getEntities(EntityRegister.PUFF_SHROOM.get(), EntityUtil.getEntityAABB(this, x, x), (shroom)-> {
				return ! EntityUtil.canTargetEntity(this, shroom);
			})) {
				if(shroom.canStartSuperMode()) {
				    shroom.startSuperMode(false);
				    ++ cnt;
				}
				shroom.setLiveTick(0);
			}
			PlayerEntity player = EntityUtil.getEntityOwner(level, this);
			if(player != null && player instanceof ServerPlayerEntity) {
				EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, cnt);
			}
		}
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 0.5f);
	}
	
	public int getMaxLiveTick() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 1485 + 15 * lvl;
		}
		return 1800;
	}
	
	public int getHelpRange() {
		if(this.isPlantInStage(1)) return 3;
		if(this.isPlantInStage(2)) return 4;
		return 6;
	}

	@Override
	public int getSuperTimeLength() {
		if(this.isPlantInStage(1)) return 40;
		if(this.isPlantInStage(2)) return 50;
		return 60;
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 29 + lvl;
		}
		return 50;
	}
	
	@Override
	public float getShootRange() {
		return 10;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.PUFF_SHROOM;
	}

}
