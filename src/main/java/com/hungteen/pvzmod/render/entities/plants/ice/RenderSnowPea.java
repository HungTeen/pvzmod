package com.hungteen.pvzmod.render.entities.plants.ice;

import com.hungteen.pvzmod.entities.plants.ice.EntitySnowPea;
import com.hungteen.pvzmod.model.entities.plants.ice.ModelSnowPea;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSnowPea extends RenderPlantBase<EntitySnowPea>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/ice/snow_pea.png");

    public RenderSnowPea(RenderManager renderManager)
    {
        super(renderManager, new ModelSnowPea(), 0.4F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySnowPea entity)
    {
        return TEXTURE;
    }
}
