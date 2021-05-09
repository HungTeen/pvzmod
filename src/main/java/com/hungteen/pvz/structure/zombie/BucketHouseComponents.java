package com.hungteen.pvz.structure.zombie;

public class BucketHouseComponents {

//	public static final ResourceLocation res1 = StringUtil.prefix("zombie_house/bucket_house");
//	
//	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
//	      list.add(new BucketHouseComponent(manager, res1, pos1, rotation));
//    }
//	
//	public static class BucketHouseComponent extends PVZTemplateComponent{
//
//		private static final IStructurePieceType type = StructureRegister.BUCKET_HOUSE;
//		
//		public BucketHouseComponent(TemplateManager manager, CompoundNBT nbt) {
//			super(type, manager, nbt);
//		}
//
//		public BucketHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
//			super(type, manager, res, pos, rotation);
//		}
//		
//		@Override
//		public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn,
//				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
//			BlockPos mid = BlockPos.ZERO;
//			int dx = 6, dz = 5;
//			switch(rotation) {
//			case CLOCKWISE_90:{mid = this.templatePosition.offset(-dz, 0, dx); break;}
//			case CLOCKWISE_180:{mid = this.templatePosition.offset(-dx, 0, -dz); break;}
//			case COUNTERCLOCKWISE_90:{mid = this.templatePosition.offset(dz, 0, -dx); break;}
//			default:{mid = this.templatePosition.offset(dx, 0, dz); break;}
//			}
//			int height = chunkGeneratorIn.getFirstOccupiedHeight(mid.getX(), mid.getZ(), Type.WORLD_SURFACE_WG);
//			this.templatePosition = new BlockPos(this.templatePosition.getX(), height + 1, this.templatePosition.getZ());
//			return super.func_225577_a_(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
//		}
//		
//        @Override
//		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
//				MutableBoundingBox sbb) {
//			if(function.equals("bonus_chest1")){
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.BUCKET_HOUSE_CHEST, null);
//			} else if(function.equals("bonus_chest2")) {
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.BUCKET_HOUSE_CHEST, null);
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
//			if(num == 0) return EntityRegister.CONEHEAD_ZOMBIE.get();
//			if(num == 1) return EntityRegister.POLE_ZOMBIE.get();
//			if(num == 2) return EntityRegister.BUCKETHEAD_ZOMBIE.get();
//			return null;
//		}
//		
//	}
}
