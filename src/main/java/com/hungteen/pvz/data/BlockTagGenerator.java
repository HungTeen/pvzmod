package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGenerator extends BlockTagsProvider{

	public BlockTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
//		this.func_200426_a(PVZBlockTags.AMETHYST_ORES).func_200048_a(BlockRegister.AMETHYST_ORE.get());
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block tags";
	}

}
