package com.hungteen.pvz.entity.zombie.zombotany;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PumpkinZombieEntity extends AbstractZombotanyEntity {

	public PumpkinZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}
	
	@Override
	public boolean doHurtTarget(Entity entityIn) {
		if(!level.isClientSide) {
			this.heal(20);
		}
		return super.doHurtTarget(entityIn);
	}
	
	@Override
	public float getLife() {
		return 180;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.PUMPKIN_ZOMBIE;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.PUMPKIN_ZOMBIE;
	}

}
