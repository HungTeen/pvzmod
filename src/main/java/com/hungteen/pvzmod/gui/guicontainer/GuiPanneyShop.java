package com.hungteen.pvzmod.gui.guicontainer;

import java.io.IOException;

import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.gui.container.ContainerPanneyShop;
import com.hungteen.pvzmod.packet.PacketGuiButton;
import com.hungteen.pvzmod.packet.PacketHandler;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.ShopUtil;
import com.hungteen.pvzmod.util.ShopUtil.Goods;
import com.hungteen.pvzmod.util.enums.Colors;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiPanneyShop extends GuiContainer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID+":"+"textures/gui/container/panney_shop.png");
	private static final int LEFT_BUTTON = 0;
	private static final int RIGHT_BUTTON = 1;
	private static final int BUY_BUTTON = 2;
	private ContainerPanneyShop container;
	private GuiButton leftButton;
	private GuiButton rightButton;
	private GuiButton buyButton;
	private int costomerMoney;
	private int needMoney;
	
	public GuiPanneyShop(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		this.xSize=190;
		this.ySize=256;
		this.container=(ContainerPanneyShop) this.inventorySlots;
	}

	@Override
	public void initGui() {
		super.initGui();
		String left=new TextComponentTranslation("pvzgui.panney_shop_left.name").getFormattedText();
		leftButton=this.addButton(new GuiButton(LEFT_BUTTON, this.guiLeft+17,this.guiTop+50,28,20,left));
		leftButton.enabled=false;
		
		String right=new TextComponentTranslation("pvzgui.panney_shop_right.name").getFormattedText();
		rightButton=this.addButton(new GuiButton(RIGHT_BUTTON, this.guiLeft+145,this.guiTop+50,28,20,right));
		rightButton.enabled=false;
		
		String buy=new TextComponentTranslation("pvzgui.panney_shop_buy.name").getFormattedText();
		buyButton=this.addButton(new GuiButton(BUY_BUTTON, this.guiLeft+83,this.guiTop+135,24,12,buy));
		buyButton.enabled=false;
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		if(container.nowPage>0) {
			this.leftButton.enabled=true;
		}else {
			this.leftButton.enabled=false;
		}
		if(container.nowPage<container.pageNum-1) {
			this.rightButton.enabled=true;
		}else {
			this.rightButton.enabled=false;
		}
		if(this.costomerMoney>this.needMoney) {
			this.buyButton.enabled=true;
		}else {
			this.buyButton.enabled=false;
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX=(this.width-this.xSize)/2;
		int offsetY=(this.height-this.ySize)/2;
		this.drawTexturedModalRect(offsetX, offsetY, 0,0,this.xSize,this.ySize);
		this.drawCurrentGood();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = new TextComponentTranslation("pvzgui.panney_shop.name").getFormattedText();
		RenderUtil.drawCenteredScaledString(fontRenderer, name, 95, 7, 1.7f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
		
		String your_money = new TextComponentTranslation("pvzgui.panney_shop_money.name").getFormattedText();
		RenderUtil.drawCenteredScaledString(fontRenderer, your_money, 43, 140, 0.9f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
		
		String cost_money = new TextComponentTranslation("pvzgui.panney_shop_price.name").getFormattedText();
		RenderUtil.drawCenteredScaledString(fontRenderer, cost_money, 147, 140, 0.9f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
		
		this.costomerMoney=PVZGuiTabPlayerData.getPlayerMoney();
		String money = this.costomerMoney+"";
		RenderUtil.drawCenteredScaledString(fontRenderer, money, 58, 154, 1.5f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
		
		this.needMoney=ShopUtil.getGoodPrice(container.goods[container.nowPage]);
		String price = this.needMoney+"";
		RenderUtil.drawCenteredScaledString(fontRenderer, price, 132, 154, 1.5f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
		
		this.drawGoodInfo();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button==this.leftButton) {
			PacketHandler.CHANNEL.sendToServer(new PacketGuiButton(0));
		}else if(button==this.rightButton) {
//			System.out.println("button pressed");
			PacketHandler.CHANNEL.sendToServer(new PacketGuiButton(1));
		}else if(button==this.buyButton) {
			PacketHandler.CHANNEL.sendToServer(new PacketGuiButton(2));
		}
	}

	protected void drawCurrentGood()
	{
		Goods good=container.goods[container.nowPage];
		Item item=ShopUtil.getGoodByEnumName(good);
		GlStateManager.scale(4,4,4);
//		System.out.println(item.getRegistryName());
		this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(item), (this.guiLeft+63)/4, (this.guiTop+29)/4);
		GlStateManager.scale(0.25,0.25,0.25);
	}
	
	protected void drawGoodInfo()
	{
		Goods good=container.goods[container.nowPage];
		String name=ShopUtil.getGoodInfo(good);
		RenderUtil.drawCenteredScaledString(fontRenderer, name, 95, 96, 1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
}
