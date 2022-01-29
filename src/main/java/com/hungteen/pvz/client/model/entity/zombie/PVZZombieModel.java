package com.hungteen.pvz.client.model.entity.zombie;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public abstract class PVZZombieModel<T extends PVZZombieEntity> extends EntityModel<T> implements IZombieModel<T>{

	//related to attack animation.
	protected static final float HAND_MAX_ANGLE = AnimationUtil.byDegree(- 120F);
	protected static final int MAX_ANIM_CD = PVZZombieEntity.PERFORM_ATTACK_CD;
	//use to check whether the part can move or not.
	protected boolean isLeftHandFree = true;
	protected boolean isRightHandFree = true;
	protected boolean isHeadFree = true;
	//use to render last body part animation.
	protected float rightHandOriginAngel = 0;
	
	/**
	 * use for drop part entity to render.
	 * not for current entity.
	 * {link @ZombieBodyRender}
	 */
	@Override
	public void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		switch(entity.getBodyType()) {
		case BODY:{
			int cd = 20;
			if(entity.getAnimTime() < cd) {
				this.getZombieWholeBody().xRot = AnimationUtil.getUp(entity.getAnimTime(), cd, 90);
				this.getZombieRightHand().xRot = AnimationUtil.byDegree(rightHandOriginAngel) - AnimationUtil.getUp(entity.getAnimTime(), cd, 180 + rightHandOriginAngel);
			    if(entity.hasHandDefence()) {
			    	this.getZombieLeftHand().xRot = AnimationUtil.byDegree(rightHandOriginAngel) - AnimationUtil.getUp(entity.getAnimTime(), cd, 180 + rightHandOriginAngel);
			    }
			} else {
				this.getZombieWholeBody().xRot = AnimationUtil.byDegree(90);
				this.getZombieRightHand().xRot = AnimationUtil.byDegree(- 180);
				if(entity.hasHandDefence()) {
					this.getZombieLeftHand().xRot = AnimationUtil.byDegree(- 180);
			    }
			}
			break;
		}
		default:{
			break;
		}
		}
	}
	
	/**
	 * use for zombie animation.
	 * called by specific zombie render.
	 */
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.refreshAnim();
        this.updateFreeParts(entity);
        this.doWalkAnimation(limbSwing, limbSwingAmount, netHeadYaw, headPitch);
        this.performAttack(entity);
	}
	
	/**
	 * refresh the rotation of parts.
	 */
	public void refreshAnim() {
	}
	
	/**
	 * check if some part of body is free and update.
	 */
	public void updateFreeParts(T entity) {
		this.getZombieLeftHand().visible = entity.hasHand();
		this.getZombieHead().visible = entity.hasHead();
	}
	
	protected void doWalkAnimation(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.getZombieHead().yRot = netHeadYaw / (180F / (float)Math.PI);
        this.getZombieHead().xRot = headPitch / (180F / (float)Math.PI);
        this.getZombieLeftLeg().xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.getZombieRightLeg().xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        if(this.isRightHandFree) this.getZombieRightHand().xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        if(this.isLeftHandFree) this.getZombieLeftHand().xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
	
	/**
	 * do attack animation
	 */
	protected void performAttack(T entity) {
		if(entity.getAnimTime() > 0) {
        	if(this.isLeftHandFree || this.isRightHandFree) {
        		this.doHandEat(entity);
        	}
        } else {// no target
        	if(isZombieAngry(entity)) {
        	    this.doPreAttackPose();
        	}
        }
	}
	
	/**
	 * hand rise when near to target.
	 */
	protected void doPreAttackPose() {
		//can hand attack
		if(this.isLeftHandFree || this.isRightHandFree) {
			if(this.isLeftHandFree) {
				this.getZombieLeftHand().xRot = HAND_MAX_ANGLE;
			}
			if(this.isRightHandFree) {
				this.getZombieRightHand().xRot = HAND_MAX_ANGLE;
			}
		} 
	}
	
	/**
	 * animate like default zombie.
	 */
	protected void doHandEat(T entity) {
		if(this.isLeftHandFree) {
			this.getZombieLeftHand().xRot = HAND_MAX_ANGLE - AnimationUtil.getUpDown(MAX_ANIM_CD - entity.getAnimTime(), MAX_ANIM_CD, - 70);
		}
		if(this.isRightHandFree) {
			this.getZombieRightHand().xRot = HAND_MAX_ANGLE - AnimationUtil.getUpDown(MAX_ANIM_CD - entity.getAnimTime(), MAX_ANIM_CD, - 70);
		}
	}
	
	protected boolean isZombieAngry(T entity) {
		return entity.isAggressive();
	}
	
	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.getZombieWholeBody().render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	/**
	 * render drop body part.
	 */
	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
		switch(entity.getBodyType()) {
		case HAND:{
			this.getZombieLeftHand().visible = true;
			this.getZombieLeftHand().setPos(0, 24, 0);
			this.getZombieLeftHand().xRot = 0;
		    this.getZombieLeftHand().render(stack, buffer, packedLight, packedOverlay);
		    break;
		}
		case HEAD:{
			this.getZombieHead().visible = true;
			this.getHelmet().ifPresent(m -> m.visible = false);
			this.getZombieHead().setPos(0, 24, 0);
		    this.getZombieHead().render(stack, buffer, packedLight, packedOverlay);
		    break;
		}
		case BODY:{
			this.getZombieLeftHand().visible = entity.hasHandDefence();
		    this.getZombieHead().visible = false;
		    this.getHandDefence().ifPresent(m -> {
		    	m.visible = entity.hasHandDefence();
		    });
		    this.getZombieWholeBody().render(stack, buffer, packedLight, packedOverlay);
		    break;
		}
		default:{
			break;
		}
		}
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
	
	@Override
	public EntityModel<T> getZombieModel() {
		return this;
	}
	
	/**
	 * get helmet to disable helmet render when drop hand.
	 */
	public Optional<ModelRenderer> getHelmet() {
		return Optional.empty();
	}
	
	/**
	 * get hand defence to disable its render when drop body.
	 */
	public Optional<ModelRenderer> getHandDefence() {
		return Optional.empty();
	}
	
	public abstract ModelRenderer getZombieLeftHand();
	
	public abstract ModelRenderer getZombieRightHand();
	
	public abstract ModelRenderer getZombieLeftLeg();
	
	public abstract ModelRenderer getZombieRightLeg();
	
	public abstract ModelRenderer getZombieHead();
	
	public abstract ModelRenderer getZombieUpBody();
	
	public abstract ModelRenderer getZombieWholeBody();
	

}
