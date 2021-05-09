package com.hungteen.pvz.structure.zombie;

public class BucketHouseStructure {
//extends ScatteredStructure<NoFeatureConfig>{
//
//	public BucketHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
//		super(configFactoryIn);
//	}
//
//	@Override
//	public IStartFactory getStartFactory() {
//		return BucketHouseStructure.Start::new;
//	}
//
//	@Override
//	public String getFeatureName() {
//		return "Bucket_House";
//	}
//
//	@Override
//	public int func_202367_b() {
//		return 3;
//	}
//	
//	@Override
//	protected int func_204030_a(ChunkGenerator<?> chunkGenerator) {
//		return PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.BucketHouseDistance.get();
//	}
//	
//	@Override
//	protected int func_211745_b(ChunkGenerator<?> chunkGenerator) {
//		return PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.BucketHouseDistance.get()/4;
//	}
//	
//	@Override
//	protected int func_202382_c() {
//		return 165745799;
//	}
//	
//	public static class Start extends StructureStart{
//
//		public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
//            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
//        }
//		
//		@Override
//		public void func_214625_a(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
//				Biome biomeIn) {
//			Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
//	        BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
//	        BucketHouseComponents.generate(templateManagerIn, blockpos, rotation, this.pieces, this.random);
//			this.calculateBoundingBox();
//		}
//	}
}
