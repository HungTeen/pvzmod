package com.hungteen.pvz.common.block;

import com.hungteen.pvz.common.world.dimension.PVZDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-14 11:08
 **/
public class PVZPortalBlock extends Block {

    public PVZPortalBlock() {
        super(BlockBehaviour.Properties.of(Material.PORTAL, MaterialColor.COLOR_BLACK).noCollission().lightLevel((p_50854_) -> {
            return 15;
        }).strength(-1.0F, 3600000.0F).noDrops());
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos blockPos, Entity entity) {
        if (level instanceof ServerLevel && entity.canChangeDimensions() && entity instanceof ServerPlayer) {
            if(level.dimension().equals(PVZDimensions.ABYSSAL_DARK)){
                teleport((ServerPlayer) entity, blockPos.north(), Level.OVERWORLD);
            } else{
                teleport((ServerPlayer) entity, blockPos.north(), PVZDimensions.ABYSSAL_DARK);
            }
        }

    }

    private static void teleport(ServerPlayer player, BlockPos pos, ResourceKey<Level> id){
        ServerLevel serverlevel = player.getServer().getLevel(id);
        if (serverlevel != null) {
            player.changeDimension(serverlevel, new ITeleporter() {
                @Override
                public Entity placeEntity (Entity entity, ServerLevel currentWorld, ServerLevel destWorld,
                float yaw, Function<Boolean, Entity > repositionEntity){
                    entity = repositionEntity.apply(false);
                    int y = player.level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
                    entity.teleportTo(pos.getX(), y, pos.getZ());
                    return entity;
                }
            });
        }
    }

}
