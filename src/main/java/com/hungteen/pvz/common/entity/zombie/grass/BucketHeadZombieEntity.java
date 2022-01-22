package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.BucketHeadZombieModel;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BucketHeadZombieEntity extends NormalZombieEntity implements IHasMetal{

	public BucketHeadZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.increaseMetal();
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public float getInnerLife() {
		return 110;
	}
	
	@Override
	public boolean hasMetal() {
		return this.getInnerDefenceLife() > 0;
	}

	@Override
	public void decreaseMetal() {
		this.setInnerDefenceLife(0);
	}

	@Override
	public void increaseMetal() {
		this.setInnerDefenceLife(this.getInnerLife());
	}
	
	/**
	 * check the visibility of buckethead model.
	 * {@link BucketHeadZombieModel#updateFreeParts(BucketHeadZombieEntity)}
	 */
	public boolean hasBucketHead(int stage) {
		final double percent = this.getInnerDefenceLife() / this.getInnerLife();
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
	public ZombieType getZombieType() {
		return GrassZombies.BUCKETHEAD_ZOMBIE;
	}

}
