package com.hungteen.pvzmod.render.entities.special;

import com.hungteen.pvzmod.entities.special.EntityGardenRake;
import com.hungteen.pvzmod.model.entities.special.ModelGardenRake;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGardenRake extends RenderLiving<EntityGardenRake>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/tool/garden_rake.png");

    public RenderGardenRake(RenderManager renderManager)
    {
        super(renderManager, new ModelGardenRake(), 0);//size
    }

    @Override
    protected void preRenderCallback(EntityGardenRake entity, float partialTickTime)
    {
    	GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityGardenRake entity)
    {
        return TEXTURE;
    }
}

