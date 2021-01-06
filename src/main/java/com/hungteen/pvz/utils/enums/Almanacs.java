package com.hungteen.pvz.utils.enums;

import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TranslationTextComponent;

public enum Almanacs {
    //player
	PLAYER,
	//grass day
	PEA_SHOOTER(Plants.PEA_SHOOTER), SUN_FLOWER(Plants.SUN_FLOWER), CHERRY_BOMB(Plants.CHERRY_BOMB), WALL_NUT(Plants.WALL_NUT),
	POTATO_MINE(Plants.POTATO_MINE), SNOW_PEA(Plants.SNOW_PEA), CHOMPER(Plants.CHOMPER), REPEATER(Plants.REPEATER),
	//grass night
	PUFF_SHROOM(Plants.PUFF_SHROOM), SUN_SHROOM(Plants.SUN_SHROOM), FUME_SHROOM(Plants.FUME_SHROOM), 
	GRAVE_BUSTER(Plants.GRAVE_BUSTER), HYPNO_SHROOM(Plants.HYPNO_SHROOM), SCAREDY_SHROOM(Plants.SCAREDY_SHROOM), 
	ICE_SHROOM(Plants.ICE_SHROOM), DOOM_SHROOM(Plants.DOOM_SHROOM),
	//pool day
	LILY_PAD(Plants.LILY_PAD), SQUASH(Plants.SQUASH), THREE_PEATER(Plants.THREE_PEATER), TANGLE_KELP(Plants.TANGLE_KELP),
	JALAPENO(Plants.JALAPENO), SPIKE_WEED(Plants.SPIKE_WEED), TORCH_WOOD(Plants.TORCH_WOOD), TALL_NUT(Plants.TALL_NUT),
	//pool night
	SEA_SHROOM(Plants.SEA_SHROOM), PLANTERN(Plants.PLANTERN), CACTUS(Plants.CACTUS),
	SPLIT_PEA(Plants.SPLIT_PEA), STAR_FRUIT(Plants.STAR_FRUIT), PUMPKIN(Plants.PUMPKIN), MAGNET_SHROOM(Plants.MAGNET_SHROOM),
	//roof
	COFFEE_BEAN(Plants.COFFEE_BEAN), MARIGOLD(Plants.MARIGOLD),
//	CABBAGE_PULT, FLOWER_POT, KERNEL_PULT, COFFEE_BEAN, GARLIC, UMBRELLA_LEAF, MARIGOLD, MELON_PULT,
	//upgrade
	GATLING_PEA(Plants.GATLING_PEA), TWIN_SUNFLOWER(Plants.TWIN_SUNFLOWER), CAT_TAIL(Plants.CAT_TAIL),
//	GLOOM_SHROOM, WINTER_MELON, GOLD_MAGNET, SPIKE_ROCK, COB_CANNON, IMITATER,
	// pvz2
	ANGEL_STAR_FRUIT(Plants.ANGEL_STAR_FRUIT),
//	ICEBERG_LETTUCE, GOLD_LEAF,
	//other
	WATER_GUARD(Plants.WATER_GUARD), STRANGE_CAT(Plants.STRANGE_CAT);
//  LIGHTLING_ROD,

	/**
	 * Zombies
	 */
	// 1
//	NORMAL_ZOMBIE, FLAG_ZOMBIE, CONEHEAD_ZOMBIE, POLE_ZOMBIE, BUCKETHEAD_ZOMBIE,
	// 2
//	TOMB_STONE, PAPER_ZOMBIE, OLD_ZOMBIE, SUNDAY_EDITION_ZOMBIE, SCREENDOOR_ZOMBIE, FOOTBALL_ZOMBIE,
//	GIGA_FOOTBALL_ZOMBIE, DANCING_ZOMBIE, BACKUP_DANCER,
	// 3
//	SNORKEL_ZOMBIE, ZOMBONI, BOBSLE_TEAM, DOLPHIN_RIDER, LAVA_ZOMBIE,
	// 4
//	JACK_IN_BOX_ZOMBIE, BALLON_ZOMBIE, DIGGER_ZOMBIE, POGO_ZOMBIE, YETI_ZOMBIE,
	// 5
//	CATAPULT_ZOMBIE, GARGANTUAR, IMP, SAD_GARGANTUAR,
	// boss
//	ZOMBOSS,
	// plant_zombie
//	PEASHOOTER_ZOMBIE, NUTWALL_ZOMBIE, GATLINGPEA_ZOMBIE, TALLNUT_ZOMBIE, SQUASH_ZOMBIE, JALAPENO_ZOMBIE
	;
	
	private Plants plant = null;
	@SuppressWarnings("unused")
	private Zombies zombie = null;
	
	private Almanacs() {}
	
	private Almanacs(Plants p) {
		this.plant = p;
	}
	
	private Almanacs(Zombies z) {
		this.zombie = z;
	}
	
	public Plants getPlant() {
		return this.plant;
	}
	
	public boolean isPlant() {
		return this.plant != null;
	}
	
	@SuppressWarnings("resource")
	public static String getAlmanacName(Almanacs a) {
		if(a == PLAYER) return Minecraft.getInstance().player.getName().getFormattedText();
		if(a.isPlant()) {
			Plants plant = a.getPlant();
			return new TranslationTextComponent("entity.pvz." + plant.toString().toLowerCase()).getFormattedText();
		}
	    return new TranslationTextComponent("entity.pvz." + a.toString().toLowerCase()).getFormattedText();
	}
	
	public static ItemStack getItemStackByAlmanac(Almanacs a) {
		Item item = null;
		if(a == Almanacs.PLAYER) item = Items.PLAYER_HEAD;
		if(a.isPlant()) {
			item = PlantUtil.getPlantSummonCard(a.getPlant());
		}
		return new ItemStack(item);
	}
	
	public static enum Categories{
		ALL, PLANTS, ZOMBIES;
	}

}
