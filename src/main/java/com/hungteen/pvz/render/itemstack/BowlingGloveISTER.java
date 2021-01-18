package com.hungteen.pvz.render.itemstack;

import java.util.Optional;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.item.tool.BowlingGloveItem;
import com.hungteen.pvz.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.render.entity.plant.defence.WallNutRender;
import com.hungteen.pvz.render.entity.plant.explosion.ExplodeONutRender;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class BowlingGloveISTER extends ItemStackTileEntityRenderer {

	private final EntityModel<WallNutEntity> model = new WallNutModel<WallNutEntity>();
	private int degree = 0;
	
	@Override
	public void render(ItemStack stack, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, null, null);
        matrixStackIn.push();
        matrixStackIn.translate(0.8F, 0.7F, 0.4F);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel.getBakedModel());
        matrixStackIn.pop();
		Optional<Plants> plantType = BowlingGloveItem.getBowlingType(stack);
		if(! plantType.isPresent()) return ;
		if (degree >= 360) {
            degree -= 360;
        }
        degree += 4;
		matrixStackIn.push();
		matrixStackIn.scale(1, - 1, 1);
		float f = getRenderSize(plantType.get());
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(0.0, - 1.501, 0.0);
		matrixStackIn.rotate(Vector3f.XN.rotationDegrees(- degree));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(plantType.get())));
        this.model.render(matrixStackIn, ivertexbuilder, combinedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
	}
	
	protected float getRenderSize(Plants plant) {
		if(plant == Plants.GIANT_WALL_NUT) return 0.38F;
		return 0.2F;
	}
	
	public ResourceLocation getEntityTexture(Plants plant) {
		if(plant == Plants.EXPLODE_O_NUT) return ExplodeONutRender.TEXTURE1;
		return WallNutRender.TEXTURE1;
	}
	
}
