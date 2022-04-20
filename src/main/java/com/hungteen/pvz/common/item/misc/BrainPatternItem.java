package com.hungteen.pvz.common.item.misc;

import com.hungteen.pvz.common.item.PVZFoods;
import com.hungteen.pvz.common.item.PVZItemTabs;
import com.hungteen.pvz.common.misc.PVZBannerPatterns;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BannerPattern;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-19 10:12
 **/
public class BrainPatternItem extends BannerPatternItem {

    public BrainPatternItem() {
        super(PVZBannerPatterns.BRAIN, new Properties().tab(CreativeModeTab.TAB_FOOD).food(PVZFoods.FAKE_BRAIN));
    }


}
