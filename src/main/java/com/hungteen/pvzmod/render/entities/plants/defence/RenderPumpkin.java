package com.hungteen.pvzmod.render.entities.plants.defence;

import com.hungteen.pvzmod.entities.plants.defence.EntityPumpkin;
import com.hungteen.pvzmod.model.entities.plants.defence.ModelPumpkin;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPumpkin extends RenderPlantBase<EntityPumpkin>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/defence/pumpkin1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/defence/pumpkin2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/defence/pumpkin3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/defence/pumpkin4.png");
    private static final ResourceLocation TEXTURE5 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/defence/pumpkin5.png");
    private static final ResourceLocation TEXTURE6 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/defence/pumpkin6.png");

    public RenderPumpkin(RenderManager renderManager)
    {
        super(renderManager, new ModelPumpkin(), 1F);//size
    }

    @Override
    protected void preRenderCallback(EntityPumpkin entity, float partialTickTime)
    {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityPumpkin entity)
    {
    	float life=entity.getHealth();
    	float normalLife=entity.getLife();
    	float superLife=entity.getSuperLife();
    	if(life>normalLife) {
    		life-=normalLife;
    		if(life>2*superLife/3) return TEXTURE4;
    		else if(life>superLife/3) return TEXTURE5;
    		return TEXTURE6;
    	}
    	else {
    		if(life>2*normalLife/3) return TEXTURE1;
    		else if(life>normalLife/3) return TEXTURE2;
    		return TEXTURE3;
    	}
    }
}