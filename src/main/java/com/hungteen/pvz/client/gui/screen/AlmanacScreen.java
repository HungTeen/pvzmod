package com.hungteen.pvz.client.gui.screen;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.paz.IPAZEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.gui.search.CategoryToggleWidget;
import com.hungteen.pvz.client.gui.search.SearchOption;
import com.hungteen.pvz.common.container.AlmanacContainer;
import com.hungteen.pvz.compat.patchouli.PVZPatchouliHandler;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AlmanacScreen extends AbstractOptionScreen<AlmanacContainer> {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac.png");
	private static final int MAX_ENTRY_COUNT = 7;
	private final List<Pair<IAlmanacEntry, Number>> entries = new ArrayList<>();
	private SearchOption option;
	private CreatureEntity renderEntity;
	private int currentPos = 0;
	
	public AlmanacScreen(AlmanacContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 150;
		this.imageHeight = 200;
	}

	@Override
	public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_) {
		if (this.entries.size() > MAX_ENTRY_COUNT) {
			final int next = (int) ((double) this.currentPos - p_mouseScrolled_5_);
			this.currentPos = MathHelper.clamp(next, 0, this.entries.size() - MAX_ENTRY_COUNT);
		}
		return true;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderAlmanac(stack, mouseX, mouseY);
		this.renderTooltip(stack, mouseX, mouseY);
	}
	
	protected void renderAlmanac(MatrixStack stack, int mouseX, int mouseY){
		if(! this.searchGui.getCurrentOption().isPresent()){
			return ;
		}
		SearchOption option = this.searchGui.getCurrentOption().get();
		if(! this.isOptionUnLocked(option)){
			return ;
		}
		this.renderTitle(stack, option);
		this.renderLogo(stack, option);
		this.renderBar(stack, option);
//		InventoryScreen.renderEntityInInventory(this.leftPos, this.topPos, 30, 1, 1, this.minecraft.player);
//		renderEntityInInventory(this.leftPos + 37, this.topPos + 60, 30F, this.leftPos - mouseX, this.topPos - mouseY, option);

		if(getRenderEntity(option) instanceof IPAZEntity){
			this.entries.clear();
			((IPAZEntity) getRenderEntity(option)).addAlmanacEntries(this.entries);
			for(int i = 0; i < Math.min(this.entries.size(), MAX_ENTRY_COUNT); ++ i){
				this.currentPos = MathHelper.clamp(this.currentPos, 0, this.entries.size() - 1);
				drawEntryAt(stack, this.entries.get(this.currentPos + i), i);
			}
		}
	}

	private void drawEntryAt(MatrixStack stack, Pair<IAlmanacEntry, Number> pair, int pos){
		final int incHeight = 20;
		final int posX = this.leftPos + 8 + 2;
		final int posY = this.topPos + 49 + 5;
		final String text = pair.getFirst().getText() + " : " + (pair.getSecond() instanceof Float ? String.format("%.2f", pair.getSecond()) : String.format("%d", pair.getSecond()));
		StringUtil.drawScaledString(stack, font, text, posX, posY + incHeight * pos, Colors.WHITE, 1f);
	}

	protected void renderTitle(MatrixStack stack, SearchOption a) {
		stack.pushPose();
		int dx = this.leftPos + 49 + 94 / 2, dy = this.topPos + 9;
		StringUtil.drawCenteredScaledString(stack, this.font, a.getType().getText().getString(), dx, dy, Colors.WHITE, 1f);
		stack.popPose();
	}
	
	@SuppressWarnings("deprecation")
	protected void renderLogo(MatrixStack stack, SearchOption a) {
		int dx = this.leftPos + 9, dy = this.topPos + 9;
		int scale = 2;
		RenderSystem.pushMatrix();
		RenderSystem.scaled(scale, scale, scale);
		RenderSystem.translated((dx % scale) * 1.0d / scale , (dy % scale) * 1.0d / scale, 0);
		this.itemRenderer.renderGuiItem(SearchOption.getItemStackByOption(a), dx / scale, dy / scale);
		RenderSystem.popMatrix();
	}

	protected void renderBar(MatrixStack stack, SearchOption a) {
		stack.pushPose();
		//Do not change the position values, if changed pls modify with the below method.
		final int len = 22;
		int posX = 53;
		int posY = 25;
		{//guide book.
			ItemStack guideBook = PVZPatchouliHandler.getPatchouliGuide();
			if(guideBook != ItemStack.EMPTY){
				this.itemRenderer.renderGuiItem(guideBook, this.leftPos + posX, this.topPos + posY);
			} else{
				this.minecraft.getTextureManager().bind(TEXTURE);
				blit(stack, this.leftPos + posX, this.topPos + posY, 224, 0, 16, 16);
			}
		}
		posX += len;
		{
			if(a.getType() instanceof IPlantType){
				ItemStack itemStack = new ItemStack(((IPlantType) a.getType()).getEssence().getEssenceItem());
				this.itemRenderer.renderGuiItem(itemStack, this.leftPos + posX, this.topPos + posY);
			}
		}
		posX += len;
		{
			this.itemRenderer.renderGuiItem(new ItemStack(a.getType().getRank().getTemplateCard()), this.leftPos + posX, this.topPos + posY);
		}
		posX += len;
		{
			this.minecraft.getTextureManager().bind(TEXTURE);
			if(a.getType().getSkills().isEmpty()) {
				blit(stack, this.leftPos + posX, this.topPos + posY, 224, 16, 16, 16);
			} else {
				blit(stack, this.leftPos + posX, this.topPos + posY, 240, 16, 16, 16);
			}
		}
		stack.popPose();
	}

	@Override
	protected void renderTooltip(MatrixStack stack, int mouseX, int mouseY) {
		super.renderTooltip(stack, mouseX, mouseY);
		if(this.option != null){
			final int len = 22;
			int posX = this.leftPos + 53;
			int posY = this.topPos + 25;
			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){

			}
			posX += len;
			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
				
				if(this.option.getType() instanceof IPlantType){
					Item item = ((IPlantType) this.option.getType()).getEssence().getEssenceItem();
					this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(
							new TranslationTextComponent("item.pvz." + item.getRegistryName().getPath())
					), mouseX, mouseY);
				}
			}
			posX += len;
			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
				this.minecraft.screen.renderComponentTooltip(stack, Arrays.asList(
						new TranslationTextComponent("item.pvz." + this.option.getType().getRank().getTemplateCard().getRegistryName().getPath())
				), mouseX, mouseY);
			}
			posX += len;
			if(MathUtil.isInArea(mouseX, mouseY, posX, posY, 16, 16)){
				List<ITextComponent> list = new ArrayList<>();
				this.option.getType().getSkills().forEach(skill -> {
					list.add(skill.getText().withStyle(TextFormatting.GREEN));
				});
				if(list.isEmpty()) {
//					list.add(new TranslationTextComponent("gui.))
					this.minecraft.screen.renderComponentTooltip(stack, list, mouseX, mouseY);
				} else {
					this.minecraft.screen.renderComponentTooltip(stack, list, mouseX, mouseY);
				}
			}
		}
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		if(this.entries.size() <= MAX_ENTRY_COUNT) {
			blit(stack, this.leftPos + 130, this.topPos + 49, 162, 0, 12, 15);
		} else {
			final int len = MathUtil.getBarLen(this.currentPos, this.entries.size() - MAX_ENTRY_COUNT, 143 - 15);
			blit(stack, this.leftPos + 130, this.topPos + 49 + len, 150, 0, 12, 15);
		}
//		InventoryScreen.renderEntityInInventory(this.leftPos, this.topPos, 30, 1, 1, this.minecraft.player);
	}

	//TODO Display Entity Model on Almanac Screen.
	public void renderEntityInInventory(int offsetX, int offsetY, float scale, float vecX, float vecY, SearchOption option) {
		LivingEntity livingEntity = getRenderEntity(option);
		if(this.getRenderEntity(option) == null){
			return;
		}
		scale /= option.getType().getRenderScale();
		float f = (float)Math.atan((double)(vecX / 40.0F));
		float f1 = (float)Math.atan((double)(vecY / 40.0F));
		RenderSystem.pushMatrix();
		RenderSystem.translatef((float)offsetX, (float)offsetY, 1000F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrixstack = new MatrixStack();
		matrixstack.translate(0.0D, 0.0D, 1000.0D);
		matrixstack.scale((float)scale, (float)scale, (float)scale);
		Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
		Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
		quaternion.mul(quaternion1);
		matrixstack.mulPose(quaternion);
		float f2 = livingEntity.yBodyRot;
		float f3 = livingEntity.yRot;
		float f4 = livingEntity.xRot;
		float f5 = livingEntity.yHeadRotO;
		float f6 = livingEntity.yHeadRot;
		livingEntity.yBodyRot = 180.0F + f * 20.0F;
		livingEntity.yRot = 180.0F + f * 40.0F;
		livingEntity.xRot = -f1 * 20.0F;
		livingEntity.yHeadRot = livingEntity.yRot;
		livingEntity.yHeadRotO = livingEntity.yRot;
		EntityRendererManager entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
		quaternion1.conj();
		entityrenderermanager.overrideCameraOrientation(quaternion1);
		entityrenderermanager.setRenderShadow(false);
		IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
		RenderSystem.runAsFancy(() -> {
			entityrenderermanager.render(livingEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
		});
		irendertypebuffer$impl.endBatch();
		entityrenderermanager.setRenderShadow(true);
		livingEntity.yBodyRot = f2;
		livingEntity.yRot = f3;
		livingEntity.xRot = f4;
		livingEntity.yHeadRotO = f5;
		livingEntity.yHeadRot = f6;
		RenderSystem.popMatrix();
	}

	public CreatureEntity getRenderEntity(SearchOption option){
		if(option.getType().getEntityType().isPresent()) {
			if (option.equals(this.option)) {
				return this.renderEntity == null ? this.renderEntity = option.getType().getEntityType().get().create(this.minecraft.level) : this.renderEntity;
			} else {
				this.currentPos = 0;
			}
			this.option = option;
			return this.renderEntity = option.getType().getEntityType().get().create(this.minecraft.level);
		}
		return null;
	}
	
	@Override
	public boolean isOptionUnLocked(SearchOption option) {
		return ! PlayerUtil.isPAZLocked(this.minecraft.player, option.getType());
	}

	@Override
	public List<CategoryToggleWidget.SearchCategories> getSearchCategories() {
		return Arrays.asList(CategoryToggleWidget.SearchCategories.ALL, CategoryToggleWidget.SearchCategories.PLANTS, CategoryToggleWidget.SearchCategories.ZOMBIES);
	}

}
