package com.hungteen.pvzmod.render.entities.plants.magic;

import com.hungteen.pvzmod.entities.plants.magic.EntityCatTail;
import com.hungteen.pvzmod.model.entities.plants.magic.ModelCatTail;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCatTail extends RenderPlantBase<EntityCatTail>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/magic/cat_tail.png");

    public RenderCatTail(RenderManager renderManager)
    {
        super(renderManager, new ModelCatTail(), 0.6F);//size
    }

    @Override
    protected void preRenderCallback(EntityCatTail entity, float partialTickTime)
    {
        GlStateManager.scale(0.15F, 0.15F, 0.15F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityCatTail entity)
    {
        return TEXTURE;
    }
}