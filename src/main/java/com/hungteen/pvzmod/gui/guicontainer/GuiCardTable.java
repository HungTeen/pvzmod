package com.hungteen.pvzmod.gui.guicontainer;

import java.io.IOException;

import com.hungteen.pvzmod.gui.container.ContainerCardTable;
import com.hungteen.pvzmod.packet.PacketGuiButton;
import com.hungteen.pvzmod.packet.PacketHandler;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class GuiCardTable extends GuiContainer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID+":"+"textures/gui/container/card_table.png");
	private static final int CRAFT_BUTTON = 0;
	private GuiButton craftButton;
	private IInventory player;
	private ContainerCardTable container;
	
	public GuiCardTable(World world,InventoryPlayer player,BlockPos pos) {
		super(new ContainerCardTable(world,player,pos));
		this.xSize=256;
		this.ySize=256;
		this.player=player;
		this.container=(ContainerCardTable) this.inventorySlots;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		String name=new TextComponentTranslation("pvzgui.card_table_button.name").getFormattedText();
		craftButton=this.addButton(new GuiButton(CRAFT_BUTTON, this.guiLeft+204,this.guiTop+101,26,10,name));
		craftButton.enabled=false;
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.craftButton.enabled=this.container.getCanButtonPress();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		PacketHandler.CHANNEL.sendToServer(new PacketGuiButton(0));
//		System.out.println("pressed");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX=(this.width-this.xSize)/2;
		int offsetY=(this.height-this.ySize)/2;
		this.drawTexturedModalRect(offsetX, offsetY, 0,0,this.xSize,this.ySize);
		this.drawPlantCard();
	}

	protected void drawPlantCard()
	{
		Plants plant=this.container.getCraftPlant();
		if(plant==null) return ;
		this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(PlantsUtil.getItemFromPlant(plant)), (this.guiLeft+209), (this.guiTop+80));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	    String percent=this.container.getCraftProp()+"%";
	    int len=32;
	    int width=12;
	    RenderUtil.drawCenteredScaledString(fontRenderer, percent, 201+len/2, 64, 1, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
	    String name = new TextComponentTranslation("pvzgui.card_table.name").getFormattedText();
		RenderUtil.drawCenteredScaledString(fontRenderer, name, 52+13, 36, 1, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
//		this.fontRenderer.drawString(percent, 201+len/2 - this.fontRenderer.getStringWidth(percent) / 2, 63, Colors.BLACK);            //#404040
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
}
