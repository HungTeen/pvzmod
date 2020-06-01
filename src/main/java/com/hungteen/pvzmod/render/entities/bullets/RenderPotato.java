package com.hungteen.pvzmod.render.entities.bullets;

import com.hungteen.pvzmod.entities.bullets.EntityPotato;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPotato extends RenderBase<EntityPotato>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:" + "textures/items/potato.png");
	
	public RenderPotato(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		super(renderManagerIn, itemIn, itemRendererIn);
	}

	protected ResourceLocation getEntityTexture(EntityPotato entity)
    {
        return TEXTURE;
    }

	@Override
	protected float getScale(EntityPotato entity) {
		return 1;
	}
}