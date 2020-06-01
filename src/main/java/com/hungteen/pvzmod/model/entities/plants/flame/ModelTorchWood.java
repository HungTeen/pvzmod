package com.hungteen.pvzmod.model.entities.plants.flame;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelTorchWood extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer head1;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;

	public ModelTorchWood() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 149, 199, -13.0F, -30.0F, -13.0F, 26, 27, 26, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 91, 222, -12.0F, -32.0F, -14.0F, 24, 29, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 33, 220, -12.0F, -32.0F, 13.0F, 24, 29, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 22, 156, -14.0F, -32.0F, -12.0F, 1, 29, 24, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 82, 164, 13.0F, -32.0F, -12.0F, 1, 29, 24, 0.0F, false));

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(0.0F, -32.0F, -14.0F);
		setRotationAngle(head1, -0.7854F, 0.0F, 0.0F);
		body.addChild(head1);
		head1.cubeList.add(new ModelBox(head1, 200, 187, -12.0F, -1.0F, -2.0F, 24, 1, 3, 0.0F, false));

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -32.0F, 14.0F);
		setRotationAngle(head2, 0.7854F, 0.0F, 0.0F);
		body.addChild(head2);
		head2.cubeList.add(new ModelBox(head2, 196, 174, -12.0F, -1.0F, -1.0F, 24, 1, 3, 0.0F, false));

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(-13.0F, -32.0F, 0.0F);
		setRotationAngle(head3, 0.0F, 0.0F, 0.7854F);
		body.addChild(head3);
		head3.cubeList.add(new ModelBox(head3, 200, 140, -3.0F, 0.0F, -12.0F, 3, 1, 24, 0.0F, false));

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(13.0F, -32.0F, 0.0F);
		setRotationAngle(head4, 0.0F, 0.0F, -0.7854F);
		body.addChild(head4);
		head4.cubeList.add(new ModelBox(head4, 201, 108, 0.0F, 0.0F, -12.0F, 3, 1, 24, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 180, 68, -5.0F, -1.0F, -19.0F, 10, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 180, 68, -5.0F, -3.0F, -17.0F, 10, 3, 25, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 180, 68, -5.0F, -2.0F, -18.0F, 10, 2, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 174, 11, -5.0F, -1.0F, -19.0F, 10, 1, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 174, 11, -5.0F, -3.0F, -17.0F, 10, 3, 25, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 174, 11, -5.0F, -2.0F, -18.0F, 10, 2, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 174, 11, -4.0F, -1.0F, -19.0F, 8, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 174, 11, -4.0F, -3.0F, -17.0F, 8, 3, 25, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 174, 11, -4.0F, -2.0F, -18.0F, 8, 2, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 130, 114, -5.0F, -1.0F, -19.0F, 10, 1, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 130, 114, -5.0F, -3.0F, -17.0F, 10, 3, 25, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 130, 114, -5.0F, -2.0F, -18.0F, 10, 2, 1, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, -2.3562F, 0.0F);
		bone5.cubeList.add(new ModelBox(bone5, 94, 42, -5.0F, -1.0F, -19.0F, 10, 1, 1, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 94, 42, -5.0F, -3.0F, -17.0F, 10, 3, 25, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 94, 42, -5.0F, -2.0F, -18.0F, 10, 2, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, 2.3562F, 0.0F);
		bone6.cubeList.add(new ModelBox(bone6, 96, 2, -5.0F, -1.0F, -19.0F, 10, 1, 1, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 96, 2, -5.0F, -3.0F, -17.0F, 10, 3, 25, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 96, 2, -5.0F, -2.0F, -18.0F, 10, 2, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		bone.render(f5);
		bone2.render(f5);
		bone4.render(f5);
		bone5.render(f5);
		bone6.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}