package com.hungteen.pvzmod.model.entities.plants.ice;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelIcebergLettuce extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer jiao;
	private final ModelRenderer bone;

	public ModelIcebergLettuce() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 17.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 62, 94, -8.0F, -9.0F, -8.0F, 16, 16, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 24, 106, -9.0F, 4.0F, -8.0F, 1, 3, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 27, 84, 8.0F, 4.0F, -8.0F, 1, 3, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 2, 104, -9.0F, 1.0F, -2.0F, 1, 3, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 4, 83, 8.0F, 1.0F, -2.0F, 1, 3, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 62, 86, -9.0F, -2.0F, 4.0F, 1, 3, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 50, 68, 8.0F, -2.0F, 4.0F, 1, 3, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 79, 59, -8.0F, -5.0F, 8.0F, 16, 12, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 6, 72, -8.0F, 5.0F, -9.0F, 16, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 4, 51, -1.0F, -10.0F, -6.0F, 2, 1, 13, 0.0F, false));

		jiao = new ModelRenderer(this);
		jiao.setRotationPoint(0.0F, -9.0F, -8.0F);
		setRotationAngle(jiao, -0.6981F, 0.0F, 0.0F);
		body.addChild(jiao);
		jiao.cubeList.add(new ModelBox(jiao, 112, 86, -1.0F, -2.0F, -3.0F, 2, 2, 5, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -1.0F, -3.0F);
		setRotationAngle(bone, 0.7854F, 0.0F, 1.5708F);
		jiao.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 100, 84, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}