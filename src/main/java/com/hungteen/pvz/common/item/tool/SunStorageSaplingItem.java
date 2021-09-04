package com.hungteen.pvz.common.item.tool;

import java.util.List;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toclient.PlaySoundPacket;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class SunStorageSaplingItem extends Item {

	public static final String STORAGE_STRING = "sun_storage_amount";
	public final int MAX_STORAGE_NUM;

	public SunStorageSaplingItem(int num) {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_MISC).stacksTo(1));
		this.MAX_STORAGE_NUM = num;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.startUsingItem(handIn);
	    return ActionResult.success(playerIn.getItemInHand(handIn));
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if(entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLiving;
			if(! worldIn.isClientSide) {
				player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
					int sunNum = l.getPlayerData().getResource(Resources.SUN_NUM);
					int lvl = l.getPlayerData().getResource(Resources.TREE_LVL);
					int maxNum = PlayerUtil.getPlayerMaxSunNum(lvl);
					int amount = getStorageSunAmount(stack);
					if(sunNum == maxNum || amount == 0) return ;
					if(sunNum + amount > maxNum) {
						amount -= maxNum - sunNum;
						sunNum = maxNum;
					} else {
						sunNum += amount;
						amount = 0;
					}
					setStorageSunAmount(stack, amount);
					l.getPlayerData().setResource(Resources.SUN_NUM, sunNum);
					PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
						return (ServerPlayerEntity) player;
					}), new PlaySoundPacket(0));
				});
			}
		}
		return stack;
	}
	
	public static boolean isSunStorageFull(ItemStack stack) {
		if(stack.getItem() instanceof SunStorageSaplingItem) {
			SunStorageSaplingItem item = (SunStorageSaplingItem) stack.getItem();
			int amount = getStorageSunAmount(stack);
			return (item.MAX_STORAGE_NUM <= amount);
		}
		return true;
	}

	public static int getStorageSunAmount(ItemStack stack) {
		int amount = stack.getOrCreateTag().getInt(STORAGE_STRING);
		return amount;
	}
	
	public static ItemStack setStorageSunAmount(ItemStack stack, int amount) {
		stack.getOrCreateTag().putInt(STORAGE_STRING, amount);
		return stack;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 50;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return (1 - getStorageSunAmount(stack) * 1.0F / this.MAX_STORAGE_NUM);
	}

	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_storage_amount").append(":" + getStorageSunAmount(stack)).withStyle(TextFormatting.YELLOW));
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.allowdedIn(group)) {
			items.add(new ItemStack(this));
			items.add(setStorageSunAmount(new ItemStack(this), this.MAX_STORAGE_NUM));
		}
	}

}
