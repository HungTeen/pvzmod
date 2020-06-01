package com.hungteen.pvzmod.render.entities.plants.fight;

import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.model.entities.plants.fight.ModelSpikeWeed;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpikeWeed extends RenderPlantBase<EntitySpikeWeed>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/fight/spike_weed.png");

    public RenderSpikeWeed(RenderManager renderManager)
    {
        super(renderManager, new ModelSpikeWeed(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySpikeWeed entity)
    {
        return TEXTURE;
    }
}
