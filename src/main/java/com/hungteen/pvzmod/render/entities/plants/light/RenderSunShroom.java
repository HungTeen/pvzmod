package com.hungteen.pvzmod.render.entities.plants.light;

import com.hungteen.pvzmod.entities.plants.light.EntitySunShroom;
import com.hungteen.pvzmod.model.entities.plants.light.ModelSunShroom;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSunShroom extends RenderPlantBase<EntitySunShroom>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/light/sun_shroom.png");

    public RenderSunShroom(RenderManager renderManager)
    {
        super(renderManager, new ModelSunShroom(), 0.3F);//size
    }

    @Override
    protected void preRenderCallback(EntitySunShroom entity, float partialTickTime)
    {
        GlStateManager.scale(0.2F, 0.2F, 0.2F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntitySunShroom entity)
    {
        return TEXTURE;
    }
}

