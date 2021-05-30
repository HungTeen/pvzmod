package com.hungteen.pvz.client;

import com.hungteen.pvz.client.render.layer.fullskin.ColdLayer;
import com.hungteen.pvz.common.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy{

	public static final Minecraft MC = Minecraft.getInstance();
	
	public void preInit() {};
	
	public void init() {
//		KeyBindRegister.init();
	};
	
	public void postInit() {
		this.addLayersForRender();
	};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addLayersForRender() {
		MC.getEntityRenderDispatcher().renderers.values().forEach(r -> {
			if (r instanceof LivingRenderer) {
				((LivingRenderer) r).addLayer(new ColdLayer<>((LivingRenderer) r));
			}
		});
	}
	
}
