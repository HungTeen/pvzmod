package com.hungteen.pvzmod.render.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.special.EntityBalloon;
import com.hungteen.pvzmod.model.entities.zombies.special.ModelBalloon;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBalloon extends RenderLiving<EntityBalloon>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/sky/balloon.png");

    public RenderBalloon(RenderManager renderManager)
    {
        super(renderManager, new ModelBalloon(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityBalloon entity, float partialTickTime)
    {
    	if(entity.getIsSmall()) {
    		GlStateManager.scale(0.1F, 0.1F, 0.1F);
    	}
    	else{
    		GlStateManager.scale(0.5F, 0.5F, 0.5F);
    	}
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityBalloon entity)
    {
    	return TEXTURE;
    }
}
