package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.structure.davevilla.DaveVillaStructure;
import com.hungteen.pvz.structure.zombie.BucketHouseStructure;
import com.hungteen.pvz.structure.zombie.DolphinHouseStructure;

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
	
}
