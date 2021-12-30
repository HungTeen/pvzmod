package com.hungteen.pvz.common.item.display;

import com.hungteen.craid.api.CRaidAPI;
import com.hungteen.craid.api.IRaidComponent;
import com.hungteen.pvz.client.gui.screen.StrangeHelpScreen;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.compat.craid.PVZRaidComponent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import java.util.List;
import java.util.Optional;

public class ChallengeEnvelopeItem extends Item{

	public static final String CHALLENGE_TYPE = "challenge_type";

	public ChallengeEnvelopeItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_ENVELOPE).stacksTo(1));
	}

	public static ResourceLocation getChallengeType(ItemStack stack) {
		return new ResourceLocation(stack.getOrCreateTag().getString(CHALLENGE_TYPE));
	}

	public static Optional<PVZRaidComponent> getRaidComponent(ItemStack stack){
		final IRaidComponent raidComponent = CRaidAPI.get().getRaidTypes().getOrDefault(getChallengeType(stack), null);
		return raidComponent instanceof PVZRaidComponent ? Optional.ofNullable((PVZRaidComponent) raidComponent) : Optional.empty();
	}

	public static ItemStack setChallengeType(ItemStack stack, ResourceLocation res) {
		stack.getOrCreateTag().putString(CHALLENGE_TYPE, res.toString());
		return stack;
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.allowdedIn(group)) {
			CRaidAPI.get().getRaidTypes().forEach((res, com) -> {
				if(com instanceof PVZRaidComponent){
					items.add(setChallengeType(new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get()), res));
				}
			});
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		getRaidComponent(stack).ifPresent(com -> {
			com.getToolTips().forEach(text -> tooltip.add(text));
		});
	}

	@SuppressWarnings("deprecation")
	@OnlyIn(Dist.CLIENT)
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(worldIn.isClientSide) {
			DistExecutor.runWhenOn(Dist.CLIENT, ()->()->{
				Minecraft.getInstance().setScreen(new StrangeHelpScreen());
			});
		}
		return ActionResult.pass(playerIn.getItemInHand(handIn));
	}

}
