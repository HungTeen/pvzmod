package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.misc.ComponentModel;
import com.hungteen.pvz.client.model.plant.WallNutModel;
import com.hungteen.pvz.client.render.entity.layer.ComponentLayer;
import com.hungteen.pvz.common.entity.plant.WallNut;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 18:20
 **/
public class WallNutRender extends PVZPlantRender<WallNut> {

    private final ResourceLocation TEX1 = Util.texture("entity/plant/wall_nut/wall_nut.png");
    private final ResourceLocation TEX2 = Util.texture("entity/plant/wall_nut/wall_nut_1.png");
    private final ResourceLocation TEX3 = Util.texture("entity/plant/wall_nut/wall_nut_2.png");

    public WallNutRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new WallNutModel<>(rendererManager.bakeLayer(PVZModelLayers.WALL_NUT)), 0.5F);
        this.addLayer(new WallNutArmorLayer(this, new WallNutModel.WallNutArmorModel(rendererManager.bakeLayer(PVZModelLayers.WALL_NUT_ARMOR))));
    }

    @Override
    public ResourceLocation getTextureLocation(WallNut entity) {
//    	final ResourceLocation res = ClientProxy.MC.getBlockRenderer().getBlockModelShaper().getTexture(entity.level.getBlockState(entity.blockPosition().below()), entity.level, entity.blockPosition().below()).getName();
//        return new ResourceLocation(res.getNamespace(), "textures/" + res.getPath() + ".png");
        final double percent = entity.getHealth() / entity.getMaxHealth();
        return percent > 2 / 3F ? TEX1 : percent > 1 / 3F ? TEX2 : TEX3;
    }

    protected static class WallNutArmorLayer extends ComponentLayer<WallNut> {

        private final ResourceLocation ARMOR1 = Util.texture("entity/plant/wall_nut/wall_nut_armor.png");
        private final ResourceLocation ARMOR2 = Util.texture("entity/plant/wall_nut/wall_nut_armor_1.png");
        private final ResourceLocation ARMOR3 = Util.texture("entity/plant/wall_nut/wall_nut_armor_2.png");

        public WallNutArmorLayer(RenderLayerParent<WallNut, EntityModel<WallNut>> parent, ComponentModel model) {
            super(parent, model);
        }

        @Override
        public ResourceLocation getRenderTexture(WallNut entity) {
//            final double percent = entity.getInnerDefenceLife() / entity.getSuperLife();
//            return percent > 2 / 3F ? ARMOR1 : percent > 1 / 3F ? ARMOR2 : ARMOR3;
            return ARMOR1;
        }

        @Override
        public boolean canRender(WallNut entity) {
//            return entity.getInnerDefenceLife() > 0;
            return false;
        }

    }
}
