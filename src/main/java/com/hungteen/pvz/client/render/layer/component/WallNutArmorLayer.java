package com.hungteen.pvz.client.render.layer.component;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-02 17:08
 **/
public class WallNutArmorLayer extends ComponentLayer<WallNutEntity> {

    private final ResourceLocation ARMOR1 = StringUtil.prefix("textures/entity/plant/defence/wall_nut_armor.png");
    private final ResourceLocation ARMOR2 = StringUtil.prefix("textures/entity/plant/defence/wall_nut_armor_1.png");
    private final ResourceLocation ARMOR3 = StringUtil.prefix("textures/entity/plant/defence/wall_nut_armor_2.png");

    public WallNutArmorLayer(IEntityRenderer<WallNutEntity, EntityModel<WallNutEntity>> entityRendererIn) {
        super(entityRendererIn, new WallNutModel.WallNutArmorModel());
    }

    @Override
    public boolean canRender(WallNutEntity entity) {
        return entity.getInnerDefenceLife() > 0;
    }

    @Override
    public ResourceLocation getRenderTexture(WallNutEntity entity) {
        final double percent = entity.getInnerDefenceLife() / entity.getSuperLife();
        return percent > 2 / 3F ? ARMOR1 : percent > 1 / 3F ? ARMOR2 : ARMOR3;
    }

}
