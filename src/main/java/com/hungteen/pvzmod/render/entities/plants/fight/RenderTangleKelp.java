package com.hungteen.pvzmod.render.entities.plants.fight;

import com.hungteen.pvzmod.entities.plants.fight.EntityTangleKelp;
import com.hungteen.pvzmod.model.entities.plants.fight.ModelTangleKelp;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTangleKelp extends RenderPlantBase<EntityTangleKelp>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/fight/tangle_kelp.png");

    public RenderTangleKelp(RenderManager renderManager)
    {
        super(renderManager, new ModelTangleKelp(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityTangleKelp entity, float partialTickTime)
    {
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityTangleKelp entity)
    {
        return TEXTURE;
    }
}
