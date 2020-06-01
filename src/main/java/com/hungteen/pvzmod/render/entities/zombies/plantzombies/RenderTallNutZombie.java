package com.hungteen.pvzmod.render.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityTallNutZombie;
import com.hungteen.pvzmod.model.entities.zombies.plantzombies.ModelTallNutZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.render.layers.LayerZombieBeard;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTallNutZombie extends RenderZombieBase<EntityTallNutZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/tallnut_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/tallnut_zombie2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/tallnut_zombie3.png");
    private static final ResourceLocation TEXTURE4 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/tallnut_zombie4.png");
    
    public RenderTallNutZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelTallNutZombie(), 0.5F);//size
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityTallNutZombie entity) {
		double life=entity.getHealth();
		double max=entity.getMaxHealth();
    	if(life*4>=max*3) return TEXTURE1;  
    	else if(life*2>=max) return TEXTURE2;
    	else if(life*4>=max) return TEXTURE3;
    	else return TEXTURE4;
	}
}


