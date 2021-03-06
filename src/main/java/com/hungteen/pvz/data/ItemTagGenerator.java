package com.hungteen.pvz.data;

import java.util.Comparator;
import java.util.function.Predicate;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.block.EssenceOreBlock;
import com.hungteen.pvz.item.material.TemplateCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.item.tool.card.SummonCardItem;
import com.hungteen.pvz.misc.tag.PVZItemTags;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTagGenerator extends ItemTagsProvider{

	public ItemTagGenerator(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper helper) {
		super(generatorIn, provider, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		//mc tag
		this.tag(PVZItemTags.ARROWS).add(ItemRegister.TARGET_ARROW.get());
		
		//forge tag
		this.tag(PVZItemTags.PEAS).add(ItemRegister.PEA.get());
		this.tag(PVZItemTags.NUTS).add(ItemRegister.NUT.get());
		this.tag(PVZItemTags.CHILIPEPPERS).add(ItemRegister.PEPPER.get());
		this.tag(PVZItemTags.CABBAGES).add(ItemRegister.CABBAGE.get());
		this.tag(PVZItemTags.CABBAGE_SEEDS).add(ItemRegister.CABBAGE_SEEDS.get());
		this.tag(PVZItemTags.CORNS).add(ItemRegister.CORN.get());
		this.tag(PVZItemTags.CORN_SEEDS).add(ItemRegister.CORN_SEEDS.get());
		this.tag(PVZItemTags.AMETHYST_INGOTS).add(ItemRegister.AMETHYST_INGOT.get());
		this.tag(PVZItemTags.STEEL_INGOTS).add(ItemRegister.STEEL_INGOT.get());
		this.tag(PVZItemTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get().asItem());
		
		//pvz tag
		for(Item item : ForgeRegistries.ITEMS) {
			if(item instanceof SummonCardItem) {
				if(item instanceof PlantCardItem) {
					this.tag(PVZItemTags.PLANT_CARDS).add(item);
					if(((SummonCardItem) item).isEnjoyCard){
						this.tag(PVZItemTags.PLANT_ENJOY_CARDS).add(item);
					} else{
						this.tag(PVZItemTags.PLANT_SUMMON_CARDS).add(item);
					}
				}
			} else if(item instanceof BlockItem) {
				Block b = ((BlockItem) item).getBlock();
				if(b instanceof EssenceOreBlock) {
					this.tag(PVZItemTags.ESSENCE_ORES).add(item);
				}
			}
		}
		this.tag(PVZItemTags.TEMPLATE_CARDS).add(this.getFilterItems(i -> i instanceof TemplateCardItem));
	}
	
	private Item[] getFilterItems(Predicate<Item> predicate) {
		return registry.stream()
				.filter(predicate)
				.sorted(Comparator.comparing(ForgeRegistries.ITEMS::getKey))
				.toArray(Item[]::new);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies item tags";
	}

}
