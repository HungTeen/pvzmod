package com.hungteen.pvzmod.render.entities.plants.flame;

import com.hungteen.pvzmod.entities.plants.flame.EntityTorchWood;
import com.hungteen.pvzmod.model.entities.plants.flame.ModelTorchWood;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTorchWood extends RenderPlantBase<EntityTorchWood>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/flame/torch_wood1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/flame/torch_wood2.png");

    public RenderTorchWood(RenderManager renderManager)
    {
        super(renderManager, new ModelTorchWood(), 0.4F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTorchWood entity)
    {
    	if(entity.isPlantInSuperMode()) return TEXTURE2;
        return TEXTURE1;
    }

}

