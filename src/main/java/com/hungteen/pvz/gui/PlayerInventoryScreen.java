package com.hungteen.pvz.gui;

import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.gui.widget.PVZButton;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerInventoryScreen extends ContainerScreen<PlayerInventoryContainer>{

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/player_inventory.png");
	private static final String TITLE = new TranslationTextComponent("gui.pvz.player_inventory.title").getFormattedText();
	protected Button leftButton;
	protected Button rightButton;
	
	public PlayerInventoryScreen(PlayerInventoryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 208;
		this.ySize = 244;
	}
	
	@Override
	protected void init() {
		super.init();
		leftButton=this.addButton(new ChangeButton(TEXTURE,this.guiLeft+6,this.guiTop+136,false,(button)-> {
			if(this.container.currentPage>1) {
				this.container.currentPage--;
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.PLAYER_INVENTORY,-1));
			}
		}));
		
		rightButton=this.addButton(new ChangeButton(TEXTURE, this.guiLeft+188, this.guiTop+136, true,(button)-> {
			if(this.container.currentPage<PlayerUtil.MAX_SLOT_NUM/54) {
				this.container.currentPage++;
				PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.PLAYER_INVENTORY,1));
			}
		}));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        for(int i=0;i<this.container.getLockRow();i++) {
        	for(int j=0;j<9;j++) {
        		blit(this.guiLeft+24+18*j, this.guiTop+119-18*i, 208, 0, 16, 16);
        	}
        }
        StringUtil.drawCenteredScaledString(this.font, TITLE, this.width / 2, this.guiTop + 6, Colors.BLACK, 1.6f);
		StringUtil.drawCenteredScaledString(this.font, this.container.currentPage + "/" + PlayerUtil.MAX_SLOT_NUM / 54, this.width / 2, this.guiTop + 141, Colors.BLACK, 1.5f);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
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
