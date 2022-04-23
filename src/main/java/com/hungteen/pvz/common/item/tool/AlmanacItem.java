package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.api.interfaces.IHasAlmanac;
import com.hungteen.pvz.client.gui.screen.AlmanacScreen;
import com.hungteen.pvz.common.enchantment.misc.RangeReachEnchantment;
import com.hungteen.pvz.common.item.base.PVZUsefulItem;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-23 10:15
 **/
public class AlmanacItem extends PVZUsefulItem {

    private static final float RANGE = 10;

    public AlmanacItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        final EntityHitResult entityRay = EntityUtil.rayTraceEntities(level, player, player.getLookAngle(), RangeReachEnchantment.getReachDistance(player.getItemInHand(hand), RANGE), IHasAlmanac.class::isInstance);
        if(entityRay != null && entityRay.getType() == HitResult.Type.ENTITY) {
            if(entityRay.getEntity() instanceof LivingEntity && entityRay.getEntity() instanceof IHasAlmanac) {
                if(! level.isClientSide){
//                    player.awardStat(Stats.ITEM_USED.get(this));
//                    player.getCooldowns().addCooldown(this, 10);
                } else{
                    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                        Minecraft.getInstance().setScreen(new AlmanacScreen((LivingEntity) entityRay.getEntity()));
                    });
                }
            }
        }
        return super.use(level, player, hand);
    }


}
