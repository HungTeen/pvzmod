package com.hungteen.pvz.event;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PVZClientEvent {

//	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
//	public static void addEntityLayer() {
//		for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : Minecraft.getInstance().getRenderManager().renderers
//				.entrySet()) {
//			EntityRenderer render = entry.getValue();
//			if (render instanceof LivingRenderer) {
//				((LivingRenderer) render).addLayer(new ColdLayer<>((LivingRenderer) render));
//			}
//		}
//		Field renderingRegistryField = ObfuscationReflectionHelper.findField(RenderingRegistry.class, "INSTANCE");
//        Field entityRendersField = ObfuscationReflectionHelper.findField(RenderingRegistry.class, "entityRenderers");
//        RenderingRegistry registry = null;
//        try {
//            Field modifier = Field.class.getDeclaredField("modifiers");
//            modifier.setAccessible(true);
//            registry = (RenderingRegistry) renderingRegistryField.get(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (registry != null) {
//            Map<EntityType<? extends Entity>, IRenderFactory<? extends Entity>> entityRenders = null;
//            try {
//                Field modifier1 = Field.class.getDeclaredField("modifiers");
//                modifier1.setAccessible(true);
//                entityRenders = (Map<EntityType<? extends Entity>, IRenderFactory<? extends Entity>>) entityRendersField.get(registry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (entityRenders != null) {
//                for (Map.Entry<EntityType<? extends Entity>, IRenderFactory<? extends Entity>> entry : entityRenders.entrySet()) {
//                    if (entry.getValue() != null) {
//                        try {
//                            EntityRenderer render = entry.getValue().createRenderFor(Minecraft.getInstance().getRenderManager());
//                            if (render != null && render instanceof LivingRenderer) {
//                            	((LivingRenderer) render).addLayer(new ColdLayer<>((LivingRenderer) render));
//                            }
//                        } catch (NullPointerException exp) {
//                            PVZMod.LOGGER.warn("PVZ could not apply stone render layer to " + entry.getKey().getTranslationKey() + ", someone isn't registering their renderer properly... <.<");
//                        }
//                    }
//
//                }
//            }
//        }
//	}

	@SubscribeEvent
	public void onGuiOpened(GuiOpenEvent event) {
//		if (IafConfig.customMainMenu && event.getGui() instanceof MainMenuScreen
//				&& !(event.getGui() instanceof IceAndFireMainMenu)) {
//			event.setGui(new IceAndFireMainMenu());
//		}
	}
}
