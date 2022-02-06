package com.hungteen.pvz.client.particle;

import java.util.Random;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ParticleUtil {

	/**
	 * spawn particles like zombie rising from dirt.
	 */
	public static void spawnSplash(World world, Vector3d vec, int cnt) {
		for(int i = 0; i < cnt; ++i) {
			Random rand = world.random;
			world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x + 0.5d, vec.y, vec.z + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
			world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x + 0.5d, vec.y, vec.z - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
			world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x - 0.5d, vec.y, vec.z + 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
			world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), vec.x - 0.5d, vec.y, vec.z - 0.5d, (rand.nextFloat() - 0.5) / 10, 0.05d, (rand.nextFloat() - 0.5) / 10);
		}
	}
}
