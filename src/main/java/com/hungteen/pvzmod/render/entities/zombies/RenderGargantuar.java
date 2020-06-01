package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityGargantuar;
import com.hungteen.pvzmod.model.entities.zombies.ModelGargantuar;
import com.hungteen.pvzmod.model.entities.zombies.normal.ModelBucketHeadZombie;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerGargantuarHeldItem;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGargantuar extends RenderZombieBase<EntityGargantuar>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/gargantuar/gargantuar1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/gargantuar/gargantuar2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/gargantuar/gargantuar3.png");
    
    public RenderGargantuar(RenderManager renderManager)
    {
        super(renderManager, new ModelGargantuar(), 1F);//size
    }

    @Override
    protected void addNewLayer() {
    	super.addNewLayer();
    	this.addLayer(new LayerGargantuarHeldItem(this));
    }
    
    @Override
    protected void preRenderCallback(EntityGargantuar entity, float partialTickTime)
    {
    	if(entity.getIsSmall()) {
    		GlStateManager.scale(0.3F, 0.3F, 0.3F);
    	}
    	else{
    		GlStateManager.scale(1F, 1F, 1F);
    	}
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityGargantuar entity)
    {
    	float life=entity.getHealth();
    	float max=entity.getMaxHealth();
    	if(life*3>=max*2) return TEXTURE1;
    	else if(life*3>=max) return TEXTURE2;
    	return TEXTURE3;
    }
}
