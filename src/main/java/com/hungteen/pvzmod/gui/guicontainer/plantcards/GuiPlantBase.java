package com.hungteen.pvzmod.gui.guicontainer.plantcards;


import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class GuiPlantBase extends GuiContainer{

	protected Plants plantType;
	protected int plantCardX=18;
	protected int plantCardY=20;
	protected int nameWidth=132;
	protected int nameHeight=32;
	protected int dataCnt;
	protected int dataIconX=20;
	protected int dataIconY=116;
	protected int dataIconYdelta=23;
	protected final int dataMaxCnt=10;
	protected String[] dataInfo = new String[6];
	protected String[] dataOutput = new String[6];
	protected String[] plantInfo = new String[6];
	protected int barOffsetX = 17;
	protected int barOffsetY = 5;
	protected int barLength=50;
	protected int barWidth=8;
	protected int textLength=164;
	protected int textWidth=64;
	protected int textOffsetX=19;
	protected int textOffsetY=52;
	protected int maxLvl;
	protected int lvl;
	protected int xp;
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID+":"+"textures/gui/container/plant_book.png");
	
	public GuiPlantBase(Container inventorySlotsIn,Plants plant) {
		super(inventorySlotsIn);
		this.plantType=plant;
		this.xSize=200;
		this.ySize=256;
		this.dataCnt=0;
		this.lvl=PVZGuiTabPlayerData.getPlayerPlantCardLvl(plantType);
		this.xp=PVZGuiTabPlayerData.getPlayerPlantCardXp(plantType);
		this.maxLvl=PlantsUtil.getPlantMaxLvl(plantType);
//		System.out.println(this.plantType);
		dataInfo[1]=new TextComponentTranslation("text.plantLvl.name").getFormattedText();
		dataInfo[2]=new TextComponentTranslation("text.plantXp.name").getFormattedText();
		dataInfo[3]=new TextComponentTranslation("text.plantCost.name").getFormattedText();
		dataInfo[4]=new TextComponentTranslation("text.plantCD.name").getFormattedText();
		dataInfo[5]=new TextComponentTranslation("text.plantHP.name").getFormattedText();
		for(int i=0;i<6;i++) plantInfo[i]="";
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1f, 1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int offsetX=(this.width-this.xSize)/2;
		int offsetY=(this.height-this.ySize)/2;
		this.drawTexturedModalRect(offsetX, offsetY, 0,0,this.xSize,this.ySize);
		this.drawPlantCard();	
		this.dataCnt=0;
		this.drawDataPlantLvl();
		this.drawDataPlantXp();
		this.drawDataPlantSunCost();
		this.drawDataPlantCD();
		this.drawDataPlantHealth();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.drawPlantName();
		this.drawBarInfo();
		this.drawPlantInfo();
	}
	
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
			}
		}
	}
	
	protected void drawBarInfo()
	{
		for(int i=1;i<=5;i++) {
			int x=this.barOffsetX+this.dataIconX+this.barLength/2;
		    int y=this.barOffsetY+this.dataIconY+(i-1)*this.dataIconYdelta+1;
		    RenderUtil.drawCenteredScaledString(mc.fontRenderer, this.dataOutput[i], x, y,0.8f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
		}
	}
	
	protected void drawDataPlantLvl()
	{
		++this.dataCnt;
		this.drawDataBar(new ItemStack(Items.EXPERIENCE_BOTTLE), this.lvl, this.maxLvl);
		this.dataOutput[this.dataCnt]=this.dataInfo[this.dataCnt]+":"+this.lvl;
	}
	
	protected void drawDataPlantXp()
	{
		++this.dataCnt;
		this.drawDataBar(new ItemStack(Items.EXPERIENCE_BOTTLE), this.xp, PlantsUtil.getPlantLvlUpXp(lvl));
		this.dataOutput[this.dataCnt]=this.dataInfo[this.dataCnt]+":"+this.xp;
	}
	
	protected void drawDataPlantSunCost()
	{
		++this.dataCnt;
		this.drawDataBar(new ItemStack(ItemRegister.LIGHT_ELEMENT), 1,1);
		this.dataOutput[this.dataCnt]=this.dataInfo[this.dataCnt]+":"+PlantsUtil.getPlantSunCost(plantType);
	}
	
	protected void drawDataPlantHealth()
	{
		++this.dataCnt;
		this.drawDataBar(new ItemStack(Items.APPLE), PlantsUtil.getPlantMaxHealth(this.plantType,lvl), PlantsUtil.getPlantMaxHealth(this.plantType,maxLvl));
		this.dataOutput[this.dataCnt]=this.dataInfo[this.dataCnt]+":"+PlantsUtil.getPlantMaxHealth(this.plantType,lvl);
	}
	
	protected void drawDataPlantCD()
	{
		++this.dataCnt;
		int a=PlantsUtil.getPlantCoolDownTime(plantType, lvl)-PlantsUtil.getPlantCoolDownTime(plantType, maxLvl);
		int b=PlantsUtil.getPlantCoolDownTime(plantType, 1)-PlantsUtil.getPlantCoolDownTime(plantType, maxLvl);
		this.drawDataBar(new ItemStack(Items.CLOCK), a,b);
		this.dataOutput[this.dataCnt]=this.dataInfo[this.dataCnt]+":"+PlantsUtil.getPlantCoolDownTime(plantType, lvl)*1.0f/20+"s";
	}
	
	protected void drawDataBar(ItemStack item,double a,double b)
	{
		this.itemRender.renderItemAndEffectIntoGUI(item, this.guiLeft+dataIconX, this.guiTop+dataIconY+(this.dataCnt-1)*this.dataIconYdelta);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int x=this.dataIconX+this.barOffsetX;
		int y=this.dataIconY+this.barOffsetY+(this.dataCnt-1)*this.dataIconYdelta;
		this.drawTexturedModalRect(this.guiLeft+x,this.guiTop+y,206,0,this.barLength,this.barWidth);
		int len=MathHelper.floor(barLength*a/b);
		if(a!=0&&len==0) len=1;
		this.drawTexturedModalRect(this.guiLeft+x,this.guiTop+y,206,8,len,this.barWidth);
	}
	
	protected void drawPlantCard()
	{
		GlStateManager.scale(2f, 2f, 2f);
		this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(PlantsUtil.getItemFromPlant(this.plantType)), (this.guiLeft+plantCardX)/2, (this.guiTop+plantCardY)/2);
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
	}
	
	protected void drawPlantName()
	{
		int x=plantCardX+nameHeight+nameWidth/2;
		int y=plantCardY+nameHeight/4;
		String name=PlantsUtil.getPlantName(plantType);
		int color=this.getNameColor();
		RenderUtil.drawCenteredScaledString(mc.fontRenderer, name, x, y,1.5f, color, RenderUtil.StringRenderType.NORMAL);
	}
	
	protected int getNameColor()
	{
		int lvl=PVZGuiTabPlayerData.getPlayerPlantCardLvl(this.plantType);
		if(lvl<=6) return Colors.BROWN;
		else if(lvl<=13) return Colors.SILVER;
		else if(lvl<=20) return Colors.GOLD_YELLOW;
		return Colors.BROWN;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
}
