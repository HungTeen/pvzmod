package com.hungteen.pvz.render.entity.plant.assist;

import com.hungteen.pvz.entity.plant.assist.GraveBusterEntity;
import com.hungteen.pvz.model.entity.plant.assist.GraveBusterModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GraveBusterRender extends PVZPlantRender<GraveBusterEntity>{

	public GraveBusterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GraveBusterModel(), 0);
	}

	@Override
	protected void preRenderCallback(GraveBusterEntity entity, MatrixStack matrixStackIn,
			float partialTickTime) {
		float sz = this.getScaleByEntity(entity);
		matrixStackIn.scale(sz, sz, sz);
		if(entity.isEating()) {
			float height = 1.5f;
			float downOffset = (1 - entity.getAttackTime() * 1.0f / entity.getAttackCD()) * height;
		    matrixStackIn.translate(0, - downOffset, 0);
		}
	}
	
	@Override
	public Vec3d getTranslateVec(GraveBusterEntity entity) {
		if(entity.isEating()) {
			float height = 1.5f;
			float downOffset = (1 - entity.getAttackTime() * 1.0f / entity.getAttackCD()) * height;
		    return new Vec3d(0, - downOffset, 0);
		}
		return super.getTranslateVec(entity);
	}
	
	@Override
	public float getScaleByEntity(GraveBusterEntity entity) {
		return 1f;
	}

	@Override
	public ResourceLocation getEntityTexture(GraveBusterEntity entity) {
		return StringUtil.prefix("textures/entity/plant/assist/grave_buster.png");
	}

}
