package com.hungteen.pvzmod.blocks;

import java.util.Random;

import com.hungteen.pvzmod.registry.ItemRegister;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockPotato;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class JewelOre extends OreBase{

	public JewelOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(10.0F);
		setResistance(17f);
		setHarvestLevel("pickaxe",3);
	}

//	@Override
//	public Item getItemDropped(IBlockState state,Random rand,int fortune)
//	{
//		return ItemInit.JEWEL;
//	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		int max=4,min=1;
		return rand.nextInt(max)+min;
	}
	
	
}
