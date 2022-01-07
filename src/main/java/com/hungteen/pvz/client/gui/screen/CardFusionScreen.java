package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.client.gui.widget.DisplayField;
import com.hungteen.pvz.common.container.CardFusionContainer;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.hungteen.pvz.common.tileentity.CardFusionTileEntity;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;

public class CardFusionScreen extends PVZContainerScreen<CardFusionContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/card_fusion.png");
	protected Button craftButton;
	
	public CardFusionScreen(CardFusionContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 178;
		this.imageHeight = 255;
		this.tips.add(new DisplayField.TipField(3, 3, Arrays.asList(
			    new TranslationTextComponent("gui.pvz.card_fusion_table.tip1"),
				new TranslationTextComponent("gui.pvz.card_fusion_table.tip2"),
				new TranslationTextComponent("gui.pvz.card_fusion_table.tip3")
		)));
	}

	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.leftPos + 76, this.topPos + 119, 26, 18, new TranslationTextComponent("gui.pvz.fragment_splice"), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.CARD_FUSION, 0, 0));
			}
		}));
		this.craftButton.visible = false;
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.craftButton.visible = this.canCraftNow();
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("block.pvz.card_fusion_table").getString(), this.leftPos + this.imageWidth / 2, this.topPos + 8, Colors.BLACK, 1F);
		final float percent1 = this.menu.te.array.get(0) * 100.0F / CardFusionTileEntity.CRAFT_SUN_COST;
		final float percent2 = this.menu.te.array.get(1) * 100.0F / CardFusionTileEntity.CRAFT_ESSENCE_COST;
		StringUtil.drawCenteredScaledString(stack, font, "" + String.format("%.0f%%", percent1), this.leftPos + 19, this.topPos + 53, Colors.BLACK, 0.8F);
		StringUtil.drawCenteredScaledString(stack, font, "" + String.format("%.0f%%", percent2), this.leftPos + 161, this.topPos + 53, Colors.BLACK, 0.8F);
	    StringUtil.drawCenteredScaledString(stack, font, "" + String.format("%.0f%%", percent1), this.leftPos + 18, this.topPos + 52, Colors.WHITE, 0.8F);
		StringUtil.drawCenteredScaledString(stack, font, "" + String.format("%.0f%%", percent2), this.leftPos + 160, this.topPos + 52, Colors.WHITE, 0.8F);
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

		final int maxLen = 52;
		final int len1 = MathUtil.getBarLen(this.menu.te.array.get(0), CardFusionTileEntity.CRAFT_SUN_COST, maxLen);
		final int len2 = MathUtil.getBarLen(this.menu.te.array.get(1), CardFusionTileEntity.CRAFT_ESSENCE_COST, maxLen);
		blit(stack, this.leftPos + 9, this.topPos + 77 - len1 + 1, 178, 0, 16, len1);
		blit(stack, this.leftPos + 153, this.topPos + 77 - len2 + 1, 194, 0, 16, len2);

		stack.popPose();

		super.renderBg(stack, partialTicks, mouseX, mouseY);
	}

	protected boolean canCraftNow() {
		return this.menu.canCraft();
	}

}
