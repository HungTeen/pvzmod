package com.hungteen.pvz.gui.screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.client.resources.I18n;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.BrandingControl;

@OnlyIn(Dist.CLIENT)
public class PVZMainMenuScreen extends MainMenuScreen {

	public static final RenderSkyboxCube PANORAMA_RESOURCES = new RenderSkyboxCube(StringUtil.prefix("textures/gui/mainmenu/panorama"));
	private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
	public static final ResourceLocation SPLASHES = StringUtil.prefix("splashes.txt");
	private String splashText;
	private final RenderSkybox panorama = new RenderSkybox(PANORAMA_RESOURCES);
	Random rand;

	public PVZMainMenuScreen() {
		rand = new Random();
		if (splashText == null) {
			this.splashText = this.getRandomSplashText();
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
//			RenderSystem.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
			float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Util.getMillis() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
			f2 = f2 * 100.0F / (float) (this.font.width(this.splashText) + 32);
			stack.scale(f2, f2, f2);
			drawCenteredString(stack, this.font, this.splashText, 0, -8, 16776960);
			stack.popPose();
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

	protected String getRandomSplashText() {
		List<String> texts = getSplashTexts();
		return texts.get(this.rand.nextInt(texts.size()));
	}

	protected List<String> getSplashTexts() {
		try (IResource iresource = getSplashResource();
				BufferedReader bufferedreader = new BufferedReader(
						new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8));) {
			List<String> list = bufferedreader.lines().map(String::trim).filter((p_215277_0_) -> {
				return p_215277_0_.hashCode() != 125780783;
			}).collect(Collectors.toList());
			return list;
		} catch (IOException var36) {
			return Collections.emptyList();
		}
	}
	
	private IResource getSplashResource() {
		if(this.minecraft == null) this.minecraft = Minecraft.getInstance();
		ResourceLocation fileLoc = StringUtil.prefix("lang/splashes/" + this.minecraft.options.languageCode + ".txt");
        ResourceLocation backupLoc = StringUtil.prefix("lang/splashes/en_us.txt");
        IResource resource = null;
        try {
            resource = this.minecraft.getResourceManager().getResource(fileLoc);
        } catch (IOException e) {
            try {
                resource = this.minecraft.getResourceManager().getResource(backupLoc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return resource;
	}
}
