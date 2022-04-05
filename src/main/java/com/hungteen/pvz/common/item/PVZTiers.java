package com.hungteen.pvz.common.item;

import com.hungteen.pvz.common.tag.PVZBlockTags;
import com.hungteen.pvz.utils.Util;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 22:53
 **/
public class PVZTiers {

    public static final Tier ORIGIN = TierSortingRegistry.registerTier(new ForgeTier(3, 666, 7.0F, 3.0F, 18, BlockTags.NEEDS_DIAMOND_TOOL, () -> {
        return Ingredient.of(PVZItems.ORIGIN_INGOT.get());
    }), Util.prefix("origin"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));


}
