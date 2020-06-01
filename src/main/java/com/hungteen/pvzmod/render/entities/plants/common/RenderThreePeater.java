package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityThreePeater;
import com.hungteen.pvzmod.model.entities.plants.common.ModelThreePeater;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderThreePeater extends RenderPlantBase<EntityThreePeater>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/three_peater.png");

    public RenderThreePeater(RenderManager renderManager)
    {
        super(renderManager, new ModelThreePeater(), 0.4F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityThreePeater entity)
    {
        return TEXTURE;
    }
}