package com.hungteen.pvzmod.render.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.model.entities.zombies.special.ModelDuckyTube;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDuckyTube extends RenderLiving<EntityDuckyTube>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/water/ducky_tube.png");

    public RenderDuckyTube(RenderManager renderManager)
    {
        super(renderManager, new ModelDuckyTube(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityDuckyTube entity, float partialTickTime)
    {
    	if(entity.getIsSmall()) {
    		GlStateManager.scale(0.1F, 0.1F, 0.1F);
    	}
    	else{
    		GlStateManager.scale(0.5F, 0.5F, 0.5F);
    	}
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityDuckyTube entity)
    {
    	return TEXTURE;
    }
}