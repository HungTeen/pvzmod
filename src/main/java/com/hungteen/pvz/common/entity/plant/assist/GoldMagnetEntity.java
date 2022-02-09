package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity;
import com.hungteen.pvz.common.entity.misc.drop.DropEntity;
import com.hungteen.pvz.common.entity.misc.drop.JewelEntity;
import com.hungteen.pvz.common.entity.misc.drop.DropEntity.DropStates;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoldMagnetEntity extends PVZPlantEntity {

	private final Set<DropEntity> coinSet = new HashSet<>();
	private static final int SEARCH_CD = 60;

	public GoldMagnetEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (! level.isClientSide) {
			this.tickCoinSet();
			this.setAttackTime(this.coinSet.size());
		}
	}

	private void tickCoinSet() {
		// maintain the set. keep absorbed exist coin.
		Set<DropEntity> tmp = new HashSet<>();
		this.coinSet.forEach(coin -> {
			if (EntityUtil.isEntityValid(coin) && coin.getDropState() == DropStates.ABSORB) {
				tmp.add(coin);
			}
		});
		this.coinSet.clear();
		this.coinSet.addAll(tmp);
		tmp.clear();
		// if it can not work, release the coins.
		if (! this.checkCanWorkNow()) {
			this.coinSet.forEach(coin -> {
				coin.setDropState(DropStates.NORMAL);
			});
			this.coinSet.clear();
			return;
		}
		// find new coin regularly. 
		if (this.getExistTick() % SEARCH_CD == 0) {
			final float range = this.getSearchRange();
			level.getEntitiesOfClass(DropEntity.class, EntityUtil.getEntityAABB(this, range, range), drop -> {
				return (drop instanceof CoinEntity || drop instanceof JewelEntity) && drop.getDropState() == DropStates.NORMAL && ! this.coinSet.contains(drop);
			}).forEach(coin -> {
				coin.setDropState(DropStates.ABSORB);
				this.coinSet.add(coin);
			});
			if(! this.coinSet.isEmpty()) {
				EntityUtil.playSound(this, SoundRegister.MAGNET.get());
			}
		}
		// absorb all coins in the set.
		this.coinSet.forEach(coin -> {
			final double speed = 0.35D;
			Vector3d now = new Vector3d(this.getX(), this.getY() + this.getBbHeight(), this.getZ());
			Vector3d vec = now.subtract(coin.position());
			if (vec.length() <= 1) {
				this.onCollectCoin(coin);
			} else {
				coin.setDeltaMovement(vec.normalize().scale(speed));
			}
		});
	}
	
	/**
	 * {@link #tickCoinSet()}
	 */
	protected void onCollectCoin(DropEntity drop) {
		this.getOwnerPlayer().ifPresent(player -> {
			if(drop instanceof CoinEntity) {
				PlayerUtil.addResource(player, Resources.MONEY, drop.getAmount());
				EntityUtil.playSound(this, SoundRegister.COIN_PICK.get());
			} else if(drop instanceof JewelEntity) {
				PlayerUtil.addResource(player, Resources.GEM_NUM, drop.getAmount());
				EntityUtil.playSound(this, SoundRegister.JEWEL_PICK.get());
			}
		});
		drop.remove();
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.add(Pair.of(PAZAlmanacs.WORK_RANGE, this.getSearchRange()));
	}

	public int getSearchRange() {
		return 8;
	}

	protected boolean checkCanWorkNow() {
		return this.getOwnerPlayer().isPresent();
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 1.3f);
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.GOLD_MAGNET;
	}

}
