package com.hungteen.pvz.client.model.zombie;

import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.utils.interfaces.IEntityDropPart;
import com.hungteen.pvz.utils.interfaces.IDropPartModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:18
 *
 **/
public class HumanoidZombieModel<T extends PVZZombie> extends HumanoidModel<T> implements IDropPartModel<T> {

    public HumanoidZombieModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer(){
        return createBodyLayer(64, 64);
    }

    public static LayerDefinition createBodyLayer(int width, int height){
        MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshDefinition.getRoot();
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(1.9F, 12.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, width, height);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.getLeftHand().visible = entity.hasLeftHand();
        this.getRightHand().visible = entity.hasRightHand();
        this.getHead().visible = entity.hasHead();
    }

    @Override
    public void tickPartAnim(IEntityDropPart entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        switch(entity.getDropPartType()) {
            case WHOLE_BODY:{
//                int cd = 20;
//                if(entity.getAnimTime() < cd) {
//                    this.getZombieWholeBody().xRot = AnimationUtil.getUp(entity.getAnimTime(), cd, 90);
//                    this.getZombieRightHand().xRot = AnimationUtil.byDegree(rightHandOriginAngel) - AnimationUtil.getUp(entity.getAnimTime(), cd, 180 + rightHandOriginAngel);
//                    if(entity.hasHandDefence()) {
//                        this.getZombieLeftHand().xRot = AnimationUtil.byDegree(rightHandOriginAngel) - AnimationUtil.getUp(entity.getAnimTime(), cd, 180 + rightHandOriginAngel);
//                    }
//                } else {
//                    this.getZombieWholeBody().xRot = AnimationUtil.byDegree(90);
//                    this.getZombieRightHand().xRot = AnimationUtil.byDegree(- 180);
//                    if(entity.hasHandDefence()) {
//                        this.getZombieLeftHand().xRot = AnimationUtil.byDegree(- 180);
//                    }
//                }
//                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void renderBody(IEntityDropPart entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        switch(entity.getDropPartType()) {
            case LEFT_HAND:{
                this.getLeftHand().visible = true;
                this.getLeftHand().setPos(0, 24, 0);
                this.getLeftHand().xRot = 0;
                this.getLeftHand().render(stack, buffer, packedLight, packedOverlay);
                break;
            }
            case RIGHT_HAND:{
                this.getRightHand().visible = true;
                this.getRightHand().setPos(0, 24, 0);
                this.getRightHand().xRot = 0;
                this.getRightHand().render(stack, buffer, packedLight, packedOverlay);
                break;
            }
            case HEAD:{
                this.getHead().visible = true;
//                this.getHelmet().ifPresent(m -> m.visible = false);
                this.getHead().setPos(0, 24, 0);
                this.getHead().render(stack, buffer, packedLight, packedOverlay);
                break;
            }
            case WHOLE_BODY:{
                this.getLeftHand().visible = entity.hasHandDefence();
                this.getHead().visible = false;
//                this.getHandDefence().ifPresent(m -> {
//                    m.visible = entity.hasHandDefence();
//                });
//                this.getModel().render(stack, buffer, packedLight, packedOverlay);
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public ModelPart getPart(String part) {
        return null;
    }

    @Override
    public ModelPart getLeftHand() {
        return this.leftArm;
    }

    @Override
    public ModelPart getRightHand() {
        return this.rightArm;
    }

    @Override
    public ModelPart getLeftLeg() {
        return this.leftLeg;
    }

    @Override
    public ModelPart getRightLeg() {
        return this.rightLeg;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public EntityModel<T> getModel() {
        return this;
    }

}
