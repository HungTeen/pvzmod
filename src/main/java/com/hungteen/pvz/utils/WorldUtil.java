package com.hungteen.pvz.utils;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-29 09:59
 **/
public class WorldUtil {

    /**
     * @param pos center position.
     * @param minR minimum distance.
     * @param maxR maximum distance.
     * @return result position.
     */
    public static BlockPos getSuitableHeightRandomPos(Level world, BlockPos pos, int minR, int maxR) {
        BlockPos offset = MathUtil.getRandomRangePos(world.random, minR, maxR);
        return getSuitableHeightPos(world, pos.offset(offset.getX(), 0, offset.getZ()));
    }

    public static BlockPos getSuitableHeightRandomPos(Level world, BlockPos pos, int maxR) {
        return getSuitableHeightRandomPos(world, pos, 0, maxR);
    }

    public static BlockPos getSuitableHeightPos(Level world, BlockPos pos) {
        int y = world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ());
        return new BlockPos(pos.getX(), y, pos.getZ());
    }

    /**
     * Spawn Random speed Particle at pos.
     */
    public static void spawnRandomSpeedParticle(Level world, ParticleOptions type, Vec3 pos, float speed) {
        spawnRandomSpeedParticle(world, type, pos, speed, speed);
    }

    /**
     * Spawn Random speed Particle at pos.
     */
    public static void spawnRandomSpeedParticle(Level world, ParticleOptions type, Vec3 pos, float horizontalSpeed, float verticalSpeed) {
        final float speedX = (world.random.nextFloat() - 0.5F) * horizontalSpeed * 2;
        final float speedY = (world.random.nextFloat() - 0.5F) * verticalSpeed * 2;
        final float speedZ = (world.random.nextFloat() - 0.5F) * horizontalSpeed * 2;
        world.addParticle(type, pos.x, pos.y, pos.z, speedX, speedY, speedZ);
    }

    @SuppressWarnings("deprecation")
    public static int calculateGenHeight(Level worldIn, int x, int z){
        int y = worldIn.getMaxBuildHeight();
        boolean foundGround = false;
        while(!foundGround && y-- >= worldIn.getSeaLevel()-1){
            foundGround = worldIn.getBlockState(new BlockPos(x,y,z)).canOcclude();
        }
        return y;
    }

//    @Nullable
//    public static BlockPos findRandomSpawnPos(Level world, BlockPos center, int chance, int minRange, int maxRange, Predicate<BlockPos> predicate) {
//        final int range = minRange, distance = maxRange - minRange;
//        if(distance <= 0) {
//            return null;
//        }
//
//        for (int i = 0; i < chance; ++i) {
//            final float f = world.random.nextFloat() * ((float) Math.PI * 2F);
//            final int radius = MathUtil.getRandomMinMax(world.getRandom(), minRange, maxRange);
//            final int x = center.getX() + Mth.floor(Mth.cos(f) * radius);
//            final int z = center.getZ() + Mth.floor(Mth.sin(f) * radius);
//            final int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
//            final BlockPos pos = new BlockPos(x, y, z);
//            if (world.hasChunksAt(pos.offset(-range, -range, -range), pos.offset(range, range, range))
//                    && world.getChunkSource().isEntityTickingChunk(new ChunkPos(pos))) {
//                if(predicate.test(pos) && world.getBrightness(LightType.BLOCK, pos) < 7) {
//                    return pos;
//                }
//            }
//        }
//        return null;
//    }
}
