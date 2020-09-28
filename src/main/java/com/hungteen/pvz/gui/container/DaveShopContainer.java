package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.gui.DaveShopScreen.Goods;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class DaveShopContainer extends Container{

	private final PlayerEntity player;
	private int tradeType;
	private Inventory output = new Inventory(1);
	
	public DaveShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.DAVE_SHOP.get(), id);
		this.player = player;
		
		this.addSlot(new Slot(output, 0, 171, 86));
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				this.addSlot(new Slot(player.inventory,j+i*9+9,117+18*j,113+18*i));
			}
		}
		
		for(int i=0;i<9;i++) {
			this.addSlot(new Slot(player.inventory,i,117+18*i,171));
		}
	}
	
	public void buyGood(Goods good) {
		if(good == Goods.ENERGY) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getEnergyCost(stats.getPlayerStats(Resources.ENERGY_NUM)));
				stats.addPlayerStats(Resources.MAX_ENERGY_NUM, 1);
			});
		}else if(good == Goods.SLOT) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getSlotCost(stats.getPlayerStats(Resources.SLOT_NUM)));
				stats.addPlayerStats(Resources.SLOT_NUM, 18);
			});
		}else{
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getGoodCost(good));
			});
		}
	}
	
	public void setTradeType(int type) {
		this.tradeType = type;
	}
	
	public boolean canClickBuyButton() {
		return this.output.getStackInSlot(0).isEmpty();
	}
	
//	@Override
//	public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
//		return false;
//	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		return super.transferStackInSlot(playerIn, index);
	}

}
