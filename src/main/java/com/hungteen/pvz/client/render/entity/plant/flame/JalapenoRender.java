package com.hungteen.pvz.client.render.entity.plant.flame;

import com.hungteen.pvz.client.model.entity.plant.flame.JalapenoModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JalapenoRender extends PVZPlantRender<JalapenoEntity>{

	public JalapenoRender(EntityRendererManager rendererManager) {
		super(rendererManager, new JalapenoModel(), 0.4f);
	}

	@Override
	public float getScaleByEntity(JalapenoEntity entity) {
		float plus = entity.getAttackTime()*1f/entity.getReadyTime();
		return 0.5f+plus/20f;
	}

	@Override
	public ResourceLocation getTextureLocation(JalapenoEntity entity) {
		return StringUtil.prefix("textures/entity/plant/flame/jalapeno.png");
	}

}
