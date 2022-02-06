package com.hungteen.pvz.common.world.structure.zombie;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.common.entity.zombie.grass.TombStoneEntity;
import com.hungteen.pvz.common.world.structure.PVZTemplateComponent;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class GraveHouseComponents {

	public static final ResourceLocation res = StringUtil.prefix("zombie_house/graveyard");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      list.add(new GraveHouseComponent(manager, res, pos1, rotation));
    }
	
	public static class GraveHouseComponent extends PVZTemplateComponent{

		private static final IStructurePieceType type = StructureRegister.GRAVE_HOUSE_PIECE;
		
		public GraveHouseComponent(TemplateManager manager, CompoundNBT nbt) {
			super(type, manager, nbt);
		}

		public GraveHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(type, manager, res, pos, rotation);
		}
		
		@Override
		public boolean postProcess(ISeedReader worldIn, StructureManager p_230383_2_, ChunkGenerator chunkGeneratorIn, Random randomIn,
				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn, BlockPos blockPos) {
			int dx = 15, dz = 15;
			switch(rotation) {
			case CLOCKWISE_90:{dx = - 15; break;}
			case CLOCKWISE_180:{dx = dz = - 15; break;}
			case COUNTERCLOCKWISE_90:{dz = - 15; break;}
			default:{}
			}
			int x = this.templatePosition.getX(), z = this.templatePosition.getZ(); 
			int h1 = chunkGeneratorIn.getFirstOccupiedHeight(x , z, Heightmap.Type.WORLD_SURFACE_WG);
			int h2 = chunkGeneratorIn.getFirstOccupiedHeight(x + dx, z, Heightmap.Type.WORLD_SURFACE_WG);
			int h3 = chunkGeneratorIn.getFirstOccupiedHeight(x , z + dz, Heightmap.Type.WORLD_SURFACE_WG);
			int h4 = chunkGeneratorIn.getFirstOccupiedHeight(x + dx, z + dz, Heightmap.Type.WORLD_SURFACE_WG);
			int height = Math.min(Math.min(h1, h2), Math.min(h3, h4));
			this.templatePosition=new BlockPos(x, height, z);
			return super.postProcess(worldIn, p_230383_2_, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn, blockPos);
		}
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if(function.equals("bonus_chest1")) {
				this.createChest(worldIn, sbb, rand, pos, PVZLoot.GRAVE_YARD_CHEST, null);
			} else if(function.equals("bonus_chest2")) {
				this.createChest(worldIn, sbb, rand, pos, PVZLoot.GRAVE_YARD_CHEST, null);
			} else if(function.equals("spawner")) {
				worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
				TileEntity te = worldIn.getBlockEntity(pos);
				if(te instanceof MobSpawnerTileEntity) {
					((MobSpawnerTileEntity)te).getSpawner().setEntityId(getRandomEntityType(rand));
				}
			} else if(function.startsWith("tomb")) {
				if(rand.nextInt(3) == 0) {
					TombStoneEntity tomb = EntityRegister.TOMB_STONE.get().create(worldIn.getLevel());
				    EntityUtil.onEntitySpawn(worldIn, tomb, pos);
				    worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
		
		protected EntityType<?> getRandomEntityType(Random rand){
			int num = rand.nextInt(2);
			if(num == 0) return EntityRegister.GIGA_FOOTBALL_ZOMBIE.get();
			else if(num == 1) return EntityRegister.FOOTBALL_ZOMBIE.get();
			return null;
		}
		
	}
	
}
