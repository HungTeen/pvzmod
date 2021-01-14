package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.structure.shop.DaveVillaStructure;
import com.hungteen.pvz.structure.shop.SunTempleStructure;
import com.hungteen.pvz.structure.zombie.BucketHouseStructure;
import com.hungteen.pvz.structure.zombie.DolphinHouseStructure;
import com.hungteen.pvz.structure.zombie.GraveHouseStructure;
import com.hungteen.pvz.structure.zombie.YetiHouseStructure;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegister {

	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, PVZMod.MOD_ID);
	
	//structure
	public static final RegistryObject<Structure<NoFeatureConfig>> DAVE_VILLA = FEATURES.register("dave_villa", () -> {return new DaveVillaStructure(NoFeatureConfig::deserialize);});
	public static final RegistryObject<Structure<NoFeatureConfig>> BUCKET_HOUSE = FEATURES.register("bucket_house", () -> {return new BucketHouseStructure(NoFeatureConfig::deserialize);});
	public static final RegistryObject<Structure<NoFeatureConfig>> DOLPHIN_HOUSE = FEATURES.register("dolphin_house", () -> {return new DolphinHouseStructure(NoFeatureConfig::deserialize);});
	public static final RegistryObject<Structure<NoFeatureConfig>> GRAVE_HOUSE = FEATURES.register("grave_house", () -> {return new GraveHouseStructure(NoFeatureConfig::deserialize);});
	public static final RegistryObject<Structure<NoFeatureConfig>> SUN_TEMPLE = FEATURES.register("sun_temple", () -> {return new SunTempleStructure(NoFeatureConfig::deserialize);});
	public static final RegistryObject<Structure<NoFeatureConfig>> YETI_HOUSE = FEATURES.register("yeti_house", () -> {return new YetiHouseStructure(NoFeatureConfig::deserialize);});
	
}
