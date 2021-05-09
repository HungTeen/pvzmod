package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegister {

	public static final DeferredRegister<Feature<?>> FEATURES =  DeferredRegister.create(ForgeRegistries.FEATURES, PVZMod.MOD_ID);
	
	//structure
//	public static final RegistryObject<Structure<NoFeatureConfig>> DAVE_VILLA = FEATURES.register("dave_villa", () -> {return new DaveVillaStructure(NoFeatureConfig::func_214639_a);});
//	public static final RegistryObject<Structure<NoFeatureConfig>> BUCKET_HOUSE = FEATURES.register("bucket_house", () -> {return new BucketHouseStructure(NoFeatureConfig::func_214639_a);});
//	public static final RegistryObject<Structure<NoFeatureConfig>> DOLPHIN_HOUSE = FEATURES.register("dolphin_house", () -> {return new DolphinHouseStructure(NoFeatureConfig::func_214639_a);});
//	public static final RegistryObject<Structure<NoFeatureConfig>> GRAVE_HOUSE = FEATURES.register("grave_house", () -> {return new GraveHouseStructure(NoFeatureConfig::func_214639_a);});
//	public static final RegistryObject<Structure<NoFeatureConfig>> SUN_TEMPLE = FEATURES.register("sun_temple", () -> {return new SunTempleStructure(NoFeatureConfig::func_214639_a);});
//	public static final RegistryObject<Structure<NoFeatureConfig>> YETI_HOUSE = FEATURES.register("yeti_house", () -> {return new YetiHouseStructure(NoFeatureConfig::func_214639_a);});
	
}
