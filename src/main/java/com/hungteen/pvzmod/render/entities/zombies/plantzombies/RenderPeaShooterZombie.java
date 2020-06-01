package com.hungteen.pvzmod.render.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityPeaShooterZombie;
import com.hungteen.pvzmod.model.entities.zombies.plantzombies.ModelPeaShooterZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeaShooterZombie extends RenderZombieBase<EntityPeaShooterZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/peashooter_zombie.png");

    public RenderPeaShooterZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelPeaShooterZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityPeaShooterZombie entity)
    {
    	return TEXTURE;
    }
}

