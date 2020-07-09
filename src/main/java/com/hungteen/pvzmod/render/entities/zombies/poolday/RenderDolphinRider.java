package com.hungteen.pvzmod.render.entities.zombies.poolday;

import com.hungteen.pvzmod.entities.zombies.poolday.EntityDolphinRider;
import com.hungteen.pvzmod.model.entities.zombies.poolday.ModelDolphinRider;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDolphinRider extends RenderZombieBase<EntityDolphinRider>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/water/dolphin_rider.png");

    public RenderDolphinRider(RenderManager renderManager)
    {
        super(renderManager, new ModelDolphinRider(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDolphinRider entity)
    {
    	return TEXTURE;
    }
}
