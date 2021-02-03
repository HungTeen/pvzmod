package com.hungteen.pvz.data;

import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.item.tool.card.SummonCardItem;
import com.hungteen.pvz.misc.tag.PVZItemTags;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTagGenerator extends ItemTagsProvider{

	public ItemTagGenerator(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerTags() {
		for(Item item : ForgeRegistries.ITEMS) {
			if(item instanceof SummonCardItem) {
				if(item instanceof PlantCardItem) {
					this.getBuilder(PVZItemTags.PLANT_CARDS).add(item);
					if(((SummonCardItem) item).isEnjoyCard) this.getBuilder(PVZItemTags.PLANT_ENJOY_CARDS).add(item);
					else this.getBuilder(PVZItemTags.PLANT_SUMMON_CARDS).add(item);
				}
			}
		}
		this.getBuilder(PVZItemTags.TEMPLATE_CARDS).add(ItemRegister.GRAY_CARD.get(), ItemRegister.WHITE_CARD.get(), ItemRegister.GREEN_CARD.get(), ItemRegister.BLUE_CARD.get(), ItemRegister.PURPLE_CARD.get(), ItemRegister.GOLD_CARD.get(), ItemRegister.RED_CARD.get());
		this.getBuilder(PVZItemTags.PEAS).add(ItemRegister.PEA.get());
		this.getBuilder(PVZItemTags.NUTS).add(ItemRegister.NUT.get());
		this.getBuilder(PVZItemTags.CHILIPEPPERS).add(ItemRegister.PEPPER.get());
		this.getBuilder(PVZItemTags.CABBAGES).add(ItemRegister.CABBAGE.get());
		this.getBuilder(PVZItemTags.CABBAGE_SEEDS).add(ItemRegister.CABBAGE_SEEDS.get());
		this.getBuilder(PVZItemTags.CORNS).add(ItemRegister.CORN.get());
		this.getBuilder(PVZItemTags.CORN_SEEDS).add(ItemRegister.CORN_SEEDS.get());
		this.getBuilder(PVZItemTags.AMETHYST_INGOTS).add(ItemRegister.AMETHYST_INGOT.get());
		this.getBuilder(PVZItemTags.STEEL_INGOTS).add(ItemRegister.STEEL_INGOT.get());
		this.getBuilder(PVZItemTags.AMETHYST_ORES).add(BlockRegister.AMETHYST_ORE.get().asItem());
		this.getBuilder(PVZItemTags.ARROWS).add(ItemRegister.TARGET_ARROW.get());
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies item tags";
	}

}
