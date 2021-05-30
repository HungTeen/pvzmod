package com.hungteen.pvz.common.entity.misc.bowling;

import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
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
		if(this.hitEntities == null) {
			this.hitEntities = new IntOpenHashSet();
		}
		if(this.hitEntities != null && ! this.hitEntities.contains(entity.getId())) {
			entity.hurt(PVZDamageSource.causeBowlingDamage(this, this.getOwner()), 200);
			this.hitEntities.add(entity.getId());
		    EntityUtil.playSound(this, SoundRegister.BOWLING_HIT.get());
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2.5F, 2.5F);
	}
	
	@Override
	protected void changeDiretion() {
	}

}
