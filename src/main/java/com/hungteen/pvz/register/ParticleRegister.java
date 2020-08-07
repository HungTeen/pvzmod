package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.particle.bomb.CherryBombParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegister {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, PVZMod.MOD_ID);
	
	public static final RegistryObject<BasicParticleType> BOMB_PARTICLE = PARTICLE_TYPES.register("red_bomb", ()->{return new BasicParticleType(false);});

	@SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
		@SuppressWarnings("resource")
		ParticleManager manager = Minecraft.getInstance().particles;
        manager.registerFactory(ParticleRegister.BOMB_PARTICLE.get(), (sprite) -> {return new CherryBombParticle.Factory(sprite);});
    }
}
