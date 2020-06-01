package com.hungteen.pvzmod.render.entities.plants.electricity;

import com.hungteen.pvzmod.entities.plants.electricity.EntityLightningRod;
import com.hungteen.pvzmod.model.entities.plants.electricity.ModelLightningRod;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLightningRod extends RenderPlantBase<EntityLightningRod>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/electricity/lightning_rod.png");

    public RenderLightningRod(RenderManager renderManager)
    {
        super(renderManager, new ModelLightningRod(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityLightningRod entity, float partialTickTime)
    {
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityLightningRod entity)
    {
        return TEXTURE;
    }
}
