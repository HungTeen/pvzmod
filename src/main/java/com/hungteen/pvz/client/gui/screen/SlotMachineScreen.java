package com.hungteen.pvz.client.gui.screen;

import java.util.ArrayList;
import java.util.Arrays;

import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.client.gui.widget.DisplayField;
import com.hungteen.pvz.common.container.SlotMachineContainer;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlotMachineScreen extends PVZContainerScreen<SlotMachineContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/slot_machine.png");
	protected Button slowLotteryButton;
	protected Button fastLotteryButton;
	
	public SlotMachineScreen(SlotMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 227;
		this.tips.add(new DisplayField.TipField(4, 4, new ArrayList<>()));
	}
	
	@Override
	protected void init() {
		super.init();
		this.slowLotteryButton = this.addButton(new Button(this.leftPos + 13, this.topPos + 119, 62, 20, new TranslationTextComponent("gui.pvz.slot_machine.slow"), (button) -> {
			if(this.slowLotteryButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.SLOT_MACHINE, 0, 0));
			}
		}));
		this.slowLotteryButton.visible = false;

		this.fastLotteryButton = this.addButton(new Button(this.leftPos + 102, this.topPos + 119, 62, 20, new TranslationTextComponent("gui.pvz.slot_machine.fast"), (button) -> {
			if(this.fastLotteryButton.visible) {
				PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.SLOT_MACHINE, 1, 0));
			}
		}));
		this.fastLotteryButton.visible = false;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);

		final boolean canLottery = this.canLottery();
		this.slowLotteryButton.visible = canLottery;
		this.fastLotteryButton.visible = canLottery;

		this.tips.forEach(e -> {
			if(e instanceof DisplayField.TipField){
				((DisplayField.TipField) e).setTips(Arrays.asList(
						new TranslationTextComponent("gui.pvz.slot_machine.tip1"),
						new TranslationTextComponent("gui.pvz.slot_machine.tip2", PlayerUtil.getResource(this.minecraft.player, Resources.SUN_NUM), this.menu.te.getSunCost()),
						new TranslationTextComponent("gui.pvz.slot_machine.tip3", PlayerUtil.getResource(this.minecraft.player, Resources.LOTTERY_CHANCE))
				));
			}
		});

		this.renderSlotMachine(stack);

		this.renderTooltip(stack, mouseX, mouseY);
    }
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	@SuppressWarnings("deprecation")
	private void renderSlotMachine(MatrixStack stack) {
		final int currentPos = this.menu.te.array.get(13);
		final float percent = Math.min(1F, this.menu.te.array.get(12) * 1.0F / this.menu.te.array.get(15));
		RenderSystem.pushMatrix();
		RenderSystem.translated(0, (1 - percent) * 18, 0);
		
		for(int i = 0; i < 4; ++ i) {
			final int row = (currentPos + i - 2 + 4) % 4;
			for(int j = 0; j < 3; ++ j) {
				final SlotMachineTileEntity.SlotType type = this.menu.te.getLotteryType().getSlotType(this.menu.te.array.get(3 * row + j));
				final int x = this.leftPos + 62 + 18 * j;
				final int y = this.topPos + 58 - 18 * i;

				RenderSystem.pushMatrix();
				switch (type.getSlotTypes()){
					case ITEM:{
						type.getStack().ifPresent(itemStack -> {
							this.itemRenderer.blitOffset -= 100;
							this.itemRenderer.renderAndDecorateItem(itemStack, x, y);
							this.itemRenderer.blitOffset += 100;
						});
						break;
					}
					case SUN:{
						RenderSystem.enableBlend();
						this.minecraft.getTextureManager().bind(TEXTURE);
						blit(stack, x, y, 176, 16, 16, 16);
						break;
					}
					case JEWEL:{
						this.minecraft.getTextureManager().bind(TEXTURE);
						blit(stack, x, y, 176, 0, 16, 16);
						break;
					}
//					case :{
//						RenderSystem.enableBlend();
//						this.minecraft.getTextureManager().bind(TEXTURE);
//						blit(stack, x, y, 176, 32, 16, 16);
//					}
					case EVENT:{
						RenderSystem.enableBlend();
						this.minecraft.getTextureManager().bind(TEXTURE);
						blit(stack, x, y, 176, 48, 16, 16);
					}
				default:
					break;
				}
				RenderSystem.popMatrix();
			}
		}
		RenderSystem.popMatrix();

		RenderSystem.pushMatrix();
		RenderSystem.translated(0, 0, 120);
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos + 61, this.topPos + 74, 61, 74, 54, 21);
		blit(stack, this.leftPos + 61, this.topPos + 1, 61, 1, 54, 21);
		
		RenderSystem.translated(0, 0, 200);
		StringUtil.drawCenteredScaledString(stack, font, this.title.getString(), this.leftPos + this.imageWidth / 2, this.topPos + 6, Colors.BLACK, 1.2F);
		RenderSystem.popMatrix();
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		stack.popPose();
		
		super.renderBg(stack, partialTicks, mouseX, mouseY);
	}

	private boolean canLottery() {
		return this.menu.te.array.get(14) == 1;
	}

}
