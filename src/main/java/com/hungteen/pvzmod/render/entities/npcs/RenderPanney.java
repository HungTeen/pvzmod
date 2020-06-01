package com.hungteen.pvzmod.render.entities.npcs;

import com.hungteen.pvzmod.entities.npcs.EntityPanney;
import com.hungteen.pvzmod.entities.npcs.EntityTreeMan;
import com.hungteen.pvzmod.model.entities.ModelPanney;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPanney extends RenderLiving<EntityPanney>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/npc/panney.png");

    public RenderPanney(RenderManager renderManager)
    {
        super(renderManager, new ModelPanney(),1F);//size
    }

    @Override
    protected void preRenderCallback(EntityPanney entity, float partialTickTime)
    {
        GlStateManager.scale(2.5F, 2.5F, 2.5F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityPanney entity)
    {
        return TEXTURE;
    }
}
