package com.hungteen.pvz.common.entity.zombie;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.common.impl.type.zombie.PVZZombies;
import com.hungteen.pvz.common.misc.PVZBannerPatterns;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-24 07:58
 **/
public class LeaderZombie extends NormalZombie {

    public LeaderZombie(EntityType<? extends PVZZombie> entityType, Level level) {
        super(entityType, level);
        this.setItemSlot(EquipmentSlot.HEAD, getLeaderBanner());
    }

    public static ItemStack getLeaderBanner() {
        final ItemStack itemstack = new ItemStack(Items.RED_BANNER);
        final CompoundTag tag = new CompoundTag();
        ListTag listTag = (new BannerPattern.Builder())
                .addPattern(BannerPattern.BORDER, DyeColor.BLUE)
                .addPattern(BannerPattern.TRIANGLES_TOP, DyeColor.WHITE)
                .addPattern(BannerPattern.TRIANGLES_BOTTOM, DyeColor.WHITE)
                .addPattern(PVZBannerPatterns.BRAIN, DyeColor.PINK)
                .toListTag();
        tag.put("Patterns", listTag);
        BlockItem.setBlockEntityData(itemstack, BlockEntityType.BANNER, tag);
        itemstack.hideTooltipPart(ItemStack.TooltipPart.ADDITIONAL);
        itemstack.setHoverName((new TranslatableComponent("block.minecraft.ominous_banner")).withStyle(ChatFormatting.DARK_GREEN));
        return itemstack;
    }

    @Override
    protected float getLife() {
        return 40;
    }

    @Override
    public IZombieType getZombieType() {
        return PVZZombies.LEADER_ZOMBIE;
    }
}
