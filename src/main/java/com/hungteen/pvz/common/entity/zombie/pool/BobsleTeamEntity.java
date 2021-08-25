package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BobsleTeamEntity extends PVZZombieEntity implements IMultiPartEntity{

	public static final int PART_NUM = 2;
	private static final int MAX_OUT_SNOW_TICK = 100;
	private PVZZombiePartEntity[] parts = new PVZZombiePartEntity[PART_NUM];
	private int outSnowTick;
	
	public BobsleTeamEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setIsWholeBody();
		this.resetParts();
		this.setImmuneAllEffects();
		this.canBeMini = false;
		this.maxDeathTime = 1;
	}
	
	@Override
	public VariantType getVariantType() {
		return VariantType.NORMAL;
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_VERY_FAST);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
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
			if(! this.parts[i].isAddedToWorld()) {
				this.level.addFreshEntity(this.parts[i]);
			}
			float j = 2 * 3.14159f * this.yHeadRot / 360;
			float dis = this.getPartOffset(i);
			Vector3d pos = this.position();
			this.parts[i].yRotO = this.yRot;
			this.parts[i].xRotO = this.xRot;
			this.parts[i].moveTo(pos.x() - Math.sin(j) * dis, pos.y() + 0.05f, pos.z() + Math.cos(j) * dis, this.yRot, this.xRot);
			this.parts[i].setOwner(this);
		}
	}
	
	public PVZMultiPartEntity[] getMultiParts() {
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
	public void zombieTick() {
		super.zombieTick();
		if(!level.isClientSide) {
			if(this.isInWaterOrBubble() || (this.isOnGround() && !EntityUtil.isOnSnow(this) && !EntityUtil.isOnIce(this))) {
				++ this.outSnowTick;
				if(this.outSnowTick > MAX_OUT_SNOW_TICK) {
					this.onFallBody(DamageSource.DRY_OUT);
					this.onZombieRemove();
					this.remove();
				}
			} else {
				this.outSnowTick = 0;
			}
		}
	}
	
	@Override
	protected void onZombieRemove() {
		if(! level.isClientSide) {
			for(int i = 0; i < 4; ++ i) {
				BobsleZombieEntity zombie = EntityRegister.BOBSLE_ZOMBIE.get().create(level);
				ZombieUtil.copySummonZombieData(this, zombie);
				EntityUtil.onMobEntityRandomPosSpawn(level, zombie, this.blockPosition(), 2);
			}
		}
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setFriction(0.95F);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1.25f, 1.4f);
	}
	
	@Override
	public float getLife() {
		return 60;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("out_snow_tick")) {
			this.outSnowTick = compound.getInt("out_snow_tick");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("out_snow_tick", this.outSnowTick);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.BOBSLE_TEAM;
	}

	@Override
	public ZombieType getZombieType() {
		return PoolZombies.BOBSLE_TEAM;
	}

}
