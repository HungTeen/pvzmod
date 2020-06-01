package com.hungteen.pvzmod.items.tools;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.registry.CreativeTabRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.interfaces.IHasModel;

import net.minecraft.item.ItemHoe;

public class ToolHoe extends ItemHoe implements IHasModel{

	public ToolHoe(String name,ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabRegister.TOOL_TAB);
		
		ItemRegister.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	
}
