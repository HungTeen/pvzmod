package com.hungteen.pvz.item.tool;

import java.util.List;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Bundles;

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
		super(new Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(! worldIn.isRemote && stack.getItem() == this) {
			this.onLottery(playerIn);
			stack.shrink(1);
		}
		return ActionResult.resultSuccess(stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.jack_box").applyTextStyle(TextFormatting.RED));
	}
	
	private void onLottery(PlayerEntity player) {
		if(player.getRNG().nextInt(PVZConfig.COMMON_CONFIG.ItemSettings.JackBoxSurpriseChance.get()) == 0){
			EntityUtil.playSound(player, SoundRegister.JACK_SURPRISE.get());
			Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(player.world, player) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
			player.world.createExplosion(player, player.getPosX(), player.getPosY(), player.getPosZ(), 3f, mode);
		} else {
			EntityUtil.playSound(player, SoundRegister.JACK_SAY.get());
			player.addItemStackToInventory(Bundles.getRandomAllBundle());
		}
	}

}
