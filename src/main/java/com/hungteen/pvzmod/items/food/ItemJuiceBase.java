package com.hungteen.pvzmod.items.food;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJuiceBase extends ItemFood implements IHasModel{

	public ItemJuiceBase(String name,int amount, float saturation,boolean isWolfFood) {
		super(amount, saturation,isWolfFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.FOOD);
		this.setMaxStackSize(1);
		ItemRegister.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
