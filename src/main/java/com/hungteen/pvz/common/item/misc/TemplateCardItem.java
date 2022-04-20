package com.hungteen.pvz.common.item.misc;

import com.hungteen.pvz.api.types.ICardType;
import com.hungteen.pvz.common.item.base.PVZMiscItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 20:57
 **/
public class TemplateCardItem extends PVZMiscItem {

    public final ICardType cardType;

    public TemplateCardItem(ICardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return this.cardType.getRarity();
    }
}
