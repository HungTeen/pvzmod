package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.render.drop.CoinRender;
import com.hungteen.pvz.render.drop.EnergyRender;
import com.hungteen.pvz.render.drop.SunRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, PVZMod.MOD_ID);
    
	//entitytype
	public static final RegistryObject<EntityType<SunEntity>> SUN = registerEntityType(SunEntity::new, "sun", EntityClassification.MISC);
	public static final RegistryObject<EntityType<CoinEntity>> COIN = registerEntityType(CoinEntity::new, "coin", EntityClassification.MISC);
	public static final RegistryObject<EntityType<EnergyEntity>> ENERGY = registerEntityType(EnergyEntity::new, "energy", EntityClassification.MISC,0.9f,2f);
	
	@SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(SUN.get(), SunRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COIN.get(), CoinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ENERGY.get(), EnergyRender::new);
    }

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory,String name,EntityClassification classification)
	{
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory,String name,EntityClassification classification,float w,float h)
	{
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).size(w, h).build(StringUtil.prefix(name).toString());});
	}
}
