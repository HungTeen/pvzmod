package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityDoubleShooter;
import com.hungteen.pvzmod.model.entities.plants.common.ModelDoubleShooter;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDoubleShooter extends RenderPlantBase<EntityDoubleShooter>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/double_shooter.png");

    public RenderDoubleShooter(RenderManager renderManager)
    {
        super(renderManager, new ModelDoubleShooter(), 0.4F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDoubleShooter entity)
    {
        return TEXTURE;
    }
}