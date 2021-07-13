package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.common.event.PVZLivingEvents;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TreeProtectionEnchantment extends PVZEnchantment {

	public TreeProtectionEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.ARMOR , new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
	}
	
	/**
	 * make living can not receive damage larger than its max health.
	 * {@link PVZLivingEvents#onLivingHurt(LivingHurtEvent)}
	 */
	public static void handleTreeProtection(final LivingHurtEvent ev) {
		ev.getEntityLiving().getArmorSlots().forEach(stack -> {
			if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.TREE_PROTECTION.get(), stack) > 0){
				ev.setAmount(Math.min(ev.getAmount(), EntityUtil.getCurrentMaxHealth(ev.getEntityLiving())));
				return ;
			}
	    });
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 40;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 100;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
}
