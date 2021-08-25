package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.BucketHeadZombieModel;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BucketHeadZombieEntity extends NormalZombieEntity implements IHasMetal{

	public BucketHeadZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.hasDirectDefence = true;
		this.increaseMetal();
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public float getExtraLife() {
		return 110;
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
		this.setDefenceLife(this.getExtraLife());
	}
	
	/**
	 * check the visibility of buckethead model.
	 * {@link BucketHeadZombieModel#updateFreeParts(BucketHeadZombieEntity)}
	 */
	public boolean hasBucketHead(int stage) {
		final float percent = this.getDefenceLife() / this.getExtraLife();
		if(stage == 3) {
			return percent > 2.0f / 3;
		} else if(stage == 2) {
			return percent > 1.0f / 3;
		} else if(stage == 1) {
			return percent > 0;
		}
		return false;
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.BUCKET_HEAD;
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return hasMetal() ? SoundRegister.METAL_HIT.get() : super.getHurtSound(damageSourceIn);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.BUCKETHEAD_ZOMBIE;
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.BUCKETHEAD_ZOMBIE;
	}

}
