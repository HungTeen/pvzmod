package com.hungteen.pvz.structure.zombie;

public class GraveHouseStructure {
//extends ScatteredStructure<NoFeatureConfig>{

//	public GraveHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
//		super(configFactoryIn);
//	}
//
//	@Override
//	public IStartFactory getStartFactory() {
//		return GraveHouseStructure.Start::new;
//	}
//
//	@Override
//	public String getFeatureName() {
//		return "Grave_House";
//	}
//
//	@Override
//	public int func_202367_b() {
//		return 3;
//	}
//	
//	@Override
//	protected int func_204030_a(ChunkGenerator<?> chunkGenerator) {
//		return PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.GraveHouseDistance.get();
//	}
//	
//	@Override
//	protected int func_211745_b(ChunkGenerator<?> chunkGenerator) {
//		return PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.GraveHouseDistance.get() / 4;
//	}
//	
//	@Override
//	protected int func_202382_c() {
//		return 998244353;
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
//	        BlockPos blockpos = new BlockPos(chunkX * 16, 63, chunkZ * 16);
//	        GraveHouseComponents.generate(templateManagerIn, blockpos, rotation, this.pieces, this.random);
//			this.calculateBoundingBox();
//		}
//	}
}
