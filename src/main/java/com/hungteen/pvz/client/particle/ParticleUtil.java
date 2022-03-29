package com.hungteen.pvz.client.particle;

import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.SpawnParticlePacket;
import com.mojang.math.Vector3d;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-29 11:27
 **/
public class ParticleUtil {

    public static void spawnParticles(Level level, ParticleType<?> particleType, Vec3 vec){
        PVZPacketHandler.sendToNearByClient(level, vec, 50, new SpawnParticlePacket(particleType.getRegistryName().toString(), vec.x, vec.y, vec.z));
    }

    public static void spawnParticles(Level level, ParticleType<?> particleType, Vec3 vec3, Vec3 speed){
        PVZPacketHandler.sendToNearByClient(level, vec3, 50, new SpawnParticlePacket(particleType.getRegistryName().toString(), speed.x, speed.y, speed.z));
    }

//    /**
//     * spawn particles like zombie rising from dirt.
//     */
//    public static void spawnSplash(Level world, Vector3d vec, int cnt) {
//        for(int i = 0; i < cnt; ++i) {
//            Random rand = world;
//            world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x + 0.5d, vec.y, vec.z + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
//            world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x + 0.5d, vec.y, vec.z - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
//            world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x - 0.5d, vec.y, vec.z + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
//            world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x - 0.5d, vec.y, vec.z - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
//        }
//    }
}
