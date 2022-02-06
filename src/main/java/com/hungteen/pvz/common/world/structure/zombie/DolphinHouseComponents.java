package com.hungteen.pvz.common.world.structure.zombie;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.common.world.structure.PVZTemplateComponent;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class DolphinHouseComponents {

	public static final ResourceLocation res = StringUtil.prefix("zombie_house/dolphin_house");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      list.add(new DolphinHouseComponent(manager, res, pos1, rotation));
    }
	
	public static class DolphinHouseComponent extends PVZTemplateComponent {

		private static final IStructurePieceType type = StructureRegister.DOLPHIN_HOUSE_PIECE;
		
		public DolphinHouseComponent(TemplateManager manager, CompoundNBT nbt) {
			super(type, manager, nbt);
		}

		public DolphinHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(type, manager, res, pos, rotation);
		}
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if(function.equals("bonus_chest1")){
				this.createChest(worldIn, sbb, rand, pos, PVZLoot.DOLPHIN_HOUSE_CHEST, null);
			} else if(function.equals("bonus_chest2")) {
				this.createChest(worldIn, sbb, rand, pos, PVZLoot.DOLPHIN_HOUSE_CHEST, null);
			} else if(function.equals("spawner")) {
				worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
				TileEntity te = worldIn.getBlockEntity(pos);
				if(te instanceof MobSpawnerTileEntity) {
					((MobSpawnerTileEntity)te).getSpawner().setEntityId(getRandomEntityType(rand));
				}
			}
		}
		
		protected EntityType<?> getRandomEntityType(Random rand){
			int num = rand.nextInt(3);
			if(num == 0) return EntityRegister.SNORKEL_ZOMBIE.get();
			else if(num == 1) return EntityRegister.LAVA_ZOMBIE.get();
			else if(num == 2) return EntityRegister.DOLPHIN_RIDER_ZOMBIE.get();
			return null;
		}
		
	}
		
}
