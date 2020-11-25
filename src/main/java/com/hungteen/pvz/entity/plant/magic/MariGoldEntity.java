package com.hungteen.pvz.entity.plant.magic;

import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.CoinEntity.CoinType;
import com.hungteen.pvz.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
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
		CoinEntity coin = EntityRegister.COIN.get().create(world);
		coin.setAmount(this.getRandomAmount());
		EntityUtil.onMobEntityRandomPosSpawn(world, coin, getPosition(), 3);
	}

	protected void genSpecCoin(CoinType type) {
		CoinEntity coin = EntityRegister.COIN.get().create(world);
		coin.setAmountByType(type);
		EntityUtil.onMobEntityRandomPosSpawn(world, coin, getPosition(), 3);
	}

	@Override
	public void genSuper() {
		for (int i = 0; i < this.getSuperGenCnt(); ++i) {
			this.genSomething();
		}
		if (this.isPlantInStage(1)) {
			this.genSpecCoin(CoinType.SILVER);
		} else if (this.isPlantInStage(2)) {
			this.genSpecCoin(CoinType.GOLD);
		} else {
			this.genSpecCoin(CoinType.GOLD);
			this.genSpecCoin(CoinType.GOLD);
		}
	}

	private int getRandomAmount() {
		int num = this.getRNG().nextInt(100);
		int silverNum = this.getSilverChance();
		int goldNum = this.getGoldChance();
		if (num < goldNum)
			return CoinType.GOLD.money;
		if (num < silverNum + goldNum)
			return CoinType.SILVER.money;
		return CoinType.COPPER.money;
	}

	public float getAveGenAmount() {
		int silverNum = this.getSilverChance();
		int goldNum = this.getGoldChance();
		int copperNum = 100 - silverNum - goldNum;
		return (silverNum * CoinType.SILVER.money + goldNum * CoinType.GOLD.money + copperNum * CoinType.COPPER.money) * 1.0f / 100;
	}
	
	public int getSilverChance() {
		int lvl = this.getPlantLvl();
		if (lvl <= 19)
			return 8 + 2 * lvl;
		return 50;
	}

	public int getGoldChance() {
		int lvl = this.getPlantLvl();
		if (lvl <= 20) {
			int now = (lvl - 1) / 5;
			return now + 1;
		}
		return 4;
	}

	public int getSuperGenCnt() {
		if (this.isPlantInStage(1))
			return 4;
		if (this.isPlantInStage(2))
			return 5;
		return 6;
	}

	@Override
	public int getGenCD() {
		return 1200;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, 1.6f);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.MARIGOLD;
	}

}
