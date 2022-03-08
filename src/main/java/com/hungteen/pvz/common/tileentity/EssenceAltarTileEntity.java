package com.hungteen.pvz.common.tileentity;

import com.hungteen.pvz.common.container.EssenceAltarContainer;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-20 16:06
 **/
public class EssenceAltarTileEntity extends PVZTileEntity implements INamedContainerProvider, ITickableTileEntity {

    public final ItemStackHandler handler = new ItemStackHandler(4);
    public int tick = 0;

    public EssenceAltarTileEntity() {
        super(TileEntityRegister.ESSENCE_ALTAR.get());
    }
    
    @Override
   	public void tick() {
    	++ tick;
   	}

    @Override
    public void load(BlockState state, CompoundTag compound) {
        super.load(state, compound);
        if (compound.contains("essence_altar_slots")) {
            this.handler.deserializeNBT(compound.getCompound("essence_altar_slots"));
        }
        if(compound.contains("tick")) {
        	this.tick = compound.getInt("tick");
        }
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        compound.put("essence_altar_slots", this.handler.serializeNBT());
        compound.putInt("tick", this.tick);
        return super.save(compound);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.pvz.essence_altar");
    }

    @Override
    public Container createMenu(int id, PlayerInventory inv, Player player) {
        return new EssenceAltarContainer(id, player, this.worldPosition);
    }

}
