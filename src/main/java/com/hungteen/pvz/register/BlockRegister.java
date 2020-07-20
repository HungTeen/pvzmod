package com.hungteen.pvz.register;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.PVZMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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

	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5,6).sound(SoundType.METAL)));
	
	
	/**
	 * 注册itemblock
	 */
	@SubscribeEvent
	public static void registerBlockItem(RegistryEvent.Register<Item> ev)
	{
		IForgeRegistry<Item> items = ev.getRegistry();
		List<RegistryObject<? extends Block>> blocks = Arrays.asList(
				STEEL_BLOCK
		);
		for(RegistryObject<? extends Block> block:blocks) {
			items.register(new BlockItem(block.get(),new Item.Properties().group(GroupRegister.GROUP_BLOCKS)).setRegistryName(block.get().getRegistryName()));
		}
	}
	
}
