package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.client.model.zombie.PVZZombieModel;
import com.hungteen.pvz.client.render.entity.PVZMobRender;
import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.utils.interfaces.IDropPartModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 09:35
 **/
public class PVZZombieRender<T extends PVZZombie> extends PVZMobRender<T> {

    public static final float MINI_SCALE = 0.32F;
    protected final IDropPartModel<T> dropPartModel;
    protected final IDropPartModel<T> dropBodyModel;

    public PVZZombieRender(EntityRendererProvider.Context rendererManager, EntityModel<T> entityModel, Function<EntityRendererProvider.Context, IDropPartModel<T>> func, float shadowSizeIn) {
        super(rendererManager, entityModel, shadowSizeIn);
        this.dropPartModel = func.apply(rendererManager);
        this.dropBodyModel = func.apply(rendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(T zombie) {
        return zombie.getPAZType().getDefaultResource();
    }

    public IDropPartModel getDropPartModel() {
        return null;
    }

}
