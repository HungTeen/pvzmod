package com.hungteen.pvz.render.tileentity;

import com.hungteen.pvz.block.AbstractFacingBlock;
import com.hungteen.pvz.block.special.SunFlowerTrophyBlock;
import com.hungteen.pvz.model.entity.plant.light.SunFlowerModel;
import com.hungteen.pvz.tileentity.SunFlowerTrophyTileEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunFlowerTrophyTER extends TileEntityRenderer<SunFlowerTrophyTileEntity> {

	protected final SunFlowerModel model = new SunFlowerModel();
	
	public SunFlowerTrophyTER(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(SunFlowerTrophyTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		SunFlowerTrophyBlock block = (SunFlowerTrophyBlock) tileEntityIn.getBlockState().getBlock();
		if(block == null) return ;
		matrixStackIn.push();
		matrixStackIn.scale(- 1, - 1, 1);
		float size = 0.4F;
		matrixStackIn.scale(size, size, size);
		matrixStackIn.translate(- 1.25D, - 3.4D, 1.25D);
		Direction facing = tileEntityIn.getBlockState().get(AbstractFacingBlock.FACING);
		if(facing == Direction.SOUTH) matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180));
		else if(facing == Direction.WEST) matrixStackIn.rotate(Vector3f.YP.rotationDegrees(- 90));
		else if(facing == Direction.EAST) matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90));
		IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntitySolid(getResourceByBlock(block)));
		this.model.render(matrixStackIn, builder, 200, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
		
		matrixStackIn.pop();
	}
	
	private ResourceLocation getResourceByBlock(SunFlowerTrophyBlock block) {
		if(block.lvl == 1) return StringUtil.prefix("textures/entity/plant/light/silver_sunflower.png");
		if(block.lvl == 2) return StringUtil.prefix("textures/entity/plant/light/gold_sunflower.png");
		return StringUtil.prefix("textures/entity/plant/light/diamond_sunflower.png");
	}

}
