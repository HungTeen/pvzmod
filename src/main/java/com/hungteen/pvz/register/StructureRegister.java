package com.hungteen.pvz.register;

import com.hungteen.pvz.structure.davevilla.DaveVillaComponents;
import com.hungteen.pvz.structure.zombie.BucketHouseComponents;
import com.hungteen.pvz.structure.zombie.DolphinHouseComponents;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class StructureRegister {
	
	public static IStructurePieceType DAVE_VILLA;
	public static IStructurePieceType BUCKET_HOUSE;
	public static IStructurePieceType DOLPHIN_HOUSE;
	
	public static void registerStructureType(){
		DAVE_VILLA = Registry.register(Registry.STRUCTURE_PIECE, "dave_villa", DaveVillaComponents.DaveVillaComponent::new);
		BUCKET_HOUSE = Registry.register(Registry.STRUCTURE_PIECE, "bucket_house",BucketHouseComponents.BucketHouseComponent::new);
	    DOLPHIN_HOUSE = Registry.register(Registry.STRUCTURE_PIECE, "dolphin_house",DolphinHouseComponents.DolphinHouseComponent::new);
	}
}
