package com.hungteen.pvzmod.registry;

import com.hungteen.pvzmod.potion.PVZPotion;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Enums;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionRegister {

	//effects
	public static final Potion COLD_EFFECT = new PVZPotion("cold",true,Enums.RGBIntegers.IRIS_BLUE).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -0.05, 2);
	
	public static final Potion FROZEN_EFFECT = new PVZPotion("frozen",true,Enums.RGBIntegers.ELECTRIC_BLUE).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -1, 2);
	
	public static final Potion SMALL_LIFE_EFFECT = new PVZPotion("small_life",true,Enums.RGBIntegers.GREEN).registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH,MathHelper.getRandomUUID().toString(),-0.1,2);
	
	public static final Potion BUTTER_EFFECT = new PVZPotion("butter",true,Colors.YELLOW).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -1, 2);
	//potions
	public static final PotionType FROZEN_POTION = new PotionType("frozen_potion", new PotionEffect[] {new PotionEffect(FROZEN_EFFECT, 600)}).setRegistryName("frozen_potion");

	public static final PotionType LONG_FROZEN_POTION = new PotionType("long_frozen_potion", new PotionEffect[] {new PotionEffect(FROZEN_EFFECT, 1200)}).setRegistryName("long_frozen_potion");

	public static void registerPotions()
	{
		registerPotion(FROZEN_POTION,LONG_FROZEN_POTION,FROZEN_EFFECT);
		ForgeRegistries.POTIONS.register(COLD_EFFECT);
		ForgeRegistries.POTIONS.register(SMALL_LIFE_EFFECT);
		ForgeRegistries.POTIONS.register(BUTTER_EFFECT);
		registerPotionMixes();
	}

	private static void registerPotion(PotionType type,PotionType longType,Potion effect)
	{
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(type);
		ForgeRegistries.POTION_TYPES.register(longType);
	}
	
	private static void registerPotionMixes()
	{
		PotionHelper.addMix(FROZEN_POTION, Items.REDSTONE, LONG_FROZEN_POTION);
		PotionHelper.addMix(PotionTypes.AWKWARD, Item.getItemFromBlock(Blocks.FROSTED_ICE), FROZEN_POTION);
	}
}
