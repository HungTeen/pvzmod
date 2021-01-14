package com.hungteen.pvz.structure.zombie;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.zombie.poolnight.YetiZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.StructureRegister;
import com.hungteen.pvz.structure.PVZTemplateComponent;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.nbt.CompoundNBT;
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

public class YetiHouseComponents {

	public static final ResourceLocation res1 = StringUtil.prefix("zombie_house/yeti_house");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      list.add(new YetiHouseComponent(manager, res1, pos1, rotation));
    }
	
	public static class YetiHouseComponent extends PVZTemplateComponent{

		private static final IStructurePieceType type = StructureRegister.YETI_HOUSE;
		
		public YetiHouseComponent(TemplateManager manager, CompoundNBT nbt) {
			super(type, manager, nbt);
		}

		public YetiHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(type, manager, res, pos, rotation);
		}
		
		@Override
		public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn,
				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
			int height = chunkGeneratorIn.func_222531_c(this.templatePosition.getX(), this.templatePosition.getZ(), Type.WORLD_SURFACE_WG);
			this.templatePosition = new BlockPos(this.templatePosition.getX(), height - 1, this.templatePosition.getZ());
			return super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
		}
		
        @Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if(function.equals("chest1")){
				this.generateChest(worldIn, sbb, rand, pos, PVZLoot.BUCKET_HOUSE_CHEST, null);
			} else if(function.equals("chest2")) {
				this.generateChest(worldIn, sbb, rand, pos, PVZLoot.BUCKET_HOUSE_CHEST, null);
			} else if(function.equals("chest3")) {
				this.generateChest(worldIn, sbb, rand, pos, PVZLoot.BUCKET_HOUSE_CHEST, null);
			} else if(function.equals("spawn")) {
				if(rand.nextInt(3) == 0) {
					YetiZombieEntity yeti = EntityRegister.YETI_ZOMBIE.get().create(worldIn.getWorld());
					EntityUtil.onMobEntitySpawn(worldIn.getWorld(), yeti, pos);
				}
			}
		}
	}
}
