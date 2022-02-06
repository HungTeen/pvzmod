package com.hungteen.pvz.common.item.armor;

import com.hungteen.pvz.client.model.armor.FootballArmorModel;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumMap;
import java.util.Map;

public class FootballArmorItem extends PVZArmorItem{

	@SuppressWarnings("rawtypes")
	private static final Map<EquipmentSlotType, BipedModel> modelMap = new EnumMap<>(EquipmentSlotType.class);
	
	public FootballArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot) {
		super(materialIn, slot);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		if(slot == EquipmentSlotType.HEAD) return StringUtil.ARMOR_PREFIX + "football_helmet.png";
		if(slot == EquipmentSlotType.CHEST) return StringUtil.ARMOR_PREFIX + "football_chestplate.png";
		if(slot == EquipmentSlotType.LEGS) return StringUtil.ARMOR_PREFIX + "football_layer_2.png";
		return StringUtil.ARMOR_PREFIX + "football_layer_1.png";
	}
	
	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {
		return (A) modelMap.get(armorSlot);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void initArmorModel(){
		modelMap.put(EquipmentSlotType.HEAD, new FootballArmorModel(EquipmentSlotType.HEAD, 1f));
		modelMap.put(EquipmentSlotType.CHEST, new FootballArmorModel(EquipmentSlotType.CHEST, 1f));
	}

}
