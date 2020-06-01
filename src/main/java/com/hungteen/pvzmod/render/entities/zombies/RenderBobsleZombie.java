package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityBobsleZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelBobsleZombie;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBobsleZombie extends RenderZombieBase<EntityBobsleZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/snow/bobsle_zombie.png");

    public RenderBobsleZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelBobsleZombie(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBobsleZombie entity)
    {
    	return TEXTURE;
    }
}