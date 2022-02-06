package com.hungteen.pvz.common.item.tool.mc;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.PVZItemTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-03 10:08
 **/
public class OriginSwordItem extends SwordItem {

    public OriginSwordItem() {
        super(PVZItemTier.ORIGIN, 3, -2.4F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add(new TranslationTextComponent("tooltip.pvz.origin_sword").withStyle(TextFormatting.DARK_GREEN));
    }
}
