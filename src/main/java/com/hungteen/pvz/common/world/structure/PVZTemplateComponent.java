package com.hungteen.pvz.common.world.structure;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public abstract class PVZTemplateComponent extends TemplateStructurePiece {

	private static final BlockPos STRUCTURE_OFFSET = new BlockPos(0, 0, 0);
	protected final Rotation rotation;
	protected final ResourceLocation res;
	
	public PVZTemplateComponent(IStructurePieceType type, TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
		super(type, 0);
		this.templatePosition = pos;
		this.rotation = rotation;
		this.res=res;
		this.setUpTemplate(manager);
	}
	
	public PVZTemplateComponent(IStructurePieceType type, TemplateManager manager, CompoundNBT nbt) {
		super(type, nbt);
		this.res = new ResourceLocation(nbt.getString("Template"));
		this.rotation = Rotation.valueOf(nbt.getString("Rot"));
		this.setUpTemplate(manager);
	}

	private void setUpTemplate(TemplateManager p_204754_1_) {
		Template template = p_204754_1_.getOrCreate(this.res);
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
				.setMirror(Mirror.NONE).setRotationPivot(STRUCTURE_OFFSET)
				.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
		this.setup(template, this.templatePosition, placementsettings);
	}
	
	@Override
	protected void addAdditionalSaveData(CompoundNBT tagCompound) {
		super.addAdditionalSaveData(tagCompound);
		tagCompound.putString("Template", this.res.toString());
        tagCompound.putString("Rot", this.rotation.name());
	}
	
}
