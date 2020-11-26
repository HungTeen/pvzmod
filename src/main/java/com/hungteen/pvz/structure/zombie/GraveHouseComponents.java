package com.hungteen.pvz.structure.zombie;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.StructureRegister;
import com.hungteen.pvz.structure.PVZTemplateComponent;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class GraveHouseComponents {

	public static final ResourceLocation res = StringUtil.prefix("zombie_house/graveyard");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      list.add(new GraveHouseComponent(manager, res, pos1, rotation));
    }
	
	public static class GraveHouseComponent extends PVZTemplateComponent{

		private static final IStructurePieceType type = StructureRegister.GRAVE_HOUSE;
		
		public GraveHouseComponent(TemplateManager manager, CompoundNBT nbt) {
			super(type, manager, nbt);
		}

		public GraveHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(type, manager, res, pos, rotation);
		}
		
		@Override
		public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn,
				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
			int dx = 15, dz = 15;
			switch(rotation) {
			case CLOCKWISE_90:{dx = - 15; break;}
			case CLOCKWISE_180:{dx = dz = - 15; break;}
			case COUNTERCLOCKWISE_90:{dz = - 15; break;}
			default:{}
			}
			int x = this.templatePosition.getX(), z = this.templatePosition.getZ(); 
			int h1 = chunkGeneratorIn.func_222531_c(x , z, Type.WORLD_SURFACE_WG);
			int h2 = chunkGeneratorIn.func_222531_c(x + dx, z, Type.WORLD_SURFACE_WG);
			int h3 = chunkGeneratorIn.func_222531_c(x , z + dz, Type.WORLD_SURFACE_WG);
			int h4 = chunkGeneratorIn.func_222531_c(x + dx, z + dz, Type.WORLD_SURFACE_WG);
			int height = Math.min(Math.min(h1, h2), Math.min(h3, h4));
			this.templatePosition=new BlockPos(x, height, z);
			return super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
		}
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if(function.equals("bonus_chest1")) {
				this.generateChest(worldIn, sbb, rand, pos, PVZLoot.GRAVE_YARD_CHEST, null);
			} else if(function.equals("bonus_chest2")) {
				this.generateChest(worldIn, sbb, rand, pos, PVZLoot.GRAVE_YARD_CHEST, null);
			} else if(function.equals("spawner")) {
				worldIn.setBlockState(pos, Blocks.SPAWNER.getDefaultState(), 2);
				TileEntity te = worldIn.getTileEntity(pos);
				if(te instanceof MobSpawnerTileEntity) {
					((MobSpawnerTileEntity)te).getSpawnerBaseLogic().setEntityType(getRandomEntityType(rand));
				}
			} else if(function.startsWith("tomb")) {
				TombStoneEntity tomb = EntityRegister.TOMB_STONE.get().create(worldIn.getWorld());
				EntityUtil.onMobEntitySpawn(worldIn, tomb, pos);
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
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
