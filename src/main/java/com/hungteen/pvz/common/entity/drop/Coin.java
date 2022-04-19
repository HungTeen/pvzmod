package com.hungteen.pvz.common.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-12 22:17
 **/
public abstract class Coin extends PVZDrop {

    public Coin(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
    }

    /**
     * spawn coin by amount.
     */
    public static void spawnCoin(Level world, BlockPos pos, int amount) {
        final EntityType<Coin> coin = amount < 10 ? PVZEntities.COPPER_COIN.get() :
                amount < 100 ? PVZEntities.SILVER_COIN.get() : PVZEntities.GOLD_COIN.get();
        final Coin coinEntity = coin.create(world);
        coinEntity.setAmount(amount);
        EntityUtil.onEntitySpawn(world, coinEntity, pos);
    }

    @Override
    public void onCollect(LivingEntity living) {
        if (! this.level.isClientSide) {
            if(living instanceof Player){
                PlayerUtil.addResource((Player) living, Resources.MONEY, this.getAmount());
                PlayerUtil.playClientSound((Player) living, PVZSounds.COIN_PICK.get());
            }
        }
        this.discard();
    }

    @Override
    protected SoundEvent getDropSound() {
        return PVZSounds.COIN_DROP.get();
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(0.7F, 0.7F);
    }

    @Override
    protected int getMaxLiveTick() {
        return PVZConfig.getCoinLiveTick();
    }

    public static class CopperCoin extends Coin{

        public CopperCoin(EntityType<? extends Mob> type, Level worldIn) {
            super(type, worldIn);
        }

        @Override
        public ItemStack getRenderStack() {
            return new ItemStack(PVZItems.COPPER_COIN.get());
        }

        @Override
        protected int getDefaultAmount() {
            return 1;
        }
    }

    public static class SilverCoin extends Coin{

        public SilverCoin(EntityType<? extends Mob> type, Level worldIn) {
            super(type, worldIn);
        }

        @Override
        public ItemStack getRenderStack() {
            return new ItemStack(PVZItems.SILVER_COIN.get());
        }

        @Override
        protected int getDefaultAmount() {
            return 10;
        }
    }

    public static class GoldCoin extends Coin{

        public GoldCoin(EntityType<? extends Mob> type, Level worldIn) {
            super(type, worldIn);
        }

        @Override
        public ItemStack getRenderStack() {
            return new ItemStack(PVZItems.GOLD_COIN.get());
        }

        @Override
        protected int getDefaultAmount() {
            return 100;
        }
    }
}
