package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PumpkinZombieEntity extends AbstractZombotanyEntity {

	public PumpkinZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_SLOW);
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
	public ZombieType getZombieType() {
		return Zombotanies.PUMPKIN_ZOMBIE;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.PUMPKIN_ZOMBIE;
	}

}
