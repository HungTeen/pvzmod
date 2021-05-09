package com.hungteen.pvz.entity.misc.bowling;

import com.hungteen.pvz.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

public class WallNutBowlingEntity extends AbstractBowlingEntity {

	public WallNutBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public WallNutBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity entity) {
		super(type, worldIn, entity);
	}

	@Override
	protected void dealDamageTo(Entity entity) {
		++ this.hitCount;
		for(int i = 1; i < this.hitCount; ++ i) {
			CoinEntity coin = EntityRegister.COIN.get().create(level);
			coin.setAmount(1);
			EntityUtil.onMobEntityRandomPosSpawn(level, coin, this.blockPosition(), 1);
		}
		entity.hurt(PVZDamageSource.causeBowlingDamage(this, this.getOwner()).setCount(hitCount), 30);
		EntityUtil.playSound(this, SoundRegister.BOWLING_HIT.get());
		PlayerEntity player = (PlayerEntity) this.getOwner();
		if(player != null && player instanceof ServerPlayerEntity) {
			EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, this, hitCount);
		}
	}

}
