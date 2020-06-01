package com.hungteen.pvzmod.model.entities;

//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCoin extends ModelBase {
	private final ModelRenderer GC;

	public ModelCoin() {
		textureWidth = 128;
		textureHeight = 128;

		GC = new ModelRenderer(this);
		GC.setRotationPoint(3.0F, 24.0F, 0.0F);
		GC.cubeList.add(new ModelBox(GC, 0, 0, -35.0F, -64.0F, 0.0F, 64, 64, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GC.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.GC.rotateAngleY = ageInTicks*0.02f;
    }
}