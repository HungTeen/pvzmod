package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityLavaZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelLavaZombie;
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
public class RenderLavaZombie extends RenderZombieBase<EntityLavaZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/water/lava_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/water/lava_zombie2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/water/lava_zombie3.png");
    
    public RenderLavaZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelLavaZombie(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLavaZombie entity)
    {
    	float life=entity.getHealth();
    	float max=entity.getMaxHealth();
    	if(life*3>=max*2) return TEXTURE1;
    	else if(life*3>=max) return TEXTURE2;
    	return TEXTURE3;
    }
}