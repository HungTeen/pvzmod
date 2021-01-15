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
		this.xSize = 176;
		this.ySize = 227;
	}
	
	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.guiLeft + 79, this.guiTop + 120, 18, 18, new TranslationTextComponent("gui.pvz.slot_machine").getFormattedText(), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.SLOT_MACHINE, 0, 0));
			}
		}));
		this.craftButton.visible = false;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		this.craftButton.visible = this.canLottery();
		this.renderSlotMachine();
		this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	private void renderSlotMachine() {
		RenderSystem.pushMatrix();
		float percent = this.container.te.array.get(12) * 1.0F / this.container.te.array.get(15);
		RenderSystem.translated(0, (1 - percent) * 18, 0);
		int currentPos = this.container.te.array.get(13);
		for(int i = 0; i < 4; ++ i) {
			int row = (currentPos + i - 2 + 4) % 4;
			for(int j = 0; j < 3; ++ j) {
				SlotOptions option = SlotMachineTileEntity.OPTION_LIST.get(this.container.te.array.get(3 * row + j));
				int x = this.guiLeft + 62 + 18 * j;
				int y = this.guiTop + 58 - 18 * i;
				if(option.plantType.isPresent()) {
					Plants plant = option.plantType.get();
					ItemStack stack = new ItemStack(PlantUtil.getPlantEnjoyCard(plant));
					this.itemRenderer.zLevel -= 100;
					this.itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
					this.itemRenderer.zLevel += 100;
				} else if(option.isJewel) {
					RenderSystem.pushMatrix();
					RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
					this.minecraft.getTextureManager().bindTexture(TEXTURE);
					blit(x, y, 176, 0, 16, 16);
					RenderSystem.popMatrix();
				} else if(option.isSun) {
					RenderSystem.pushMatrix();
					RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
					RenderSystem.enableBlend();
					this.minecraft.getTextureManager().bindTexture(TEXTURE);
					blit(x, y, 176, 16, 16, 16);
					RenderSystem.popMatrix();
				}
			}
		}
		RenderSystem.popMatrix();
		RenderSystem.pushMatrix();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.translated(0, 0, 120);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(this.guiLeft + 61, this.guiTop + 74, 61, 74, 54, 21);
		blit(this.guiLeft + 61, this.guiTop + 1, 61, 1, 54, 21);
		StringUtil.drawScaledString(font, new TranslationTextComponent("block.pvz.slot_machine").getFormattedText(), this.guiLeft + 5, this.guiTop + 6, Colors.BLACK, 1F);
		StringUtil.drawScaledString(font, new TranslationTextComponent("gui.pvz.slot_machine.left").appendText(":" + ClientPlayerResources.getPlayerStats(Resources.LOTTERY_CHANCE)).getFormattedText(), this.guiLeft + this.xSize / 2 + 25, this.guiTop + 6, Colors.BLACK, 1F);
		StringUtil.drawScaledString(font, new TranslationTextComponent("gui.pvz.slot_machine.sun").appendText(":" + ClientPlayerResources.getPlayerStats(Resources.SUN_NUM)).getFormattedText(), this.guiLeft + 12, this.guiTop + 125, Colors.BLACK, 0.8F);
		StringUtil.drawScaledString(font, new TranslationTextComponent("gui.pvz.slot_machine.cost").appendText(":" + 25).getFormattedText(), this.guiLeft + this.xSize / 2 + 15, this.guiTop + 125, Colors.BLACK, 0.8F);
		RenderSystem.popMatrix();
	}
	
	private boolean canLottery() {
		return ClientPlayerResources.getPlayerStats(Resources.SUN_NUM) >= SlotMachineTileEntity.SUN_COST && ClientPlayerResources.getPlayerStats(Resources.LOTTERY_CHANCE) > 0 && this.container.te.array.get(14) == 1;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.pushMatrix();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		RenderSystem.popMatrix();
	}

}
