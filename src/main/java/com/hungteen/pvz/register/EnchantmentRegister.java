package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.enchantment.BreakOutEnchantment;
import com.hungteen.pvz.enchantment.CharmEnchantment;
import com.hungteen.pvz.enchantment.EnergyTransferEnchantment;
import com.hungteen.pvz.enchantment.SoillessPlantEnchantment;
import com.hungteen.pvz.enchantment.SunMendingEnchantment;
import com.hungteen.pvz.enchantment.SunReduceEnchantment;
import com.hungteen.pvz.enchantment.SunShovelEnchantment;
import com.hungteen.pvz.enchantment.TreeProtectionEnchantment;
import com.hungteen.pvz.item.tool.card.BlockPlantCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.item.tool.card.SummonCardItem;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegister {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PVZMod.MOD_ID);

	public static final RegistryObject<Enchantment> SUN_COST_REDUCE = ENCHANTMENTS.register("sun_cost_reduce", SunReduceEnchantment::new);
	public static final RegistryObject<Enchantment> ENERGY_TRANSFER = ENCHANTMENTS.register("energy_transfer", EnergyTransferEnchantment::new);
	public static final RegistryObject<Enchantment> BREAK_OUT = ENCHANTMENTS.register("break_out", BreakOutEnchantment::new);
	public static final RegistryObject<Enchantment> TREE_PROTECTION = ENCHANTMENTS.register("tree_protection", TreeProtectionEnchantment::new);
	public static final RegistryObject<Enchantment> CHARM = ENCHANTMENTS.register("charm", CharmEnchantment::new);
	public static final RegistryObject<Enchantment> SUN_SHOVEL = ENCHANTMENTS.register("sun_shovel", SunShovelEnchantment::new);
	public static final RegistryObject<Enchantment> SOILLESS_PLANT = ENCHANTMENTS.register("soilless_plant", SoillessPlantEnchantment::new);
	public static final RegistryObject<Enchantment> SUN_MENDING = ENCHANTMENTS.register("sun_mending", SunMendingEnchantment::new);
	
	public static final EnchantmentType SUMMON_CARD = EnchantmentType.create("summon_card", (item) -> {
		return item instanceof SummonCardItem;
	});

	public static final EnchantmentType PLANT_CARD = EnchantmentType.create("plant_card", (item) -> {
		return item instanceof PlantCardItem && ! (item instanceof BlockPlantCardItem);
	});
	
	public static final EnchantmentType SHOVEL = EnchantmentType.create("shovel", (item) -> {
		return item instanceof ShovelItem;
	});
	
}
