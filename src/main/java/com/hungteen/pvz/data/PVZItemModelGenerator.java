package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.item.armor.FootballArmorItem;
import com.hungteen.pvz.item.armor.GigaArmorItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class PVZItemModelGenerator extends ItemModelProvider{

	public PVZItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, PVZMod.MOD_ID, existingFileHelper);
	}

	@Override
	public String getName() {
		return "Plants vs Zombies item and itemblock models";
	}

	@Override
	protected void registerModels() {
		for (Item i : ForgeRegistries.ITEMS) {
			if (i instanceof PVZSpawnEggItem) {//for spawn eggs
				getBuilder(i.getRegistryName().getPath()).parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
			} else if(i instanceof PlantCardItem) {//for plant cards
				Plants plant = ((PlantCardItem) i).getPlant();
				ResourceLocation plantResource = StringUtil.prefix("screenshot/plant/"+plant.toString().toLowerCase());
				if(((PlantCardItem) i).isEnjoyCard()) {
					ResourceLocation r = StringUtil.prefix("item/red_card");
					generated(i.getRegistryName().getPath(), r, plantResource);
				}else {
					ResourceLocation r = StringUtil.prefix("item/"+PlantUtil.getPlantRankByName(plant).toString().toLowerCase()+"_card");
					generated(i.getRegistryName().getPath(), r, plantResource);
				}
			} else if(i instanceof FootballArmorItem || i instanceof GigaArmorItem) {
				generated(i.getRegistryName().getPath(), StringUtil.prefix("item/" + i.getRegistryName().getPath()));
			}
		}
	}
	
	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/generated");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

}
