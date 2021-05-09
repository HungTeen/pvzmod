package com.hungteen.pvz.structure.zombie;

public class GraveHouseComponents {

//	public static final ResourceLocation res = StringUtil.prefix("zombie_house/graveyard");
//	
//	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
//	      list.add(new GraveHouseComponent(manager, res, pos1, rotation));
//    }
//	
//	public static class GraveHouseComponent extends PVZTemplateComponent{
//
//		private static final IStructurePieceType type = StructureRegister.GRAVE_HOUSE;
//		
//		public GraveHouseComponent(TemplateManager manager, CompoundNBT nbt) {
//			super(type, manager, nbt);
//		}
//
//		public GraveHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
//			super(type, manager, res, pos, rotation);
//		}
//		
//		@Override
//		public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn,
//				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
//			int dx = 15, dz = 15;
//			switch(rotation) {
//			case CLOCKWISE_90:{dx = - 15; break;}
//			case CLOCKWISE_180:{dx = dz = - 15; break;}
//			case COUNTERCLOCKWISE_90:{dz = - 15; break;}
//			default:{}
//			}
//			int x = this.templatePosition.getX(), z = this.templatePosition.getZ(); 
//			int h1 = chunkGeneratorIn.getFirstOccupiedHeight(x , z, Type.WORLD_SURFACE_WG);
//			int h2 = chunkGeneratorIn.getFirstOccupiedHeight(x + dx, z, Type.WORLD_SURFACE_WG);
//			int h3 = chunkGeneratorIn.getFirstOccupiedHeight(x , z + dz, Type.WORLD_SURFACE_WG);
//			int h4 = chunkGeneratorIn.getFirstOccupiedHeight(x + dx, z + dz, Type.WORLD_SURFACE_WG);
//			int height = Math.min(Math.min(h1, h2), Math.min(h3, h4));
//			this.templatePosition=new BlockPos(x, height, z);
//			return super.func_225577_a_(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
//		}
//		
//		@Override
//		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
//				MutableBoundingBox sbb) {
//			if(function.equals("bonus_chest1")) {
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.GRAVE_YARD_CHEST, null);
//			} else if(function.equals("bonus_chest2")) {
//				this.createChest(worldIn, sbb, rand, pos, PVZLoot.GRAVE_YARD_CHEST, null);
//			} else if(function.equals("spawner")) {
//				worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
//				TileEntity te = worldIn.getBlockEntity(pos);
//				if(te instanceof MobSpawnerTileEntity) {
//					((MobSpawnerTileEntity)te).getSpawner().setEntityId(getRandomEntityType(rand));
//				}
//			} else if(function.startsWith("tomb")) {
//				if(rand.nextInt(3) == 0) {
//					TombStoneEntity tomb = EntityRegister.TOMB_STONE.get().create(worldIn.getLevel());
//				    EntityUtil.onMobEntitySpawn(worldIn, tomb, pos);
//				    worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
//				}
//			}
//		}
//		
//		protected EntityType<?> getRandomEntityType(Random rand){
//			int num = rand.nextInt(2);
//			if(num == 0) return EntityRegister.GIGA_FOOTBALL_ZOMBIE.get();
//			else if(num == 1) return EntityRegister.FOOTBALL_ZOMBIE.get();
//			return null;
//		}
//		
//	}
//	
}
