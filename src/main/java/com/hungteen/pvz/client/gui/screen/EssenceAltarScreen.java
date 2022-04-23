package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.client.RenderUtil;
import com.hungteen.pvz.client.gui.RecipeManager;
import com.hungteen.pvz.client.gui.widget.DisplayField;
import com.hungteen.pvz.client.gui.widget.PVZButton;
import com.hungteen.pvz.common.menu.EssenceAltarMenu;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.Util;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 12:27
 **/
public class EssenceAltarScreen extends PVZContainerScreen<EssenceAltarMenu> {

    private static final ResourceLocation TEXTURE = Util.texture("gui/container/essence_altar.png");
    private static final int MAX_ENTRY_COUNT = 4;
    private final RecipeManager recipeManager = new RecipeManager();
    private EssenceButton[] buttons = new EssenceButton[MAX_ENTRY_COUNT];
    private IPAZType tmpType;
    private int currentPos = 0;
    private int mouseX;
    private int mouseY;

    public EssenceAltarScreen(EssenceAltarMenu screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.imageWidth = 176;
        this.imageHeight = 170;
        this.tips.add(new DisplayField.TipField(3, 3, Arrays.asList(
                new TranslatableComponent("gui.pvz.essence_altar.tip1"),
                new TranslatableComponent("gui.pvz.essence_altar.tip2"),
                new TranslatableComponent("gui.pvz.essence_altar.tip3")
        )));
    }

    @Override
    protected void init() {
        super.init();
        for(int i = 0; i < MAX_ENTRY_COUNT; ++ i){
            final int pos = i;
            this.buttons[i] = new EssenceButton(this.leftPos + 69, this.topPos + 5 + 19 * i, button -> {
                if(button.visible) {
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, this.currentPos + pos);
                }
            });
            this.addRenderableWidget(this.buttons[i]);
            this.buttons[i].visible = false;
        }
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        stack.pushPose();
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        stack.popPose();

        //render scroll bar.
        final int count = this.menu.getAvailableSkills().size();
        RenderUtil.renderScrollBar(this, stack, this.leftPos + 156, this.topPos + 7, this.currentPos, count, MAX_ENTRY_COUNT, 76);

        super.renderBg(stack, partialTicks, mouseX, mouseY);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        final List<ISkillType> skills = this.menu.getAvailableSkills();
        final List<Integer> levels = this.menu.getCurrentSkillLevel();
        this.currentPos = Mth.clamp(this.currentPos, 0, Math.max(0, skills.size() - MAX_ENTRY_COUNT));
        final int count = Math.min(skills.size(), MAX_ENTRY_COUNT);
        for(int i = 0; i < MAX_ENTRY_COUNT; ++ i){
            final boolean hasEntry = (i < count);
            this.buttons[i].visible = hasEntry && this.menu.canLearnCurrentSkill(skills.get(this.currentPos + i));
            if(i < count){
                this.renderEntry(stack, i, skills.get(this.currentPos + i), levels.get(this.currentPos + i));
            }
        }

        if(this.menu.getPAZType().isPresent() && this.tmpType != this.menu.getPAZType().get()){
            this.tmpType = this.menu.getPAZType().get();
            this.recipeManager.setRecipe(Arrays.asList(
                    //TODO Check Null because of zombie card.
                    Pair.of(Ingredient.of(this.tmpType.getEnjoyCard().get()), this.menu.getSlot(1)),
                    Pair.of(Ingredient.of(this.tmpType.getEssenceType().getEssenceItem()), this.menu.getSlot(2)),
                    Pair.of(Ingredient.of(this.tmpType.getRankType().getMaterial()), this.menu.getSlot(3))
            ));
        } else if(! this.menu.getPAZType().isPresent()){
            this.tmpType = null;
            this.recipeManager.clear();
        }

        this.recipeManager.render(this.minecraft, stack, this.leftPos, this.topPos, partialTicks);

        this.renderTooltip(stack, mouseX, mouseY);
        this.recipeManager.renderGhostRecipeTooltip(this.minecraft, stack, this.leftPos, this.topPos, mouseX, mouseY);
    }

    private void renderEntry(PoseStack stack, int buttonPos, ISkillType type, int lvl){
        this.menu.getPAZType().ifPresent(pazType -> {
            final int x = this.buttons[buttonPos].x;
            final int y = this.buttons[buttonPos].y;

            this.renderLogo(stack, pazType.getRankType(), x + 2, y + 2);

            RenderUtil.drawCenteredScaledString(stack, this.minecraft.font, type.getCostAt(lvl) + "", x + 13, y + 12, Colors.WHITE, 0.6F);
            RenderUtil.drawScaledString(stack, this.minecraft.font, type.getText().append(" " + StringUtil.getRomanString(lvl + 1)).getString(), x + 18, y + 6, Colors.WHITE, 0.75F);

            if(this.buttons[buttonPos].isHovered()){
                this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(type.getDescription()), this.mouseX, this.mouseY);
            }
        });
    }

    private void renderLogo(PoseStack stack, IRankType rank, int posX, int posY){
        RenderSystem.setShaderTexture(0, Util.WIDGETS);
        int x = 239;
        int y = 74;
//        if(rank == RankTypes.GRAY){
//            x = 174; y = 58;
//        } else if(rank == RankTypes.WHITE){
//            x = 190; y = 58;
//        } else if(rank == RankTypes.GREEN){
//            x = 206; y = 58;
//        } else if(rank == RankTypes.BLUE){
//            x = 222; y = 58;
//        } else if(rank == RankTypes.PURPLE){
//            x = 174; y = 74;
//        } else if(rank == RankTypes.GOLD){
//            x = 190; y = 74;
//        } else if(rank == RankTypes.RED){
//            x = 206; y = 74;
//        } else if(rank == RankTypes.BLACK){
//            x = 222; y = 74;
//        }

        stack.pushPose();
        this.blit(stack, posX, posY, x, y, 11, 10);
        stack.popPose();
    }

    @Override
    protected void slotClicked(Slot slot, int x, int y, ClickType type) {
        super.slotClicked(slot, x, y, type);
        if(slot != null && slot.getSlotIndex() > 0 && slot.getSlotIndex() < 4){
            this.recipeManager.clear();
        }
    }

    @Override
    public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
        if (this.menu.getAvailableSkills().size() > MAX_ENTRY_COUNT) {
            final int next = (int) ((double) this.currentPos - p_mouseScrolled_5_);
            this.currentPos = Mth.clamp(next, 0, this.menu.getAvailableSkills().size() - MAX_ENTRY_COUNT);
        }
        return true;
    }

    private static class EssenceButton extends PVZButton {

        public EssenceButton(int x, int y, Button.OnPress onPress) {
            super(Util.WIDGETS, x, y, 82, 19, onPress);
        }

        @Override
        protected Pair<Integer, Integer> getButtonUV() {
            return Pair.of(174, 0);
        }

        @Override
        protected Pair<Integer, Integer> getButtonUVOffset() {
            return Pair.of(0, 19);
        }
    }


}
