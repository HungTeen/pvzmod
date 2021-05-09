package com.hungteen.pvz.structure.shop;

public class SunTempleComponents {

//	public static final ResourceLocation res = StringUtil.prefix("shop/sun_temple");
//	
//	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
//	      list.add(new SunTempleComponent(manager, res, pos1, rotation));
//    }
//	
//	public static class SunTempleComponent extends PVZTemplateComponent {
//
//		private static final IStructurePieceType type = StructureRegister.SUN_TEMPLE;
//		
//		public SunTempleComponent(TemplateManager manager, CompoundNBT nbt) {
//			super(type, manager, nbt);
//		}
//
//		public SunTempleComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
//			super(type, manager, res, pos, rotation);
//		}
//		
//		@Override
//		public boolean postProcess(ISeedReader worldIn, StructureManager manager, ChunkGenerator chunkGeneratorIn, Random randomIn,
//				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn, BlockPos blockPos) {
////			BlockPos mid = BlockPos.ZERO;
////			int dx = 6, dz = 5;
////			switch(rotation) {
////			case CLOCKWISE_90:{mid = this.templatePosition.add(-dz, 0, dx); break;}
////			case CLOCKWISE_180:{mid = this.templatePosition.add(-dx, 0, -dz); break;}
////			case COUNTERCLOCKWISE_90:{mid = this.templatePosition.add(dz, 0, -dx); break;}
////			default:{mid = this.templatePosition.add(dx, 0, dz); break;}
////			}
////			int height = chunkGeneratorIn.getFirstOccupiedHeight(mid.getX(), mid.getZ(), Type.WORLD_SURFACE_WG);
////			this.templatePosition = new BlockPos(this.templatePosition.getX(), height + 1, this.templatePosition.getZ());
//			return super.postProcess(worldIn, manager, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn, blockPos);
//		}
//		
//        @Override
//		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
//				MutableBoundingBox sbb) {
//			if(function.equals("spawn")){
//				SunDaveEntity dave = EntityRegister.SUN_DAVE.get().create(worldIn.getLevel());
//				EntityUtil.onMobEntitySpawn(worldIn, dave, pos.above());
//			} else if(function.equals("chest")) {
//				this.createChest(worldIn, sbb, rand, pos.above(), PVZLoot.BUCKET_HOUSE_CHEST, null);
//				worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
//				TileEntity te = worldIn.getBlockEntity(pos);
//				if(te instanceof MobSpawnerTileEntity) {
//					((MobSpawnerTileEntity)te).getSpawner().setEntityId(EntityRegister.RA_ZOMBIE.get());
//				}
//			}
//		}
//		
//	}
}
