package com.hungteen.pvzmod.items;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.registry.CreativeTabRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.interfaces.IHasModel;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemClock;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String name,CreativeTabs tab)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ItemRegister.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
