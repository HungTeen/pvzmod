package com.hungteen.pvz.render.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.BucketHeadZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassday.BucketHeadZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.render.layer.ZombieBeardLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BucketHeadZombieRender extends PVZZombieRender<BucketHeadZombieEntity>{

	public BucketHeadZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BucketHeadZombieModel() ,0.5f);
	}

	@Override
	protected void addZombieLayers() {
		super.addZombieLayers();
		this.addLayer(new ZombieBeardLayer<>(this));
	}
	
	@Override
	protected float getScaleByEntity(BucketHeadZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(BucketHeadZombieEntity entity) {
		double hp=entity.getHealth();
		double max=entity.getMaxHealth();
		if(hp<=max/4) return StringUtil.prefix("textures/entity/zombie/grassday/buckethead_zombie4.png");
		if(hp<=max/2) return StringUtil.prefix("textures/entity/zombie/grassday/buckethead_zombie3.png");
		if(hp<=max*3/4) return StringUtil.prefix("textures/entity/zombie/grassday/buckethead_zombie2.png");
		return StringUtil.prefix("textures/entity/zombie/grassday/buckethead_zombie1.png");
	}

}

