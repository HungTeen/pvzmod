package com.hungteen.pvzmod.render.entities.npcs;

import com.hungteen.pvzmod.entities.npcs.EntityHim;
import com.hungteen.pvzmod.entities.plants.common.EntityPeaShooter;
import com.hungteen.pvzmod.model.entities.ModelHim;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



@SideOnly(Side.CLIENT)
public class RenderHim extends RenderLiving<EntityHim>
{
    private static final ResourceLocation HIM_TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/npc/him.png");

    public RenderHim(RenderManager renderManager)
    {
        super(renderManager, new ModelHim(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityHim entity)
    {
        return RenderHim.HIM_TEXTURE;
    }

    @Override
    public void doRender(EntityHim entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}