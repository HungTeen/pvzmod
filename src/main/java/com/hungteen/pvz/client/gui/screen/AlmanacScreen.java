package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.IPAZEntity;
import com.hungteen.pvz.client.RenderUtil;
import com.hungteen.pvz.utils.Util;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-23 10:59
 **/
public class AlmanacScreen extends PVZScreen {

    private static final ResourceLocation TEXTURE = Util.texture("gui/almanac.png");
    private static final int MAX_ENTRY_COUNT = 7;
    private final List<Pair<IAlmanacEntry, Number>> entries = new ArrayList<>();
    private final LivingEntity renderEntity;
    private int currentPos = 0;

    public AlmanacScreen(LivingEntity renderEntity) {
        this.renderEntity = renderEntity;
        this.imageWidth = 150;
        this.imageHeight = 200;
        if(this.renderEntity instanceof IPAZEntity){
            ((IPAZEntity) this.renderEntity).addAlmanacEntries(this.entries);
        }
    }

    @Override
    public boolean mouseScrolled(double p_94686_, double p_94687_, double p_94688_) {
        if (this.entries.size() > MAX_ENTRY_COUNT) {
            final int next = (int) ((double) this.currentPos - p_94688_);
            this.currentPos = Mth.clamp(next, 0, this.entries.size() - MAX_ENTRY_COUNT);
        }
        return true;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
    	RenderSystem.setShaderTexture(0, TEXTURE);
        stack.pushPose();
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        stack.popPose();

        RenderUtil.renderScrollBar(this, stack, this.leftPos + 130, this.topPos + 49, this.currentPos, this.entries.size(), MAX_ENTRY_COUNT, 143);
        
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderAlmanac(stack, mouseX, mouseY);
        this.renderTooltip(stack, mouseX, mouseY);
    }


    protected void renderAlmanac(PoseStack stack, int mouseX, int mouseY) {
        this.renderTitle(stack);
        this.renderBar(stack);

//        InventoryScreen.renderEntityInInventory(this.leftPos, this.topPos, 30, 1, 1, this.minecraft.player);
        if(this.renderEntity != null){
            RenderUtil.renderLivingOnGUI(this.renderEntity, this.leftPos + 26, this.topPos + 43, 20F, mouseX, mouseY, 0, 0, 0);
        }

        for (int i = 0; i < Math.min(this.entries.size(), MAX_ENTRY_COUNT); ++i) {
            this.currentPos = Mth.clamp(this.currentPos, 0, this.entries.size() - 1);
            drawEntryAt(stack, this.entries.get(this.currentPos + i), i);
        }
    }

    protected void renderTooltip(PoseStack stack, int mouseX, int mouseY) {
        final int len = 22;
        int posX = this.leftPos + 53;
        int posY = this.topPos + 25;
//        if (MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)) {
//
//        }
//        posX += len;
//        if (MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)) {
//            if (this.option.getType() instanceof IPlantType) {
//                Item item = ((IPlantType) this.option.getType()).getEssence().getEssenceItem();
//                this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(
//                        new TranslationTextComponent("item.pvz." + item.getRegistryName().getPath())
//                ), mouseX, mouseY);
//            }
//        }
//        posX += len;
//        if (MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)) {
//            this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(
//                    new TranslationTextComponent("item.pvz." + this.option.getType().getRank().getTemplateCard().getRegistryName().getPath())
//            ), mouseX, mouseY);
//        }
//        posX += len;
//        if (MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)) {
//            List<ITextComponent> list = new ArrayList<>();
//            this.option.getType().getSkills().forEach(skill -> {
//                list.add(skill.getText().withStyle(TextFormatting.GREEN));
//            });
//            if (list.isEmpty()) {
////					list.add(new TranslationTextComponent("gui.))
//                this.minecraft.screen.renderComponentTooltip(stack, list, mouseX, mouseY);
//            } else {
//                this.minecraft.screen.renderComponentTooltip(stack, list, mouseX, mouseY);
//            }
//        }
    }

    private void drawEntryAt(PoseStack stack, Pair<IAlmanacEntry, Number> pair, int pos) {
        final int incHeight = 20;
        final int posX = this.leftPos + 8 + 2;
        final int posY = this.topPos + 49 + 5;
        final String text = pair.getFirst().getText() + " : " + (pair.getSecond() instanceof Float ? String.format("%.2f", pair.getSecond()) : String.format("%d", pair.getSecond()));
        RenderUtil.drawScaledString(stack, font, text, posX, posY + incHeight * pos, Colors.WHITE, 1f);
    }

    protected void renderTitle(PoseStack stack) {
        stack.pushPose();
        int dx = this.leftPos + 49 + 94 / 2, dy = this.topPos + 9;
        RenderUtil.drawCenteredScaledString(stack, this.font, renderEntity.getType().getDescription().getString(), dx, dy, Colors.WHITE, 1f);
        stack.popPose();
    }

    protected void renderBar(PoseStack stack) {
        stack.pushPose();
        //Do not change the position values, if changed pls modify with the below method.
        final int len = 22;
        int posX = 53;
        int posY = 25;
        {//guide book.
//            ItemStack guideBook = PVZPatchouliHandler.getPatchouliGuide();
//            if (guideBook != ItemStack.EMPTY) {
//                this.itemRenderer.renderGuiItem(guideBook, this.leftPos + posX, this.topPos + posY);
//            } else {
//                this.minecraft.getTextureManager().bind(TEXTURE);
//                blit(stack, this.leftPos + posX, this.topPos + posY, 224, 0, 16, 16);
//            }
            posX += len;
        }
        {
//            if (a.getType() instanceof IPlantType) {
//                ItemStack itemStack = new ItemStack(((IPlantType) a.getType()).getEssence().getEssenceItem());
//                this.itemRenderer.renderGuiItem(itemStack, this.leftPos + posX, this.topPos + posY);
//            }
            posX += len;
        }
        {
//            this.itemRenderer.renderGuiItem(new ItemStack(a.getType().getRank().getTemplateCard()), this.leftPos + posX, this.topPos + posY);
            posX += len;
        }
        {
//            RenderSystem.setShaderTexture(0, TEXTURE);
//            if (a.getType().getSkills().isEmpty()) {
//                blit(stack, this.leftPos + posX, this.topPos + posY, 224, 16, 16, 16);
//            } else {
//                blit(stack, this.leftPos + posX, this.topPos + posY, 240, 16, 16, 16);
//            }
        }
        stack.popPose();
    }

}
