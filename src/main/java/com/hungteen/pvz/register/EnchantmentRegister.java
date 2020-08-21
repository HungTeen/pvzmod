package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.enchantment.BreakOutEnchantment;
import com.hungteen.pvz.enchantment.EnergyTransferEnchantment;
import com.hungteen.pvz.enchantment.SunReduceEnchantment;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.item.tool.card.SummonCardItem;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegister {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(ForgeRegistries.ENCHANTMENTS, PVZMod.MOD_ID);

	public static final RegistryObject<Enchantment> SUN_COST_REDUCE = ENCHANTMENTS.register("sun_cost_reduce",SunReduceEnchantment::new);
	public static final RegistryObject<Enchantment> ENERGY_TRANSFER = ENCHANTMENTS.register("energy_transfer",EnergyTransferEnchantment::new);
	public static final RegistryObject<Enchantment> BREAK_OUT = ENCHANTMENTS.register("break_out",BreakOutEnchantment::new);

	public static final EnchantmentType SUMMON_CARD = EnchantmentType.create("summon_card", (item) -> {
		return item instanceof SummonCardItem;
	});

	public static final EnchantmentType PLANT_CARD = EnchantmentType.create("plant_card", (item) -> {
		return item instanceof PlantCardItem;
	});
}
