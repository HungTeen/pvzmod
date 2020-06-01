package com.hungteen.pvzmod.render.entities.bullets;

import com.hungteen.pvzmod.entities.bullets.EntityCabbage;
import com.hungteen.pvzmod.entities.bullets.EntityMelon;
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
public class RenderMelon<T extends Entity> extends RenderBase<EntityMelon>
{
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/items/cabbage.png");
	
	public RenderMelon(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	protected ResourceLocation getEntityTexture(EntityMelon entity)
    {
        return TEXTURE1;
    }

	@Override
	protected float getScale(EntityMelon entity) {
		return 4f;
	}
	
	@Override
	public ItemStack getStackToRender(EntityMelon entityIn) {
		if(entityIn.getMelonState()==EntityMelon.State.ICE) {
			return new ItemStack(ItemRegister.WINTER_MELON);
		}else {
	    	return new ItemStack(ItemRegister.MELON);
		}
	}
}
