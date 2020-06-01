package com.hungteen.pvzmod.render.entities.plants.explosion;

import com.hungteen.pvzmod.entities.plants.explosion.EntityCherryBomb;
import com.hungteen.pvzmod.model.entities.plants.explosion.ModelCherryBomb;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCherryBomb extends RenderPlantBase<EntityCherryBomb>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/explosion/cherry_bomb.png");

    public RenderCherryBomb(RenderManager renderManager)
    {
        super(renderManager, new ModelCherryBomb(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityCherryBomb entity, float partialTickTime)
    {
    	float size=entity.ticksExisted*1.0f/entity.getReadyTime()*0.2f;
        GlStateManager.scale(0.5F+size, 0.5F+size, 0.5F+size);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityCherryBomb entity)
    {
        return TEXTURE;
    }
}