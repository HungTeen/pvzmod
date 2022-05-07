package com.hungteen.pvz.common.block.plant;

import com.hungteen.pvz.common.block.FacingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 10:55
 **/
public class FlowerPotBlock extends FacingBlock {

    private static final VoxelShape AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);

    public FlowerPotBlock() {
        super(Block.Properties.copy(Blocks.FLOWER_POT).strength(1F).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return AABB;
    }

    public BlockState getStateForPlacement(Player player) {
        if(player == null) return this.defaultBlockState();
        return this.defaultBlockState().setValue(FACING, player.getDirection().getOpposite());
    }

    public BlockState getStateForPlacement(Direction direction) {
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Nullable
    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity) {
        return BlockPathTypes.WALKABLE;
    }

}
