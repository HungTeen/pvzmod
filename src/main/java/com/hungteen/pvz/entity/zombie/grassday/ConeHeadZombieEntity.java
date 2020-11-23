package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ConeHeadZombieEntity extends NormalZombieEntity{

	public ConeHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public float getLife() {
		return 60;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.PLASTIC_HIT.get();
	}
	
//	@Override
//	protected void zombieDropItem() {
//		if(this.rand.nextInt(PVZConfig.COMMON_CONFIG.EntitySettings.EntityDropItem.ConeHeadDropChance.get())==0) {
//			this.entityDropItem(ItemRegister.CONE_HEAD.get());
//		}
//	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.CONEHEAD_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.CONEHEAD_ZOMBIE;
	}
}
