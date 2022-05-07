package com.hungteen.pvz.common.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-13 08:36
 **/
public class Jewel extends PVZDrop {

    public Jewel(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onCollect(LivingEntity living) {
        if(! this.level.isClientSide) {
            if(living instanceof Player){
                PlayerUtil.addResource((Player) living, Resources.GEM_NUM, this.getAmount());
                PlayerUtil.playClientSound((Player) living, PVZSounds.JEWEL_PICK.get());
            }
        }
        this.discard();
    }

    @Override
    protected int getMaxLiveTick() {
        return PVZConfig.getJewelLiveTick();
    }

    @Override
    protected int getDefaultAmount() {
        return 1;
    }

    @Override
    public ItemStack getRenderStack() {
        return new ItemStack(PVZItems.JEWEL.get());
    }

    @Override
    protected SoundEvent getDropSound() {
        return PVZSounds.JEWEL_DROP.get();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.8F, 0.8F);
    }

}
