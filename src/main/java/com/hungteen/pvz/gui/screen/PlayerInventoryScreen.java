package com.hungteen.pvz.gui.screen;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.gui.widget.PVZButton;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerInventoryScreen extends ContainerScreen<PlayerInventoryContainer>{

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/player_inventory.png");
	private static final String TITLE = new TranslationTextComponent("gui.pvz.player_inventory.title").getString();
	protected Button leftButton;
	protected Button rightButton;
	
	public PlayerInventoryScreen(PlayerInventoryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 208;
		this.imageHeight = 244;
	}
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	@Override
	protected void init() {
		super.init();
		leftButton=this.addButton(new ChangeButton(TEXTURE, this.leftPos + 6, this.topPos + 136, false, (button) -> {
			if(this.menu.currentPage > 1) {
				-- this.menu.currentPage;
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.PLAYER_INVENTORY, 0, - 1));
			}
		}));
		
		rightButton=this.addButton(new ChangeButton(TEXTURE, this.leftPos + 188, this.topPos + 136, true,(button) -> {
			if(this.menu.currentPage < PlayerUtil.MAX_SLOT_NUM / 54) {
				++ this.menu.currentPage;
				PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.PLAYER_INVENTORY, 0, 1));
			}
		}));
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        int lock = this.menu.currentPage * 54 - ClientPlayerResources.getPlayerStats(Resources.SLOT_NUM);
        int num = MathHelper.clamp(lock / 9, 0, 6);
        for(int i = 0;i < num;i ++) {
        	for(int j = 0;j < 9;j ++) {
        		blit(stack, this.leftPos + 24 + 18 * j, this.topPos + 119 - 18 * i, 208, 0, 16, 16);
        	}
        }
        StringUtil.drawCenteredScaledString(stack, this.font, TITLE, this.width / 2, this.topPos + 6, Colors.BLACK, 1.6f);
		StringUtil.drawCenteredScaledString(stack, this.font, this.menu.currentPage + "/" + PlayerUtil.MAX_SLOT_NUM / 54, this.width / 2, this.topPos + 141, Colors.BLACK, 1.5f);
	    stack.popPose();
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		renderTooltip(stack, mouseX, mouseY);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	static class ChangeButton extends PVZButton{

		public ChangeButton(ResourceLocation location, int x, int y, boolean right,
				IPressable onPress) {
			super(location, x, y, 14, 22, right, onPress);
		}

		@Override
		protected Pair<Integer, Integer> getButtonUV() {
			int x = this.right?224:242;
			int y = this.isHovered?32:0;
			return new Pair<Integer, Integer>(x,y);
		}
		
	}
	
}
