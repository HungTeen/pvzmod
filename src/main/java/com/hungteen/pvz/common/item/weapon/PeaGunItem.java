package com.hungteen.pvz.common.item.weapon;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.bullet.PeaBullet;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.common.event.events.PeaGunShootEvent;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItemTabs;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.tag.PVZItemTags;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-19 19:42
 **/
public class PeaGunItem extends ProjectileWeaponItem {

    private static final Set<IPAZType> SHOOT_MODES = new HashSet<>(Arrays.asList(
            PVZPlants.PEA_SHOOTER, PVZPlants.SNOW_PEA
    ));
    private static final IPAZType DEFAULT_MODE = PVZPlants.PEA_SHOOTER;
    private static final String SHOOT_TYPE = "ShootType";

    public PeaGunItem() {
        super(new Properties().tab(PVZItemTabs.PVZ_USEFUL).stacksTo(1).durability(1200));
    }

    /**
     * Register Pea Gun Shoot Mode.
     */
    public static void registerShootMode(IPAZType mode) {
        if (!SHOOT_MODES.contains(mode)) {
            SHOOT_MODES.add(mode);
        } else {
            Util.warn("Pea Gun Shoot Mode Register : Duplicate !");
        }
    }

    /**
     * Get All Shoot Modes.
     */
    public static boolean validShootModeItem(IPAZType type){
        return SHOOT_MODES.contains(type);
    }

    @Nonnull
    public static IPAZType getShootMode(ItemStack stack) {
        final String mode = stack.getOrCreateTag().getString(SHOOT_TYPE);
        final Optional<IPAZType> opt = PVZAPI.get().getPAZType(mode);
        return opt.isPresent() ? (SHOOT_MODES.contains(opt.get()) ? opt.get() : DEFAULT_MODE) : DEFAULT_MODE;
    }

    public static void setShootMode(ItemStack stack, IPAZType mode) {
        stack.getOrCreateTag().putString(SHOOT_TYPE, mode.getIdentity());
    }

    /**
     * {@link PVZPlayerEvents#tickPlayer(net.minecraftforge.event.TickEvent.PlayerTickEvent)}
     */
    public static void checkHeadShoot(Player player) {
        final ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);

        if (stack.getItem() instanceof PeaGunItem && !player.getCooldowns().isOnCooldown(stack.getItem())) {
            final ItemStack bullet = player.getProjectile(stack);
            final IPAZType shootMode = getShootMode(stack);
            if (!bullet.isEmpty()) {
                ((PeaGunItem) stack.getItem()).performShoot(player.level, player, stack, shootMode);
                player.getCooldowns().addCooldown(stack.getItem(), Math.max(10, getShootCD(player, stack)));
            } else {
                player.getCooldowns().addCooldown(stack.getItem(), 200);//cool down for shoot fail.
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        final ItemStack bullet = player.getProjectile(stack);
        if (bullet.isEmpty()) {
            if (!level.isClientSide) {
                PlayerUtil.sendTipTo(player, new TranslatableComponent("help.pvz.no_bullet").withStyle(ChatFormatting.RED));
                PlayerUtil.setItemStackCD(player, stack, 10);
            }
            return InteractionResultHolder.fail(stack);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        final int cd = getShootCD(player, stack);
        final IPAZType shootMode = getShootMode(stack);
        if (player instanceof Player && count + 5 < this.getUseDuration(stack) && count % cd == 0) {
            if (!MinecraftForge.EVENT_BUS.post(new PeaGunShootEvent((Player) player, stack, shootMode))) {
                this.performShoot(player.level, (Player) player, stack, shootMode);
            }
        }
    }

    /**
     * @param itemStack : pea gun stack.
     */
    public void performShoot(Level world, Player player, ItemStack itemStack, IPAZType mode) {
        final ItemStack projectile = player.getProjectile(itemStack);

        if (mode == PVZPlants.PEA_SHOOTER) {
            this.shootPea(world, player, mode, projectile, 0.5, 0, 0);
        } else if (mode == PVZPlants.SNOW_PEA) {
            this.shootPea(world, player, mode, projectile, 0.5, 0, 0);
//        } else if (mode == PVZPlants.REPEATER) {
//            this.shootPea(world, player, mode, projectile, 0.5, 0, 0);
//            this.shootPea(world, player, mode, projectile, 0, 0, 0);
//        } else if (mode == PVZPlants.THREE_PEATER) {
//            this.shootPea(world, player, mode, projectile, 0.25, -0.25, -15);
//            this.shootPea(world, player, mode, projectile, 0.25, 0, 0);
//            this.shootPea(world, player, mode, projectile, 0.25, 0.25, 15);
//        } else if (mode == PVZPlants.SPLIT_PEA) {
//            this.shootPea(world, player, mode, projectile, 0.25, 0, 0);
//            this.shootPea(world, player, mode, projectile, 0, 0, 180);
//            this.shootPea(world, player, mode, projectile, -0.5, 0, 180);
//        } else if (mode == PVZPlants.STAR_FRUIT) {
//            final int base = player.getRandom().nextInt(72);
//            for (int i = 0; i < 5; ++i) {
//                this.shootPea(world, player, mode, projectile, 0.25, 0, base + 72 * i);
//            }
//        } else if (mode == OtherPlants.ANGEL_STAR_FRUIT) {
//            for (int i = 0; i < 5; ++i) {
//                this.shootPea(world, player, mode, projectile, 0.25, 0, 72 * i);
//            }
//        } else if (mode == PVZPlants.GATLING_PEA) {
//            this.shootPea(world, player, mode, projectile, 1.5, 0, 0);
//            this.shootPea(world, player, mode, projectile, 1, 0, 0);
//            this.shootPea(world, player, mode, projectile, 0.5, 0, 0);
//            this.shootPea(world, player, mode, projectile, 0, 0, 0);
        } else {
            //Child mod can achieve their own shoot mode by subscribe event.
            return;
        }

        SoundEvent soundEvent = SoundEvents.SNOW_GOLEM_SHOOT;
        if (mode == PVZPlants.SNOW_PEA || projectile.is(PVZItems.SNOW_PEA.get())) {
            soundEvent = PVZSounds.SNOW_SHOOT.get();
            ;
        }
        EntityUtil.playSound(player, soundEvent);

        this.shrinkItemStack(player, projectile, mode);

        if (PlayerUtil.isPlayerSurvival(player)) {
            itemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
        }
    }

    public void shootPea(Level world, Player player, IPAZType mode, ItemStack stack, double forwardOffset, double rightOffset, float angle) {
        final Vec3 vec = player.getLookAngle();
        final double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
        final double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;

        final PeaBullet pea = PVZEntities.PEA_BULLET.get().create(world);
        pea.setPos(player.getX() + deltaX, player.getEyeY() - 0.4, player.getZ() + deltaZ);
        pea.shootPea(vec, 1.3F, angle);

//        pea.setPeaType(this.getPeaType(player));
        pea.setBulletState(this.getPeaState(mode, stack));
        pea.setPower(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem()));

        pea.summonByOwner(player);
        pea.setAttackDamage(1.5F);
        world.addFreshEntity(pea);
    }

    private void shrinkItemStack(Player player, ItemStack projectile, IPAZType mode) {
        //creative player or energetic player don't consume bullet.
        if (!player.hasEffect(PVZEffects.ENERGETIC_EFFECT.get()) && PlayerUtil.isPlayerSurvival(player)) {
            final boolean infinity = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, projectile) > 0;
            if (infinity) {
                if (projectile.is(PVZItems.PEA.get()) ||
                        (projectile.is(PVZItems.SNOW_PEA.get()) && mode == PVZPlants.SNOW_PEA)) {
                    return;
                }
            }
            projectile.shrink(1);
        }
    }

    /**
     * get shoot bullet state.
     */
    private PVZProjectile.BulletStates getPeaState(IPAZType mode, ItemStack bullet) {
        return mode == PVZPlants.SNOW_PEA ? PVZProjectile.BulletStates.ICE :
                bullet.is(PVZItems.SNOW_PEA.get()) ? PVZProjectile.BulletStates.ICE :
                        bullet.is(PVZItems.FLAME_PEA.get()) ? PVZProjectile.BulletStates.FIRE :
                                PVZProjectile.BulletStates.NORMAL;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(new TranslatableComponent("tooltip.pvz.pea_gun").withStyle(ChatFormatting.GREEN));
        components.add(new TranslatableComponent("tooltip.pvz.pea_gun_mode." + getShootMode(stack)).withStyle(ChatFormatting.GREEN));
    }

    @Override
    public void fillItemCategory(CreativeModeTab creativeModeTab, NonNullList<ItemStack> stacks) {
        if (this.allowdedIn(creativeModeTab)) {
            SHOOT_MODES.forEach(mode -> {
                ItemStack stack = new ItemStack(this);
                setShootMode(stack, mode);
                stacks.add(stack);
            });
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.QUICK_CHARGE || enchantment == Enchantments.INFINITY_ARROWS
                || enchantment == Enchantments.POWER_ARROWS || super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(PVZItemTags.PEA_GUN_BULLETS);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 20;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack stack1) {
        return stack1.getItem().equals(PVZItems.APPEASE_ESSENCE.get());
    }

    /**
     * 30 20 15 10
     */
    public static int getShootCD(LivingEntity living, ItemStack stack) {
        if (living.hasEffect(PVZEffects.ENERGETIC_EFFECT.get())) {
            return 2;
        }
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return lvl == 0 ? 30 : 25 - Math.min(3, lvl) * 5;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType == EquipmentSlot.HEAD;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 1000000;
    }
}
