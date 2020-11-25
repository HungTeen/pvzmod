package com.hungteen.pvz.data;

import com.hungteen.pvz.misc.tag.PVZBlockTags;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;

public class BlockTagGenerator extends BlockTagsProvider{

	public BlockTagGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerTags() {
		this.getBuilder(PVZBlockTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get());
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block tags";
	}

}
