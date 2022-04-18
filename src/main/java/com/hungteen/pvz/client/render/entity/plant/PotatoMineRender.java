package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.component.SurroundDirtModel;
import com.hungteen.pvz.client.model.entity.plant.PotatoMineModel;
import com.hungteen.pvz.client.render.entity.layer.PVZLightLayer;
import com.hungteen.pvz.client.render.entity.layer.SurroundDirtLayer;
import com.hungteen.pvz.common.entity.plant.PotatoMine;
import com.hungteen.pvz.utils.Util;
import com.mojang.math.Vector3d;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-08 16:03
 **/
public class PotatoMineRender extends PVZPlantRender<PotatoMine> {

    public PotatoMineRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new PotatoMineModel<>(rendererManager.bakeLayer(PVZModelLayers.POTATO_MINE)), 0.5F);
        this.addLayer(new SurroundDirtLayer<>(this, new SurroundDirtModel<>(rendererManager.bakeLayer(PVZModelLayers.SURROUND_DIRT))));
        this.addLayer(new PotatoMineRender.MineLightLayer(this));
    }

    @Override
    public float getScaleByEntity(PotatoMine entity) {
        final float sz = super.getScaleByEntity(entity);
        if(entity.isMineReady()) {
            final float scale = 0.15F;
            return sz + entity.getAnimTick() * scale / entity.getAnimationCD();
        }
        return sz;
    }

    @Override
    public Vector3d getTranslateVec(PotatoMine entity) {
        final float offsetY = 0.35F;
        if(entity.isRisingFromDirt()) {
            final int time = entity.getPrepareCD() - entity.getExistTick();
            return new Vector3d(0, time * offsetY / PotatoMine.RISING_ANIM_CD, 0);
        }
        return entity.isMineReady() ? new Vector3d(0, 0, 0) : new Vector3d(0, offsetY, 0);
    }

    public static class MineLightLayer extends PVZLightLayer<PotatoMine> {

        private static final RenderType MINE_LIGHT = RenderType.eyes(Util.texture("entity/plant/potato_mine/mine_light.png"));

        public MineLightLayer(RenderLayerParent<PotatoMine, EntityModel<PotatoMine>> layerParent) {
            super(layerParent);
        }

        @Override
        public boolean canRender(PotatoMine entity) {
            final int T = (entity.getSignChangeCD() << 1);
            final int current = entity.getExistTick() % T;
            return (current < (T >> 1));
        }

        @Override
        public RenderType renderType() {
            return MINE_LIGHT;
        }
    }

}
