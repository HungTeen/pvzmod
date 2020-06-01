package com.hungteen.pvzmod.render.entities.bullets;

import com.hungteen.pvzmod.entities.bullets.EntityPea;
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
public class RenderPea<T extends Entity> extends RenderBase<EntityPea>
{
	private static final ResourceLocation TEXTURE1 = new ResourceLocation(Reference.MODID + ":" + "textures/items/pea.png");
	
	public RenderPea(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	
	protected ResourceLocation getEntityTexture(EntityPea entity)
    {
        return TEXTURE1;
    }

	@Override
	protected float getScale(EntityPea entity) {
		EntityPea.Type type=entity.getPeaType();
		if(type==EntityPea.Type.BIG) return 2.5f;
		else if(type==EntityPea.Type.HUGE) return 5f;
		return 1f;
	}
	
	@Override
	public ItemStack getStackToRender(EntityPea entityIn) {
		if(entityIn.getPeaState()==EntityPea.State.SNOW) return new ItemStack(ItemRegister.SNOW_PEA);
		else if(entityIn.getPeaState()==EntityPea.State.FIRE) return new ItemStack(ItemRegister.FIRE_PEA);
		else if(entityIn.getPeaState()==EntityPea.State.BLUE_FIRE) return new ItemStack(ItemRegister.BLUE_FIRE_PEA);
		else return new ItemStack(ItemRegister.PEA);
	}
}