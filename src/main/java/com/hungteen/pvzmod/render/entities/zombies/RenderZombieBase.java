package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.normal.EntityFlagZombie;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCharm;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.render.layers.LayerZombieBeard;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public abstract class RenderZombieBase<T extends EntityZombieBase> extends RenderLiving<T>
{

    public RenderZombieBase(RenderManager renderManager,ModelBase model,float shadowSize)
    {
        super(renderManager, model, shadowSize);//size
        this.addNewLayer();
    }

    protected void addNewLayer()
    {
    	this.addLayer(new LayerSuperMode(this));
        this.addLayer(new LayerIceBlock(this));
        this.addLayer(new LayerCold(this));
        this.addLayer(new LayerButter(this));
        this.addLayer(new LayerCharm(this));
    }
    
    @Override
    protected void preRenderCallback(EntityZombieBase entity, float partialTickTime)
    {
    	if(entity.getIsSmall()) {
    		GlStateManager.scale(0.1F, 0.1F, 0.1F);
    	}
    	else{
    		GlStateManager.scale(0.5F, 0.5F, 0.5F);
    	}
    }
    
}

