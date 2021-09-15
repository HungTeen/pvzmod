package com.hungteen.pvz.common.item.tool.zombie;

import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.impl.Bundles;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

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
		super(new Properties().tab(PVZItemGroups.PVZ_MISC).stacksTo(1));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if(! worldIn.isClientSide && stack.getItem() == this) {
			this.onLottery(playerIn);
			stack.shrink(1);
		}
		return ActionResult.success(stack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.jack_box").withStyle(TextFormatting.RED));
	}
	
	private void onLottery(PlayerEntity player) {
		if(player.getRandom().nextInt(PVZConfig.COMMON_CONFIG.ItemSettings.JackBoxSurpriseChance.get()) == 0){
			EntityUtil.playSound(player, SoundRegister.JACK_SURPRISE.get());
			Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(player.level, player) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
			player.level.explode(player, player.getX(), player.getY(), player.getZ(), 3f, mode);
		} else {
			EntityUtil.playSound(player, SoundRegister.JACK_SAY.get());
			player.addItem(Bundles.RANDOM_ALL.getEnjoyCard(player.getRandom()));
		}
	}

}
