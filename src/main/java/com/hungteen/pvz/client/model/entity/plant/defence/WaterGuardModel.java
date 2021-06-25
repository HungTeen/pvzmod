package com.hungteen.pvz.client.model.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.defence.WaterGuardEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WaterGuardModel extends PVZPlantModel<WaterGuardEntity> {
	private final ModelRenderer total;
	private final ModelRenderer side;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer side5;
	private final ModelRenderer side6;

	public WaterGuardModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(188, 221).addBox(-8.0F, -17.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		side = new ModelRenderer(this);
		side.setPos(0.0F, -1.0F, 0.0F);
		total.addChild(side);
		setRotationAngle(side, 0.0F, 0.0F, -3.1416F);
		side.texOffs(144, 234).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, true);
		side.texOffs(165, 214).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, true);
		side.texOffs(221, 202).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, true);
		side.texOffs(202, 199).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, true);
		side.texOffs(189, 202).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		side.texOffs(136, 233).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		side.texOffs(147, 211).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, true);
		side.texOffs(154, 193).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, true);
		side.texOffs(228, 186).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, true);
		side.texOffs(204, 186).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		side.texOffs(186, 187).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

		side2 = new ModelRenderer(this);
		side2.setPos(-8.0F, -9.0F, 0.0F);
		total.addChild(side2);
		setRotationAngle(side2, 0.0F, 0.0F, -1.5708F);
		side2.texOffs(92, 235).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side2.texOffs(69, 229).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side2.texOffs(109, 216).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side2.texOffs(89, 211).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side2.texOffs(75, 248).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side2.texOffs(48, 244).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side2.texOffs(22, 241).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side2.texOffs(39, 225).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side2.texOffs(3, 236).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side2.texOffs(21, 223).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side2.texOffs(5, 221).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side3 = new ModelRenderer(this);
		side3.setPos(8.0F, -9.0F, 0.0F);
		total.addChild(side3);
		setRotationAngle(side3, 0.0F, 0.0F, 1.5708F);
		side3.texOffs(43, 205).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side3.texOffs(10, 198).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side3.texOffs(82, 192).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side3.texOffs(121, 200).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side3.texOffs(144, 195).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side3.texOffs(66, 192).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side3.texOffs(35, 189).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side3.texOffs(6, 179).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side3.texOffs(112, 184).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side3.texOffs(160, 179).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side3.texOffs(140, 184).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side4 = new ModelRenderer(this);
		side4.setPos(0.0F, -17.0F, 0.0F);
		total.addChild(side4);
		side4.texOffs(213, 163).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side4.texOffs(189, 157).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side4.texOffs(34, 170).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side4.texOffs(71, 175).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side4.texOffs(104, 183).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side4.texOffs(6, 166).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side4.texOffs(59, 163).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side4.texOffs(22, 154).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side4.texOffs(131, 169).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side4.texOffs(115, 167).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side4.texOffs(160, 168).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side5 = new ModelRenderer(this);
		side5.setPos(0.0F, -9.0F, -8.0F);
		total.addChild(side5);
		setRotationAngle(side5, 1.5708F, 0.0F, 0.0F);
		side5.texOffs(212, 140).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side5.texOffs(161, 146).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side5.texOffs(121, 147).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side5.texOffs(86, 156).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side5.texOffs(9, 150).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side5.texOffs(50, 147).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side5.texOffs(77, 140).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side5.texOffs(104, 134).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side5.texOffs(148, 138).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side5.texOffs(192, 139).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side5.texOffs(30, 144).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side6 = new ModelRenderer(this);
		side6.setPos(0.0F, -9.0F, 8.0F);
		total.addChild(side6);
		setRotationAngle(side6, -1.5708F, 0.0F, 0.0F);
		side6.texOffs(7, 121).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side6.texOffs(53, 121).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side6.texOffs(91, 115).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side6.texOffs(133, 122).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side6.texOffs(173, 131).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side6.texOffs(209, 126).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side6.texOffs(230, 118).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side6.texOffs(9, 102).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side6.texOffs(41, 105).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side6.texOffs(76, 108).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side6.texOffs(121, 107).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(WaterGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<WaterGuardEntity> getPlantModel() {
		return this;
	}
}