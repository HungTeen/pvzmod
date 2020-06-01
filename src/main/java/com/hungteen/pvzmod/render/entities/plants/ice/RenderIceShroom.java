package com.hungteen.pvzmod.render.entities.plants.ice;

import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.model.entities.plants.ice.ModelIceShroom;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderIceShroom extends RenderPlantBase<EntityIceShroom>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/ice/ice_shroom.png");

    public RenderIceShroom(RenderManager renderManager)
    {
        super(renderManager, new ModelIceShroom(), 0.4F);//size
    }

    @Override
    protected void preRenderCallback(EntityIceShroom entity, float partialTickTime)
    {
        GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityIceShroom entity)
    {
        return TEXTURE;
    }
}

