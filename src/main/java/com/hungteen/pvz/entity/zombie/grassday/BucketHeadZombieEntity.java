package com.hungteen.pvz.entity.zombie.grassday;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BucketHeadZombieEntity extends NormalZombieEntity implements IHasMetal{

	public static final float BUCKET_HEALTH = 110;
	
	public BucketHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void onZombieInitialSpawn() {
		super.onZombieInitialSpawn();
		this.hasDirectDefence = true;
		this.increaseMetal();
	}

	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public boolean hasMetal() {
		return this.getDefenceLife() > 0;
	}

	@Override
	public void decreaseMetal() {
		this.setDefenceLife(0);
	}

	@Override
	public void increaseMetal() {
		this.setDefenceLife(BUCKET_HEALTH);
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.BUCKET_HEAD;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return hasMetal() ? SoundRegister.METAL_HIT.get() : super.getHurtSound(damageSourceIn);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.BUCKETHEAD_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BUCKETHEAD_ZOMBIE;
	}

}
