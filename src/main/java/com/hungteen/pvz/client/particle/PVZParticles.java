package com.hungteen.pvz.client.particle;

import com.hungteen.pvz.PVZMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-29 10:05
 *
 * Step 1. make your own particle class. <br>
 * Step 2. bind your particle factory at {@link com.hungteen.pvz.client.ClientRegister#registerFactories(ParticleFactoryRegisterEvent)}
 **/
public class PVZParticles {

    //don't forget register particle factory in client register
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =  DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PVZMod.MOD_ID);

//    public static final RegistryObject<BasicParticleType> RED_BOMB = PARTICLE_TYPES.register("red_bomb", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> YELLOW_BOMB = PARTICLE_TYPES.register("yellow_bomb", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> DIRT_BURST_OUT = PARTICLE_TYPES.register("dirt_burst_out", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> YELLOW_FLAME = PARTICLE_TYPES.register("yellow_flame", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> BLUE_FLAME = PARTICLE_TYPES.register("blue_flame", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> SLEEP = PARTICLE_TYPES.register("sleep", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> SPORE = PARTICLE_TYPES.register("spore", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> FUME = PARTICLE_TYPES.register("fume", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> SNOW_FLOWER = PARTICLE_TYPES.register("snow_flower", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> DOOM = PARTICLE_TYPES.register("doom", ()->{return new BasicParticleType(false);});
    public static final RegistryObject<SimpleParticleType> MELON_SLICE = PARTICLE_TYPES.register("melon_slice", () -> new SimpleParticleType(false));
//    public static final RegistryObject<BasicParticleType> FROZEN_MELON_SLICE = PARTICLE_TYPES.register("frozen_melon_slice", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> GREEN_SWEEP = PARTICLE_TYPES.register("green_sweep", ()->{return new BasicParticleType(false);});
//    public static final RegistryObject<BasicParticleType> POP_CORN = PARTICLE_TYPES.register("pop_corn", ()->{return new BasicParticleType(false);});

}
