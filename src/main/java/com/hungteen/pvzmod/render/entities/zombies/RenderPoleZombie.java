package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.day.EntityPoleZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelPoleZombie;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPoleZombie extends RenderZombieBase<EntityPoleZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/pole/pole_zombie.png");

    public RenderPoleZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelPoleZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityPoleZombie entity)
    {
    	return TEXTURE;
    }
}
