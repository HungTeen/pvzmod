package com.hungteen.pvz.register;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.block.EssenceOreBlock;
import com.hungteen.pvz.block.OriginBlock;
import com.hungteen.pvz.block.plants.ChomperBlock;
import com.hungteen.pvz.block.plants.LilyPadBlock;
import com.hungteen.pvz.block.plants.PVZSaplingBlock;
import com.hungteen.pvz.block.plants.PeaBlock;
import com.hungteen.pvz.block.plants.ToxicShroomBlock;
import com.hungteen.pvz.item.blockitem.LilyPadItem;
import com.hungteen.pvz.world.feature.tree.NutTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {
	
	// hardness
	//https://minecraft.gamepedia.com/Breaking#Blocks_by_hardness

	// resistance
	//https://minecraft.gamepedia.com/Explosion#Blast_resistance

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, PVZMod.MOD_ID);

	//ore
	public static final RegistryObject<Block> ORIGIN_ORE = BLOCKS.register("origin_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> APPEASE_ORE = BLOCKS.register("appease_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> LIGHT_ORE = BLOCKS.register("light_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> EXPLOSION_ORE = BLOCKS.register("explosion_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> DEFENCE_ORE = BLOCKS.register("defence_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> ICE_ORE = BLOCKS.register("ice_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> ENFORCE_ORE = BLOCKS.register("enforce_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> TOXIC_ORE = BLOCKS.register("toxic_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> ASSIST_ORE = BLOCKS.register("assist_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> MAGIC_ORE = BLOCKS.register("magic_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> FLAME_ORE = BLOCKS.register("flame_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> SPEAR_ORE = BLOCKS.register("spear_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> ARMA_ORE = BLOCKS.register("arma_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> ELECTRIC_ORE = BLOCKS.register("electric_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> SHADOW_ORE = BLOCKS.register("shadow_ore",EssenceOreBlock::new);
	public static final RegistryObject<Block> AMETHYST_ORE = BLOCKS.register("amethyst_ore",() -> new Block(Block.Properties.from(Blocks.DIAMOND_ORE)));
	
	//block
	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK).hardnessAndResistance(8,8)));
	public static final RegistryObject<Block> AMETHYST_BLOCK =BLOCKS.register("amethyst_block", ()->new Block(Block.Properties.from(Blocks.EMERALD_BLOCK).hardnessAndResistance(9, 9))); 
	public static final RegistryObject<Block> ORIGIN_BLOCK = BLOCKS.register("origin_block", OriginBlock::new);
	
	//crops
	public static final RegistryObject<Block> PEA_PLANT = BLOCKS.register("pea_plant", ()->new PeaBlock(Block.Properties.from(Blocks.WHEAT)));
	public static final RegistryObject<Block> TOXIC_SHROOM = BLOCKS.register("toxic_shroom", ()->new ToxicShroomBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));
	
	//plants
	public static final RegistryObject<Block> NUT_LEAVES = BLOCKS.register("nut_leaves", ()->new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> NUT_SAPLING = BLOCKS.register("nut_sapling", ()->new PVZSaplingBlock(NutTree::new));
	public static final RegistryObject<Block> CHOMPER = BLOCKS.register("chomper", ChomperBlock::new);
	public static final RegistryObject<LilyPadBlock> LILY_PAD = BLOCKS.register("lily_pad", LilyPadBlock::new);
	
	//tileentity block
	
	/**
	 * 注册itemblock
	 */
	@SubscribeEvent
	public static void registerBlockItem(RegistryEvent.Register<Item> ev){
		IForgeRegistry<Item> items = ev.getRegistry();
		List<RegistryObject<? extends Block>> blocks = Arrays.asList(
				ORIGIN_ORE,APPEASE_ORE,LIGHT_ORE,EXPLOSION_ORE,DEFENCE_ORE,ICE_ORE,ENFORCE_ORE,TOXIC_ORE,ASSIST_ORE,MAGIC_ORE,FLAME_ORE,SPEAR_ORE,ARMA_ORE,ELECTRIC_ORE,SHADOW_ORE,AMETHYST_ORE,
				STEEL_BLOCK,AMETHYST_BLOCK,ORIGIN_BLOCK,
				NUT_LEAVES,NUT_SAPLING,CHOMPER,LILY_PAD
		);
		for(RegistryObject<? extends Block> block:blocks) {
			if(block==LILY_PAD) {
				items.register(new LilyPadItem().setRegistryName(block.get().getRegistryName()));
			}else {
				items.register(new BlockItem(block.get(),new Item.Properties().group(GroupRegister.PVZ_MISC)).setRegistryName(block.get().getRegistryName()));
			}
		}
	}
	
}
