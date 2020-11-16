package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.BackupDancerEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.BackupDancerModel;
import com.hungteen.pvz.render.entity.zombie.UnderGroundZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BackupDancerRender extends UnderGroundZombieRender<BackupDancerEntity>{

	public BackupDancerRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BackupDancerModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(BackupDancerEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(BackupDancerEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassnight/backup_dancer.png");
	}

}
