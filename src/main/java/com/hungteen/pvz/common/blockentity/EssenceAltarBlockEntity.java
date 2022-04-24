package com.hungteen.pvz.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.core.config.Order;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 10:48
 **/
public class EssenceAltarBlockEntity extends PVZBlockEntity implements Nameable {

    private static final Random RANDOM = new Random();
    public int tick = 0;
    private Component name;

    public EssenceAltarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(PVZBlockEntities.ESSENCE_ALTAR.get(), blockPos, blockState);
    }

    public static void altarAnimationTick(Level level, BlockPos blockPos, BlockState blockState, EssenceAltarBlockEntity blockEntity){
        ++ blockEntity.tick;
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.hasCustomName()) {
            tag.putString("CustomName", Component.Serializer.toJson(this.name));
        }

    }

    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
        }

    }

    @Override
    public Component getName() {
        return new TranslatableComponent("block.pvz.essence_altar");
    }

    public void setCustomName(@javax.annotation.Nullable Component component) {
        this.name = component;
    }

    @javax.annotation.Nullable
    public Component getCustomName() {
        return this.name;
    }

}
