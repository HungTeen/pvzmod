package com.hungteen.pvz.common.block.cubes;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.events.OriginEffectEvent;
import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;
import com.hungteen.pvz.common.recipe.PVZRecipeTypes;
import com.hungteen.pvz.common.recipe.RadiationRecipe;
import com.hungteen.pvz.utils.BlockUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.MinecraftForge;

import java.util.Optional;
import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 22:44
 **/
public class OriginBlock extends Block {

    private static final int RADIATION_RANGE = 1;

    public OriginBlock() {
        super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN)
                .strength(15, 50)
                .lightLevel(i -> 15)
                .randomTicks()
                .sound(SoundType.ANCIENT_DEBRIS)
                .noOcclusion()
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (! worldIn.isClientSide) {
            if (! worldIn.isAreaLoaded(pos, 3)) return;
            final int r = RADIATION_RANGE;
            boolean success = false;
            for (int i = -r; i <= r; ++i) {
                for (int j = -r; j <= r; ++j) {
                    for (int k = -r; k <= r; ++k) {
                        if (rand.nextDouble() < PVZConfig.getOriginEffectChance()) {
                            success |= checkAndGrow(worldIn, pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                        }
                    }
                }
            }

            if(success){
                OriginEffectEntity.create(worldIn, pos, Colors.GREEN);
            }
        }
    }

    /**
     * check specific block and grow if matched.
     */
    private boolean checkAndGrow(Level world, int x, int y, int z) {
        final BlockPos pos = new BlockPos(x, y, z);
        Optional<RadiationRecipe> opt = getRadiationRecipe(world, world.getBlockState(pos).getBlock());
        if(opt.isPresent()){
            final ItemStack stack = opt.get().getResultItem();
            Optional<Block> blockOpt = BlockUtil.getBlock(stack.getItem().getRegistryName());
            if(! MinecraftForge.EVENT_BUS.post(new OriginEffectEvent(world, pos))){
                if(blockOpt.isPresent()){
                    world.setBlockAndUpdate(pos, blockOpt.get().defaultBlockState());
                } else{
                    ItemUtil.dropItem(world, stack, x + 0.5, y + 0.5, z + 0.5);
                }
                return true;
            }
        }
        return false;
    }

    public static Optional<RadiationRecipe> getRadiationRecipe(Level level, Block block) {
        final ItemStack stack = new ItemStack(block);
        return level.getRecipeManager().getRecipeFor(PVZRecipeTypes.RADIATION_RECIPE_TYPE.get(), new SimpleContainer(stack), level);
    }

}
