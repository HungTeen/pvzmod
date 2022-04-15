package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.client.model.zombie.HumanoidZombieModel;
import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.utils.interfaces.IDropPartModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 09:35
 **/
public class HumanoidZombieRender<T extends PVZZombie> extends HumanoidMobRenderer<T, HumanoidZombieModel<T>> {

    public static final float MINI_SCALE = 0.32F;
    protected final IDropPartModel<T> dropPartModel;
    protected final IDropPartModel<T> dropBodyModel;

    public HumanoidZombieRender(EntityRendererProvider.Context rendererManager, HumanoidZombieModel<T> entityModel, HumanoidZombieModel<T> innerModel, HumanoidZombieModel<T> outerModel, Function<EntityRendererProvider.Context, IDropPartModel<T>> func, float shadowSizeIn) {
        super(rendererManager, entityModel, shadowSizeIn);
        this.dropPartModel = func.apply(rendererManager);
        this.dropBodyModel = func.apply(rendererManager);
        this.addLayer(new HumanoidArmorLayer<>(this, innerModel, outerModel));
    }

    @Override
    public ResourceLocation getTextureLocation(T zombie) {
        return zombie.getPAZType().getDefaultResource();
    }

}
