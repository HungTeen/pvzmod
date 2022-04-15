package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.zombie.NormalZombieModel;
import com.hungteen.pvz.common.entity.zombie.NormalZombie;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 09:47
 **/
public class NormalZombieRender<T extends NormalZombie> extends HumanoidZombieRender<T> {

    public NormalZombieRender(EntityRendererProvider.Context rendererManager) {
        super(rendererManager, new NormalZombieModel<>(rendererManager.bakeLayer(PVZModelLayers.NORMAL_ZOMBIE)),
                new NormalZombieModel<>(rendererManager.bakeLayer(PVZModelLayers.NORMAL_ZOMBIE_INNER_ARMOR)),
                new NormalZombieModel<>(rendererManager.bakeLayer(PVZModelLayers.NORMAL_ZOMBIE_OUTER_ARMOR)),
                r -> new NormalZombieModel<>(r.bakeLayer(PVZModelLayers.NORMAL_ZOMBIE)),
                0.5F);
    }

}
