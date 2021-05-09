package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagGenerator extends ItemTagsProvider{

	public ItemTagGenerator(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper helper) {
		super(generatorIn, provider, PVZMod.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
//		for(Item item : ForgeRegistries.ITEMS) {
//			if(item instanceof SummonCardItem) {
//				if(item instanceof PlantCardItem) {
//					this.func_200426_a(PVZItemTags.PLANT_CARDS).func_200048_a(item);
//					if(((SummonCardItem) item).isEnjoyCard) this.func_200426_a(PVZItemTags.PLANT_ENJOY_CARDS).func_200048_a(item);
//					else this.func_200426_a(PVZItemTags.PLANT_SUMMON_CARDS).func_200048_a(item);
//				}
//			}
//		}
//		this.func_200426_a(PVZItemTags.TEMPLATE_CARDS).func_200573_a(ItemRegister.GRAY_CARD.get(), ItemRegister.WHITE_CARD.get(), ItemRegister.GREEN_CARD.get(), ItemRegister.BLUE_CARD.get(), ItemRegister.PURPLE_CARD.get(), ItemRegister.GOLD_CARD.get(), ItemRegister.RED_CARD.get());
//		this.func_200426_a(PVZItemTags.PEAS).func_200048_a(ItemRegister.PEA.get());
//		this.func_200426_a(PVZItemTags.NUTS).func_200048_a(ItemRegister.NUT.get());
//		this.func_200426_a(PVZItemTags.CHILIPEPPERS).func_200048_a(ItemRegister.PEPPER.get());
//		this.func_200426_a(PVZItemTags.CABBAGES).func_200048_a(ItemRegister.CABBAGE.get());
//		this.func_200426_a(PVZItemTags.CABBAGE_SEEDS).func_200048_a(ItemRegister.CABBAGE_SEEDS.get());
//		this.func_200426_a(PVZItemTags.CORNS).func_200048_a(ItemRegister.CORN.get());
//		this.func_200426_a(PVZItemTags.CORN_SEEDS).func_200048_a(ItemRegister.CORN_SEEDS.get());
//		this.func_200426_a(PVZItemTags.AMETHYST_INGOTS).func_200048_a(ItemRegister.AMETHYST_INGOT.get());
//		this.func_200426_a(PVZItemTags.STEEL_INGOTS).func_200048_a(ItemRegister.STEEL_INGOT.get());
//		this.func_200426_a(PVZItemTags.AMETHYST_ORES).func_200048_a(BlockRegister.AMETHYST_ORE.get().asItem());
//		this.func_200426_a(PVZItemTags.ARROWS).func_200048_a(ItemRegister.TARGET_ARROW.get());
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies item tags";
	}

}
