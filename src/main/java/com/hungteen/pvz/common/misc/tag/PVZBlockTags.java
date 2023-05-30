package com.hungteen.pvz.common.misc.tag;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

/**
 * used in {@link com.hungteen.pvz.data.tag.BlockTagGenerator}
 */
public class PVZBlockTags {

	//forge
	public static final INamedTag<Block> AMETHYST_ORES = forgeTag("ores/amethyst");
	public static final INamedTag<Block> PLANT_SUIT_BLOCKS = forgeTag("plant_suit_block");
	
	/* pvz */
	//related to essence ores
	public static final INamedTag<Block> ESSENCE_ORES = pvzTag("ores/essence");
	public static final INamedTag<Block> TO_APPEASE_ORES = pvzTag("radiations/to_appease_ores");
	public static final INamedTag<Block> TO_LIGHT_ORES = pvzTag("radiations/to_light_ores");
	public static final INamedTag<Block> TO_EXPLOSION_ORES = pvzTag("radiations/to_explosion_ores");
	public static final INamedTag<Block> TO_DEFENCE_ORES = pvzTag("radiations/to_defence_ores");
	public static final INamedTag<Block> TO_ENFORCE_ORES = pvzTag("radiations/to_enforce_ores");
	public static final INamedTag<Block> TO_ICE_ORES = pvzTag("radiations/to_ice_ores");
	public static final INamedTag<Block> TO_TOXIC_ORES = pvzTag("radiations/to_toxic_ores");
	public static final INamedTag<Block> TO_ASSIST_ORES = pvzTag("radiations/to_assist_ores");
	public static final INamedTag<Block> TO_MAGIC_ORES = pvzTag("radiations/to_magic_ores");
	public static final INamedTag<Block> TO_FLAME_ORES = pvzTag("radiations/to_flame_ores");
	public static final INamedTag<Block> TO_SPEAR_ORES = pvzTag("radiations/to_spear_ores");
	public static final INamedTag<Block> TO_ARMA_ORES = pvzTag("radiations/to_arma_ores");
	public static final INamedTag<Block> TO_ELECTRIC_ORES = pvzTag("radiations/to_electric_ores");
	public static final INamedTag<Block> TO_SHADOW_ORES = pvzTag("radiations/to_shadow_ores");
	public static final INamedTag<Block> GOLD_TILES = pvzTag("gold_tile");

	private static INamedTag<Block> pvzTag(String name){
        return BlockTags.createOptional(StringUtil.prefix(name));
    }
	
	private static INamedTag<Block> forgeTag(String name){
        return BlockTags.createOptional(new ResourceLocation("forge", name));
    }
}
