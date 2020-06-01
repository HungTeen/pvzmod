package com.hungteen.pvzmod.render.entities.plants.ice;

import com.hungteen.pvzmod.entities.plants.ice.EntityWinterMelon;
import com.hungteen.pvzmod.model.entities.plants.ice.ModelWinterMelon;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWinterMelon extends RenderPlantBase<EntityWinterMelon>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/ice/winter_melon1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/ice/winter_melon2.png");

    public RenderWinterMelon(RenderManager renderManager)
    {
        super(renderManager, new ModelWinterMelon(), 0.6F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWinterMelon entity)
    {
    	if(entity.getIsOut()==true) return TEXTURE2;
        return TEXTURE1;
    }
}