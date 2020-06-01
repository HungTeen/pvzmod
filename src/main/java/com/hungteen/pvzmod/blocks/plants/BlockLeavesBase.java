package com.hungteen.pvzmod.blocks.plants;

import java.util.List;
import java.util.Random;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.registry.CreativeTabRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.interfaces.IHasModel;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeavesBase extends BlockLeaves implements IHasModel
{		
	public static String type;
	
	public BlockLeavesBase(String name) 
	{
		type = name.replaceAll("_leaves", "").trim();
		//System.out.println(type);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setCreativeTab(CreativeTabRegister.BLOCK_TAB);
		setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
				
		BlockRegister.BLOCKS.add(this);
		ItemRegister.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		//System.out.println(type);
		if(type.equals("nut")) return Item.getItemFromBlock(BlockRegister.NUT_SAPLING);
		else return Item.getItemFromBlock(Blocks.SAPLING);
	}
	
	public Item getOtherItemDropped(IBlockState state, Random rand, int fortune) 
	{
		if(type.equals("nut")) return ItemRegister.NUT;
		else return null;
	}
	
	@Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int chance = this.getSaplingDropChance(state);

        if (fortune > 0)
        {
            chance -= 2 << fortune;
            if (chance < 10) chance = 10;
        }

        if (rand.nextInt(chance) == 0)
        {
            ItemStack drop = new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state));
            if (!drop.isEmpty())
                drops.add(drop);
        }

        if (rand.nextInt(chance) == 0)
        {
        	ItemStack drop = new ItemStack(getOtherItemDropped(state, rand, fortune), 1, damageDropped(state));
            if (!drop.isEmpty())
                drops.add(drop);
        }
        
        chance = 200;
        if (fortune > 0)
        {
            chance -= 10 << fortune;
            if (chance < 40) chance = 40;
        }

        this.captureDrops(true);
        if (world instanceof World)
            this.dropApple((World)world, pos, state, chance); 
        drops.addAll(this.captureDrops(false));
    }
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = 0;
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()) i |= 2;
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) i|= 4;
		return i;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(this);
	}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {return 10;}
	
	@Override
	public EnumType getWoodType(int meta) {return null;}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) 
	{
		return NonNullList.withSize(1, new ItemStack(this));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() 
	{
		return BlockRenderLayer.TRANSLUCENT;
	}	
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}