package com.hungteen.pvzmod.render.entities.plants.magic;

import com.hungteen.pvzmod.entities.plants.magic.EntityMariGold;
import com.hungteen.pvzmod.model.entities.plants.magic.ModelMariGold;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMariGold extends RenderPlantBase<EntityMariGold>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/magic/marigold.png");

    public RenderMariGold(RenderManager renderManager)
    {
        super(renderManager, new ModelMariGold(), 0.6F);//size
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMariGold entity)
    {
        return TEXTURE;
    }
}
