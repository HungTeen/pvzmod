package com.hungteen.pvz.utils.enums;


public enum Plants {
	    //1-1 ~ 1-10
		PEA_SHOOTER("pea_shooter"),
		SUN_FLOWER("sun_flower"),
		CHERRY_BOMB("cherry_bomb"),
	    WALL_NUT("wall_nut"),
	    POTATO_MINE("potato_mine"),
	    SNOW_PEA("snow_pea"),
	    CHOMPER("chomper"),//���
	    DOUBLE_SHOOTER("double_shooter"),
	    //2-1 ~ 2-10
	    PULF_SHROOM("pulf_shroom"),//��Ԩ
	    SUN_SHROOM("sun_shroom"),//��Ԩ
	    FUME_SHROOM("fume_shroom"),//��Ԩ
	    GRAVE_VINE("grave_vine"),//��Ԩ
	    HYPNO_SHROOM("hypno_shroom"),
	    SCAREDY_SHROOM("scaredy_shroom"),//��Ԩ
	    ICE_SHROOM("ice_shroom"),
	    DOOM_SHROOM("doom_shroom"),//��Ԩ
	    //3-1 ~ 3-10
	    LILY_PAD("lily_pad"),
	    SQUASH("squash"),
	    THREE_PEATER("three_peater"),
	    TANGLE_KELP("tangle_kelp"),
	    JALAPENO("jalapeno"),
	    SPIKE_WEED("spike_weed"),
	    TORCH_WOOD("torch_wood"),
	    TALL_NUT("tall_nut"),
	    //4-1 ~ 4-10
	    SEA_SHROOM("sea_shroom"),//��Ԩ
	    PLANTERN("plantern"),//��Ԩ
	    CACTUS("cactus"),//���
	    BLOVER("blover"),//��Ԩ
	    SPLIT_PEA("split_pea"),
	    STAR_FRUIT("star_fruit"),//δ֪
	    PUMPKIN("pumpkin"),
	    MAGNET_SHROOM("magnet_shroom"),//��Ԩ
	    //5-1 5-10
	    CABBAGE_PULT("cabbage_pult"),
	    FLOWER_POT("flower_pot"),
	    KERNEL_PULT("kernel_pult"),
	    COFFEE_BEAN("coffee_bean"),
	    GARLIC("garlic"),//��������ˣ��ѱ����
	    UMBRELLA_LEAF("umbrella_leaf"),//��Ԩ
	    MARIGOLD("marigold"),
	    MELON_PULT("melon_pult"),
	    //upgrate
	    GATLING_PEA("gatling_pea"),
	    TWIN_SUNFLOWER("twin_sunflower"),
	    GLOOM_SHROOM("gloom_shroom"),//��Ԩ
	    CAT_TAIL("cat_tail"),
	    WINTER_MELON("winter_melon"),
	    GOLD_MAGNET("gold_magnet"),//��Ԩ
	    SPIKE_ROCK("spike_rock"),
	    COB_CANNON("cob_cannon"),
	    IMITATER("imitater"),
	    //pvz2
	    ICEBERG_LETTUCE("iceberg_lettuce"),
	    GOLD_LEAF("gold_leaf"),
	    LIGHTLING_ROD("lightning_rod"),
	    STRANGE_CAT("strange_cat");
	String name;
	Plants(String name){
		this.name=name;
	}
	
	public static Plants getPlantByName(String name) {
		if(name.equals("pea_shooter")) return PEA_SHOOTER;
		else if(name.equals("sun_flower")) return SUN_FLOWER;
		else if(name.equals("cherry_bomb")) return CHERRY_BOMB;
		else if(name.equals("wall_nut")) return WALL_NUT;
		else if(name.equals("potato_mine")) return POTATO_MINE;
		else if(name.equals("snow_pea")) return SNOW_PEA;
		else if(name.equals("double_shooter")) return DOUBLE_SHOOTER;
		else if(name.equals("hypno_shroom")) return HYPNO_SHROOM;
		else if(name.equals("ice_shroom")) return ICE_SHROOM;
		else if(name.equals("lily_pad")) return LILY_PAD;
		else if(name.equals("squash")) return SQUASH;
		else if(name.equals("three_peater")) return THREE_PEATER;
		else if(name.equals("tangle_kelp")) return TANGLE_KELP;
		else if(name.equals("jalapeno")) return JALAPENO;
		else if(name.equals("spike_weed")) return SPIKE_WEED;
		else if(name.equals("torch_wood")) return TORCH_WOOD;
		else if(name.equals("tall_nut")) return TALL_NUT;
		else if(name.equals("split_pea")) return SPLIT_PEA;
		else if(name.equals("pumpkin")) return PUMPKIN;
		else if(name.equals("cabbage_pult")) return CABBAGE_PULT;
		else if(name.equals("flower_pot")) return FLOWER_POT;
		else if(name.equals("kernel_pult")) return KERNEL_PULT;
		else if(name.equals("coffee_bean")) return COFFEE_BEAN;
		else if(name.equals("marigold")) return MARIGOLD;
		else if(name.equals("melon_pult")) return MELON_PULT;
		else if(name.equals("gatling_pea")) return GATLING_PEA;
		else if(name.equals("twin_sunflower")) return TWIN_SUNFLOWER;
		else if(name.equals("cat_tail")) return CAT_TAIL;
		else if(name.equals("winter_melon")) return WINTER_MELON;
		else if(name.equals("spike_rock")) return SPIKE_ROCK;
		else if(name.equals("iceberg_lettuce")) return ICEBERG_LETTUCE;
		else if(name.equals("gold_leaf")) return GOLD_LEAF;
		else if(name.equals("lightning_rod")) return LIGHTLING_ROD;
		else if(name.equals("strange_cat")) return STRANGE_CAT;
		else return null;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
