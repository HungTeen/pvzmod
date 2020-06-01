package com.hungteen.pvzmod.render.entities.npcs;

import com.hungteen.pvzmod.entities.npcs.EntityCrazyDave;
import com.hungteen.pvzmod.entities.npcs.EntityTreeMan;
import com.hungteen.pvzmod.model.entities.ModelCrazyDave;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrazyDave extends RenderLiving<EntityCrazyDave>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/npc/crazy_dave.png");

    public RenderCrazyDave(RenderManager renderManager)
    {
        super(renderManager, new ModelCrazyDave(), 0.7F);//size
    }

    @Override
    protected void preRenderCallback(EntityCrazyDave entity, float partialTickTime)
    {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityCrazyDave entity)
    {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityCrazyDave entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
