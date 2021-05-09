package com.hungteen.pvz.data;

import java.util.Arrays;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockModelGenerator extends BlockModelProvider{

	public BlockModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, PVZMod.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		Arrays.asList(
				BlockRegister.ORIGIN_ORE, BlockRegister.APPEASE_ORE, BlockRegister.LIGHT_ORE, 
				BlockRegister.EXPLOSION_ORE, BlockRegister.DEFENCE_ORE, BlockRegister.ICE_ORE, 
				BlockRegister.ENFORCE_ORE, BlockRegister.TOXIC_ORE, BlockRegister.ASSIST_ORE, 
				BlockRegister.MAGIC_ORE, BlockRegister.FLAME_ORE, BlockRegister.SPEAR_ORE, 
				BlockRegister.ARMA_ORE, BlockRegister.ELECTRIC_ORE, BlockRegister.SHADOW_ORE,
				BlockRegister.AMETHYST_ORE
		).forEach((object) -> {
			fullCube(object);
		});
	}
	
	private void fullCube(RegistryObject<? extends Block> block) {
		cubeAll(block.getId().getPath(), StringUtil.prefix("block/" + block.getId().getPath()));
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block models";
	}

}
