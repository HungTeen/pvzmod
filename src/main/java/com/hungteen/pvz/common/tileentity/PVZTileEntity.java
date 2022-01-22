package com.hungteen.pvz.common.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-01-20 16:32
 **/
public abstract class PVZTileEntity extends TileEntity {

    public PVZTileEntity(TileEntityType<?> type) {
        super(type);
    }

    /**
     * used by container.
     */
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
                (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }

}
