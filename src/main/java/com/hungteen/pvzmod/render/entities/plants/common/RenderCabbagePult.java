package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.model.entities.plants.common.ModelCabbagePult;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCabbagePult extends RenderPlantBase<EntityCabbagePult>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/cabbage_pult1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/cabbage_pult2.png");

    public RenderCabbagePult(RenderManager renderManager)
    {
        super(renderManager, new ModelCabbagePult(), 0.6F);//size
    }

    @Override
    protected void preRenderCallback(EntityCabbagePult entity, float partialTickTime)
    {
        GlStateManager.scale(0.45F, 0.45F, 0.45F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityCabbagePult entity)
    {
    	if(entity.getIsOut()==true) return TEXTURE2;
        return TEXTURE1;
    }
}