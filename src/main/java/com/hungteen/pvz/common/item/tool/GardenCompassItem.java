package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static com.hungteen.pvz.common.world.biome.BiomeRegister.BIOMES;
import static com.hungteen.pvz.common.world.biome.BiomeRegister.ZEN_GARDEN;
import static net.minecraft.command.impl.LocateBiomeCommand.ERROR_INVALID_BIOME;

public class GardenCompassItem extends Item {
    public BlockPos pos;

    public GardenCompassItem() {
        super((new Item.Properties()).tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
        pos = null;
    }
    private BlockPos getPointPosition(LivingEntity player) {
        BlockPos blockpos = new BlockPos(player.position());
        Biome tmp = player.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getOptional(new ResourceLocation("pvz","zen_garden")).get();
        BlockPos blockpos1 = ((ServerWorld)player.level).findNearestBiome(tmp, blockpos, 6400, 8);
        return blockpos1;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(handIn == Hand.MAIN_HAND) {
            if(! worldIn.isClientSide){
                pos = getPointPosition(playerIn);
            }
            return ActionResult.success(playerIn.getItemInHand(handIn));
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @SubscribeEvent
    public static void disturb(PlayerEvent.PlayerChangedDimensionEvent ev){
        for (ItemStack item : ev.getPlayer().inventory.items){
            if (item.getItem() instanceof GardenCompassItem){
                ((GardenCompassItem)item.getItem()).pos = null;
            }
        }
    }
}
