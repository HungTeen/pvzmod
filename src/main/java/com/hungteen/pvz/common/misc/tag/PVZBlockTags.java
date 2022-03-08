package com.hungteen.pvz.common.misc.tag;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;

/**
 * used in {@link com.hungteen.pvz.data.tag.BlockTagGenerator}
 */
public class PVZBlockTags {

	//forge
	public static final TagKey<Block> AMETHYST_ORES = forgeTag("ores/amethyst");
	public static final TagKey<Block> PLANT_SUIT_BLOCKS = forgeTag("plant_suit_block");
	
	/* pvz */
	//related to essence ores
	public static final TagKey<Block> ESSENCE_ORES = pvzTag("ores/essence");
	public static final TagKey<Block> TO_APPEASE_ORES = pvzTag("radiations/to_appease_ores");
	public static final TagKey<Block> TO_LIGHT_ORES = pvzTag("radiations/to_light_ores");
	public static final TagKey<Block> TO_EXPLOSION_ORES = pvzTag("radiations/to_explosion_ores");
	public static final TagKey<Block> TO_DEFENCE_ORES = pvzTag("radiations/to_defence_ores");
	public static final TagKey<Block> TO_ENFORCE_ORES = pvzTag("radiations/to_enforce_ores");
	public static final TagKey<Block> TO_ICE_ORES = pvzTag("radiations/to_ice_ores");
	public static final TagKey<Block> TO_TOXIC_ORES = pvzTag("radiations/to_toxic_ores");
	public static final TagKey<Block> TO_ASSIST_ORES = pvzTag("radiations/to_assist_ores");
	public static final TagKey<Block> TO_MAGIC_ORES = pvzTag("radiations/to_magic_ores");
	public static final TagKey<Block> TO_FLAME_ORES = pvzTag("radiations/to_flame_ores");
	public static final TagKey<Block> TO_SPEAR_ORES = pvzTag("radiations/to_spear_ores");
	public static final TagKey<Block> TO_ARMA_ORES = pvzTag("radiations/to_arma_ores");
	public static final TagKey<Block> TO_ELECTRIC_ORES = pvzTag("radiations/to_electric_ores");
	public static final TagKey<Block> TO_SHADOW_ORES = pvzTag("radiations/to_shadow_ores");
	public static final TagKey<Block> GOLD_TILES = pvzTag("gold_tile");

	private static TagKey<Block> pvzTag(String name){
        return BlockTags.createOptional(StringUtil.prefix(name));
    }
	
	private static TagKey<Block> forgeTag(String name){
        return BlockTags.createOptional(new ResourceLocation("forge", name));
    }
}
