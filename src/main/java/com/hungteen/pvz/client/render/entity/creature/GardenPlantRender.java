package com.hungteen.pvz.client.render.entity.creature;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.creature.SproutModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.client.render.entity.PVZMobRender;
import com.hungteen.pvz.common.entity.creature.garden.GardenPlant;
import com.hungteen.pvz.utils.Util;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3d;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-20 11:41
 **/
public class GardenPlantRender <T extends GardenPlant> extends PVZMobRender<T> {

    private static final ResourceLocation SPROUT = Util.texture("entity/creature/sprout.png");

    public GardenPlantRender(EntityRendererProvider.Context context) {
        super(context, new SproutModel<>(context.bakeLayer(PVZModelLayers.SPROUT)), 0.4F);
    }

    @Override
    public void render(T plant, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(plant, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        if(plant.getGrowStage() > 0){
            final LivingEntity entity = plant.getOrCreateDisplayEntity(plant.level);
            if (entity != null) {
                plant.syncAnimation(entity);
                final float scale = plant.getRenderScale();
                matrixStackIn.pushPose();
                matrixStackIn.scale(scale, scale, scale);
                Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0.0D, 0.0D, 0.0D, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                matrixStackIn.popPose();
            }
        }
    }

    @Override
    public float getScaleByEntity(T entity) {
        return 1;
    }

    @Override
    public Vector3d getTranslateVec(T entity) {
        return super.getTranslateVec(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return SPROUT;
    }

}