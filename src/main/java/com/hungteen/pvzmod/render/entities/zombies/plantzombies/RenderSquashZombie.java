package com.hungteen.pvzmod.render.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.plantzombies.EntitySquashZombie;
import com.hungteen.pvzmod.model.entities.zombies.plantzombies.ModelSquashZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSquashZombie extends RenderZombieBase<EntitySquashZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/squash_zombie.png");

    public RenderSquashZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelSquashZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntitySquashZombie entity)
    {
    	return TEXTURE;
    }
}

