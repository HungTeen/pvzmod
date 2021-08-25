package com.hungteen.pvz.common.entity.zombie.other;

import java.util.HashSet;
import java.util.Set;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.drop.DropEntity.DropStates;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.OtherZombies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class RaZombieEntity extends PVZZombieEntity {

	public static final int ABSORB_TICK = 40;
	private final Set<SunEntity> sunSet = new HashSet<>();
	private final int MaxSearchTick = 400;
	private final int MinSearchTick = 100;
	private int searchTick = 0;
	private int sunAmount = 0;
	private final double MaxSearchRange = 20;

	public RaZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if (!level.isClientSide) {
			this.tickSunSet();
			if (this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			}
			if (this.searchTick > 0) {
				--this.searchTick;
			}
			if (this.searchTick <= 0) {
				this.searchTick = this.getRandom().nextInt(this.MaxSearchTick - this.MinSearchTick + 1)
						+ this.MinSearchTick;
				this.setAttackTime(ABSORB_TICK);
				EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
				// search new sun.
				level.getEntitiesOfClass(SunEntity.class, MathUtil.getAABBWithPos(blockPosition(), MaxSearchRange),
						(sun) -> {
							return sun.getDropState() != DropStates.STEAL && !this.sunSet.contains(sun);
						}).forEach((sun) -> {
							sun.setDropState(DropStates.STEAL);
							this.sunSet.add(sun);
						});
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void tickSunSet() {
		// maintain the set.
		Set<SunEntity> tmp = new HashSet<>();
		this.sunSet.forEach((sun) -> {
			if (sun != null && !sun.removed && sun.getDropState() == DropStates.STEAL) {
				tmp.add(sun);
			}
		});
		this.sunSet.clear();
		this.sunSet.addAll(tmp);
		tmp.clear();
		// if the set is full, then release the sun.
		if (! this.checkCanWorkNow()) {
			this.sunSet.forEach((sun) -> {
				sun.setDropState(DropStates.NORMAL);
			});
			this.sunSet.clear();
			return;
		}
		// absorb suns in the set.
		this.sunSet.forEach((sun) -> {
			double speed = 0.3D;
			Vector3d now = this.position().add(0, getEyeHeight(), 0);
			Vector3d vec = now.subtract(sun.position());
			if (vec.length() <= 2) {
				this.sunAmount += sun.getAmount();
				sun.remove();
			} else {
				sun.setDeltaMovement(vec.normalize().scale(speed));
			}
		});
	}
	
	@Override
	protected void onZombieRemove() {
		super.onZombieRemove();
		if(! level.isClientSide) {
			SunEntity.spawnSunsByAmount(level, blockPosition(), sunAmount);
			this.sunAmount = 0;
		}
	}
	
	public boolean checkCanWorkNow() {
		return this.getAttackTime() > 0;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("zombie_search_tick")) {
			this.searchTick = compound.getInt("zombie_search_tick");
		}
		if(compound.contains("zombie_sun_amount")) {
			this.sunAmount = compound.getInt("zombie_sun_amount");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("zombie_search_tick", this.searchTick);
		compound.putInt("zombie_sun_amount", this.sunAmount);
	}

	@Override
	public float getLife() {
		return 36;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.RA_ZOMBIE;
	}
	
	@Override
	public ZombieType getZombieType() {
		return OtherZombies.RA_ZOMBIE;
	}

}
