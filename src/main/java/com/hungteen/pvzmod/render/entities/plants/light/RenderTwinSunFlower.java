package com.hungteen.pvzmod.render.entities.plants.light;

import com.hungteen.pvzmod.entities.plants.light.EntityTwinSunFlower;
import com.hungteen.pvzmod.model.entities.plants.light.ModelTwinSunFlower;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSunLight;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTwinSunFlower extends RenderPlantBase<EntityTwinSunFlower>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/light/twin_sunflower.png");

    public RenderTwinSunFlower(RenderManager renderManager)
    {
        super(renderManager, new ModelTwinSunFlower(), 0.4F);//size
    }
    
    @Override
    protected void addPlantLayers() {
    	this.addLayer(new LayerSunLight(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTwinSunFlower entity)
    {
        return TEXTURE;
    }
}
