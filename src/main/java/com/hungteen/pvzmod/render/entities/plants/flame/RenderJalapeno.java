package com.hungteen.pvzmod.render.entities.plants.flame;

import com.hungteen.pvzmod.entities.plants.flame.EntityJalapeno;
import com.hungteen.pvzmod.model.entities.plants.flame.ModelJalapeno;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJalapeno extends RenderPlantBase<EntityJalapeno>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/flame/jalapeno.png");

    public RenderJalapeno(RenderManager renderManager)
    {
        super(renderManager, new ModelJalapeno(), 0.4F);//size
    }

    @Override
    protected void preRenderCallback(EntityJalapeno entity, float partialTickTime)
    {
    	float size=entity.ticksExisted*1.0f/entity.getReadyTime()*0.2f;
        GlStateManager.scale(0.5F+size, 0.5F+size, 0.5F+size);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityJalapeno entity)
    {
        return TEXTURE;
    }
}