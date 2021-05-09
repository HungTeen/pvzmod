package com.hungteen.pvz.gui.screen;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.gui.GuiHandler;
import com.hungteen.pvz.gui.container.FragmentSpliceContainer;
import com.hungteen.pvz.gui.search.SearchCategories;
import com.hungteen.pvz.gui.search.SearchOption;
import com.hungteen.pvz.network.ClickButtonPacket;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.tileentity.FragmentSpliceTileEntity;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FragmentSpliceScreen extends AbstractOptionScreen<FragmentSpliceContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/fragment_splice.png");
	protected Button craftButton;
	
	public FragmentSpliceScreen(FragmentSpliceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 210;
		this.imageHeight = 225;
	}

	@Override
	protected void init() {
		super.init();
		this.craftButton = this.addButton(new Button(this.leftPos + 92, this.topPos + 122, 26, 14, new TranslationTextComponent("gui.pvz.fragment_splice"), (button) -> {
			if(this.craftButton.visible) {
			    PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.FRAGMENT_SPLICE, 0, FragmentSpliceScreen.this.menu.te.array.get(1)));
			}
		}));
		this.craftButton.visible = false;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.searchGui.getCurrentOption().isPresent()) {
			this.searchGui.getRecipeManager().clear();
			PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.FRAGMENT_SPLICE, 1, FragmentSpliceScreen.this.menu.te.array.get(1)));
			this.setGhostRecipe(this.searchGui.getCurrentOption().get());
			this.searchGui.resetCurrentOption();
		}
	}
	
	private void setGhostRecipe(SearchOption option) {
		if(option.isPlant()) {
			Plants plantType = option.getPlant().get();
			this.searchGui.getRecipeManager().setRecipe(this.menu.getRecipeForPlant(plantType));
		}
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.craftButton.visible = this.canCraftNow();
		this.renderTooltip(stack, mouseX, mouseY);
		StringUtil.drawCenteredScaledString(stack, font, new TranslationTextComponent("block.pvz.fragment_splice").getContents(), this.leftPos + this.imageWidth / 2, this.topPos + 8, Colors.BLACK, 1F);
		StringUtil.drawCenteredScaledString(stack, font, "" + this.menu.te.array.get(0), this.leftPos + 15, this.topPos + 67, Colors.BLACK, 0.5F);
	    super.render(stack, mouseX, mouseY, partialTicks);
    }
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		int maxLen = 88;
		int len = RenderUtil.getRenderBarLen(this.menu.te.array.get(0), FragmentSpliceTileEntity.MAX_SUN_AMOUNT, maxLen);
		blit(stack, this.leftPos + 7, this.topPos + 113 - len + 1, 210, 0, 16, len);
		stack.popPose();
	}

	protected boolean canCraftNow() {
		return this.menu.te.handler.getStackInSlot(1).isEmpty() && this.menu.te.array.get(0) >= FragmentSpliceTileEntity.CRAFT_COST && this.menu.te.array.get(1) >= 0;
	}

	@Override
	public boolean isOptionUnLocked(SearchOption option) {
		return true;
	}

	@Override
	public List<SearchCategories> getSearchCategories() {
		return Arrays.asList(SearchCategories.PLANTS);
	}
	
}
