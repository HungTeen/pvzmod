package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.utils.PlayerUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public abstract class SummonCardItem extends Item{

	public final boolean isEnjoyCard;
	
	public SummonCardItem(boolean isEnjoyCard) {
		this(new Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(isEnjoyCard ? 16 : 1), isEnjoyCard);
	}
	
	public SummonCardItem(Properties properties, boolean isEnjoyCard) {
		super(properties);
		this.isEnjoyCard = isEnjoyCard;
	}
	 
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return ! this.isEnjoyCard && super.canApplyAtEnchantingTable(stack, enchantment);//enjoy card have no enchant
	}
	
	/**
	 * get base sun cost.
	 * {@link #appendHoverText(ItemStack, World, List, ITooltipFlag)}
	 */
	public static int getCardSunCost(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).getBasisSunCost(stack);
		}
		return 1;
	}
	
	/**
	 * get base card cd.
	 * {@link #appendHoverText(ItemStack, World, List, ITooltipFlag)}
	 */
	public static ICoolDown getCardCoolDown(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).getBasisCoolDown(stack);
		}
		return CoolDowns.DEFAULT;
	}

	/**
	 * {@link #appendHoverText(ItemStack, World, List, ITooltipFlag)}
	 */
	public static int getCardRequiredLevel(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).plantType.getRequiredLevel();
		}
		return 100;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.card_sun_cost", getCardSunCost(stack)).withStyle(TextFormatting.YELLOW));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.card_cd", new TranslationTextComponent(getCardCoolDown(stack).getTranslateKey()).getString()).withStyle(TextFormatting.AQUA));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.card_required_level", getCardRequiredLevel(stack)).withStyle(TextFormatting.RED));
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return this.getItemStackLimit(stack) == 1 && ! this.isEnjoyCard;
	}
	
	@Override
	public int getItemEnchantability(ItemStack stack) {
		return 20;
	}
	
	@Override
	public int getEnchantmentValue() {
		return 20;//0 ~ 45
	}

	public void notifyPlayerAndCD(PlayerEntity player, ItemStack stack, PlacementErrors error) {
		this.notifyPlayerAndCD(player, stack, error, 0);
	}

	/**
	 * send helpful info.
	 */
	public void notifyPlayerAndCD(PlayerEntity player, ItemStack stack, PlacementErrors error, int arg) {
		if(! player.level.isClientSide) {
			PlayerUtil.sendMsgTo(player, error.getTextByArg(arg, TextFormatting.RED));
			player.getCooldowns().addCooldown(stack.getItem(), 10);
		}
	}
	protected enum PlacementErrors{
		SUN_ERROR("sun"),
		CD_ERROR("cd"),
		LOCK_ERROR("lock"),
		UPGRADE_ERROR("upgrade"),
		GROUND_ERROR("ground"),
		OUTER_ERROR("outer"),
		OUTER_FULL("outer_full");

		private final String info;

		PlacementErrors(String s){
			this.info = s;
		}

		public IFormattableTextComponent getTextByArg(int arg, TextFormatting color){
			return new TranslationTextComponent("help.pvz."+ this.info, arg).withStyle(color);
		}
	}
	
}
