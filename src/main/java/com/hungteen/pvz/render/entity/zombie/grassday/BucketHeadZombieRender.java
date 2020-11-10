package com.hungteen.pvz.render.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.BucketHeadZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassday.BucketHeadZombieModel;
import com.hungteen.pvz.render.entity.zombie.UnderGroundZombieRender;
import com.hungteen.pvz.render.layer.DuckyTubeLayer;
import com.hungteen.pvz.render.layer.ZombieBeardLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BucketHeadZombieRender extends UnderGroundZombieRender<BucketHeadZombieEntity>{

	public BucketHeadZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BucketHeadZombieModel() ,0.5f);
	}

	@Override
	protected void addZombieLayers() {
		super.addZombieLayers();
		this.addLayer(new ZombieBeardLayer<>(this));
		this.addLayer(new DuckyTubeLayer<BucketHeadZombieEntity>(this));
	}
	
	@Override
	protected float getScaleByEntity(BucketHeadZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(BucketHeadZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassday/buckethead_zombie.png");
	}

}

