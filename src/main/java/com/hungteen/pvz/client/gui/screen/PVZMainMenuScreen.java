package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.compat.patchouli.PVZPatchouliHandler;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.BrandingControl;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class PVZMainMenuScreen extends MainMenuScreen {

	private static final RenderSkyboxCube PANORAMA_RESOURCES = new RenderSkyboxCube(StringUtil.prefix("textures/gui/mainmenu/panorama"));
	private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
	private static final ResourceLocation SPLASHES = StringUtil.prefix("splashes.txt");
	private String splashText;
	private final RenderSkybox panorama = new RenderSkybox(PANORAMA_RESOURCES);
	private final Random rand = new Random();;

	public PVZMainMenuScreen() {
		if (splashText == null) {
			this.splashText = StringUtil.getRandomLangText(minecraft, rand, "splashes");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		stack.pushPose();
		//render skybox
		this.panorama.render(partialTicks, 1f);
		//render main title
		this.minecraft.getTextureManager().bind(MINECRAFT_TITLE_TEXTURES);
        this.blit(stack, this.width / 2 - 274 / 2, 30, 0, 0, 155, 44);
        this.blit(stack, this.width / 2 - 274 / 2 + 155, 30, 0, 45, 155, 44);
		// splash render
		if (this.splashText != null) {
			stack.pushPose();
			stack.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
			stack.mulPose(Vector3f.ZP.rotationDegrees(- 22.5F));
			float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Util.getMillis() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
			f2 = f2 * 100.0F / (float) (this.font.width(this.splashText) + 32);
			stack.scale(f2, f2, f2);
			drawCenteredString(stack, this.font, this.splashText, 0, -8, 16776960);
			stack.popPose();
		}
		// tip render
		if(! PVZPatchouliHandler.isPatchouliLoaded()) {
			stack.pushPose();
			String tip = new TranslationTextComponent("help.pvz.patchouli").getString();
			drawString(stack, this.font, tip, this.width - this.font.width(tip) - 2, this.height - 20, Colors.RED);
			stack.popPose();
//			stack.pushPose();
//			final String tip = "install patchouli mod pls";
//			stack.translate((float) (this.width / 2 - 90), 70.0F, 0.0F);
//			stack.mulPose(Vector3f.ZP.rotationDegrees(22.5F));
//			float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Util.getMillis() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
//			f2 = f2 * 100.0F / (float) (this.font.width(tip) + 32);
//			stack.scale(f2, f2, f2);
//			drawCenteredString(stack, this.font, tip, 0, -8, Colors.RED);
//			stack.popPose();
		}
        //render version
        String s = "Minecraft " + SharedConstants.getCurrentVersion().getName() + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : "/" + this.minecraft.getVersionType());
        if (this.minecraft.isProbablyModded()) {
           s = s + I18n.get("menu.modded");
        }
        BrandingControl.forEachLine(true, true, (brdline, brd) ->
           drawString(stack, this.font, brd, 2, this.height - ( 10 + brdline * (this.font.lineHeight + 1)), 16777215)
        );
        BrandingControl.forEachAboveCopyrightLine((brdline, brd) ->
             drawString(stack, this.font, brd, this.width - font.width(brd), this.height - (10 + (brdline + 1) * ( this.font.lineHeight + 1)), 16777215)
        );
		// render copyright
		ForgeHooksClient.renderMainMenu(this, stack, this.font, this.width, this.height);
		String s1 = "Copyright Mojang AB. Do not distribute!";
		drawString(stack, this.font, s1, this.width - this.font.width(s1) - 2, this.height - 10, 0xFFFFFFFF);
		// render button
		for (int i = 0; i < this.buttons.size(); i++) {
			buttons.get(i).render(stack, mouseX, mouseY, minecraft.getFrameTime());
		}
		for (int i = 0; i < this.buttons.size(); i++) {
			buttons.get(i).render(stack, mouseX, mouseY, minecraft.getFrameTime());
		}
		stack.popPose();
	}

}
