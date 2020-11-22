package com.hungteen.pvz.item.armor;

import java.util.EnumMap;
import java.util.Map;

import com.hungteen.pvz.model.armor.BucketHeadModel;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BucketArmorItem extends ArmorItem{

	@SuppressWarnings("rawtypes")
	private static final Map<EquipmentSlotType,BipedModel> modelMap = new EnumMap<>(EquipmentSlotType.class);
	
	public BucketArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot) {
		super(materialIn, slot, new Item.Properties().group(GroupRegister.PVZ_MISC));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return StringUtil.ARMOR_PREFIX + "bucket_head.png";
	}
	
	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {
		return (A) modelMap.get(armorSlot);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void initArmorModel() {
		modelMap.put(EquipmentSlotType.HEAD, new BucketHeadModel(1f));
	}
}
