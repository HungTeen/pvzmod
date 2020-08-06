package com.hungteen.pvz.register;

import com.hungteen.pvz.particle.bomb.CherryBombParticle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryRegister {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleRegister.BOMB_PARTICLE.get(), (sprite) -> {
            return new CherryBombParticle.Factory(sprite);
        });
    }
}