package com.hungteen.pvz.entity.misc.bowling;

import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GiantNutBowlingEntity extends AbstractBowlingEntity {

	public GiantNutBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public GiantNutBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity entity) {
		super(type, worldIn, entity);
	}

	@Override
	protected void dealDamageTo(Entity entity) {
		entity.attackEntityFrom(PVZDamageSource.causeBowlingDamage(this, this.getOwner()), 200);
		EntityUtil.playSound(this, SoundRegister.BOWLING_HIT.get());
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(2.5F, 2.5F);
	}
	
	@Override
	protected void changeDiretion() {
	}

}
