package com.hungteen.pvzmod.damage;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.resources.I18n;

public enum PVZDamageType {
	NORMAL, //Normal bullet,or hit
	WEAK,      
	EXPLOSION, // ÍÁ¶¹À×
	SNOW, //º®±ùÍã¶¹¡¢±ùÎ÷¹Ï
	ICE,  //º®±ù¹½¡¢±ù¶³Éú²Ë
	FIRE, //»ðÍã¶¹ »ð±¬À±½·
	BUTTER, //»ÆÓÍ
	LIGHTNING, //electricity attack
	MAGIC,   //Ä§·¨¹¥»÷ Èç÷È»ó¹½
	SUN,// sun attack
	EAT, //½©Ê¬ÆÕÍ¨¹¥»÷
	DEAD, //Æû³µÄëÑ¹¡¢¾ÞÈËÔÒ ³ý¸ÖµØ´Ì¾ù²»¿ÉµÖ¿¹
	MOTAL; //ÅË¶àÀ­Ä§ºÐ
	
	@Override
	public String toString() {
		switch(this) {
		case WEAK:
			return ChatFormatting.WHITE+I18n.format("pvz.DamageType.WEAK",new Object[0]);
			case EXPLOSION:
				return ChatFormatting.DARK_RED+I18n.format("pvz.DamageType.EXPLOSION",new Object[0]);
			case SNOW:
				return ChatFormatting.WHITE+I18n.format("pvz.DamageType.SNOW",new Object[0]);
			case ICE:
				return ChatFormatting.AQUA+I18n.format("pvz.DamageType.ICE",new Object[0]);
			case FIRE:
				return ChatFormatting.RED+I18n.format("pvz.DamageType.FIRE",new Object[0]);
			case BUTTER:
				return ChatFormatting.YELLOW+I18n.format("pvz.DamageType.BUTTER",new Object[0]);
			case LIGHTNING:
				return ChatFormatting.DARK_BLUE+I18n.format("pvz.DamageType.LIGHTNING",new Object[0]);
			case MAGIC:
				return ChatFormatting.LIGHT_PURPLE+I18n.format("pvz.DamageType.MAGIC",new Object[0]);
			case SUN:
			    return ChatFormatting.YELLOW+I18n.format("pvz.DamageType.SUN",new Object[0]);
			case EAT:
			    return ChatFormatting.DARK_GREEN+I18n.format("pvz.DamageType.EAT",new Object[0]);
			case DEAD:
			    return ChatFormatting.DARK_RED+I18n.format("pvz.DamageType.DEAD",new Object[0]);
			case MOTAL:
			    return ChatFormatting.BLACK+I18n.format("pvz.DamageType.MOTAL",new Object[0]);
		default:
			return ChatFormatting.GREEN+I18n.format("pvz.DamageType.NORMAL",new Object[0]);
		}
	}
	
	
}
