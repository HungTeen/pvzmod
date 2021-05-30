package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.FireCrackersEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class FireCrackersModel extends EntityModel<FireCrackersEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bombs;
	private final ModelRenderer bone;
	private final ModelRenderer bomb1;
	private final ModelRenderer bomb2;
	private final ModelRenderer bone2;
	private final ModelRenderer bomb3;
	private final ModelRenderer bomb4;
	private final ModelRenderer bone3;
	private final ModelRenderer bomb5;
	private final ModelRenderer bomb6;
	private final ModelRenderer bone4;
	private final ModelRenderer bomb7;
	private final ModelRenderer bomb8;
	private final ModelRenderer bone5;
	private final ModelRenderer bomb9;
	private final ModelRenderer bomb10;
	private final ModelRenderer bone6;
	private final ModelRenderer bomb11;
	private final ModelRenderer bomb12;
	private final ModelRenderer bone7;
	private final ModelRenderer bomb13;
	private final ModelRenderer bomb14;
	private final ModelRenderer bone8;
	private final ModelRenderer bomb15;
	private final ModelRenderer bomb16;
	private final ModelRenderer bone9;
	private final ModelRenderer bomb17;
	private final ModelRenderer bomb18;
	private final ModelRenderer bone10;
	private final ModelRenderer bomb19;
	private final ModelRenderer bomb20;
	private final ModelRenderer bone11;
	private final ModelRenderer bomb21;
	private final ModelRenderer bomb22;
	private final ModelRenderer bone12;
	private final ModelRenderer bomb23;
	private final ModelRenderer bomb24;
	private final ModelRenderer bone13;
	private final ModelRenderer bomb25;
	private final ModelRenderer bomb26;
	private final ModelRenderer bone14;
	private final ModelRenderer bomb27;
	private final ModelRenderer bomb28;

	public FireCrackersModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 21.25F, 0.0F);
		setRotationAngle(total, 0.0F, 0.0F, 0.7854F);
		total.texOffs(0, 46).addBox(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 17.0F, -0.8F, false);
		total.texOffs(0, 46).addBox(-1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 2.0F, -0.75F, false);

		bombs = new ModelRenderer(this);
		bombs.setPos(0.0F, 1.0F, 0.0F);
		total.addChild(bombs);
		

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 8.0F);
		bombs.addChild(bone);
		

		bomb1 = new ModelRenderer(this);
		bomb1.setPos(-0.25F, 0.0F, -1.0F);
		bone.addChild(bomb1);
		setRotationAngle(bomb1, 0.0F, -0.5236F, 0.0F);
		bomb1.texOffs(0, 58).addBox(-4.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb1.texOffs(0, 54).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb2 = new ModelRenderer(this);
		bomb2.setPos(0.25F, 0.0F, -1.0F);
		bone.addChild(bomb2);
		setRotationAngle(bomb2, 0.0F, -2.618F, 0.0F);
		bomb2.texOffs(0, 58).addBox(-4.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb2.texOffs(0, 54).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 6.0F);
		bombs.addChild(bone2);
		

		bomb3 = new ModelRenderer(this);
		bomb3.setPos(-0.25F, 0.0F, -1.0F);
		bone2.addChild(bomb3);
		setRotationAngle(bomb3, 0.0F, -0.5236F, 0.0F);
		bomb3.texOffs(0, 58).addBox(-4.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb3.texOffs(0, 54).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb4 = new ModelRenderer(this);
		bomb4.setPos(0.25F, 0.0F, -1.0F);
		bone2.addChild(bomb4);
		setRotationAngle(bomb4, 0.0F, -2.618F, 0.0F);
		bomb4.texOffs(0, 58).addBox(-4.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb4.texOffs(0, 54).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 6.0F);
		bombs.addChild(bone3);
		

		bomb5 = new ModelRenderer(this);
		bomb5.setPos(-0.25F, 0.0F, -1.0F);
		bone3.addChild(bomb5);
		setRotationAngle(bomb5, 0.0F, -0.5236F, 0.0F);
		bomb5.texOffs(0, 58).addBox(-5.0F, -2.0F, -2.7321F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb5.texOffs(0, 54).addBox(-3.0F, -2.0F, -2.7321F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb6 = new ModelRenderer(this);
		bomb6.setPos(0.25F, 0.0F, -1.0F);
		bone3.addChild(bomb6);
		setRotationAngle(bomb6, 0.0F, -2.618F, 0.0F);
		bomb6.texOffs(0, 58).addBox(-5.0F, -2.0F, 0.7321F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb6.texOffs(0, 54).addBox(-3.0F, -2.0F, 0.7321F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.0F, 4.0F);
		bombs.addChild(bone4);
		

		bomb7 = new ModelRenderer(this);
		bomb7.setPos(-0.25F, 0.0F, -1.0F);
		bone4.addChild(bomb7);
		setRotationAngle(bomb7, 0.0F, -0.5236F, 0.0F);
		bomb7.texOffs(0, 58).addBox(-5.0F, -2.0F, -2.7321F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb7.texOffs(0, 54).addBox(-3.0F, -2.0F, -2.7321F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb8 = new ModelRenderer(this);
		bomb8.setPos(0.25F, 0.0F, -1.0F);
		bone4.addChild(bomb8);
		setRotationAngle(bomb8, 0.0F, -2.618F, 0.0F);
		bomb8.texOffs(0, 58).addBox(-5.0F, -2.0F, 0.7321F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb8.texOffs(0, 54).addBox(-3.0F, -2.0F, 0.7321F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.0F, 4.0F);
		bombs.addChild(bone5);
		

		bomb9 = new ModelRenderer(this);
		bomb9.setPos(-0.25F, 0.0F, -1.0F);
		bone5.addChild(bomb9);
		setRotationAngle(bomb9, 0.0F, -0.5236F, 0.0F);
		bomb9.texOffs(0, 58).addBox(-6.0F, -2.0F, -4.4641F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb9.texOffs(0, 54).addBox(-4.0F, -2.0F, -4.4641F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb10 = new ModelRenderer(this);
		bomb10.setPos(0.25F, 0.0F, -1.0F);
		bone5.addChild(bomb10);
		setRotationAngle(bomb10, 0.0F, -2.618F, 0.0F);
		bomb10.texOffs(0, 58).addBox(-6.0F, -2.0F, 2.4641F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb10.texOffs(0, 54).addBox(-4.0F, -2.0F, 2.4641F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 4.0F);
		bombs.addChild(bone6);
		

		bomb11 = new ModelRenderer(this);
		bomb11.setPos(-0.25F, 0.0F, -1.0F);
		bone6.addChild(bomb11);
		setRotationAngle(bomb11, 0.0F, -0.5236F, 0.0F);
		bomb11.texOffs(0, 58).addBox(-7.0F, -2.0F, -6.1962F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb11.texOffs(0, 54).addBox(-5.0F, -2.0F, -6.1962F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb12 = new ModelRenderer(this);
		bomb12.setPos(0.25F, 0.0F, -1.0F);
		bone6.addChild(bomb12);
		setRotationAngle(bomb12, 0.0F, -2.618F, 0.0F);
		bomb12.texOffs(0, 58).addBox(-7.0F, -2.0F, 4.1962F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb12.texOffs(0, 54).addBox(-5.0F, -2.0F, 4.1962F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 0.0F, 4.0F);
		bombs.addChild(bone7);
		

		bomb13 = new ModelRenderer(this);
		bomb13.setPos(-0.25F, 0.0F, -1.0F);
		bone7.addChild(bomb13);
		setRotationAngle(bomb13, 0.0F, -0.5236F, 0.0F);
		bomb13.texOffs(0, 58).addBox(-8.0F, -2.0F, -7.9282F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb13.texOffs(0, 54).addBox(-6.0F, -2.0F, -7.9282F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb14 = new ModelRenderer(this);
		bomb14.setPos(0.25F, 0.0F, -1.0F);
		bone7.addChild(bomb14);
		setRotationAngle(bomb14, 0.0F, -2.618F, 0.0F);
		bomb14.texOffs(0, 58).addBox(-8.0F, -2.0F, 5.9282F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb14.texOffs(0, 54).addBox(-6.0F, -2.0F, 5.9282F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 1.5708F);
		

		bomb15 = new ModelRenderer(this);
		bomb15.setPos(-0.25F, 1.0F, 0.0F);
		bone8.addChild(bomb15);
		setRotationAngle(bomb15, 0.0F, -0.5236F, 0.0F);
		bomb15.texOffs(0, 58).addBox(-4.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb15.texOffs(0, 54).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb16 = new ModelRenderer(this);
		bomb16.setPos(0.25F, 1.0F, 0.0F);
		bone8.addChild(bomb16);
		setRotationAngle(bomb16, 0.0F, -2.618F, 0.0F);
		bomb16.texOffs(0, 58).addBox(-4.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb16.texOffs(0, 54).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, 1.5708F);
		

		bomb17 = new ModelRenderer(this);
		bomb17.setPos(-0.25F, 1.0F, 0.0F);
		bone9.addChild(bomb17);
		setRotationAngle(bomb17, 0.0F, -0.5236F, 0.0F);
		bomb17.texOffs(0, 58).addBox(-5.0F, -2.0F, -2.7321F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb17.texOffs(0, 54).addBox(-3.0F, -2.0F, -2.7321F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb18 = new ModelRenderer(this);
		bomb18.setPos(0.25F, 1.0F, 0.0F);
		bone9.addChild(bomb18);
		setRotationAngle(bomb18, 0.0F, -2.618F, 0.0F);
		bomb18.texOffs(0, 58).addBox(-5.0F, -2.0F, 0.7321F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb18.texOffs(0, 54).addBox(-3.0F, -2.0F, 0.7321F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, 1.5708F);
		

		bomb19 = new ModelRenderer(this);
		bomb19.setPos(-0.25F, 1.0F, 0.0F);
		bone10.addChild(bomb19);
		setRotationAngle(bomb19, 0.0F, -0.5236F, 0.0F);
		bomb19.texOffs(0, 58).addBox(-6.0F, -2.0F, -4.4641F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb19.texOffs(0, 54).addBox(-4.0F, -2.0F, -4.4641F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb20 = new ModelRenderer(this);
		bomb20.setPos(0.25F, 1.0F, 0.0F);
		bone10.addChild(bomb20);
		setRotationAngle(bomb20, 0.0F, -2.618F, 0.0F);
		bomb20.texOffs(0, 58).addBox(-6.0F, -2.0F, 2.4641F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb20.texOffs(0, 54).addBox(-4.0F, -2.0F, 2.4641F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 1.5708F);
		

		bomb21 = new ModelRenderer(this);
		bomb21.setPos(-0.25F, 1.0F, 0.0F);
		bone11.addChild(bomb21);
		setRotationAngle(bomb21, 0.0F, -0.5236F, 0.0F);
		bomb21.texOffs(0, 58).addBox(-7.0F, -2.0F, -6.1962F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb21.texOffs(0, 54).addBox(-5.0F, -2.0F, -6.1962F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb22 = new ModelRenderer(this);
		bomb22.setPos(0.25F, 1.0F, 0.0F);
		bone11.addChild(bomb22);
		setRotationAngle(bomb22, 0.0F, -2.618F, 0.0F);
		bomb22.texOffs(0, 58).addBox(-7.0F, -2.0F, 4.1962F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb22.texOffs(0, 54).addBox(-5.0F, -2.0F, 4.1962F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 1.5708F);
		

		bomb23 = new ModelRenderer(this);
		bomb23.setPos(-0.25F, 1.0F, 0.0F);
		bone12.addChild(bomb23);
		setRotationAngle(bomb23, 0.0F, -0.5236F, 0.0F);
		bomb23.texOffs(0, 58).addBox(-8.0F, -2.0F, -7.9282F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb23.texOffs(0, 54).addBox(-6.0F, -2.0F, -7.9282F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb24 = new ModelRenderer(this);
		bomb24.setPos(0.25F, 1.0F, 0.0F);
		bone12.addChild(bomb24);
		setRotationAngle(bomb24, 0.0F, -2.618F, 0.0F);
		bomb24.texOffs(0, 58).addBox(-8.0F, -2.0F, 5.9282F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb24.texOffs(0, 54).addBox(-6.0F, -2.0F, 5.9282F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, 1.5708F);
		

		bomb25 = new ModelRenderer(this);
		bomb25.setPos(-0.25F, 1.0F, 0.0F);
		bone13.addChild(bomb25);
		setRotationAngle(bomb25, 0.0F, -0.5236F, 0.0F);
		bomb25.texOffs(0, 58).addBox(-9.0F, -2.0F, -9.6603F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb25.texOffs(0, 54).addBox(-7.0F, -2.0F, -9.6603F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb26 = new ModelRenderer(this);
		bomb26.setPos(0.25F, 1.0F, 0.0F);
		bone13.addChild(bomb26);
		setRotationAngle(bomb26, 0.0F, -2.618F, 0.0F);
		bomb26.texOffs(0, 58).addBox(-9.0F, -2.0F, 7.6603F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb26.texOffs(0, 54).addBox(-7.0F, -2.0F, 7.6603F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, -1.0F, 6.0F);
		bombs.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, 1.5708F);
		

		bomb27 = new ModelRenderer(this);
		bomb27.setPos(-0.25F, 1.0F, 0.0F);
		bone14.addChild(bomb27);
		setRotationAngle(bomb27, 0.0F, -0.5236F, 0.0F);
		bomb27.texOffs(0, 58).addBox(-10.0F, -2.0F, -11.3923F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb27.texOffs(0, 54).addBox(-8.0F, -2.0F, -11.3923F, 3.0F, 2.0F, 2.0F, -0.82F, false);

		bomb28 = new ModelRenderer(this);
		bomb28.setPos(0.25F, 1.0F, 0.0F);
		bone14.addChild(bomb28);
		setRotationAngle(bomb28, 0.0F, -2.618F, 0.0F);
		bomb28.texOffs(0, 58).addBox(-10.0F, -2.0F, 9.3923F, 4.0F, 2.0F, 2.0F, -0.5F, false);
		bomb28.texOffs(0, 54).addBox(-8.0F, -2.0F, 9.3923F, 3.0F, 2.0F, 2.0F, -0.82F, false);
	}

	@Override
	public void setupAnim(FireCrackersEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}