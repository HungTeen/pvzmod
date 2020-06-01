package com.hungteen.pvzmod.render.entities.drops;

import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.model.entities.ModelSun;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSun extends RenderLiving<EntitySun>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/drop/sun.png");

    public RenderSun(RenderManager renderManager)
    {
        super(renderManager, new ModelSun(), 0F);//size
    }
    
    @Override
    protected void preRenderCallback(EntitySun entity, float partialTickTime)
    {
    	int num=entity.getAmount();
    	//System.out.println(num);
    	float size=num*1.0f/200f;
    	GlStateManager.scale(size, size, size);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntitySun entity)
    {
        return RenderSun.TEXTURE;
    }

    @Override
    public void doRender(EntitySun entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
