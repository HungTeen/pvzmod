package com.hungteen.pvzmod.render.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityPeaShooter;
import com.hungteen.pvzmod.model.entities.plants.common.ModelPeaShooter;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.render.layers.LayerSuperMode;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeaShooter extends RenderPlantBase<EntityPeaShooter>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/common/pea_shooter.png");

    public RenderPeaShooter(RenderManager renderManager)
    {
        super(renderManager, new ModelPeaShooter(), 0.4F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPeaShooter entity)
    {
        return TEXTURE;
    }
}