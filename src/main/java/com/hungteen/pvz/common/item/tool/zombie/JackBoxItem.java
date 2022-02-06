package com.hungteen.pvz.common.item.tool.zombie;

import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.datapack.LotteryTypeLoader;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class JackBoxItem extends Item {

	public JackBoxItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_USEFUL));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
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
		return ActionResult.success(stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.jack_box").withStyle(TextFormatting.RED));
	}

}
