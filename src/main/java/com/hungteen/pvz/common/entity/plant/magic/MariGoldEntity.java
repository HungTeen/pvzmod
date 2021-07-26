package com.hungteen.pvz.common.entity.plant.magic;

import com.hungteen.pvz.common.entity.drop.CoinEntity;
import com.hungteen.pvz.common.entity.drop.CoinEntity.CoinType;
import com.hungteen.pvz.common.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

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
		EntityUtil.onMobEntityRandomPosSpawn(level, coin, blockPosition(), 3);
	}

	protected void genSpecCoin(CoinType type) {
		CoinEntity coin = EntityRegister.COIN.get().create(level);
		coin.setAmountByType(type);
		EntityUtil.onMobEntityRandomPosSpawn(level, coin, blockPosition(), 3);
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

	public float getAveGenAmount() {
		int silverNum = this.getSilverChance();
		int goldNum = this.getGoldChance();
		int copperNum = 100 - silverNum - goldNum;
		return (silverNum * CoinType.SILVER.money + goldNum * CoinType.GOLD.money + copperNum * CoinType.COPPER.money) * 1.0f / 100;
	}
	
	public int getSilverChance() {
		return PlantUtil.getPlantAverageProgress(this, 10, 50);
	}

	public int getGoldChance() {
		final int lvl = this.getPlantLvl();
		if (lvl <= 20) {
			final int now = (lvl - 1) / 2;
			return now + 1;
		}
		return 10;
	}

	public int getSuperGenCnt() {
		return this.getThreeStage(3, 5, 7);
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
	public Plants getPlantEnumName() {
		return Plants.MARIGOLD;
	}

}
