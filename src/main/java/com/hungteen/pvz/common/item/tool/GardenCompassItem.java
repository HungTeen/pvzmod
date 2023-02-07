package com.hungteen.pvz.common.item.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;



public class GardenCompassItem extends Item {

    public GardenCompassItem() {
        super((new Item.Properties()).tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(handIn == Hand.MAIN_HAND) {
            if(! worldIn.isClientSide){
                 if (playerIn.getItemBySlot(EquipmentSlotType.MAINHAND).getItem() instanceof GardenCompassItem){
                     setPos(playerIn.getItemBySlot(EquipmentSlotType.MAINHAND), getPointPosition(playerIn));
                 }
            }
            return ActionResult.success(playerIn.getItemInHand(handIn));
        }
        return super.use(worldIn, playerIn, handIn);
    }

    public static BlockPos getPos(ItemStack stack) {
        final boolean valid = stack.getOrCreateTag().getBoolean("pos");
        if (valid) {
            BlockPos p = new BlockPos(stack.getOrCreateTag().getInt("pos_x"),
                    stack.getOrCreateTag().getInt("pos_y"),
                    stack.getOrCreateTag().getInt("pos_z"));
            return p;
        }
        return null;
    }

    public static ItemStack setPos(ItemStack stack, BlockPos pos) {
        stack.getOrCreateTag().putBoolean("pos", pos != null);
        if (pos != null){
            stack.getOrCreateTag().putInt("pos_x", pos.getX());
            stack.getOrCreateTag().putInt("pos_y", pos.getY());
            stack.getOrCreateTag().putInt("pos_z", pos.getZ());
        }
        return stack;
    }

    private BlockPos getPointPosition(LivingEntity player) {
        BlockPos blockpos = new BlockPos(player.position());
        Biome tmp = player.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getOptional(new ResourceLocation("pvz","zen_garden")).get();
        BlockPos blockpos1 = ((ServerWorld)player.level).findNearestBiome(tmp, blockpos, 6400, 8);
        return blockpos1;
    }

    @SubscribeEvent
    public static void disturb(PlayerEvent.PlayerChangedDimensionEvent ev){
        for (ItemStack item : ev.getPlayer().inventory.items){
            if (item.getItem() instanceof GardenCompassItem){
                setPos(item, null);
            }
        }
    }
}
