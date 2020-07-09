package com.hungteen.pvzmod.render.entities.zombies.poolday;

import com.hungteen.pvzmod.entities.zombies.poolday.EntityBobsle;
import com.hungteen.pvzmod.model.entities.zombies.poolday.ModelBobsle;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.render.layers.LayerButter;
import com.hungteen.pvzmod.render.layers.LayerCold;
import com.hungteen.pvzmod.render.layers.LayerIceBlock;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBobsle extends RenderZombieBase<EntityBobsle>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/snow/bobsle.png");

    public RenderBobsle(RenderManager renderManager)
    {
        super(renderManager, new ModelBobsle(), 0.5F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBobsle entity)
    {
    	return TEXTURE;
    }
}
