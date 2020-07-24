package com.hungteen.pvz.register;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.blocks.BlockOrigin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
	//黑曜石 50//末影箱 22//硬化玻璃 10//钻石块 5//蜘蛛网 4//信标 3//圆石 2//西瓜 1//石英 0.8//泥土 0.5

	// resistance
	//https://minecraft.gamepedia.com/Explosion#Blast_resistance
	//黑曜石 1200//末影箱 600//末地石 9//钻石块 6//蜘蛛网 4//矿石 3//木头 2//沙石 0.8

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, PVZMod.MOD_ID);

	//ore
	public static final RegistryObject<Block> OriginOre = BLOCKS.register("origin_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> AppeaseOre = BLOCKS.register("appease_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> LightOre = BLOCKS.register("light_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> ExplosionOre = BLOCKS.register("explosion_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> DefenceOre = BLOCKS.register("defence_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> IceOre = BLOCKS.register("ice_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> EnforceOre = BLOCKS.register("enforce_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> ToxicOre = BLOCKS.register("toxic_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> AssistOre = BLOCKS.register("assist_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> MagicOre = BLOCKS.register("magic_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> FlameOre = BLOCKS.register("flame_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> SpearOre = BLOCKS.register("spear_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> ArmaOre = BLOCKS.register("arma_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> ElectricOre = BLOCKS.register("electric_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> ShadowOre = BLOCKS.register("shadow_ore",() -> new Block(getEssenceOreProperties()));
	public static final RegistryObject<Block> AmethystOre = BLOCKS.register("amethyst_ore",() -> new Block(Block.Properties.from(Blocks.DIAMOND_ORE)));
	
	//block
	public static final RegistryObject<Block> SteelBlock = BLOCKS.register("steel_block",() -> new Block(Block.Properties.from(Blocks.IRON_BLOCK).hardnessAndResistance(8,8)));
	public static final RegistryObject<Block> AmethystBlock =BLOCKS.register("amethyst_block", ()->new Block(Block.Properties.from(Blocks.EMERALD_BLOCK).hardnessAndResistance(9, 9))); 
	public static final RegistryObject<Block> OriginBlock = BLOCKS.register("origin_block", BlockOrigin::new);
	
	/**
	 * 注册itemblock
	 */
	@SubscribeEvent
	public static void registerBlockItem(RegistryEvent.Register<Item> ev)
	{
		IForgeRegistry<Item> items = ev.getRegistry();
		List<RegistryObject<? extends Block>> blocks = Arrays.asList(
				OriginOre,AppeaseOre,LightOre,ExplosionOre,DefenceOre,IceOre,EnforceOre,ToxicOre,AssistOre,MagicOre,FlameOre,SpearOre,ArmaOre,ElectricOre,ShadowOre,AmethystOre,
				SteelBlock,AmethystBlock,OriginBlock
		);
		for(RegistryObject<? extends Block> block:blocks) {
			items.register(new BlockItem(block.get(),new Item.Properties().group(GroupRegister.PVZ_GROUP)).setRegistryName(block.get().getRegistryName()));
		}
	}
	
	public static Block.Properties getEssenceOreProperties()
	{
		return Block.Properties.from(Blocks.DIAMOND_ORE).hardnessAndResistance(9,9).lightValue(10).harvestLevel(3);
	}
}
