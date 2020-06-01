package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityZomBoss;
import com.hungteen.pvzmod.model.entities.zombies.ModelZomBoss;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerGargantuarHeldItem;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.render.layers.LayerZombossYellowEyes;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZomBoss extends RenderLiving<EntityZomBoss>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/boss/zomboss.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/gargantuar/gargantuar2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/gargantuar/gargantuar3.png");
    
    public RenderZomBoss(RenderManager renderManager)
    {
        super(renderManager, new ModelZomBoss(), 4F);//size
//        this.addLayer(new LayerSuperMode(this));
//        this.addLayer(new LayerCold(this));
//        this.addLayer(new LayerGargantuarHeldItem(this));
        this.addLayer(new LayerZombossYellowEyes(this));
    }

    @Override
    protected void preRenderCallback(EntityZomBoss entity, float partialTickTime)
    {
    		GlStateManager.scale(4F, 4F, 4F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityZomBoss entity)
    {
    	float life=entity.getHealth();
//    	if(life>=entity.getMaxHealth()*2/3) {
//    		return TEXTURE1;
//    	}
//    	else if(life>=entity.getMaxHealth()/3) {
//    		return TEXTURE2;
//    	}
//    	return TEXTURE3;
    	return TEXTURE1;
    }
}

