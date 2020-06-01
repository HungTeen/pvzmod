package com.hungteen.pvzmod.render.entities.plants.ice;

import com.hungteen.pvzmod.entities.plants.ice.EntityIcebergLettuce;
import com.hungteen.pvzmod.model.entities.plants.ice.ModelIcebergLettuce;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderIcebergLettuce extends RenderPlantBase<EntityIcebergLettuce >
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/ice/iceberg_lettuce.png");
	
    public RenderIcebergLettuce(RenderManager renderManager)
    {
        super(renderManager, new ModelIcebergLettuce (), 0.5F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityIcebergLettuce  entity)
    {
    	return TEXTURE;
    }
}

