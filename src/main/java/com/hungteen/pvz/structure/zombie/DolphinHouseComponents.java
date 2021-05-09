package com.hungteen.pvz.structure.zombie;

public class DolphinHouseComponents {

//	public static final ResourceLocation res = StringUtil.prefix("zombie_house/dolphin_house");
//	
//	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
//	      list.add(new DolphinHouseComponent(manager, res, pos1, rotation));
//    }
//	
//	public static class DolphinHouseComponent extends PVZTemplateComponent {
//
//		private static final IStructurePieceType type = StructureRegister.DOLPHIN_HOUSE;
//		
//		public DolphinHouseComponent(TemplateManager manager, CompoundNBT nbt) {
//			super(type, manager, nbt);
//		}
//
//		public DolphinHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
//			super(type, manager, res, pos, rotation);
//		}
//		
//		@Override
//		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
//				MutableBoundingBox sbb) {
//			if(function.equals("bonus_chest1")){
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.DOLPHIN_HOUSE_CHEST, null);
//			} else if(function.equals("bonus_chest2")) {
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.DOLPHIN_HOUSE_CHEST, null);
//			} else if(function.equals("spawner")) {
//				worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
//				TileEntity te = worldIn.getBlockEntity(pos);
//				if(te instanceof MobSpawnerTileEntity) {
//					((MobSpawnerTileEntity)te).getSpawner().setEntityId(getRandomEntityType(rand));
//				}
//			}
//		}
//		
//		protected EntityType<?> getRandomEntityType(Random rand){
//			int num = rand.nextInt(3);
//			if(num == 0) return EntityRegister.SNORKEL_ZOMBIE.get();
//			else if(num == 1) return EntityRegister.LAVA_ZOMBIE.get();
//			else if(num == 2) return EntityRegister.DOLPHIN_RIDER_ZOMBIE.get();
//			return null;
//		}
//		
//	}
		
}
