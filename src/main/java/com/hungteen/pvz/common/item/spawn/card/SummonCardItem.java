package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.PVZRarity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class SummonCardItem extends Item{

	public final IPAZType type;
	public final boolean isEnjoyCard;
	
	public SummonCardItem(IPAZType type, boolean isEnjoyCard) {
		this(new Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(isEnjoyCard ? 16 : 1), type, isEnjoyCard);
	}
	
	public SummonCardItem(Properties properties, IPAZType type, boolean isEnjoyCard) {
		super(properties);
		this.type = type;
		this.isEnjoyCard = isEnjoyCard;
	}
	 
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return ! this.isEnjoyCard && super.canApplyAtEnchantingTable(stack, enchantment);//enjoy card have no enchant
	}
	
	/**
	 * get base sun cost.
	 * {@link #appendHoverText(ItemStack, Level, List, TooltipFlag)}
	 */
	public static int getCardSunCost(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).getBasisSunCost(stack);
		}
		return 1;
	}

	/**
	 * get base card cd.
	 * {@link #appendHoverText(ItemStack, Level, List, TooltipFlag)}
	 */
	public static ICoolDown getCardCoolDown(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).getBasisCoolDown(stack);
		}
		return CoolDowns.DEFAULT;
	}

	/**
	 * {@link #appendHoverText(ItemStack, Level, List, TooltipFlag)}
	 */
	public static int getCardRequiredLevel(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).plantType.getRequiredLevel();
		}
		return 100;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.pvz.card_sun_cost", getCardSunCost(stack)).withStyle(ChatFormatting.YELLOW));
		tooltip.add(new TranslatableComponent("tooltip.pvz.card_cd", new TranslatableComponent(getCardCoolDown(stack).getTranslateKey()).getString()).withStyle(ChatFormatting.AQUA));
		PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(m -> {
			//this paz type is locked.
			if (m.isPAZLocked(this.type)) {
				tooltip.add(new TranslatableComponent("tooltip.pvz.card_required_level", getCardRequiredLevel(stack)).withStyle(ChatFormatting.RED));
			}
		});
	}

	public static void appendSkillToolTips(ItemStack stack, List<Component> tooltip){
		if(stack.getItem() instanceof SummonCardItem){
			final IPAZType type = ((SummonCardItem) stack.getItem()).type;
			type.getSkills().forEach(skill -> {
				final int lvl = SkillTypes.getSkillLevel(stack, skill);
				if(lvl > 0){
					tooltip.add(skill.getText().append(StringUtil.getRomanString(lvl)).withStyle(ChatFormatting.DARK_PURPLE));
				}
			});
		}
	}

	@Override
	public Rarity getRarity(ItemStack itemStack) {
		if(itemStack.getItem() instanceof SummonCardItem){
	    	return PVZRarity.getRarityByRank(((SummonCardItem) itemStack.getItem()).type.getRank());
		}
		return super.getRarity(itemStack);
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

	public void notifyPlayerAndCD(Player player, ItemStack stack, PlacementErrors error) {
		this.notifyPlayerAndCD(player, stack, error, 0);
	}

	/**
	 * send helpful info.
	 */
	public void notifyPlayerAndCD(Player player, ItemStack stack, PlacementErrors error, int arg) {
		if(! player.level.isClientSide) {
			PlayerUtil.sendMsgTo(player, error.getTextByArg(arg, ChatFormatting.RED));
			PlayerUtil.setItemStackCD(player, stack, 10);
			PlayerUtil.playClientSound(player, SoundRegister.NO.get());
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

		public MutableComponent getTextByArg(int arg, ChatFormatting color){
			return new TranslatableComponent("help.pvz."+ this.info, arg).withStyle(color);
		}
	}
	
}
