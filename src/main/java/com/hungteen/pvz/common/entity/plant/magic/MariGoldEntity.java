package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity.CoinType;
import com.hungteen.pvz.common.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class MariGoldEntity extends PlantProducerEntity {

	public MariGoldEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void genSomething() {
		CoinEntity coin = EntityRegister.COIN.get().create(level);
		coin.setAmount(this.getRandomAmount());
		EntityUtil.onEntityRandomPosSpawn(level, coin, blockPosition(), 3);
	}

	protected void genSpecCoin(CoinType type) {
		CoinEntity coin = EntityRegister.COIN.get().create(level);
		coin.setAmountByType(type);
		EntityUtil.onEntityRandomPosSpawn(level, coin, blockPosition(), 3);
	}

	@Override
	public void genSuper() {
		for (int i = 0; i < this.getSuperGenCnt(); ++i) {
			this.genSomething();
		}
		this.genSpecCoin(CoinType.GOLD);
	}

	private int getRandomAmount() {
		final int num = this.getRandom().nextInt(100);
		final int silverNum = this.getSilverChance();
		final int goldNum = this.getGoldChance();
		if (num < goldNum) {
			return CoinType.GOLD.money;
		}
		if (num < silverNum + goldNum) {
			return CoinType.SILVER.money;
		}
		return CoinType.COPPER.money;
	}
	
	public int getSilverChance() {
		return 10;
	}

	public int getGoldChance() {
		return 1;
	}

	public int getSuperGenCnt() {
		return 5;
	}

	@Override
	public int getGenCD() {
		return 1200;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.6f);
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.MARIGOLD;
	}

}
