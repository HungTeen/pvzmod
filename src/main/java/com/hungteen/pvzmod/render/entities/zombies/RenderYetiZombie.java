package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityYetiZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelYetiZombie;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderYetiZombie extends RenderZombieBase<EntityYetiZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/snow/yeti_zombie.png");

    public RenderYetiZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelYetiZombie(), 1F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityYetiZombie entity)
    {
    	return TEXTURE;
    }
}
