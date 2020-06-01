package com.hungteen.pvzmod.render.entities.plants.magic;

import com.hungteen.pvzmod.entities.plants.magic.EntityHypnoShroom;
import com.hungteen.pvzmod.model.entities.plants.magic.ModelHypnoShroom;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerCharm;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHypnoShroom extends RenderPlantBase<EntityHypnoShroom>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/magic/hypno_shroom.png");

    public RenderHypnoShroom(RenderManager renderManager)
    {
        super(renderManager, new ModelHypnoShroom(), 0.5F);//size
    }
    
    @Override
    protected void addPlantLayers() {
    	this.addLayer(new LayerCharm(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHypnoShroom entity)
    {
        return TEXTURE;
    }
}

