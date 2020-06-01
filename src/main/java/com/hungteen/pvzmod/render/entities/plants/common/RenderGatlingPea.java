package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityGatlingPea;
import com.hungteen.pvzmod.model.entities.plants.common.ModelGatlingPea;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGatlingPea extends RenderPlantBase<EntityGatlingPea>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/gatling_pea.png");

    public RenderGatlingPea(RenderManager renderManager)
    {
        super(renderManager, new ModelGatlingPea(), 0.4F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGatlingPea entity)
    {
        return TEXTURE;
    }
}
