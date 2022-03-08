package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.ores.EssenceOreBlock;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.material.TemplateCardItem;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

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
		this.tag(PVZItemTags.PEA_GUN_BULLETS).add(ItemRegister.PEA.get()).add(ItemRegister.SNOW_PEA.get()).add(ItemRegister.FLAME_PEA.get());
		this.tag(PVZItemTags.REACH_ITEMS).add(ItemRegister.RESOURCE_COLLECTOR.get());
		this.tag(PVZItemTags.GRAY_MATERIALS).add(Items.STONE, Items.CLAY);
		this.tag(PVZItemTags.WHITE_MATERIALS).add(Items.IRON_INGOT).add(Items.QUARTZ);
		this.tag(PVZItemTags.GREEN_MATERIALS).add(Items.EMERALD).add(ItemRegister.ORIGIN_INGOT.get());
		this.tag(PVZItemTags.BLUE_MATERIALS).add(Items.DIAMOND).add(Items.LAPIS_BLOCK);
		this.tag(PVZItemTags.PURPLE_MATERIALS).add(ItemRegister.AMETHYST_INGOT.get()).add(Items.CRYING_OBSIDIAN);
		this.tag(PVZItemTags.GOLD_MATERIALS).add(Items.GOLD_INGOT);
		this.tag(PVZItemTags.RED_MATERIALS).add(Items.REDSTONE_BLOCK);
		this.tag(PVZItemTags.BLACK_MATERIALS).add(Items.NETHERITE_SCRAP);
		RankTypes.getRanks().forEach(rank -> {
			if(rank.getCardTag() != null){
				this.tag(rank.getCardTag()).add(ItemRegister.MEGA_CARD.get()).add(rank.getTemplateCard());
			}
		});
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
