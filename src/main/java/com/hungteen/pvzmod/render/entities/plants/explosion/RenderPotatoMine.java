package com.hungteen.pvzmod.render.entities.plants.explosion;

import com.hungteen.pvzmod.entities.plants.explosion.EntityPotatoMine;
import com.hungteen.pvzmod.model.entities.plants.explosion.ModelPotatoMine;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPotatoMine extends RenderPlantBase<EntityPotatoMine>
{
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/explosion/potato_mine1.png");
	private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/explosion/potato_mine2.png");
	
    public RenderPotatoMine(RenderManager renderManager)
    {
        super(renderManager, new ModelPotatoMine(), 0.3F);
    }

    @Override
    protected void preRenderCallback(EntityPotatoMine entity, float partialTickTime)
    {
        GlStateManager.scale(0.6F, 0.6F, 0.6F);
        if(entity.getMineState()==EntityPotatoMine.MineState.PRE) {
        	GlStateManager.translate(0,0.6f,0);
        }
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityPotatoMine entity)
    {
    	if(entity.getSignState()) return TEXTURE1;
    	else return TEXTURE2;
    }
}

