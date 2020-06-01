package com.hungteen.pvzmod.render.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityNutWallZombie;
import com.hungteen.pvzmod.model.entities.zombies.plantzombies.ModelNutWallZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNutWallZombie extends RenderZombieBase<EntityNutWallZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/nutwall_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/nutwall_zombie2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant_zombie/nutwall_zombie3.png");
    
    public RenderNutWallZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelNutWallZombie(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityNutWallZombie entity)
    {
    	double life=entity.getHealth();
    	double max=entity.getMaxHealth();
    	if(life*3>=max*2) return TEXTURE1;
    	else if(life*3>=max) return TEXTURE2;
    	return TEXTURE3;
    }
}

