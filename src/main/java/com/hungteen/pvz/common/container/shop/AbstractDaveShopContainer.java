package com.hungteen.pvz.common.container.shop;

import com.hungteen.pvz.common.advancement.trigger.PVZTradeTrigger;
import com.hungteen.pvz.common.container.PVZContainer;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractDaveShopContainer extends PVZContainer {

    protected final Player player;
    protected final Optional<AbstractDaveEntity> daveOpt;
    protected Inventory output = new Inventory(1);

    public AbstractDaveShopContainer(ContainerType<? extends Container> type, int id, Player player, int entityId) {
        super(type, id);
        this.player = player;
        final Entity entity = player.level.getEntity(entityId);
        if (entity instanceof AbstractDaveEntity) {
            this.daveOpt = Optional.ofNullable((AbstractDaveEntity) entity);
        } else {
            this.daveOpt = Optional.empty();
        }

        this.addSlot(new Slot(output, 0, 171, 86) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        this.addInventoryAndHotBar(player, 117, 113);
    }

    public void onSell(int pos) {
        final AbstractDaveEntity.GoodType goodType = this.getValidGoods(player).get(pos);
        this.buyGood(goodType);
        this.daveOpt.ifPresent(dave -> {
            dave.sellGoodForTransactions(goodType);
            if (player instanceof ServerPlayer) {
                PVZTradeTrigger.INSTANCE.trigger((ServerPlayer) player, dave, goodType.getGoodPrice());
            }
        });
    }

    public void buyGood(AbstractDaveEntity.GoodType good) {
        if (good.getType().isEnergy()) {
            PlayerUtil.addResource(player, Resources.MAX_ENERGY_NUM, 1);
        } else if (good.getType().isSlot()) {
            PlayerUtil.addResource(player, Resources.SLOT_NUM, 1);
        } else if(good.getType().isMoney()){
        	PlayerUtil.addResource(player, Resources.MONEY, good.getType().getLvl());
        } else {
            this.output.setItem(0, good.getGood().copy());
        }
    }

    public List<AbstractDaveEntity.GoodType> getValidGoods(Player player) {
        if (this.daveOpt.isPresent()) {
            return this.daveOpt.get().getGoodList().stream().filter(goodType -> {
                return goodType.getType().isValid(player) && (goodType.isMustChoose() || goodType.getTransactionLimit() > 0);
            }).sorted(Comparator.comparingInt(AbstractDaveEntity.GoodType::getPriority).reversed()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public Optional<Integer> getLeftRefreshTime() {
        if (this.daveOpt.isPresent()) {
            return Optional.ofNullable(this.daveOpt.get().getLeftRefreshTime());
        }
        return Optional.empty();
    }

    public boolean canClickBuyButton() {
        return this.output.getItem(0).isEmpty();
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 28) {
                if (!moveItemStackTo(itemstack1, 28, this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(itemstack1, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.daveOpt.ifPresent(dave -> dave.setCustomer(null));
        this.clearContainer(player, player.level, this.output);
    }

}