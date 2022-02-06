package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.api.raid.IWaveComponent;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ChallengeInfoScreen extends Screen{

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac.png");
	private static final int MAX_ENTRY_COUNT = 14;
	private final List<IFormattableTextComponent> entries = new ArrayList<>();
	private final IChallengeComponent challengeComponent;
	private final int xSize = 150;
	private final int ySize = 200;
	private int currentPos = 0;

	public ChallengeInfoScreen(IChallengeComponent challengeComponent) {
		super(StringUtil.EMPTY);
		this.challengeComponent = challengeComponent;
		this.initEntries(challengeComponent);
	}

	private void initEntries(IChallengeComponent challengeComponent){
		for(int i = 0; i < challengeComponent.getTotalWaveCount(); ++ i){
			final IWaveComponent waveComponent = challengeComponent.getWaves().get(i);
			this.entries.add(new TranslationTextComponent("gui.pvz.challenge_info.wave", i + 1, waveComponent.getLastDuration()).withStyle(TextFormatting.BOLD).withStyle(TextFormatting.RED));
			challengeComponent.getSpawns(i).forEach(spawn -> {
				this.entries.add(new TranslationTextComponent("gui.pvz.challenge_info.spawn", spawn.getSpawnTick(), spawn.getSpawnAmount(), spawn.getSpawnType().getDescription().getString()));
			});
		}
	}

	@Override
	public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
		if (this.entries.size() > MAX_ENTRY_COUNT) {
			final int next = (int) ((double) this.currentPos - p_mouseScrolled_5_);
			this.currentPos = MathHelper.clamp(next, 0, this.entries.size() - MAX_ENTRY_COUNT);
		}
		return true;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		int cornerX = (this.width - this.xSize) / 2;
		int cornerY = (this.height - this.ySize) / 2;

		{
			this.minecraft.getTextureManager().bind(TEXTURE);
			blit(stack, cornerX, cornerY, 0, 0, this.xSize, this.ySize);
			if(this.entries.size() <= MAX_ENTRY_COUNT) {
				blit(stack, cornerX + 130, cornerY + 49, 162, 0, 12, 15);
			} else {
				final int len = MathUtil.getBarLen(this.currentPos, this.entries.size() - MAX_ENTRY_COUNT, 143 - 15);
				blit(stack, cornerX + 130, cornerY + 49 + len, 150, 0, 12, 15);
			}
		}

		{
			stack.pushPose();
			int dx = cornerX + 49 + 94 / 2, dy = cornerY + 9;
			StringUtil.drawCenteredScaledString(stack, this.font, this.challengeComponent.getChallengeName().getString(), dx, dy, Colors.WHITE, 1f);
			stack.popPose();
		}

		{
			int dx = cornerX + 9, dy = cornerY + 9;
			int scale = 2;
			RenderSystem.pushMatrix();
			RenderSystem.scaled(scale, scale, scale);
			RenderSystem.translated((dx % scale) * 1.0d / scale , (dy % scale) * 1.0d / scale, 0);
			this.itemRenderer.renderGuiItem(new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get()), dx / scale, dy / scale);
			RenderSystem.popMatrix();
		}

		{
			for(int i = 0; i < Math.min(this.entries.size(), MAX_ENTRY_COUNT); ++ i){
				this.currentPos = MathHelper.clamp(this.currentPos, 0, this.entries.size() - 1);
				final String text = this.entries.get(this.currentPos + i).getString();
				final int incHeight = 10;
				final int posX = cornerX + 8 + 2;
				final int posY = cornerY + 49 + 5;
				StringUtil.drawScaledString(stack, font, text, posX, posY + incHeight * i, Colors.WHITE, 0.5f);
			}
		}
	}

	protected void renderBar(MatrixStack stack, SearchOption a) {
//		stack.pushPose();
//		//Do not change the position values, if changed pls modify with the below method.
//		final int len = 22;
//		int posX = 53;
//		int posY = 25;
//		{//guide book.
//			ItemStack guideBook = PVZPatchouliHandler.getPatchouliGuide();
//			if(guideBook != ItemStack.EMPTY){
//				this.itemRenderer.renderGuiItem(guideBook, this.leftPos + posX, this.topPos + posY);
//			} else{
//				this.minecraft.getTextureManager().bind(TEXTURE);
//				blit(stack, this.leftPos + posX, this.topPos + posY, 224, 0, 16, 16);
//			}
//		}
//		posX += len;
//		{
//			if(a.getType() instanceof IPlantType){
//				ItemStack itemStack = new ItemStack(((IPlantType) a.getType()).getEssence().getEssenceItem());
//				this.itemRenderer.renderGuiItem(itemStack, this.leftPos + posX, this.topPos + posY);
//			}
//		}
//		posX += len;
//		{
//			this.itemRenderer.renderGuiItem(new ItemStack(a.getType().getRank().getTemplateCard()), this.leftPos + posX, this.topPos + posY);
//		}
//		posX += len;
//		{
//			this.minecraft.getTextureManager().bind(TEXTURE);
//			if(a.getType().getSkills().isEmpty()) {
//				blit(stack, this.leftPos + posX, this.topPos + posY, 224, 16, 16, 16);
//			} else {
//				blit(stack, this.leftPos + posX, this.topPos + posY, 240, 16, 16, 16);
//			}
//		}
//		stack.popPose();
	}

	protected void renderTooltip(MatrixStack stack, int mouseX, int mouseY) {
//		if(this.option != null){
//			final int len = 22;
//			int posX = this.leftPos + 53;
//			int posY = this.topPos + 25;
//			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
//
//			}
//			posX += len;
//			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
//
//				if(this.option.getType() instanceof IPlantType){
//					Item item = ((IPlantType) this.option.getType()).getEssence().getEssenceItem();
//					this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(
//							new TranslationTextComponent("item.pvz." + item.getRegistryName().getPath())
//					), mouseX, mouseY);
//				}
//			}
//			posX += len;
//			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
//				this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(
//						new TranslationTextComponent("item.pvz." + this.option.getType().getRank().getTemplateCard().getRegistryName().getPath())
//				), mouseX, mouseY);
//			}
//			posX += len;
//			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
//				List<ITextComponent> list = new ArrayList<>();
//				this.option.getType().getSkills().forEach(skill -> {
//					list.add(skill.getText().withStyle(TextFormatting.GREEN));
//				});
//				if(list.isEmpty()) {
////					list.add(new TranslationTextComponent("gui.))
//					this.minecraft.screen.renderComponentTooltip(stack, list, mouseX, mouseY);
//				} else {
//					this.minecraft.screen.renderComponentTooltip(stack, list, mouseX, mouseY);
//				}
//			}
//		}
	}
	
}
