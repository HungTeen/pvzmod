package com.hungteen.pvz.render.layer.fullskin;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity.Type;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnergyLayer <T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T,M>{

	public EnergyLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity.isInvisible()) return false;
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).getZombieType()==Type.SUPER;
		}
		if(entity instanceof PVZPlantEntity) {
			return ((PVZPlantEntity) entity).isPlantInSuperMode();
		}
		return false;
	}
	
	@Override
	protected float getU(float f) {
		return 0.01f * f;
	}
	
	@Override
	protected float getV(float f) {
		return 0.01f * f;
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/energy_armor.png");
	}

}
