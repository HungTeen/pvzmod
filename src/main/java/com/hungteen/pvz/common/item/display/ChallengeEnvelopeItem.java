package com.hungteen.pvz.common.item.display;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.client.gui.screen.ChallengeEnvelopeScreen;
import com.hungteen.pvz.client.gui.screen.ChallengeInfoScreen;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.utils.PlayerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class ChallengeEnvelopeItem extends Item {

    private static final String CHALLENGE_TYPE = "challenge_type";

    public ChallengeEnvelopeItem() {
        super(new Properties().tab(PVZItemGroups.PVZ_ENVELOPE).stacksTo(1));
    }

    public static ResourceLocation getChallengeType(ItemStack stack) {
        return new ResourceLocation(stack.getOrCreateTag().getString(CHALLENGE_TYPE));
    }

    public static Optional<IChallengeComponent> getRaidComponent(ItemStack stack) {
        return Optional.ofNullable(PVZAPI.get().getRaidTypes().getOrDefault(getChallengeType(stack), null));
    }

    public static ItemStack setChallengeType(ItemStack stack, ResourceLocation res) {
        stack.getOrCreateTag().putString(CHALLENGE_TYPE, res.toString());
        return stack;
    }
    
    public static ItemStack getChallengeEnvelope(ResourceLocation res) {
    	final ItemStack stack = new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get());
        return setChallengeType(stack, res);
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            PVZAPI.get().getRaidTypes().forEach((res, com) -> {
                items.add(setChallengeType(new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get()), res));
            });
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.pvz.challenge_envelope1").withStyle(TextFormatting.GREEN));
        tooltip.add(new TranslationTextComponent("tooltip.pvz.challenge_envelope2").withStyle(TextFormatting.GREEN));
        getRaidComponent(stack).ifPresent(com -> {
            tooltip.add(new StringTextComponent(getChallengeType(stack).toString()).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
            tooltip.add(com.getChallengeName().withStyle(TextFormatting.YELLOW).withStyle(TextFormatting.BOLD));
        });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getItemInHand(handIn).getItem() instanceof ChallengeEnvelopeItem) {
            if (worldIn.isClientSide) {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    ChallengeEnvelopeItem.getRaidComponent(playerIn.getItemInHand(handIn)).ifPresent(challengeComponent -> {
                        if(handIn == Hand.MAIN_HAND){
                            Minecraft.getInstance().setScreen(new ChallengeEnvelopeScreen(challengeComponent));
                        } else{
                            Minecraft.getInstance().setScreen(new ChallengeInfoScreen(challengeComponent));
                        }
                    });
                });
            }
            return ActionResult.success(playerIn.getItemInHand(handIn));
        }
        return ActionResult.fail(playerIn.getItemInHand(handIn));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (! context.getLevel().isClientSide && context.getItemInHand().getItem() instanceof ChallengeEnvelopeItem && context.getClickedFace() == Direction.UP) {
            final ResourceLocation res = getChallengeType(context.getItemInHand());
            final IChallengeComponent challengeComponent = ChallengeManager.getChallengeByResource(res);
            if(challengeComponent == null){
                PlayerUtil.sendMsgTo(context.getPlayer(), new TranslationTextComponent("help.pvz.no_challenge").withStyle(TextFormatting.RED));
            } else{
                if(ChallengeManager.hasChallengeNearby((ServerWorld) context.getLevel(), context.getClickedPos().above())){
                    PlayerUtil.sendMsgTo(context.getPlayer(), new TranslationTextComponent("help.pvz.full_challenge").withStyle(TextFormatting.RED));
                } else{
                    if(ChallengeManager.createChallenge((ServerWorld) context.getLevel(), getChallengeType(context.getItemInHand()), context.getClickedPos().above())) {
                        if (PlayerUtil.isPlayerSurvival(context.getPlayer())) {
                            context.getItemInHand().shrink(1);
                        }
                    } else{
                        PlayerUtil.sendMsgTo(context.getPlayer(), new TranslationTextComponent("help.pvz.wrong_challenge").withStyle(TextFormatting.RED));
                    }
                }
            }
        }
        return ActionResultType.CONSUME;
    }

}
