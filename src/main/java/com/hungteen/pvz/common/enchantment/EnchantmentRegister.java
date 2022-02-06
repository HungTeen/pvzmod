package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.enchantment.card.BandageEnchantment;
import com.hungteen.pvz.common.enchantment.card.CharmEnchantment;
import com.hungteen.pvz.common.enchantment.card.ImmediateCDEnchantment;
import com.hungteen.pvz.common.enchantment.card.plantcard.BreakOutEnchantment;
import com.hungteen.pvz.common.enchantment.card.plantcard.DenselyPlantEnchantment;
import com.hungteen.pvz.common.enchantment.card.plantcard.SoillessPlantEnchantment;
import com.hungteen.pvz.common.enchantment.misc.EnergyTransferEnchantment;
import com.hungteen.pvz.common.enchantment.misc.RangeReachEnchantment;
import com.hungteen.pvz.common.enchantment.misc.SunMendingEnchantment;
import com.hungteen.pvz.common.enchantment.misc.SunShovelEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegister {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PVZMod.MOD_ID);

	public static final RegistryObject<Enchantment> ENERGY_TRANSFER = ENCHANTMENTS.register("energy_transfer", EnergyTransferEnchantment::new);
	public static final RegistryObject<Enchantment> BREAK_OUT = ENCHANTMENTS.register("break_out", BreakOutEnchantment::new);
	public static final RegistryObject<Enchantment> CHARM = ENCHANTMENTS.register("charm", CharmEnchantment::new);
	public static final RegistryObject<Enchantment> SUN_SHOVEL = ENCHANTMENTS.register("sun_shovel", SunShovelEnchantment::new);
	public static final RegistryObject<Enchantment> SOILLESS_PLANT = ENCHANTMENTS.register("soilless_plant", SoillessPlantEnchantment::new);
	public static final RegistryObject<Enchantment> SUN_MENDING = ENCHANTMENTS.register("sun_mending", SunMendingEnchantment::new);
	public static final RegistryObject<Enchantment> DENSELY_PLANT = ENCHANTMENTS.register("densely_plant", DenselyPlantEnchantment::new);
	public static final RegistryObject<Enchantment> IMMEDIATE_CD = ENCHANTMENTS.register("immediate_cd", ImmediateCDEnchantment::new);
	public static final RegistryObject<Enchantment> RANGE_REACH = ENCHANTMENTS.register("range_reach", RangeReachEnchantment::new);
	public static final RegistryObject<Enchantment> CARD_HEAL = ENCHANTMENTS.register("bandage", BandageEnchantment::new);
	
}
