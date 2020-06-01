package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;
import com.hungteen.pvzmod.model.entities.plants.common.ModelMelonPult;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMelonPult extends RenderPlantBase<EntityMelonPult>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/melon_pult1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/melon_pult2.png");

    public RenderMelonPult(RenderManager renderManager)
    {
        super(renderManager, new ModelMelonPult(), 0.6F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMelonPult entity)
    {
    	if(entity.getIsOut()==true) return TEXTURE2;
        return TEXTURE1;
    }
}