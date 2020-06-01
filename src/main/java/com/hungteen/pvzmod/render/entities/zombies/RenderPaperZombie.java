package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.night.EntityPaperZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelPaperZombie;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.render.layers.LayerZombieBeard;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPaperZombie extends RenderZombieBase<EntityPaperZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/paper_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/paper_zombie2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/paper/paper_zombie3.png");

    public RenderPaperZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelPaperZombie(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPaperZombie entity)
    {
    	float life=entity.getHealth();
    	float max=entity.getMaxHealth();
    	if(life*3>=max*2) return TEXTURE1;
    	else if(life*3>=max) return TEXTURE2;
    	return TEXTURE3;
    }
}