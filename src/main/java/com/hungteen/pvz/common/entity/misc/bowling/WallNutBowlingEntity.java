package com.hungteen.pvz.common.entity.misc.bowling;

import com.hungteen.pvz.common.advancement.trigger.EntityEffectAmountTrigger;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WallNutBowlingEntity extends AbstractBowlingEntity {

	public WallNutBowlingEntity(EntityType<?> type, Level worldIn) {
		super(type, worldIn);
	}
	
	public WallNutBowlingEntity(EntityType<?> type, Level worldIn, Player entity) {
		super(type, worldIn, entity);
	}

	@Override
	protected void dealDamageTo(Entity entity) {
		++ this.hitCount;
		for(int i = 1; i < this.hitCount; ++ i) {
			CoinEntity coin = EntityRegister.COIN.get().create(level);
			coin.setAmount(1);
			EntityUtil.onEntityRandomPosSpawn(level, coin, this.blockPosition(), 1);
		}
		entity.hurt(PVZEntityDamageSource.normal(this, this.getOwner()).setCount(hitCount), 30);
		EntityUtil.playSound(this, SoundRegister.BOWLING_HIT.get());
		Player player = (Player) this.getOwner();
		if(player != null && player instanceof ServerPlayer) {
			EntityEffectAmountTrigger.INSTANCE.trigger((ServerPlayer) player, this, hitCount);
		}
	}

}
