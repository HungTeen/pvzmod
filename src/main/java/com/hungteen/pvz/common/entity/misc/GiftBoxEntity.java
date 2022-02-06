package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.common.entity.PVZEntityBase;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-01 18:45
 **/
public class GiftBoxEntity extends PVZEntityBase {

    private ItemStackHandler handler;

    public GiftBoxEntity(EntityType<?> type, World world) {
        super(type, world);
        this.setGlowing(true);
        this.setInvulnerable(true);
    }

    public void setDrops(NonNullList<ItemStack> items){
        handler = new ItemStackHandler(items);
    }

    @Override
    public ActionResultType interactAt(PlayerEntity player, Vector3d vector3d, Hand hand) {
        if(! this.level.isClientSide) {
        	if(this.handler != null){
                for(int i = 0; i < this.handler.getSlots(); ++ i){
                    InventoryHelper.dropItemStack(this.level, this.blockPosition().getX(), this.blockPosition().getY(), this.blockPosition().getZ(),
                        this.handler.getStackInSlot(i));
                }
                EntityUtil.playSound(this, SoundRegister.PRIZE_DROP.get());
        	}
            this.remove();
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if(nbt.contains("reward_items")){
            final CompoundNBT tmp = nbt.getCompound("reward_items");
            this.handler = new ItemStackHandler();
            this.handler.deserializeNBT(tmp);
        }

    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if(this.handler != null){
            nbt.put("reward_items", this.handler.serializeNBT());
        }
    }
}
