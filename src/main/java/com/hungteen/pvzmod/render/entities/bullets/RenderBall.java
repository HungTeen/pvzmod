package com.hungteen.pvzmod.render.entities.bullets;

import com.hungteen.pvzmod.entities.bullets.EntityBall;
import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderBall<T extends Entity> extends RenderBase<EntityBall>
{
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/items/pea.png");
	
	public RenderBall(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	
	protected ResourceLocation getEntityTexture(EntityBall entity)
    {
        return TEXTURE1;
    }

	@Override
	protected float getScale(EntityBall entity) {
		return 1.6f;
	}
	
	@Override
	public ItemStack getStackToRender(EntityBall entityIn) {
	    return new ItemStack(ItemRegister.BALL);
	}
}
