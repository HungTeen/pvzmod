package com.hungteen.pvzmod.potion;

import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.enums.Enums;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PVZPotion extends Potion{

	private final ResourceLocation TEXTURE;
	
	public PVZPotion(String name,boolean isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
		setPotionName(name);
		setIconIndex(0, 0);
		setRegistryName(new ResourceLocation(Reference.MODID+":"+name));
		this.TEXTURE=new ResourceLocation(Reference.MODID+":"+"textures/gui/effects/"+name+".png");
	}

	@Override
	public boolean hasStatusIcon() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		return true;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		mc.getTextureManager().bindTexture(TEXTURE);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
	}
	
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(TEXTURE);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
	}
}
