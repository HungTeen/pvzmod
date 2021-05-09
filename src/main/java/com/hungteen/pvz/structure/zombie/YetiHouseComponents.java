package com.hungteen.pvz.structure.zombie;

public class YetiHouseComponents {

//	public static final ResourceLocation res1 = StringUtil.prefix("zombie_house/yeti_house");
//	
//	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
//	      list.add(new YetiHouseComponent(manager, res1, pos1, rotation));
//    }
//	
//	public static class YetiHouseComponent extends PVZTemplateComponent{
//
//		private static final IStructurePieceType type = StructureRegister.YETI_HOUSE;
//		
//		public YetiHouseComponent(TemplateManager manager, CompoundNBT nbt) {
//			super(type, manager, nbt);
//		}
//
//		public YetiHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
//			super(type, manager, res, pos, rotation);
//		}
//		
//		@Override
//		public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn,
//				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
//			int height = chunkGeneratorIn.getFirstOccupiedHeight(this.templatePosition.getX(), this.templatePosition.getZ(), Type.WORLD_SURFACE_WG);
//			this.templatePosition = new BlockPos(this.templatePosition.getX(), height - 1, this.templatePosition.getZ());
//			return super.func_225577_a_(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
//		}
//		
//        @Override
//		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
//				MutableBoundingBox sbb) {
//			if(function.equals("chest1")){
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.YETI_HOUSE_CHEST, null);
//			} else if(function.equals("chest2")) {
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.YETI_HOUSE_CHEST, null);
//			} else if(function.equals("chest3")) {
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.YETI_HOUSE_CHEST, null);
//			} else if(function.equals("spawn")) {
//				if(rand.nextInt(3) == 0) {
//					YetiZombieEntity yeti = EntityRegister.YETI_ZOMBIE.get().create(worldIn.getLevel());
//					EntityUtil.onMobEntitySpawn(worldIn, yeti, pos);
//					worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
//				}
//			}
//		}
//	}
}
