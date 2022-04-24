package com.hungteen.pvz.common.blockentity;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.PVZBlocks;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 10:48
 *
 * Step 1. register block entity type. <br>
 * Step 2. if the certain type need special render, you need to bind it .
 **/
public class PVZBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PVZMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<EssenceAltarBlockEntity>> ESSENCE_ALTAR = TILE_ENTITY_TYPES.register("essence_altar", () -> {
        return BlockEntityType.Builder.of(EssenceAltarBlockEntity::new, PVZBlocks.ESSENCE_ALTAR.get()).build(null);
    });

}
