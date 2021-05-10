package com.hungteen.pvz.data.loot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.enums.Essences;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraftforge.registries.ForgeRegistries;

public class PVZBlockLootTables extends BlockLootTables {

	private final Set<Block> knownBlocks = new HashSet<>();
	private ILootCondition.IBuilder tmpBuilder;
	private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
//	private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

	@Override
	protected void addTables() {
		//drop item like coal ore
		Arrays.asList(BlockRegister.ORIGIN_ORE.get(), BlockRegister.APPEASE_ORE.get(), BlockRegister.LIGHT_ORE.get(),
				BlockRegister.EXPLOSION_ORE.get(), BlockRegister.DEFENCE_ORE.get(), BlockRegister.ICE_ORE.get(),
				BlockRegister.ENFORCE_ORE.get(), BlockRegister.TOXIC_ORE.get(), BlockRegister.ASSIST_ORE.get(),
				BlockRegister.MAGIC_ORE.get(), BlockRegister.FLAME_ORE.get(), BlockRegister.SPEAR_ORE.get(),
				BlockRegister.ARMA_ORE.get(), BlockRegister.ELECTRIC_ORE.get(), BlockRegister.SHADOW_ORE.get())
				.forEach((object) -> {
					Essences.getEssenceItem(object.essence).ifPresent((item) -> {
						this.add(object, (block) -> {
							return createOreDrop(block, item);
						});
					});
				});
		//crop
		this.tmpBuilder = getAgeBuilder(BlockRegister.CABBAGE.get(), 3);
	    this.add(BlockRegister.CABBAGE.get(), createCropDrops(BlockRegister.CABBAGE.get(), ItemRegister.CABBAGE_SEEDS.get(), this.tmpBuilder));
	    this.tmpBuilder = getAgeBuilder(BlockRegister.CORN.get(), 7);
	    this.add(BlockRegister.CORN.get(), createCropDrops(BlockRegister.CORN.get(), ItemRegister.CORN_SEEDS.get(), this.tmpBuilder));
		
	    //leaves
	    this.add(BlockRegister.NUT_LEAVES.get(), (p_218506_0_) -> {
	         return createOakLeavesDrops(p_218506_0_, BlockRegister.NUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
	    });
	    
	    //misc
		this.dropOther(BlockRegister.GOLD_TILE1.get(), Blocks.GOLD_BLOCK);
		this.dropOther(BlockRegister.GOLD_TILE2.get(), Blocks.GOLD_BLOCK);
		this.dropOther(BlockRegister.GOLD_TILE3.get(), Blocks.GOLD_BLOCK);
		//other blocks are drop itself
		ForgeRegistries.BLOCKS.forEach(block -> {
			if(block.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && ! this.knownBlocks.contains(block)) {
				this.dropSelf(block);
			}
		});
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return this.knownBlocks;
	}

	@Override
	protected void add(Block p_218507_1_, Builder p_218507_2_) {
		super.add(p_218507_1_, p_218507_2_);
		this.knownBlocks.add(p_218507_1_);
	}
	
	private static Builder createCropDrops(Block block, Item seed, ILootCondition.IBuilder bb) {
		return createCropDrops(block, block.asItem(), seed, bb);
	}
	
	private ILootCondition.IBuilder getAgeBuilder(Block block, int age) {
		return BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, age));
	}

}
