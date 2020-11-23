package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FlagZombieEntity extends NormalZombieEntity{

	public FlagZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}
	
	@Override
	public float getLife() {
		return 19;
	}
	
//	@Override
//	protected void zombieDropItem() {
//		if(this.rand.nextInt(PVZConfig.COMMON_CONFIG.EntitySettings.EntityDropItem.ZombieFlagDropChance.get())==0) {
//			this.entityDropItem(ItemRegister.ZOMBIE_FLAG.get());
//		}
//	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.FLAG_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.FLAG_ZOMBIE;
	}
}
