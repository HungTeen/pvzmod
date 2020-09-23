package com.hungteen.pvz.gui;

import com.hungteen.pvz.gui.container.PlayerInventoryContainer;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.systems.RenderSystem;

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
	private Button leftButton;
	private Button rightButton;
	
	public PlayerInventoryScreen(PlayerInventoryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 208;
		this.ySize = 244;
	}
	
	@Override
	protected void init() {
		super.init();
		String left=new TranslationTextComponent("gui.pvz.player_inventory.left").getFormattedText();
		leftButton=this.addButton(new Button(this.guiLeft+6,this.guiTop+136,20,22,left,(button)-> {
			if(this.container.currentPage>1) {
				this.container.currentPage--;
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(1,-1));
			}
		}));
		
		String right=new TranslationTextComponent("gui.pvz.player_inventory.right").getFormattedText();
		rightButton=this.addButton(new Button(this.guiLeft+188,this.guiTop+136,20,22,right,(button)-> {
			if(this.container.currentPage<PlayerUtil.MAX_SLOT_NUM/54) {
				this.container.currentPage++;
				PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(1,1));
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
	
}
