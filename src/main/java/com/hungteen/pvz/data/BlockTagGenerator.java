package com.hungteen.pvz.data;

import java.util.Comparator;
import java.util.function.Predicate;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.EssenceOreBlock;
import com.hungteen.pvz.common.misc.tag.PVZBlockTags;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockTagGenerator extends BlockTagsProvider{

	public BlockTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
		super(generatorIn, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		this.tag(PVZBlockTags.ESSENCE_ORES).add(getFilterBlocks(b -> b instanceof EssenceOreBlock));
		this.tag(PVZBlockTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get());
		this.tag(BlockTags.CLIMBABLE).add(BlockRegister.STEEL_LADDER.get());
	}
	
	private Block[] getFilterBlocks(Predicate<Block> predicate) {
		return registry.stream()
				.filter(predicate)
				.sorted(Comparator.comparing(ForgeRegistries.BLOCKS::getKey))
				.toArray(Block[]::new);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block tags";
	}

}
