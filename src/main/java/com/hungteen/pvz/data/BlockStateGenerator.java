package com.hungteen.pvz.data;

import java.util.Arrays;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockStateGenerator extends BlockStateProvider {

	public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, PVZMod.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		Arrays.asList(
				BlockRegister.ORIGIN_ORE, BlockRegister.APPEASE_ORE, BlockRegister.LIGHT_ORE, 
				BlockRegister.EXPLOSION_ORE, BlockRegister.DEFENCE_ORE, BlockRegister.ICE_ORE, 
				BlockRegister.ENFORCE_ORE, BlockRegister.TOXIC_ORE, BlockRegister.ASSIST_ORE, 
				BlockRegister.MAGIC_ORE, BlockRegister.FLAME_ORE, BlockRegister.SPEAR_ORE, 
				BlockRegister.ARMA_ORE, BlockRegister.ELECTRIC_ORE, BlockRegister.SHADOW_ORE,
				BlockRegister.AMETHYST_ORE
		).forEach((object) -> {
			singleState(object);
		});
	}
	
	private void singleState(RegistryObject<? extends Block> block) {
		simpleBlock(block.get());
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies blockstates";
	}

}
