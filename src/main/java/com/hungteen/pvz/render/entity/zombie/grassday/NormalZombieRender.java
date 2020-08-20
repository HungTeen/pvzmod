package com.hungteen.pvz.render.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.NormalZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassday.NormalZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.render.layer.ZombieBeardLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NormalZombieRender extends PVZZombieRender<NormalZombieEntity>{

	public NormalZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new NormalZombieModel(), 0.5f);
	}

	@Override
	protected void addZombieLayers() {
		super.addZombieLayers();
		this.addLayer(new ZombieBeardLayer<>(this));
	}
	
	@Override
	protected float getScaleByEntity(NormalZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(NormalZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassday/normal_zombie.png");
	}

}
