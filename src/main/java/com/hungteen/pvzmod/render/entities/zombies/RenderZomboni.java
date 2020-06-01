package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityZomboni;
import com.hungteen.pvzmod.model.entities.zombies.ModelZomboni;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZomboni extends RenderZombieBase<EntityZomboni>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/snow/zomboni1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/snow/zomboni2.png");
    private static final ResourceLocation TEXTURE3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/snow/zomboni3.png");

    public RenderZomboni(RenderManager renderManager)
    {
        super(renderManager, new ModelZomboni(), 0.5F);//size
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityZomboni entity)
    {
    	float life=entity.getHealth();
    	float max=entity.getMaxHealth();
    	if(life*3>=max*2) return TEXTURE1;
    	else if(life*3>=max) return TEXTURE2;
    	return TEXTURE3;
    }
}
