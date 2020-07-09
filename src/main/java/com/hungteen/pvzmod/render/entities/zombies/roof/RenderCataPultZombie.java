package com.hungteen.pvzmod.render.entities.zombies.roof;

import com.hungteen.pvzmod.entities.zombies.roof.EntityCataPultZombie;
import com.hungteen.pvzmod.model.entities.zombies.roof.ModelCataPultZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCataPultZombie extends RenderZombieBase<EntityCataPultZombie>
{
    private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/other/catapult_zombie1.png");
    private static final ResourceLocation TEXTURE2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/other/catapult_zombie2.png");
    
    public RenderCataPultZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelCataPultZombie(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCataPultZombie entity)
    {
    	if(entity.getIsOut()==true) return TEXTURE2;
        return TEXTURE1;
    }
}
