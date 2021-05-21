package com.hungteen.pvz.gui.screen;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.SlotMachineContainer;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.tileentity.SlotMachineTileEntity.SlotOptions;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlotMachineScreen extends ContainerScreen<SlotMachineContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/slot_machine.png");
	protected Button craftButton;
	
	public SlotMachineScreen(SlotMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 227;
	}
	
	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.leftPos + 79, this.topPos + 120, 18, 18, new TranslationTextComponent("gui.pvz.slot_machine"), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.SLOT_MACHINE, 0, 0));
			}
		}));
		this.craftButton.visible = false;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		this.craftButton.visible = this.canLottery();
		this.renderSlotMachine(stack);
		this.renderTooltip(stack, mouseX, mouseY);
    }
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	@SuppressWarnings("deprecation")
	private void renderSlotMachine(MatrixStack stack) {
		RenderSystem.pushMatrix();
		float percent = this.menu.te.array.get(12) * 1.0F / this.menu.te.array.get(15);
		RenderSystem.translated(0, (1 - percent) * 18, 0);
		int currentPos = this.menu.te.array.get(13);
		for(int i = 0; i < 4; ++ i) {
			int row = (currentPos + i - 2 + 4) % 4;
			for(int j = 0; j < 3; ++ j) {
				SlotOptions option = SlotMachineTileEntity.OPTION_LIST.get(this.menu.te.array.get(3 * row + j));
				int x = this.leftPos + 62 + 18 * j;
				int y = this.topPos + 58 - 18 * i;
				if(option.plantType.isPresent()) {
					Plants plant = option.plantType.get();
					ItemStack itemstack = new ItemStack(PlantUtil.getPlantEnjoyCard(plant));
					RenderSystem.pushMatrix();
					this.itemRenderer.blitOffset -= 100;
					this.itemRenderer.renderAndDecorateItem(itemstack, x, y);
					this.itemRenderer.blitOffset += 100;
					RenderSystem.popMatrix();
				} else if(option.isJewel) {
					RenderSystem.pushMatrix();
					this.minecraft.getTextureManager().bind(TEXTURE);
					blit(stack, x, y, 176, 0, 16, 16);
					RenderSystem.popMatrix();
				} else if(option.isSun) {
					RenderSystem.pushMatrix();
					RenderSystem.enableBlend();
					this.minecraft.getTextureManager().bind(TEXTURE);
					blit(stack, x, y, 176, 16, 16, 16);
					RenderSystem.popMatrix();
				}
			}
		}
		RenderSystem.popMatrix();
		RenderSystem.pushMatrix();
		RenderSystem.translated(0, 0, 120);
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos + 61, this.topPos + 74, 61, 74, 54, 21);
		blit(stack, this.leftPos + 61, this.topPos + 1, 61, 1, 54, 21);
		StringUtil.drawScaledString(stack, font, new TranslationTextComponent("block.pvz.slot_machine").getString(), this.leftPos + 5, this.topPos + 6, Colors.BLACK, 1F);
		StringUtil.drawScaledString(stack, font, new TranslationTextComponent("gui.pvz.slot_machine.left").append(":" + ClientPlayerResources.getPlayerStats(Resources.LOTTERY_CHANCE)).getString(), this.leftPos + this.imageWidth / 2 + 25, this.topPos + 6, Colors.BLACK, 1F);
		StringUtil.drawScaledString(stack, font, new TranslationTextComponent("gui.pvz.slot_machine.sun").append(":" + ClientPlayerResources.getPlayerStats(Resources.SUN_NUM)).getString(), this.leftPos + 12, this.topPos + 125, Colors.BLACK, 0.8F);
		StringUtil.drawScaledString(stack, font, new TranslationTextComponent("gui.pvz.slot_machine.cost").append(":" + 25).getString(), this.leftPos + this.imageWidth / 2 + 15, this.topPos + 125, Colors.BLACK, 0.8F);
		RenderSystem.popMatrix();
	}
	
	private boolean canLottery() {
		return ClientPlayerResources.getPlayerStats(Resources.SUN_NUM) >= SlotMachineTileEntity.SUN_COST && ClientPlayerResources.getPlayerStats(Resources.LOTTERY_CHANCE) > 0 && this.menu.te.array.get(14) == 1;
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		stack.popPose();
	}

}
