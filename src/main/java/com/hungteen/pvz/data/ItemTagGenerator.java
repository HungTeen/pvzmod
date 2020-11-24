package com.hungteen.pvz.data;

import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.item.tool.card.SummonCardItem;
import com.hungteen.pvz.misc.damage.PVZItemTags;
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
					if(((SummonCardItem) item).isEnjoyCard()) this.getBuilder(PVZItemTags.PLANT_ENJOY_CARDS).add(item);
					else this.getBuilder(PVZItemTags.PLANT_SUMMON_CARDS).add(item);
				}
			}
		}
		this.getBuilder(PVZItemTags.TEMPLATE_CARDS).add(ItemRegister.GRAY_CARD.get(), ItemRegister.WHITE_CARD.get(), ItemRegister.GREEN_CARD.get(), ItemRegister.BLUE_CARD.get(), ItemRegister.PURPLE_CARD.get(), ItemRegister.GOLD_CARD.get(), ItemRegister.RED_CARD.get());
		
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies item tags";
	}

}
