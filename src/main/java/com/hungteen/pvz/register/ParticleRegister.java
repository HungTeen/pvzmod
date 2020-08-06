package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegister {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, PVZMod.MOD_ID);
	
	public static final RegistryObject<ParticleType<BasicParticleType>> BOMB_PARTICLE = PARTICLE_TYPES.register("cherry_bomb", ()->{return new BasicParticleType(false);});
}
