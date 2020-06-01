package com.hungteen.pvzmod.items.armors;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.model.items.ModelBarrier;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.interfaces.IHasModel;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorModel extends ItemArmor implements IHasModel{

	public ArmorModel(String name,CreativeTabs tab,ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, 1, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(1);
		
		ItemRegister.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving,ItemStack itemStack,EntityEquipmentSlot armorSlot,ModelBiped _default)
	{
		if(itemStack!=ItemStack.EMPTY) {
			if(itemStack.getItem() instanceof ItemArmor)
			{
				ModelBarrier model = new ModelBarrier();
				
				model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				
				model.isChild = _default.isChild;
				model.isRiding = _default.isRiding;
				model.isSneak = _default.isSneak;
				model.rightArmPose = _default.rightArmPose;
				model.leftArmPose = _default.leftArmPose;
				
				return model;
			}
		}
		return null;
	}
}
