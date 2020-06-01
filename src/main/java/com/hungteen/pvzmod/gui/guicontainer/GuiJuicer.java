package com.hungteen.pvzmod.gui.guicontainer;

import com.hungteen.pvzmod.blocks.tileentities.TileEntityJuicer;
import com.hungteen.pvzmod.gui.container.ContainerJuicer;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiJuicer extends GuiContainer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID+":"+"textures/gui/container/juicer.png");
	private IInventory player;
	private TileEntityJuicer tileentity;
	
	
	public GuiJuicer(InventoryPlayer player,TileEntityJuicer te) {
		super(new ContainerJuicer(player, te));
		tileentity=te;
		this.player=player;
		
		this.xSize=176;
		this.ySize=166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX=(this.width-this.xSize)/2;
		int offsetY=(this.height-this.ySize)/2;
		this.drawTexturedModalRect(offsetX, offsetY, 0,0,this.xSize,this.ySize);
//		GlStateManager.scale(2, 2, 2);
//		this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(ItemRegister.PEA_SHOOTER_CARD), 0, 0);
//		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		drawBurn();
		drawProgress();
		drawLeft();
		drawRight();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	    String name = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(name, this.xSize/2 - this.fontRenderer.getStringWidth(name) / 2, 1, 4210752);            //#404040
	    //this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize-96+2, 4210752);      //#404040
	}
	
	private void drawBurn()
	{
		if(TileEntityJuicer.isBurning(this.tileentity)) {
			int maxTime=this.tileentity.getField(5);
		    int time=this.tileentity.getField(0);
		    int now=12*time/maxTime;
		    if(now==0&&time!=0) now++;
			int y1=47,y2=80;
			int x1=27,x2=176;
//			System.out.println(maxTime+" "+time+" "+now);
			this.drawTexturedModalRect(this.guiLeft+x1,this.guiTop+y1-now+1,x2,y2-now+1,13,now);
		}
	}
	
	private void drawProgress()
	{
		int maxTime=8;
		int time=this.tileentity.getField(3);
		int now=time*56/maxTime;
		if(now==0&&time!=0) now++;
		if(now==0) return ;
		int y1=69,y2=68;
		int x1=79,x2=176;
		this.drawTexturedModalRect(this.guiLeft+x1,this.guiTop+y1-now+1,x2,y2-now+1,17,now);
	}
	
	private void drawLeft()
	{
		int xx=21,yy=13;
		int time=this.tileentity.getField(1);
		int now=time*xx/100;
		if(now==0&&time!=0) now++;
		if(now==0) return ;
		this.drawTexturedModalRect(this.guiLeft+49,this.guiTop+36,176,0,now,13);
	}
	
	private void drawRight()
	{
		int xx=21,yy=13;
		int time=this.tileentity.getField(2);
		int now=time*xx/100;
		if(now==0&&time!=0) now++;
		if(now==0) return ;
		this.drawTexturedModalRect(this.guiLeft+106,this.guiTop+36,176,0,now,13);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
}
