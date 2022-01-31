package com.hungteen.pvz.client.render.layer.fullskin;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity.VariantType;
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
		this.scale = 1.1F;
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity.isInvisible()) return false;
		if(entity instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) entity).getVariantType() == VariantType.SUPER;
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
