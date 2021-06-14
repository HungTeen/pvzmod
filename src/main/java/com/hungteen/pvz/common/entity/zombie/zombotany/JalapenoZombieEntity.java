package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class JalapenoZombieEntity extends AbstractZombotanyEntity {

	public JalapenoZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_FAST);
	}
	
	@Override
	public void die(DamageSource cause) {
		if(! EntityUtil.isEntityCold(this) && ! EntityUtil.isEntityFrozen(this)) {
			this.startBomb();
		}
		super.die(cause);
	}
	
	public void startBomb() {
		int range = this.getFireRange();
		for(int i = - range; i <= range; ++ i) {
			JalapenoEntity.clearSnowAndSpawnFlame(this, i, 0);
			JalapenoEntity.clearSnowAndSpawnFlame(this, 0, i);
		}
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.JALAPENO.get());
		}
		JalapenoEntity.fireMob(this, range, 0.5f);
		JalapenoEntity.fireMob(this, 0.5f, range);
		if(this.isCharmed()) {
			JalapenoEntity.killIceBall(this);
		}
	}
	
	public int getFireRange() {
		return 20;
	}

	@Override
	public float getLife() {
		return 44;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.JALAPENO_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.JALAPENO_ZOMBIE;
	}
	
}
