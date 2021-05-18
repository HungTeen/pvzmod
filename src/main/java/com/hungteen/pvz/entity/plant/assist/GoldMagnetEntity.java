package com.hungteen.pvz.entity.plant.assist;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.DropEntity;
import com.hungteen.pvz.entity.drop.DropEntity.DropStates;
import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class GoldMagnetEntity extends PVZPlantEntity {

	private final Set<DropEntity> coinSet = new HashSet<>();
	private final int MaxSearchTick = 60;
	private Optional<PlayerEntity> player = Optional.empty();

	public GoldMagnetEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (! level.isClientSide) {
			if(this.getOwnerUUID().isPresent()) {
			    this.player = Optional.ofNullable(this.level.getPlayerByUUID(this.getOwnerUUID().get()));
			    this.tickCoinSet();
			}
			this.setAttackTime(this.coinSet.size());
		}
	}

	@SuppressWarnings("deprecation")
	private void tickCoinSet() {
		// maintain the set.
		Set<DropEntity> tmp = new HashSet<>();
		this.coinSet.forEach((coin) -> {
			if (coin != null && !coin.removed && coin.getDropState() == DropStates.ABSORB) {
				tmp.add(coin);
			}
		});
		this.coinSet.clear();
		this.coinSet.addAll(tmp);
		tmp.clear();
		// if the set is full, then release the sun.
		if (! this.checkCanWorkNow()) {
			this.coinSet.forEach((coin) -> {
				coin.setDropState(DropStates.NORMAL);
			});
			this.coinSet.clear();
			return;
		}
		// find new sun. 
		if (this.tickCount % this.MaxSearchTick == 0) {
			float range = this.getSearchRange();
			level.getEntitiesOfClass(DropEntity.class, EntityUtil.getEntityAABB(this, range, range), (drop) -> {
				return (drop instanceof CoinEntity || drop instanceof JewelEntity) && drop.getDropState() == DropStates.NORMAL && ! this.coinSet.contains(drop);
			}).forEach((coin) -> {
				coin.setDropState(DropStates.ABSORB);
				this.coinSet.add(coin);
			});
			if(! this.coinSet.isEmpty()) {
				EntityUtil.playSound(this, SoundRegister.MAGNET.get());
			}
		}
		// absorb suns in the set.
		this.coinSet.forEach((coin) -> {
			double speed = 0.3D;
			Vector3d now = new Vector3d(this.getX(), this.getY() + this.getBbHeight(), this.getZ());
			Vector3d vec = now.subtract(coin.position());
			if (vec.length() <= 1) {
				this.onCollectCoin(coin);
			} else {
				coin.setDeltaMovement(vec.normalize().scale(speed));
			}
		});
	}
	
	private void onCollectCoin(DropEntity drop) {
		if(! this.player.isPresent()) return ;
		if(drop instanceof CoinEntity) {
			PlayerUtil.addPlayerStats(this.player.get(), Resources.MONEY, drop.getAmount());
			EntityUtil.playSound(this, SoundRegister.COIN_PICK.get());
		} else if(drop instanceof JewelEntity) {
			PlayerUtil.addPlayerStats(this.player.get(), Resources.GEM_NUM, drop.getAmount());
			EntityUtil.playSound(this, SoundRegister.JEWEL_PICK.get());
		}
		drop.remove();
	}

	public int getSearchRange() {
		if (this.isPlantInStage(1))
			return 10;
		if (this.isPlantInStage(2))
			return 15;
		return 20;
	}

	protected boolean checkCanWorkNow() {
		return this.player.isPresent();
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 1.3f);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.GOLD_MAGNET;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
