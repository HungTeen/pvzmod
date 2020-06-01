package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityBalloonZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelBalloonZombie;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBalloonZombie extends RenderZombieBase<EntityBalloonZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/sky/balloon_zombie.png");

    public RenderBalloonZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelBalloonZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityBalloonZombie entity)
    {
    	return TEXTURE;
    }
}