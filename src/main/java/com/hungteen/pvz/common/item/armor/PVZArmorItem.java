package com.hungteen.pvz.common.item.armor;

import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-05 21:14
 **/
public abstract class PVZArmorItem extends ArmorItem {

    public PVZArmorItem(IArmorMaterial armorMaterial, EquipmentSlotType slotType) {
        super(armorMaterial, slotType, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL));
    }
}
