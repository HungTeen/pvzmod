package com.hungteen.pvzmod.render.entities.plants;

import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;

public abstract class RenderPlantBase<T extends EntityPlantBase> extends RenderLiving<T>
{

    public RenderPlantBase(RenderManager renderManager,ModelBase model,float shadowSize)
    {
        super(renderManager, model, shadowSize);//size
        addPlantLayers();
    }
    
    protected void addPlantLayers()
    {
    	
    }
    
    @Override
    protected void preRenderCallback(EntityPlantBase entity, float partialTickTime)
    {
    	GlStateManager.scale(0.5F, 0.5F, 0.5F);
    }
    
}

