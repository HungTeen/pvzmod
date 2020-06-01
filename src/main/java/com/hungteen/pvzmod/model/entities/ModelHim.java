package com.hungteen.pvzmod.model.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;


	public class ModelHim extends ModelBiped

	{
		//fields
		  ModelRenderer bipedHead;
		    ModelRenderer body;
		    ModelRenderer rightarm;
		    ModelRenderer leftarm;
		    ModelRenderer rightleg;
		    ModelRenderer leftleg;
		  
	    
		public ModelHim()
		{ 
			 textureWidth = 64;
			    textureHeight = 32;
			    
			    bipedHead = new ModelRenderer(this, 0, 0);
			    bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
			    bipedHead.setRotationPoint(0F, 0F, 0F);
			    bipedHead.setTextureSize(64, 32);
			    bipedHead.mirror = true;
			      setRotation(bipedHead, 0F, 0F, 0F);
			      body = new ModelRenderer(this, 16, 16);
			      body.addBox(-4F, 0F, -2F, 8, 12, 4);
			      body.setRotationPoint(0F, 0F, 0F);
			      body.setTextureSize(64, 32);
			      body.mirror = true;
			      setRotation(body, 0F, 0F, 0F);
			      rightarm = new ModelRenderer(this, 40, 16);
			      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
			      rightarm.setRotationPoint(-5F, 2F, 0F);
			      rightarm.setTextureSize(64, 32);
			      rightarm.mirror = true;
			      setRotation(rightarm, 0F, 0F, 0F);
			      leftarm = new ModelRenderer(this, 40, 16);
			      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
			      leftarm.setRotationPoint(5F, 2F, 0F);
			      leftarm.setTextureSize(64, 32);
			      leftarm.mirror = true;
			      setRotation(leftarm, 0F, 0F, 0F);
			      rightleg = new ModelRenderer(this, 0, 16);
			      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
			      rightleg.setRotationPoint(-2F, 12F, 0F);
			      rightleg.setTextureSize(64, 32);
			      rightleg.mirror = true;
			      setRotation(rightleg, 0F, 0F, 0F);
			      leftleg = new ModelRenderer(this, 0, 16);
			      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
			      leftleg.setRotationPoint(2F, 12F, 0F);
			      leftleg.setTextureSize(64, 32);
			      leftleg.mirror = true;
			      setRotation(leftleg, 0F, 0F, 0F);
			  }
		
		@Override
	   
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
		{
		
			super.render(entity, f, f1, f2, f3, f4, f5);
		
			bipedHead.render(f5);
		    body.render(f5);
		    rightarm.render(f5);
		    leftarm.render(f5);
		    rightleg.render(f5);
		    leftleg.render(f5);
		}
		 

		private void setRotation(ModelRenderer model, float x, float y, float z)
		{
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}

	       
	    
		

		public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
	    {
	        this.bipedHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
	        this.bipedHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
	        this.rightleg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
	        this.leftleg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
	        this.rightarm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
	        this.leftarm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
	       
	    }

	}
