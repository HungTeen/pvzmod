package com.hungteen.pvzmod.render.entities.plants.light;

import com.hungteen.pvzmod.entities.plants.light.EntityGoldLeaf;
import com.hungteen.pvzmod.model.entities.plants.light.ModelGoldLeaf;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGoldLeaf extends RenderPlantBase<EntityGoldLeaf>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/light/gold_leaf.png");

    public RenderGoldLeaf(RenderManager renderManager)
    {
        super(renderManager, new ModelGoldLeaf(), 0.6F);//size
    }

    @Override
    protected void preRenderCallback(EntityGoldLeaf entity, float partialTickTime)
    {
        GlStateManager.scale(1,1,1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityGoldLeaf entity)
    {
        return TEXTURE;
    }
}

