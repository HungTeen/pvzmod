package com.hungteen.pvz.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemModelGenerator extends ItemModelProvider{

	private Set<Item> addedItems = new HashSet<>();
	
	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, PVZMod.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		//special model generated by blockbench.
		addedItems.addAll(Arrays.asList(ItemRegister.PEA_GUN.get(), ItemRegister.ZOMBIE_FLAG.get(), ItemRegister.BOBSLE_CAR.get(),
				ItemRegister.SCREEN_DOOR.get(), ItemRegister.BOWLING_GLOVE.get(), ItemRegister.JACK_BOX.get(), ItemRegister.BALLOON.get(),
				ItemRegister.WARNING_SIGN.get(), ItemRegister.ZOMBIE_DOLL.get(), ItemRegister.POLE.get(), ItemRegister.CONE_HEAD.get(),
				ItemRegister.BUCKET_HEAD.get(), ItemRegister.SUN_COLLECTOR.get()
				));
		//single add
		Arrays.asList(ItemRegister.PEA.get(), ItemRegister.SPORE.get(), ItemRegister.CABBAGE_SEEDS.get(), ItemRegister.CORN_SEEDS.get()
				
				).forEach(i -> {
			genNormalModel(i);
			this.addedItems.add(i);
		});
		Arrays.asList(BlockRegister.NUT_SAPLING.get(), BlockRegister.STEEL_LADDER.get()).forEach(i -> {
			genItemModelWithBlock(i.asItem());
			this.addedItems.add(i.asItem());
		});
		//for mostly common items.
		for (Item i : ForgeRegistries.ITEMS) {
			if(! i.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) || addedItems.contains(i)) continue ;
			if (i instanceof PVZSpawnEggItem) {//for spawn eggs
				addedItems.add(i);
				getBuilder(i.getRegistryName().getPath()).parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
			} else if(i instanceof PlantCardItem) {//for plant cards
				Plants plant = ((PlantCardItem) i).plantType;
				ResourceLocation plantResource = StringUtil.prefix("screenshot/plant/"+plant.toString().toLowerCase());
				addedItems.add(i);
				if(((PlantCardItem) i).isEnjoyCard) {
					ResourceLocation r = StringUtil.prefix("item/red_card");
					genNormal(i.getRegistryName().getPath(), r, plantResource);
				} else {
					ResourceLocation r = StringUtil.prefix("item/"+PlantUtil.getPlantRankByName(plant).toString().toLowerCase()+"_card");
					genNormal(i.getRegistryName().getPath(), r, plantResource);
				}
			} else if(i instanceof BlockItem) {
				addedItems.add(i);
				genBlockModel(((BlockItem) i).getBlock());
			}
		}
		//for hand held item
		Arrays.asList(ItemRegister.STEEL_AXE.get(), ItemRegister.STEEL_HOE.get(), ItemRegister.STEEL_PICKAXE.get(), ItemRegister.STEEL_SHOVEL.get(),
				ItemRegister.STEEL_SWORD.get()
				).forEach(i -> {
			addedItems.add(i);
			genHeld(i.getRegistryName().getPath(), StringUtil.prefix("item/" + i.getRegistryName().getPath()));
		});
		//last step for all normal item models.
		for(Item i : ForgeRegistries.ITEMS) {
			if(i.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && ! addedItems.contains(i)) {
				genNormal(i.getRegistryName().getPath(), StringUtil.prefix("item/" + i.getRegistryName().getPath()));
			
			}
		}
	}
	
	private void genNormalModel(Item i) {
		genNormal(i.getRegistryName().getPath(), StringUtil.prefix("item/" + i.getRegistryName().getPath()));
	}
	
	private ItemModelBuilder genNormal(String name, ResourceLocation... layers) {
		return gen(name, "item/generated", layers);
	}
	
	private ItemModelBuilder genHeld(String name, ResourceLocation... layers) {
		return gen(name, "item/handheld", layers);
	}
	
	private ItemModelBuilder gen(String name, String parent, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}
	
	private void genBlockModel(Block b) {
		withExistingParent(b.getRegistryName().getPath(), StringUtil.prefix("block/" + b.getRegistryName().getPath()));
	}
	
	private void genItemModelWithBlock(Item i) {
		genNormal(i.getRegistryName().getPath(), StringUtil.prefix("block/" + i.getRegistryName().getPath()));
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies item and itemblock models";
	}

}
