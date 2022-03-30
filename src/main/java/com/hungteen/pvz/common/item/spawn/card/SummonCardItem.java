package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.PVZSounds;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.enchantment.EnchantmentHandler;
import com.hungteen.pvz.common.enchantment.card.ImmediateCDEnchantment;
import com.hungteen.pvz.common.enchantment.card.SunReductionEnchantment;
import com.hungteen.pvz.common.item.PVZItemTabs;
import com.hungteen.pvz.common.item.PVZRarities;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:27
 *
 * the card item that can summon entity such as plants and zombies.
 **/
public class SummonCardItem extends Item{

    protected static final int COMMON_CD = 10;
    public final IPAZType type;
    public final boolean isEnjoyCard;

    public SummonCardItem(IPAZType type, boolean isEnjoyCard) {
        this(new Properties().tab(PVZItemTabs.PVZ_PLANT_CARD).stacksTo(isEnjoyCard ? 32 : 1), type, isEnjoyCard);
    }

    public SummonCardItem(Properties properties, IPAZType type, boolean isEnjoyCard) {
        super(properties);
        this.type = type;
        this.isEnjoyCard = isEnjoyCard;
    }

    /**
     * get basic sun cost without consider other condition.
     */
    public static int getBaseCost(ItemStack stack) {
        //TODO Some kinds of skill will add extra sun cost.
        return (stack.getItem() instanceof SummonCardItem) ? ((SummonCardItem) stack.getItem()).type.getSunCost() : 0;
    }

    /**
     * the real sun cost when use card.
     */
    public static int getCardCost(Player player, ItemStack stack) {
        final int cost = getBaseCost(stack);
        return SunReductionEnchantment.getReductionCost(stack, cost);
    }

    /**
     * get basic cool down without consider other condition.
     */
    public static int getBaseCD(ItemStack stack) {
        return (stack.getItem() instanceof SummonCardItem) ? ((SummonCardItem) stack.getItem()).type.getCoolDown() : 0;
    }

    /**
     * the real cool down when use card.
     */
    public static int getCardCD(Player player, ItemStack stack) {
        //immediate enchantment effect.
        if(ImmediateCDEnchantment.canImmediateCD(stack, player.getRandom())){
            return COMMON_CD;
        } else{
            //handle quick charge enchantment.
            int cd = EnchantmentHandler.getQuickChargeCD(stack, getBaseCD(stack));

            //handle excite effect.
            if (player.hasEffect(PVZEffects.EXCITE_EFFECT.get())) {
                final int lvl = player.getEffect(PVZEffects.EXCITE_EFFECT.get()).getAmplifier();
                final float mult = Math.max(0, 0.9f - 0.1f * lvl);
                cd = (int) Math.floor(cd * mult);
            }
            return cd;
        }
    }

    protected static ItemStack getHeldStack(ItemStack stack) {
        return ImitaterCardItem.getDoubleStack(stack).getFirst();
    }

    protected static ItemStack getSummonStack(ItemStack stack) {
        return ImitaterCardItem.getDoubleStack(stack).getSecond();
    }

//    /**
//     * {@link #appendHoverText(ItemStack, World, List, ITooltipFlag)}
//     */
//    public static int getCardRequiredLevel(ItemStack stack) {
//        if(stack.getItem() instanceof PlantCardItem) {
//            return ((PlantCardItem) stack.getItem()).plantType.getRequiredLevel();
//        }
//        return 100;
//    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
//        tooltip.add(new TranslatableComponent("tooltip.pvz.card_sun_cost", getBaseCost(stack)).withStyle(ChatFormatting.YELLOW));
//        tooltip.add(new TranslatableComponent("tooltip.pvz.card_cd", StringUtil.toSecond(getCardCD(PVZMod.PROXY.getPlayer(), stack))).withStyle(ChatFormatting.AQUA));
//        PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(m -> {
//            //this paz type is locked.
//            if (m.isPAZLocked(this.type) && ! this.isEnjoyCard) {
//                tooltip.add(new TranslationTextComponent("tooltip.pvz.card_required_level", getCardRequiredLevel(stack)).withStyle(TextFormatting.RED));
//            }
//        });
    }

//    public static void appendSkillToolTips(ItemStack stack, List<ITextComponent> tooltip){
//        if(stack.getItem() instanceof SummonCardItem){
//            final IPAZType type = ((SummonCardItem) stack.getItem()).type;
//            type.getSkills().forEach(skill -> {
//                final int lvl = SkillTypes.getSkillLevel(stack, skill);
//                if(lvl > 0){
//                    tooltip.add(skill.getText().append(StringUtil.getRomanString(lvl)).withStyle(TextFormatting.DARK_PURPLE));
//                }
//            });
//        }
//    }

    @Override
    public Rarity getRarity(ItemStack itemStack) {
        //Upgrade Card is Epic ! Enjoy Card is Mega !
        if(itemStack.getItem() instanceof SummonCardItem){
            return ((SummonCardItem) itemStack.getItem()).isEnjoyCard ? PVZRarities.MEGA : ((SummonCardItem) itemStack.getItem()).type.getRank().getRarity();
        }
        return super.getRarity(itemStack);
    }

    /**
     * enjoy cards have no enchant.
     */
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return this.getItemStackLimit(stack) == 1 && ! this.isEnjoyCard;
    }

    /**
     * enjoy cards have no enchant.
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return ! this.isEnjoyCard && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 20;
    }

    @Override
    public int getEnchantmentValue() {
        return this.type.getRank().getEnchantPoint();
    }

    public void notifyPlayerAndCD(Player player, ItemStack stack, PlacementErrors error) {
        this.notifyPlayerAndCD(player, stack, error, 0);
    }

    /**
     * send helpful info.
     */
    public void notifyPlayerAndCD(Player player, ItemStack stack, PlacementErrors error, int arg) {
        if(! player.level.isClientSide) {
            PlayerUtil.sendTipTo(player, error.getTextByArg(arg, ChatFormatting.RED));
            PlayerUtil.setItemStackCD(player, stack, COMMON_CD);
            PlayerUtil.playClientSound(player, PVZSounds.NO.get());
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
