package com.hungteen.pvz.common.item.tool.zombie;

import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.datapack.LotteryTypeLoader;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.level.Level;

public class JackBoxItem extends Item {

	public JackBoxItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_USEFUL));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if(! worldIn.isClientSide) {
			if(playerIn.getRandom().nextInt(PVZConfig.COMMON_CONFIG.ItemSettings.JackBoxSurpriseChance.get()) == 0){
				PlayerUtil.playClientSound(playerIn, SoundRegister.JACK_SURPRISE.get());
				Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(playerIn.level, playerIn) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
				playerIn.level.explode(playerIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), 3f, mode);
			} else {
				PlayerUtil.playClientSound(playerIn, SoundRegister.JACK_MUSIC.get());
				LotteryTypeLoader.getLotteryType(SlotMachineTileEntity.LotteryType.ALL_PLANTS).ifPresent(lotteryType -> {
					lotteryType.getSlotType(playerIn.getRandom()).getStack().ifPresent(s -> {
						playerIn.addItem(s.copy());
					});
				});
			}
			stack.shrink(1);
		}
		return InteractionResultHolder.success(stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.pvz.jack_box").withStyle(ChatFormatting.RED));
	}

}
