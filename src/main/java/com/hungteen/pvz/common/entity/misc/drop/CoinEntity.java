package com.hungteen.pvz.common.entity.misc.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoinEntity extends DropEntity {

	public static final int COIN_TYPES = 3;

	public CoinEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmountByType(this.getRandomType());
	}

	@Override
	protected void onDropped() {
		super.onDropped();
		if (!level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.COIN_DROP.get());
		}
	}

	@Override
	public void onCollectedByPlayer(PlayerEntity player) {
		if (! this.level.isClientSide) {
			PlayerUtil.addResource(player, Resources.MONEY, this.getAmount());
			PlayerUtil.playClientSound(player, SoundRegister.COIN_PICK.get());
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		// 1000 <-> 1 1 <-> 0.5
		final float t = (float) Math.log10(this.getAmount());// 0 1 2 3
		final float w = t * 0.18f + 0.4f;
		return new EntitySize(w, w, false); // max (0.4w,0.4h) min(0.9w,0.9h)
	}

	/**
	 * spawn coin by type.
	 */
	public static void spawnCoin(World world, BlockPos pos, CoinType type) {
		final CoinEntity coin = EntityRegister.COIN.get().create(world);
		coin.setAmount(type.money);
		EntityUtil.onEntitySpawn(world, coin, pos);
	}

	public void setAmountByType(CoinType type) {
		this.setAmount(type.money);
	}

	protected CoinType getRandomType() {
		return CoinType.values()[this.random.nextInt(COIN_TYPES)];
	}

	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.CoinLiveTick.get();
	}

	public static enum CoinType {
		COPPER(1), SILVER(10), GOLD(100);

		public final int money;

		private CoinType(int money) {
			this.money = money;
		}
	}

}