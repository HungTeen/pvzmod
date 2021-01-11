package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BobsleTeamEntity extends PVZZombieEntity implements IMultiPartEntity{

	public static final int PART_NUM = 2;
	private PVZZombiePartEntity[] parts = new PVZZombiePartEntity[PART_NUM];
	
	public BobsleTeamEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void onZombieInitialSpawn() {
		super.onZombieInitialSpawn();
		resetParts();
	}
	
	@Override
	public Type getZombieType() {
		return Type.NORMAL;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.VERY_FAST);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
	}
	
	@Override
	public void resetParts() {
		removeParts();
		for(int i = 0; i < PART_NUM; i++) {
			this.parts[i] = new PVZZombiePartEntity(this, 1f, 1.5f);
			this.parts[i].setOwner(this);
		}
	}
	
	@Override
	public void removeParts() {
		for(int i = 0; i < PART_NUM; i++) {
			if(this.parts[i] == null) {
				continue;
			}
			this.parts[i].remove();
			this.parts[i] = null;
		}
	}
	
	@Override
	public void updateParts() {
		for(int i = 0; i < PART_NUM; i++) {
			if(this.parts[i] == null) {
				continue;
			}
			if(!this.parts[i].shouldContinuePersisting()) {
				this.world.addEntity(this.parts[i]);
			}
			float j = 2 * 3.14159f * this.rotationYawHead / 360;
			float dis = this.getPartOffset(i);
			Vec3d pos = this.getPositionVec();
			this.parts[i].prevRotationYaw = this.rotationYaw;
			this.parts[i].prevRotationPitch = this.rotationPitch;
			this.parts[i].setLocationAndAngles(pos.getX() - Math.sin(j) * dis, pos.getY() + 0.05f, pos.getZ() + Math.cos(j) * dis, this.rotationYaw, this.rotationPitch);
			this.parts[i].setOwner(this);
		}
	}
	
	public PVZMultiPartEntity[] getParts() {
		return this.parts;
	}
	
	public float getPartOffset(int num) {
		if(num == 0) {
			return -1.5f;
		}else if(num == 1) {
			return -2.5f;
		}
		return 0;
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!world.isRemote) {
			if(!EntityUtil.isOnSnow(this)) {
				this.attackEntityFrom(DamageSource.DROWN, 10);
			}
		}
	}
	
	@Override
	protected void onZombieRemove() {
		super.onZombieRemove();
		if(!world.isRemote) {
			for(int i=0;i<4;i++) {
				BobsleZombieEntity zombie = EntityRegister.BOBSLE_ZOMBIE.get().create(world);
				zombie.setPosition(this.getPosX()+(this.getRNG().nextFloat()-0.5)*4, this.getPosY()+0.5f, this.getPosZ()+(this.getRNG().nextFloat()-0.5)*4);
				world.addEntity(zombie);
			}
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1.25f, 1.4f);
	}
	
	@Override
	public float getLife() {
		return 60;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.BOBSLE_TEAM;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BOBSLE_TEAM;
	}

}
