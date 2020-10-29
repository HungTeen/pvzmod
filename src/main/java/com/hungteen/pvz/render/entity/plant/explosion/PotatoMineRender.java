package com.hungteen.pvz.render.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotatoMineRender extends PVZPlantRender<PotatoMineEntity>{

	public PotatoMineRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PotatoMineModel() ,0.3f);
	}

	@Override
	public Vec3d getTranslateVec(PotatoMineEntity entity) {
		return entity.isMineReady() ? new Vec3d(0, 0, 0) : new Vec3d(0, 0.6f, 0);
	}
	
	@Override
	public float getScaleByEntity(PotatoMineEntity entity) {
		return 0.6f;
	}
	
	@Override
	public ResourceLocation getEntityTexture(PotatoMineEntity entity) {
		if(entity.sign_red) return StringUtil.prefix("textures/entity/plant/explosion/potato_mine2.png");
		return StringUtil.prefix("textures/entity/plant/explosion/potato_mine1.png");
	}

}
