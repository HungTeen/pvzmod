package com.hungteen.pvzmod.render.entities.bullets;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.bullets.EntityThorn;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderThorn<T extends Entity> extends RenderBase<EntityThorn>
{
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/items/pea.png");
	
	public RenderThorn(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	
	protected ResourceLocation getEntityTexture(EntityThorn entity)
    {
        return TEXTURE1;
    }

	@Override
	protected float getScale(EntityThorn entity) {
		return 1f;
	}
	
	@Override
	public ItemStack getStackToRender(EntityThorn entityIn) {
		return new ItemStack(ItemRegister.THORN);
	}
}