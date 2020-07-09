package com.hungteen.pvzmod.render.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.grassnight.EntityTombStone;
import com.hungteen.pvzmod.model.entities.zombies.grassnight.ModelTombStone;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTombStone extends RenderLiving<EntityTombStone>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/tomb/tombstone1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/tomb/tombstone2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/tomb/tombstone3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/tomb/tombstone4.png");
    
    public RenderTombStone(RenderManager renderManager)
    {
        super(renderManager, new ModelTombStone(), 0.5F);//size
    }

    @Override
    protected void preRenderCallback(EntityTombStone entity, float partialTickTime)
    {
    	GlStateManager.scale(0.9F, 0.9F, 0.9F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityTombStone entity)
    {
    	float life=entity.getHealth();
    	if(life+15>=70) return TEXTURE1;
    	else if(life+30>=70) return TEXTURE2;
    	else if(life+45>=70) return TEXTURE3;
    	return TEXTURE4;
    }
}