package com.hungteen.pvz.render.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.FlagZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassday.FlagZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.render.layer.ZombieBeardLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlagZombieRender extends PVZZombieRender<FlagZombieEntity>{

	public FlagZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new FlagZombieModel(), 0.5f);
	}

	@Override
	protected void addZombieLayers() {
		super.addZombieLayers();
		this.addLayer(new ZombieBeardLayer<>(this));
	}
	
	@Override
	protected float getScaleByEntity(FlagZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(FlagZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassday/flag_zombie.png");
	}

}
