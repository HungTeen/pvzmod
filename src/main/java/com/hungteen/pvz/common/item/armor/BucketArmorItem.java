package com.hungteen.pvz.common.item.armor;

import com.hungteen.pvz.client.model.armor.BucketHeadModel;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.client.model.BipedModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.item.IArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.EnumMap;
import java.util.Map;

public class BucketArmorItem extends PVZArmorItem{

	@SuppressWarnings("rawtypes")
	private static final Map<EquipmentSlotType,BipedModel> modelMap = new EnumMap<>(EquipmentSlotType.class);
	
	public BucketArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot) {
		super(materialIn, slot);
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
