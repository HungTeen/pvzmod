package com.hungteen.pvzmod.render.entities.zombies.special;

import com.hungteen.pvzmod.entities.zombies.special.EntityJackOutBoxZombie;
import com.hungteen.pvzmod.model.entities.zombies.special.ModelJackOutBoxZombie;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJackOutBoxZombie extends RenderLiving<EntityJackOutBoxZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/jack/jack_out_box_zombie.png");
    
    public RenderJackOutBoxZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelJackOutBoxZombie(), 0.5F);//size
        this.addLayer(new LayerSuperMode(this));
        this.addLayer(new LayerIceBlock(this));
        this.addLayer(new LayerCold(this));
        this.addLayer(new LayerButter(this));
    }

    @Override
    protected void preRenderCallback(EntityJackOutBoxZombie entity, float partialTickTime)
    {
    	if(entity.getIsSmall()) {
    		GlStateManager.scale(0.1F, 0.1F, 0.1F);
    	}
    	else{
    		GlStateManager.scale(0.5F, 0.5F, 0.5F);
    	}
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityJackOutBoxZombie entity)
    {
    	return TEXTURE;
    }
}