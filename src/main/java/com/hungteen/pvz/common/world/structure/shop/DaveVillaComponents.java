package com.hungteen.pvz.common.world.structure.shop;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.common.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.common.entity.npc.PennyEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.common.world.structure.PVZTemplateComponent;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class DaveVillaComponents {
	
	public static final BlockState BASE_BLOCK = Blocks.DIRT.defaultBlockState();
	public static final ResourceLocation res1 = StringUtil.prefix("dave_villa/davevilla1");
	public static final ResourceLocation res2 = StringUtil.prefix("dave_villa/davevilla2");
	public static final ResourceLocation res3 = StringUtil.prefix("dave_villa/davevilla3");
	public static final ResourceLocation res4 = StringUtil.prefix("dave_villa/davevilla4");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      BlockPos pos2,pos3,pos4;
	      switch (rotation) {
		  case CLOCKWISE_90:{
			  pos2=pos1.offset(0, 0, 32);
			  pos3=pos1.offset(-32, 0, 0);
			  pos4=pos2.offset(-32, 0, 0);
			  break;
		  }
		  case CLOCKWISE_180:{
			  pos2=pos1.offset(-32, 0, 0);
			  pos3=pos1.offset(0, 0, -32);
			  pos4=pos2.offset(0, 0, -32);
			  break;
		  }
		  case COUNTERCLOCKWISE_90:{
			  pos2=pos1.offset(0, 0, -32);
			  pos3=pos1.offset(32, 0, 0);
			  pos4=pos2.offset(32, 0, 0);
			  break;
		  }
		  default:{
			  pos2=pos1.offset(32, 0, 0);
			  pos3=pos1.offset(0, 0, 32);
			  pos4=pos2.offset(0, 0, 32);
			  break;
		  }
		  }
	      list.add(new DaveVillaComponent(manager, res1, pos1, rotation));
	      list.add(new DaveVillaComponent(manager, res2, pos2, rotation));
	      list.add(new DaveVillaComponent(manager, res3, pos3, rotation));
	      list.add(new DaveVillaComponent(manager, res4, pos4, rotation));
    }
	
	public static class DaveVillaComponent extends PVZTemplateComponent {

        private static final IStructurePieceType type = StructureRegister.DAVE_VILLA_PIECE;
		
		public DaveVillaComponent(TemplateManager manager, CompoundNBT nbt) {
			super(type, manager, nbt);
		}

		public DaveVillaComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(type, manager, res, pos, rotation);
		}

		public BlockPos getBlockPos(){
			return this.templatePosition;
		}
		
		@Override
		protected void addAdditionalSaveData(CompoundNBT tagCompound) {
			super.addAdditionalSaveData(tagCompound);
			tagCompound.putString("Template", this.res.toString());
	        tagCompound.putString("Rot", this.rotation.name());
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if(function.equals("dave")) {
				CrazyDaveEntity dave = EntityRegister.CRAZY_DAVE.get().create(worldIn.getLevel());
				EntityUtil.onEntitySpawn(worldIn, dave, pos);
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("panney")){
				PennyEntity panney = EntityRegister.PANNEY.get().create(worldIn.getLevel());
				EntityUtil.onEntitySpawn(worldIn, panney, pos);
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("plant_pos1")){
				if(rand.nextInt(3) == 0) {
					PeaShooterEntity nut = EntityRegister.PEA_SHOOTER.get().create(worldIn.getLevel());
					EntityUtil.onEntitySpawn(worldIn, nut, pos);
				}
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("plant_pos2")){
				if(rand.nextInt(3) == 0) {
					SunFlowerEntity nut = EntityRegister.SUN_FLOWER.get().create(worldIn.getLevel());
					EntityUtil.onEntitySpawn(worldIn, nut, pos);
				}
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("plant_pos3")){
				if(rand.nextInt(3) == 0) {
					PeaShooterEntity nut = EntityRegister.PEA_SHOOTER.get().create(worldIn.getLevel());
					EntityUtil.onEntitySpawn(worldIn, nut, pos);
				}
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("plant_pos4")){
				if(rand.nextInt(2) == 0) {
					WallNutEntity nut = EntityRegister.WALL_NUT.get().create(worldIn.getLevel());
					EntityUtil.onEntitySpawn(worldIn, nut, pos);
				}
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("plant_pos5")){
				SunFlowerEntity nut = EntityRegister.SUN_FLOWER.get().create(worldIn.getLevel());
				EntityUtil.onEntitySpawn(worldIn, nut, pos);
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("plant_pos6")) {
				if(rand.nextInt(2) == 0) {
					PeaShooterEntity nut = EntityRegister.PEA_SHOOTER.get().create(worldIn.getLevel());
					EntityUtil.onEntitySpawn(worldIn, nut, pos);
				}
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
			}else if(function.equals("bonus_chest1")) {
				this.createChest(worldIn, sbb, rand, pos, PVZLoot.DAVE_VILLA_CHEST, null);
			}else if(function.equals("bonus_chest2")) {
				this.createChest(worldIn, sbb, rand, pos, PVZLoot.DAVE_VILLA_CHEST, null);
			}
		}
		
		@Override
		public boolean postProcess(ISeedReader worldIn, StructureManager manager, ChunkGenerator chunkGeneratorIn, Random randomIn,
				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn, BlockPos blockPos) {
			BlockPos pos = this.templatePosition;
			BlockPos size = this.template.getSize();
			BlockPos ab = new BlockPos(0, size.getY(), 0);
			switch(rotation) {
			case CLOCKWISE_90:{
				ab = ab.offset(-size.getZ(), 0, size.getX());
				break;
			}
			case CLOCKWISE_180:{
				ab = ab.offset(-size.getX(), 0, -size.getZ());
				break;
			}
			case COUNTERCLOCKWISE_90:{
				ab = ab.offset(size.getZ(), 0, -size.getX());
				break;
			}
			default:{
				ab = ab.offset(size.getX(), 0, size.getZ());
				break;
			}
			}
			BlockPos to = pos.offset(ab.getX(), ab.getY(), ab.getZ());
			BlockPos min = new BlockPos(Math.min(pos.getX(), to.getX()),Math.min(pos.getY(), to.getY()),Math.min(pos.getZ(), to.getZ()));
			BlockPos max = new BlockPos(Math.max(pos.getX(), to.getX()),Math.max(pos.getY(), to.getY()),Math.max(pos.getZ(), to.getZ()));
			super.postProcess(worldIn, manager, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn, blockPos);
			for(int i = min.getX(); i <= max.getX(); ++ i) {
				for(int j = min.getZ(); j <= max.getZ(); ++ j) {
					int y = min.getY() - 1;
					while(y >= 50) {
						BlockPos tmp = new BlockPos(i,y,j);
						if(worldIn.getBlockState(tmp).canOcclude()) {
							break;
						}
						else {
							worldIn.setBlock(tmp, BASE_BLOCK, 3);
						}
						y--;
					}
				}
			}
			return true;
		}

	}
	
}
