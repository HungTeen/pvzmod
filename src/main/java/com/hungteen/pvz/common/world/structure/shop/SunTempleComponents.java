package com.hungteen.pvz.common.world.structure.shop;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.common.entity.npc.SunDaveEntity;
import com.hungteen.pvz.common.world.structure.PVZTemplateComponent;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.Blocks;
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
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class SunTempleComponents {

	public static final ResourceLocation res = StringUtil.prefix("shop/sun_temple");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      list.add(new SunTempleComponent(manager, res, pos1, rotation));
    }
	
	public static class SunTempleComponent extends PVZTemplateComponent {

		private static final IStructurePieceType type = StructureRegister.SUN_TEMPLE_PIECE;
		
		public SunTempleComponent(TemplateManager manager, CompoundNBT nbt) {
			super(type, manager, nbt);
		}

		public SunTempleComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(type, manager, res, pos, rotation);
		}
		
		@Override
		public boolean postProcess(ISeedReader worldIn, StructureManager manager, ChunkGenerator chunkGeneratorIn, Random randomIn,
				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn, BlockPos blockPos) {
			return super.postProcess(worldIn, manager, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn, blockPos);
		}
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if(function.equals("spawn")){
				SunDaveEntity dave = EntityRegister.SUN_DAVE.get().create(worldIn.getLevel());
				EntityUtil.onEntitySpawn(worldIn, dave, pos.above());
			} else if(function.equals("chest")) {
				this.createChest(worldIn, sbb, rand, pos.above(), PVZLoot.SUN_TEMPLE_CHEST, null);
				worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
				TileEntity te = worldIn.getBlockEntity(pos);
				if(te instanceof MobSpawnerTileEntity) {
					((MobSpawnerTileEntity)te).getSpawner().setEntityId(EntityRegister.RA_ZOMBIE.get());
				}
			}
		}
		
	}
}
