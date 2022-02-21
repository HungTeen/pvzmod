package com.hungteen.pvz.common.item.material;

import com.hungteen.pvz.common.item.PVZMiscItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-05 22:07
 **/
public class TimeSourceItem extends PVZMiscItem {

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add(new TranslationTextComponent("tooltip.pvz.time_source").withStyle(TextFormatting.ITALIC));
        textComponents.add(new TranslationTextComponent("tooltip.pvz.wait_for_update").withStyle(TextFormatting.DARK_RED));
    }
}
