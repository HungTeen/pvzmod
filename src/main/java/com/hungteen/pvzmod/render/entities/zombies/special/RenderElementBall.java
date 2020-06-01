package com.hungteen.pvzmod.render.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.special.EntityElementBall;
import com.hungteen.pvzmod.model.entities.zombies.special.ModelElementBall;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElementBall extends RenderLiving<EntityElementBall>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/boss/flame_ball.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/boss/ice_ball.png");
    
    public RenderElementBall(RenderManager renderManager)
    {
        super(renderManager, new ModelElementBall(), 0F);//size
    }

    @Override
    protected void preRenderCallback(EntityElementBall entity, float partialTickTime)
    {
    	GlStateManager.scale(3.6F, 3.6F, 3.6F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityElementBall entity)
    {
    	if(entity.getBallState()==EntityElementBall.State.FLAME) return TEXTURE1;
    	return TEXTURE2;
    }
}