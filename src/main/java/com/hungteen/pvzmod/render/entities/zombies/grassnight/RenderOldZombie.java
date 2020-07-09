package com.hungteen.pvzmod.render.entities.zombies.grassnight;

import com.hungteen.pvzmod.entities.zombies.grassnight.EntityOldZombie;
import com.hungteen.pvzmod.model.entities.zombies.grassnight.ModelPaperZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
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
public class RenderOldZombie extends RenderZombieBase<EntityOldZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/old_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/old_zombie2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/old_zombie3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/old_zombie4.png");

    public RenderOldZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelPaperZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityOldZombie entity)
    {
    	double life=entity.getHealth();
    	double max=entity.getMaxHealth();
    	if(life*4>=max*3) return TEXTURE1;
    	else if(life*2>=max) return TEXTURE2;
    	else if(life*4>=max) return TEXTURE3;
    	else return TEXTURE4;    
    }
}