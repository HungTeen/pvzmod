package com.hungteen.pvzmod.render.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityGatlingPeaZombie;
import com.hungteen.pvzmod.model.entities.zombies.plantzombies.ModelGatlingPeaZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGatlingPeaZombie extends RenderZombieBase<EntityGatlingPeaZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/gatlingpea_zombie.png");

    public RenderGatlingPeaZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelGatlingPeaZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityGatlingPeaZombie entity)
    {
    	return TEXTURE;
    }
}


