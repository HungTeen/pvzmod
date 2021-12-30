package com.hungteen.pvz.common.block.ores;

import com.hungteen.pvz.common.block.BlockRegister;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class PVZOreBlock extends OreBlock {

    public PVZOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int xpOnDrop(Random random) {
        if(this instanceof EssenceOreBlock){// essence ore drop xp.
            if(this.equals(BlockRegister.ORIGIN_ORE.get())){
                return MathHelper.nextInt(random, 3, 7);
            } else{
                return MathHelper.nextInt(random, 1, 4);
            }
        }
        return super.xpOnDrop(random);
    }

}
