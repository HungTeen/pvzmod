package com.hungteen.pvzmod.render.entities.plants.magic;

import com.hungteen.pvzmod.entities.plants.magic.EntityStrangeCat;
import com.hungteen.pvzmod.model.entities.plants.magic.ModelCatTail;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStrangeCat extends RenderPlantBase<EntityStrangeCat>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/magic/strange_cat.png");

    public RenderStrangeCat(RenderManager renderManager)
    {
        super(renderManager, new ModelCatTail(), 0.6F);//size
    }

    @Override
    protected void preRenderCallback(EntityStrangeCat entity, float partialTickTime)
    {
        GlStateManager.scale(0.15F, 0.15F, 0.15F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityStrangeCat entity)
    {
        return TEXTURE;
    }
}