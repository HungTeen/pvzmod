package com.hungteen.pvz.client;

import com.hungteen.pvz.common.CommonProxy;
import com.hungteen.pvz.common.item.armor.BucketArmorItem;
import com.hungteen.pvz.common.item.armor.ConeArmorItem;
import com.hungteen.pvz.common.item.armor.FootballArmorItem;
import com.hungteen.pvz.common.item.armor.GigaArmorItem;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.KeyBindRegister;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemModelsProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy{

	public static final Minecraft MC = Minecraft.getInstance();
	
	@Override
	public void init() {
//		KeyBindRegister.init();
	};
	
	@Override
	public void postInit() {
		
	};
	
	@Override
	public void setUpClient() {
		ConeArmorItem.initArmorModel();
		BucketArmorItem.initArmorModel();
		FootballArmorItem.initArmorModel();
		GigaArmorItem.initArmorModel();
		KeyBindRegister.init();
		ItemModelsProperties.register(ItemRegister.SCREEN_DOOR.get(), StringUtil.prefix("blocking"), (stack, world, entity) -> {
	         return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
	    });
	}
	
}
