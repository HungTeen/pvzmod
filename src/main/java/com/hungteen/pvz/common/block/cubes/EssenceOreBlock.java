package com.hungteen.pvz.common.block.cubes;

import com.hungteen.pvz.api.type.IEssenceType;
import com.hungteen.pvz.common.impl.EssenceTypes;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:49
 **/
public class EssenceOreBlock extends OreBlock {

    public final IEssenceType essence;

    public EssenceOreBlock(IEssenceType e, int light, int minXp, int maxXp) {
        super(Block.Properties.copy(Blocks.DIAMOND_ORE)
                        .strength(9, 9)
                        .requiresCorrectToolForDrops()
                        .lightLevel(i -> light), UniformInt.of(minXp, maxXp)
        );

        this.essence = e;
    }
}
