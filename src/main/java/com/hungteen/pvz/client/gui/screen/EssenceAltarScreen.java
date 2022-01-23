package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.client.gui.GuiHandler;
import com.hungteen.pvz.client.gui.search.RecipeManager;
import com.hungteen.pvz.client.gui.widget.DisplayField;
import com.hungteen.pvz.client.gui.widget.PVZButton;
import com.hungteen.pvz.common.container.EssenceAltarContainer;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.ClickButtonPacket;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;
import java.util.List;

public class EssenceAltarScreen extends PVZContainerScreen<EssenceAltarContainer> {

	private static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/essence_altar.png");
	private static final int MAX_ENTRY_COUNT = 4;
	private final RecipeManager recipeManager = new RecipeManager();
	private EssenceButton[] buttons = new EssenceButton[MAX_ENTRY_COUNT];
	private IPAZType tmpType;
	private int currentPos = 0;
	private int mouseX;
	private int mouseY;

	public EssenceAltarScreen(EssenceAltarContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 166;
		this.tips.add(new DisplayField.TipField(3, 3, Arrays.asList(
				new TranslationTextComponent("gui.pvz.essence_altar.tip1"),
				new TranslationTextComponent("gui.pvz.essence_altar.tip2"),
				new TranslationTextComponent("gui.pvz.essence_altar.tip3")
		)));
	}

	@Override
	protected void init() {
		super.init();
		for(int i = 0; i < MAX_ENTRY_COUNT; ++ i){
			final int pos = i;
			this.buttons[i] = new EssenceButton(this.leftPos + 69, this.topPos + 5 + 19 * i, button -> {
				if(button.visible) {
					PVZPacketHandler.CHANNEL.sendToServer(new ClickButtonPacket(GuiHandler.ESSENCE_ALTAR, this.currentPos + pos, 0));
				}
			});
			this.addButton(this.buttons[i]);
			this.buttons[i].visible = true;
		}
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		stack.pushPose();
        this.minecraft.getTextureManager().bind(TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        stack.popPose();

        this.minecraft.getTextureManager().bind(StringUtil.WIDGETS);
		final int count = this.menu.getAvailableSkills().size();
		if(count > MAX_ENTRY_COUNT){
			final int len = MathUtil.getBarLen(this.currentPos, count - MAX_ENTRY_COUNT, 76 - 15);
			blit(stack, this.leftPos + 156, this.topPos + 5 + len, 15, 0, 12, 15);
		} else{
			blit(stack, this.leftPos + 156, this.topPos + 5, 27, 0, 12, 15);
		}
		super.renderBg(stack, partialTicks, mouseX, mouseY);
	}

	private void renderEntry(MatrixStack stack, int buttonPos, ISkillType type, int lvl){
		this.menu.getPAZType().ifPresent(pazType -> {
			final int x = this.buttons[buttonPos].x;
			final int y = this.buttons[buttonPos].y;

			this.renderLogo(stack, pazType.getRank(), x + 2, y + 2);

			StringUtil.drawCenteredScaledString(stack, this.minecraft.font, type.getCostAt(lvl) + "", x + 13, y + 12, Colors.WHITE, 0.6F);
			StringUtil.drawScaledString(stack, this.minecraft.font, type.getText().append(StringUtil.getRomanString(lvl + 1)).getString(), x + 18, y + 5, Colors.WHITE, 1F);

			if(this.buttons[buttonPos].visible && this.buttons[buttonPos].isHovered()){
				this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(type.getDescription()), this.mouseX, this.mouseY);
			}
		});
	}

	private void renderLogo(MatrixStack stack, IRankType rank, int posX, int posY){
		this.minecraft.getTextureManager().bind(StringUtil.WIDGETS);
		int x = 239;
		int y = 74;
		if(rank == RankTypes.GRAY){
			x = 174; y = 58;
		} else if(rank == RankTypes.WHITE){
			x = 190; y = 58;
		} else if(rank == RankTypes.GREEN){
			x = 206; y = 58;
		} else if(rank == RankTypes.BLUE){
			x = 222; y = 58;
		} else if(rank == RankTypes.PURPLE){
			x = 174; y = 74;
		} else if(rank == RankTypes.GOLD){
			x = 190; y = 74;
		} else if(rank == RankTypes.RED){
			x = 206; y = 74;
		} else if(rank == RankTypes.BLACK){
			x = 222; y = 74;
		}
		blit(stack, posX, posY, x, y, 11, 10);
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		final List<ISkillType> skills = this.menu.getAvailableSkills();
		final List<Integer> levels = this.menu.getCurrentSkillLevel();
		this.currentPos = MathHelper.clamp(this.currentPos, 0, Math.max(0, skills.size() - MAX_ENTRY_COUNT));
		final int count = Math.min(skills.size(), MAX_ENTRY_COUNT);
		for(int i = 0; i < MAX_ENTRY_COUNT; ++ i){
			this.buttons[i].visible = (i < count);
			if(this.buttons[i].visible){
				this.renderEntry(stack, i, skills.get(this.currentPos + i), levels.get(this.currentPos + i));
			}
		}

		if(this.menu.getPAZType().isPresent() && this.tmpType != this.menu.getPAZType().get()){
			this.tmpType = this.menu.getPAZType().get();
			this.recipeManager.setRecipe(Arrays.asList(
					//TODO Check Null because of zombie card.
					Pair.of(Ingredient.of(this.tmpType.getEnjoyCard().get()), this.menu.getSlot(1)),
					Pair.of(Ingredient.of(this.tmpType.getEssence().getEssenceItem()), this.menu.getSlot(2)),
					Pair.of(Ingredient.of(this.tmpType.getRank().getMaterial()), this.menu.getSlot(3))
			));
		} else if(! this.menu.getPAZType().isPresent()){
			this.tmpType = null;
			this.recipeManager.clear();
		}

		this.recipeManager.render(this.minecraft, stack, this.leftPos, this.topPos, partialTicks);

		this.renderTooltip(stack, mouseX, mouseY);
		this.recipeManager.renderGhostRecipeTooltip(this.minecraft, stack, this.leftPos, this.topPos, mouseX, mouseY);
	}

	@Override
	protected void slotClicked(Slot slot, int p_184098_2_, int p_184098_3_, ClickType p_184098_4_) {
		super.slotClicked(slot, p_184098_2_, p_184098_3_, p_184098_4_);
		if(slot != null && slot.getSlotIndex() > 0 && slot.getSlotIndex() < 4){
			this.recipeManager.clear();
		}
	}

	@Override
	public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
		if (this.menu.getAvailableSkills().size() > MAX_ENTRY_COUNT) {
			final int next = (int) ((double) this.currentPos - p_mouseScrolled_5_);
			this.currentPos = MathHelper.clamp(next, 0, this.menu.getAvailableSkills().size() - MAX_ENTRY_COUNT);
		}
		return true;
	}

	private static class EssenceButton extends PVZButton{

		public EssenceButton(int x, int y, IPressable onPress) {
			super(StringUtil.WIDGETS, x, y, 82, 19, onPress);
		}

		@Override
		protected Pair<Integer, Integer> getButtonUV() {
			return Pair.of(174, 0);
		}

		@Override
		protected Pair<Integer, Integer> getButtonUVOffset() {
			return Pair.of(0, 38);
		}
	}

}
