package com.hungteen.pvz.common.item.armor;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.item.PVZItemTabs;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.client.IItemRenderProperties;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 13:21
 **/
public abstract class PVZArmorItem extends ArmorItem {

    protected static final String PREFIX = PVZMod.MOD_ID + ":textures/models/armor/";

    public PVZArmorItem(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Properties().tab(PVZItemTabs.PVZ_USEFUL));
    }

}
