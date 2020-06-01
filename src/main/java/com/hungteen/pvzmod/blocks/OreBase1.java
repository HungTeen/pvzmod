package com.hungteen.pvzmod.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class OreBase1 extends BlockBase{

	private Item drop;
	private int min,max;
	
	public OreBase1(String name, Material material,Item drop,float hard,float resis,int level,float light,int min,int max) {
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(hard);//5
		setResistance(resis);//11
		setHarvestLevel("pickaxe",level);
		setLightLevel(light);//0.85
		this.drop=drop;
		this.min=min;
		this.max=max;
	}

	@Override
	public Item getItemDropped(IBlockState state,Random rand,int fortune)
	{
		return drop;
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(this.max)+this.min;
	}
}
