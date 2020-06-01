package com.hungteen.pvzmod.blocks;

import java.util.Random;

import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.registry.ItemRegister;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class OreBase extends BlockBase{
	public OreBase(String name,Material material)
	{
		super(name,material);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(11f);
		setHarvestLevel("pickaxe",1);
		
	}
	
//	@Override
//	public Item getItemDropped(IBlockState state,Random rand,int fortune)
//	{
//		return ItemInit.ALUMINUM_INGOT;
//	}
//	
//	@Override
//	public int quantityDropped(Random rand)
//	{
//		int max=4,min=1;
//		return rand.nextInt(max)+min;
//	}
}
