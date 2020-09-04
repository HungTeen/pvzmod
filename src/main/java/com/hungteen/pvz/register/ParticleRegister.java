package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.particle.BlueFlameParticle;
import com.hungteen.pvz.particle.DirtBurstOutParticle;
import com.hungteen.pvz.particle.YellowFlameParticle;
import com.hungteen.pvz.particle.bomb.CherryBombParticle;
import com.hungteen.pvz.particle.bomb.PotatoMineParticle;

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
	
	public static final RegistryObject<BasicParticleType> RED_BOMB = PARTICLE_TYPES.register("red_bomb", ()->{return new BasicParticleType(false);});
	public static final RegistryObject<BasicParticleType> YELLOW_BOMB = PARTICLE_TYPES.register("yellow_bomb", ()->{return new BasicParticleType(false);});
	public static final RegistryObject<BasicParticleType> DIRT_BURST_OUT = PARTICLE_TYPES.register("dirt_burst_out", ()->{return new BasicParticleType(false);});
	public static final RegistryObject<BasicParticleType> YELLOW_FLAME = PARTICLE_TYPES.register("yellow_flame", ()->{return new BasicParticleType(false);});
	public static final RegistryObject<BasicParticleType> BLUE_FLAME = PARTICLE_TYPES.register("blue_flame", ()->{return new BasicParticleType(false);});

	@SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
		@SuppressWarnings("resource")
		ParticleManager manager = Minecraft.getInstance().particles;
        manager.registerFactory(ParticleRegister.RED_BOMB.get(), (sprite) -> {return new CherryBombParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.YELLOW_BOMB.get(), (sprite) -> {return new PotatoMineParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.DIRT_BURST_OUT.get(), (sprite) -> {return new DirtBurstOutParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.YELLOW_FLAME.get(), (sprite) -> {return new YellowFlameParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.BLUE_FLAME.get(), (sprite) -> {return new BlueFlameParticle.Factory(sprite);});
    }
}
