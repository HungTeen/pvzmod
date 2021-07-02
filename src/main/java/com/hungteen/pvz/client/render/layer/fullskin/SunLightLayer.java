package com.hungteen.pvz.client.render.layer.fullskin;

import com.hungteen.pvz.common.entity.creature.FoodieZombieEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;
import com.hungteen.pvz.common.entity.zombie.other.RaZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunLightLayer<T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T,M>{

	public SunLightLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity.isInvisible()) return false;
		if(entity instanceof StarFruitEntity && ((StarFruitEntity) entity).lightTick > 0) return true;
		if(entity instanceof GoldLeafEntity && ((GoldLeafEntity) entity).getAttackTime() + 30 > ((GoldLeafEntity) entity).getReadyTime()) return true;
		if(entity instanceof PlantProducerEntity) {
			return ((PlantProducerEntity) entity).isPlantInGen();
		} else if(entity instanceof FoodieZombieEntity) {
			int tick = ((FoodieZombieEntity) entity).getGenTick();
			return tick > 0&& tick <= 60;
		}
		if(entity instanceof PVZPlantEntity && ((PVZPlantEntity) entity).getGoldTime() + 20 > GoldLeafEntity.GOLD_GEN_CD) return true;
		if(entity instanceof RaZombieEntity && ((RaZombieEntity) entity).checkCanWorkNow()) return true;
		return false;
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/sun_light.png");
	}

}
