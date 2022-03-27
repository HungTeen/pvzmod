package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.api.types.ICDType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.PVZSounds;
import com.hungteen.pvz.common.impl.type.CDTypes;
import com.hungteen.pvz.common.item.PVZItemTabs;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:27
 *
 * the card item that can summon entity such as plants and zombies.
 **/
public class SummonCardItem extends Item{

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

    public static int getCardSunCost(ItemStack stack) {
        if(stack.getItem() instanceof PlantCardItem) {
            return ((PlantCardItem) stack.getItem()).getBasisSunCost(stack);
        }
        return 0;
    }

    public static int getCardCoolDown(ItemStack stack) {
        ICDType cdType = CDTypes.DEFAULT;
        if(stack.getItem() instanceof SummonCardItem) {
           cdType = ((SummonCardItem) stack.getItem());

        }
        return CDTypes.DEFAULT.getCD(1);
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
        tooltip.add(new TranslatableComponent("tooltip.pvz.card_sun_cost", getCardSunCost(stack)).withStyle(ChatFormatting.YELLOW));
        tooltip.add(new TranslatableComponent("tooltip.pvz.card_cd", StringUtil.toSecond(getCardCoolDown(stack))).withStyle(ChatFormatting.AQUA));
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
        //Upgrade Card is Epic !
        if(itemStack.getItem() instanceof SummonCardItem && ((SummonCardItem) itemStack.getItem()).type.getUpgradeFrom().isPresent()){
            return Rarity.EPIC;
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
        return 20;//0 ~ 45.
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
            PlayerUtil.setItemStackCD(player, stack, 10);
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
