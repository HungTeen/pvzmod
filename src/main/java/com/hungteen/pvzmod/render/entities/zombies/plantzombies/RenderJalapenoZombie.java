package com.hungteen.pvzmod.render.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.normal.EntityConeHeadZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityJalapenoZombie;
import com.hungteen.pvzmod.model.entities.zombies.plantzombies.ModelJalapenoZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderJalapenoZombie extends RenderZombieBase<EntityJalapenoZombie>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/jalapeno_zombie.png");

    public RenderJalapenoZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelJalapenoZombie(), 0.5F);//size
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityJalapenoZombie entity) {
    	return TEXTURE;  
	}
}
