package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.ai.BreakLilyPadGoal;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.ai.ZombieMeleeAttackGoal;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DolphinRiderEntity extends PVZZombieEntity{

	private static final float UP_SPEED = 0.05f;
	private static final int JUMP_CD = 200;
	private static final int JUMP_CHANCE = 10;
	private int jumpTick;
	
	public DolphinRiderEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		setPathPriority(PathNodeType.WATER, 0);
		this.jumpTick=100;
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WATER_FAST);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(0, new ZombieMeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(2, new BreakLilyPadGoal(BlockRegister.LILY_PAD.get(), this, 1, 3));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 80, 60));
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(!world.isRemote) {
			if(this.jumpTick>0) this.jumpTick--;
			if(this.jumpTick==0&&this.rand.nextInt(JUMP_CHANCE)==0) {//jump
				this.playSound(SoundRegister.DOLPHIN_JUMP.get(), 1f, 1f);
				double dxz=2,dyy=0.5d;
				this.setMotion((this.rand.nextFloat()-0.5)/dxz,dyy,(this.rand.nextFloat()-0.5)/dxz);
				this.setPosition(this.getPosX(), this.getPosY()+1, this.getPosZ());
				this.jumpTick=this.rand.nextInt(JUMP_CD)+JUMP_CD;
			}
		}
	}
	
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!world.isRemote) {//swim up
			if(this.isInWater() && this.shouldUp()){
				Vec3d v = this.getMotion();
				this.setMotion(v.getX(), UP_SPEED, v.getZ());
			}
		}
	}
	
	protected boolean shouldUp() {
		return this.getSubmergedHeight() > this.getEyeHeight()/3;
	}
	
	@Override
	public boolean checkCanZombieTarget(LivingEntity target) {
		if(super.checkCanZombieTarget(target)) {
			return !this.isInWater()||target.isInWater();
		}
		return false;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.7f, 1.6f);
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.9f;
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	public float getLife() {
		return 60;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.DOLPHIN_RIDER;
	}

}
