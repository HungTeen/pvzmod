package com.hungteen.pvzmod.render.entities.drops;

import com.hungteen.pvzmod.entities.drops.EntityPlantFood;
import com.hungteen.pvzmod.model.entities.ModelPlantFood;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlantFood extends RenderLiving<EntityPlantFood>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/drop/plant_food.png");

    public RenderPlantFood(RenderManager renderManager)
    {
        super(renderManager, new ModelPlantFood(), 0F);//size
    }
    
    @Override
    protected void preRenderCallback(EntityPlantFood entity, float partialTickTime)
    {
    	GlStateManager.scale(0.45f,0.45f,0.45f);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityPlantFood entity)
    {
        return TEXTURE;
    }

    @Override
    public void doRender(EntityPlantFood entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
