package com.hungteen.pvz.client.model.zombie;

import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.utils.interfaces.IEntityDropPart;
import com.hungteen.pvz.utils.interfaces.IDropPartModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:18
 **/
public class PVZZombieModel<T extends PVZZombie> extends HumanoidModel<T> implements IDropPartModel<T> {

    public PVZZombieModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer(){
        return createBodyLayer(64, 64);
    }

    public static LayerDefinition createBodyLayer(int width, int height){
        return LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), width, height);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void tickPartAnim(IEntityDropPart entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderBody(IEntityDropPart entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay) {

    }

    @Override
    public ModelPart getPart(String part) {
        return null;
    }

    @Override
    public EntityModel<T> getModel() {
        return this;
    }

}
