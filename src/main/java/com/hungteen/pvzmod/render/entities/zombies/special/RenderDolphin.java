package com.hungteen.pvzmod.render.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.special.EntityDolphin;
import com.hungteen.pvzmod.model.entities.zombies.special.ModelDolphin;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDolphin extends RenderLiving<EntityDolphin>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/water/dolphin.png");

    public RenderDolphin(RenderManager renderManager)
    {
        super(renderManager, new ModelDolphin(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityDolphin entity, float partialTickTime)
    {
    	if(entity.getIsSmall()) {
    		GlStateManager.scale(0.1F, 0.1F, 0.1F);
    	}
    	else{
    		GlStateManager.scale(0.5F, 0.5F, 0.5F);
    	}
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityDolphin entity)
    {
    	return TEXTURE;
    }
}
