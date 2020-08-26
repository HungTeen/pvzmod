package com.hungteen.pvz.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.hungteen.pvz.capabilities.player.ClientPlayerResources;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.GuildTopics;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PVZGuildBookScreen extends Screen{

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/guild_book.png");
	private static final ResourceLocation TEXTURE1 = StringUtil.prefix("textures/gui/guild_book_1.png");
	private static final ResourceLocation WIDGETS = StringUtil.prefix("textures/gui/widgets.png");
	protected static final int X = 390;
    protected static final int Y = 245;
    public GuildTopics pageTopic;
    public List<Button> indexButtons = new ArrayList<Button>();
    public ChangePageButton previousPage;
    public ChangePageButton nextPage;
    public int bookPages;
    public int bookPagesTotal = 1;
    public int indexPages;
    public int indexPagesTotal = 1;
    protected boolean index;
	
	public PVZGuildBookScreen() {
		super(new TranslationTextComponent("gui.pvz.guild_book"));
		indexPagesTotal = (int)Math.ceil(GuildTopics.values().length/10D);
		index = true;
	}

	@Override
	protected void init() {
		super.init();
		int centerX = (this.width - X) / 2;
        int centerY = (this.height - Y) / 2;
        this.addButton(this.previousPage = new ChangePageButton(centerX + 15, centerY + 215, false, bookPages, (button) -> {
        	if(this.index) {
        		if(this.indexPages>0) {
        		    this.indexPages--;
        		}
        	}else if(this.pageTopic!=null) {
        		if(this.bookPages>0) {
        			this.bookPages--;
        		}else {
        			this.index=true;
        		}
        	}
        }));
        this.addButton(this.nextPage = new ChangePageButton(centerX + 357, centerY + 215, true, bookPages, (button) -> {
        	if(this.index) {
        		if(this.indexPages<this.indexPagesTotal-1) {
        			this.indexPages++;
        		}
        	}else if(this.pageTopic!=null) {
        		if(this.bookPages<this.pageTopic.pageNum-1) {
        			this.bookPages++;
        		}
        	}
        }));
        for(int i=0;i<GuildTopics.values().length;i++) {
        	int xIndex = i % -2;
        	int yIndex = i % 10;
        	int id = i;
        	IndexPageButton button = new IndexPageButton(id, centerX + 15 + (xIndex * 200), centerY + 10 + (yIndex * 20) - (xIndex == 1 ? 20 : 0), new TranslationTextComponent("gui.pvz.guild_book"+id).getFormattedText(), (btn) -> {
        		if (this.indexButtons.get(id) != null) {
                    this.index = false;
                    this.indexPages = 0;
                    this.bookPages = 0;
                    this.pageTopic = GuildTopics.values()[id];
                }
            });
            this.indexButtons.add(button);
            this.addButton(button);
        }
	}
	
	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
		for (Widget button : this.buttons) {
            if (button instanceof IndexPageButton) {
                button.active = index;
                button.visible = index;
            }
        }
		for (int i = 0; i < this.indexButtons.size(); i++) {
            this.indexButtons.get(i).visible = i < 10 * (this.indexPages + 1) && i >= 10 * (this.indexPages) && this.index;
        }
		this.renderBackground();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
		int cornerX = (this.width - X) / 2;
        int cornerY = (this.height - Y) / 2;
        blit(cornerX, cornerY, 0, 0, X, Y, 390, 390);
        super.render(mouseX, mouseY, partialTicks);
        if (!index) {
            drawPerPage(bookPages);
            int pageLeft = bookPages * 2 + 1;
            int pageRight = pageLeft + 1;
            font.drawString("" + pageLeft, cornerX+95, cornerY+225,Colors.BLACK);
            font.drawString("" + pageRight, cornerX+295, cornerY+225,Colors.BLACK);
        }
	}
	
	@Override
    public boolean isPauseScreen() {
        return false;
    }

    @SuppressWarnings("deprecation")
	public void drawPerPage(int bookPages) {
    	int midX = (this.width - X) / 2;
        int midY = (this.height - Y) / 2;
    	switch(this.pageTopic) {
    	case PLAYER_STATS:{
    		if(this.bookPages==0) {
    			int lvl = ClientPlayerResources.getPlayerStats(Resources.TREE_LVL);
        		int maxLvl = PlayerUtil.MAX_TREE_LVL;
        		int xp = ClientPlayerResources.getPlayerStats(Resources.TREE_XP);
        		int maxXp = PlayerUtil.getPlayerLevelUpXp(lvl);
        		int sunNum = ClientPlayerResources.getPlayerStats(Resources.SUN_NUM);
        		int maxSunNum = PlayerUtil.getPlayerMaxSunNum(lvl);
        		int energyNum = ClientPlayerResources.getPlayerStats(Resources.ENERGY_NUM);
        		int maxEnergyNum = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
        		int money = ClientPlayerResources.getPlayerStats(Resources.MONEY);
        		int gemNum = ClientPlayerResources.getPlayerStats(Resources.GEM_NUM);
        		RenderSystem.pushMatrix();
        		//render lvl
        		drawImage(TEXTURE1, midX+15, midY+20, 0, 96, 34, 32, 512);//head
        		drawImage(TEXTURE1, midX+49, midY+20, 34, 0, 126, 32, 512);//bar
        		drawImage(TEXTURE1, midX+49, midY+20, 34, 96, RenderUtil.getRenderBarLen(lvl, maxLvl, 123), 29, 512);//color
        		StringUtil.drawCenteredScaledString(font, ""+lvl, midX+110, midY+27, Colors.WHITE, 2.5f);//text
        		//render xp
        		drawImage(TEXTURE1, midX+15, midY+52, 0, 128, 34, 32, 512);//head
        		drawImage(TEXTURE1, midX+49, midY+52, 34, 0, 126, 32, 512);//bar
        		drawImage(TEXTURE1, midX+49, midY+52, 34, 128, RenderUtil.getRenderBarLen(xp, maxXp, 123), 29, 512);//color
        		StringUtil.drawCenteredScaledString(font, ""+xp, midX+110, midY+59, Colors.WHITE, 2.5f);//text
        		//render sun num
        		drawImage(TEXTURE1, midX+15, midY+84, 0, 32, 34, 32, 512);//head
        		drawImage(TEXTURE1, midX+49, midY+84, 34, 0, 126, 32, 512);//bar
        		drawImage(TEXTURE1, midX+49, midY+84, 34, 32, RenderUtil.getRenderBarLen(sunNum, maxSunNum, 123), 29, 512);//color
        		StringUtil.drawCenteredScaledString(font, ""+sunNum, midX+110, midY+91, Colors.WHITE, 2.5f);//text
        		//render energy num
        		drawImage(TEXTURE1, midX+15, midY+116, 0, 64, 34, 32, 512);//head
        		drawImage(TEXTURE1, midX+49, midY+116, 34, 0, 126, 32, 512);//bar
        		drawImage(TEXTURE1, midX+49, midY+116, 34, 64, RenderUtil.getRenderBarLen(energyNum, maxEnergyNum, 123), 29, 512);//color
        		StringUtil.drawCenteredScaledString(font, ""+energyNum, midX+110, midY+123, Colors.WHITE, 2.5f);//text
        		//render money
        		drawImage(TEXTURE1, midX+15, midY+148, 0, 160, 160, 32, 512);//bar
        		StringUtil.drawCenteredScaledString(font, ""+money, midX+110, midY+155, Colors.WHITE, 2.5f);//text
        		//render gem
        		drawImage(TEXTURE1, midX+15, midY+180, 0, 192, 160, 32, 512);//bar
        		StringUtil.drawCenteredScaledString(font, ""+gemNum, midX+110, midY+187, Colors.WHITE, 2.5f);//text
        		RenderSystem.popMatrix();
    		}else if(this.bookPages==1) {
    		}
    		break;
    	}
    	case EVENTS:{
    		break;
    	}
    	case ESSENCES:{
    		if(this.bookPages==0) {
    			RenderSystem.pushMatrix();
    			//picture 1
    			drawImage(TEXTURE1, midX+45, midY+100, 160, 0, 16, 31, 512);
    			drawImage(TEXTURE1, midX+80, midY+108, 160, 32, 25, 13, 512);
    			drawImage(TEXTURE1, midX+120, midY+100, 176, 0, 16, 31, 512);
    			//picture 2
    			drawItem(Item.getItemFromBlock(BlockRegister.ORIGIN_ORE.get()), midX+40, midY+175, 1f);
    			drawItem(Items.DIAMOND_PICKAXE, midX+60, midY+175, 1f);
    			drawImage(TEXTURE1, midX+84, midY+175, 160, 32, 25, 13, 512);
    			drawItem(ItemRegister.ORIGIN_ESSENCE.get(), midX+115, midY+175, 1f);
    			//picture 3
    			for(int i=0;i<3;i++) {
    				for(int j=0;j<3;j++) {
    					if(i==1&&j==1) {
    						drawItem(Items.GLASS, midX+230+i*16, midY+53+j*16, 1f);
    					}else {
    					    drawItem(ItemRegister.ORIGIN_ESSENCE.get(), midX+230+i*16, midY+53+j*16, 1f);
    					}
    				}
    			}//370
    			drawImage(TEXTURE1, midX+280, midY+70, 160, 32, 25, 13, 512);
    			drawItem(Item.getItemFromBlock(BlockRegister.ORIGIN_BLOCK.get()), midX+310, midY+69, 1f);
    			RenderSystem.popMatrix();
    		}else if(this.bookPages==1) {
    			RenderSystem.pushMatrix();
    			Block[] blocks = new Block[] {Blocks.GRASS_BLOCK,Blocks.GLOWSTONE,Blocks.REDSTONE_BLOCK,Blocks.GRANITE,Blocks.BLUE_ICE,Blocks.ANDESITE,Blocks.MYCELIUM,Blocks.DIORITE,Blocks.SOUL_SAND,Blocks.MAGMA_BLOCK,Blocks.GRAVEL,Blocks.SANDSTONE,Blocks.OBSIDIAN};
    			Block[] ores = new Block[] {BlockRegister.APPEASE_ORE.get(),BlockRegister.LIGHT_ORE.get(),BlockRegister.EXPLOSION_ORE.get(),BlockRegister.DEFENCE_ORE.get(),BlockRegister.ICE_ORE.get(),BlockRegister.ENFORCE_ORE.get(),
    					BlockRegister.TOXIC_ORE.get(),BlockRegister.ASSIST_ORE.get(),BlockRegister.MAGIC_ORE.get(),BlockRegister.FLAME_ORE.get(),BlockRegister.SPEAR_ORE.get(),BlockRegister.ARMA_ORE.get(),BlockRegister.SHADOW_ORE.get()};
    			Item[] essences = new Item[] {ItemRegister.APPEASE_ESSENCE.get(),ItemRegister.LIGHT_ESSENCE.get(),ItemRegister.EXPLOSION_ESSENCE.get(),ItemRegister.DEFENCE_ESSENCE.get(),ItemRegister.ICE_ESSENCE.get(),ItemRegister.ENFORCE_ESSENCE.get(),
    					ItemRegister.TOXIC_ESSENCE.get(),ItemRegister.ASSIST_ESSENCE.get(),ItemRegister.MAGIC_ESSENCE.get(),ItemRegister.FLAME_ESSENCE.get(),ItemRegister.SPEAR_ESSENCE.get(),ItemRegister.ARMA_ESSENCE.get(),ItemRegister.SHADOW_ESSENCE.get()};
    			for(int i=0;i<blocks.length;i++) {
    				drawItem(Item.getItemFromBlock(blocks[i]), midX+40, midY+15+i*16, 1f);
    				drawItem(Item.getItemFromBlock(ores[i]), midX+95, midY+15+i*16, 1f);
    				drawItem(essences[i], midX+145, midY+15+i*16, 1f);
    			}
    			drawImage(TEXTURE1, midX+60, midY+125, 160, 32, 25, 13, 512);
    			drawItem(Item.getItemFromBlock(BlockRegister.ORIGIN_BLOCK.get()), midX+64, midY+100, 1f);
    			drawImage(TEXTURE1, midX+115, midY+125, 160, 32, 25, 13, 512);
    			drawItem(Items.DIAMOND_PICKAXE, midX+119, midY+100, 1f);
    			RenderSystem.popMatrix();
    		}else if(this.bookPages==2) {
    			RenderSystem.pushMatrix();
    			RenderSystem.popMatrix();
    		}
    		break;
    	}
    	case SUMMON_CARDS:{
    		if(this.bookPages==0) {
    			RenderSystem.pushMatrix();
    			drawItem(ItemRegister.PEA_SHOOTER_CARD.get(), midX+60, midY+100, 1f);
    			drawItem(ItemRegister.PEA_SHOOTER_ENJOY_CARD.get(), midX+115, midY+100, 1f);
    			RenderSystem.popMatrix();
    		}else if(this.bookPages==1) {
    		}
    		break;
    	}
    	case ZEN_GARDEN:{
    		if(this.bookPages==0) {
    		    RenderSystem.pushMatrix();
			    drawItem(ItemRegister.NUT.get(), midX+60, midY+60, 1f);
			    drawItem(Item.getItemFromBlock(BlockRegister.CHOMPER.get()), midX+115, midY+60, 1f);
			    RenderSystem.popMatrix();
    		}
    		break;
    	}
    	case ZOMBIE_HOUSE:{
    		if(this.bookPages==0) {
    			RenderSystem.pushMatrix();
    			RenderSystem.popMatrix();
    		}
    		break;
    	}
    	case PLANT_OBTAIN:{
    		if(this.bookPages==0) {
    			RenderSystem.pushMatrix();
    			drawItem(ItemRegister.PEA.get(), midX+88, midY+20, 1f);
    			for(int i=0;i<3;i++) {
    				for(int j=0;j<3;j++) {
    					if(i==1&&j==1) {
    						drawItem(ItemRegister.ICE_ESSENCE.get(), midX+45+i*16, midY+70+j*16, 1f);
    					}else {
    						drawItem(ItemRegister.PEA.get(), midX+45+i*16, midY+70+j*16, 1f);
    					}
    				}
    			}
    			drawImage(TEXTURE1, midX+80, midY+87, 160, 32, 25, 13, 512);
    			drawItem(ItemRegister.SNOW_PEA.get(), midX+110, midY+86, 1f);
    			drawItem(ItemRegister.NUT.get(), midX+285, midY+14, 1f);
    			RenderSystem.popMatrix();
    		}else if(this.bookPages==1) {
    			RenderSystem.pushMatrix();
    			drawItem(Item.getItemFromBlock(BlockRegister.CHOMPER.get()), midX+88, midY+20, 1f);
    			RenderSystem.popMatrix();

    		}
    		break;
    	}
    	}
    	writeFromTxt();
    }
    
    @SuppressWarnings("resource")
	public void writeFromTxt() {
    	int midX = (this.width - X) / 2;
        int midY = (this.height - Y) / 2;
        String fileName = this.pageTopic.toString().toLowerCase() + "_" + this.bookPages + ".txt";
        ResourceLocation fileLoc = StringUtil.prefix("lang/guild_book/"+Minecraft.getInstance().gameSettings.language +"/" + fileName);
        ResourceLocation backupLoc = StringUtil.prefix("lang/guild_book/zh_cn/"+fileName);
        IResource resource = null;
        try {
            resource = Minecraft.getInstance().getResourceManager().getResource(fileLoc);
        } catch (IOException e) {
            try {
                resource = Minecraft.getInstance().getResourceManager().getResource(backupLoc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            Iterator<String> iterator = IOUtils.readLines(resource.getInputStream(), "UTF-8").iterator();
            String line = null;
            int linenumber = 0;
            while (iterator.hasNext()) {
                line = iterator.next().trim();
                if (line.contains("<") || line.contains(">")) {
                    continue;
                }
                RenderSystem.pushMatrix();
//                RenderSystem.scalef(0.945F, 0.945F, 0.945F);
//                RenderSystem.translatef(0, 5.5F, 0);
                if (linenumber <= 19) {
                    font.drawString(line, midX+15, midY+20 + linenumber * 10, Colors.BLACK);
                } else {
                    font.drawString(line, midX+210, midY+(linenumber - 19) * 10+3, Colors.BLACK);
                }
                linenumber++;
                RenderSystem.popMatrix();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //render page title
        RenderSystem.pushMatrix();
        String s = new TranslationTextComponent("gui.pvz.guild_book"+this.pageTopic.ordinal()).getFormattedText();
        float scale = font.getStringWidth(s) <= 100 ? 2 : font.getStringWidth(s) * 0.0125F;
        RenderSystem.scalef(scale, scale, scale);
        font.drawString(s, midX+5, midY, Colors.RED);
        RenderSystem.popMatrix();
    }
    
    public void drawImage(ResourceLocation texture, int x, int y, int u, int v, int width, int height, float scale) {
        RenderSystem.pushMatrix();
        this.minecraft.getTextureManager().bindTexture(texture);
        RenderSystem.scalef(scale / 512F, scale / 512F, scale / 512F);
        blit(x, y, u, v, width, height, 512, 512);
        RenderSystem.popMatrix();
    }

	private void drawItem(Item item, int x, int y, float scale) {
    	RenderSystem.pushMatrix();
    	RenderSystem.translatef(0, 0, 32.0F);
        this.itemRenderer.zLevel = 200.0F;
        RenderSystem.scalef(scale, scale, scale);
        this.itemRenderer.zLevel = 5;
        this.itemRenderer.renderItemAndEffectIntoGUI(new ItemStack(item), x, y);
        this.itemRenderer.zLevel = 0.0F;
        RenderSystem.scalef(1/scale, 1/scale, 1/scale);
        RenderSystem.popMatrix();
    }
    
	public class IndexPageButton extends Button {

	    public IndexPageButton(int id, int x, int y, String buttonText, Button.IPressable butn) {
	        super(x, y, 160, 32, buttonText, butn);
	        this.width = 160;
	        this.height = 32;
	    }

		@SuppressWarnings("resource")
		public void render(int mouseX, int mouseY, float partial) {
	        if (this.visible) {
	            FontRenderer fontrenderer = Minecraft.getInstance().fontRenderer;
	            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	            Minecraft.getInstance().getTextureManager().bindTexture(WIDGETS);
	            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            this.blit(this.x, this.y, 0, this.isHovered ? 32 : 0, this.width, this.height);
	            fontrenderer.drawString(getMessage(), (float) (this.x + this.width / 2 - fontrenderer.getStringWidth(this.getMessage()) / 2), (float) this.y + (this.height - 8) / 2, this.isHovered ? Colors.RED : Colors.BLACK);
	        }
	    }
	}
	
	public class ChangePageButton extends Button {
	    private final boolean right;
	    public int lastpage = 1;
	    @SuppressWarnings("unused")
		private int page;

	    public ChangePageButton(int x, int y, boolean rightButton, int bookpage, IPressable press) {
	        super(x, y, 23, 10, "", press);
	        this.right = rightButton;
	        page = bookpage;
	    }

	    @Override
	    public void render(int mouseX, int mouseY, float partial) {
	        if (this.visible) {
	        	this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	            Minecraft.getInstance().getTextureManager().bindTexture(WIDGETS);
	            int i = 0;
	            int j = 64;
	            if (this.isHovered) {
	                i += 23;
	            }
	            if (!this.right) {
	                j += 13;
	            }
	            this.blit(this.x, this.y, i, j, width, height);
	        }
	    }
	}
	
}
