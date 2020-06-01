package com.hungteen.pvzmod.render.entities.plants.light;

import com.hungteen.pvzmod.entities.plants.light.EntitySunFlower;
import com.hungteen.pvzmod.model.entities.plants.light.ModelSunFlower;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSunLight;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSunFlower extends RenderPlantBase<EntitySunFlower>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/light/sun_flower.png");

    public RenderSunFlower(RenderManager renderManager)
    {
        super(renderManager, new ModelSunFlower(), 0.4F);//size
    }
    
    @Override
    protected void addPlantLayers() {
    	this.addLayer(new LayerSunLight(this));
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntitySunFlower entity)
    {
        return TEXTURE;
    }
}
