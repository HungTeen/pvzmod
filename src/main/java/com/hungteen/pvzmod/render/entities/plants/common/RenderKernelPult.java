package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;
import com.hungteen.pvzmod.model.entities.plants.common.ModelKernelPult;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKernelPult extends RenderPlantBase<EntityKernelPult>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/kernel_pult_common.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/kernel_pult_butter.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/kernel_pult_none.png");
    
    public RenderKernelPult(RenderManager renderManager)
    {
        super(renderManager, new ModelKernelPult(), 0.6F);//size
    }

    @Override
    protected void preRenderCallback(EntityKernelPult entity, float partialTickTime)
    {
        GlStateManager.scale(0.45F, 0.45F, 0.45F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityKernelPult entity)
    {
    	if(entity.getIsOut()) return TEXTURE3;
    	if(entity.getIsButterNow()) return TEXTURE2;
        return TEXTURE1;
    }
}
