package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.enchant.SunReduceEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegister {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(ForgeRegistries.ENCHANTMENTS, PVZMod.MOD_ID);
	
	public static final RegistryObject<Enchantment> SUN_COST_REDUCE = ENCHANTMENTS.register("sun_cost_reduce", SunReduceEnchantment::new);
}
