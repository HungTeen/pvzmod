package com.hungteen.pvzmod.gui.container;

import com.hungteen.pvzmod.entities.npcs.EntityPanney;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.ShopUtil;
import com.hungteen.pvzmod.util.ShopUtil.Goods;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerPanneyShop extends Container{

	private IInventory outputSlot;
	private EntityPlayer costomer;
	public int nowPage=0;
	public int pageNum;
	public final Goods[] goods;
	
	
	public ContainerPanneyShop(World world,EntityPlayer player) {
		
		this.costomer=player;
		this.goods=ShopUtil.getShopGoodsByPlayer(player);
		this.pageNum=this.goods.length;
//		for(int i=0;i<this.goods.length;i++) {
//			System.out.println(this.goods[i]);
//		}
		outputSlot=new InventoryCraftResult();
		this.addSlotToContainer(new Slot(outputSlot,0,87,118) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				this.addSlotToContainer(new Slot(player.inventory,j+i*9+9,15+18*j,174+18*i));
			}
		}
		
		for(int i=0;i<9;i++) {
			this.addSlotToContainer(new Slot(player.inventory,i,15+18*i,232));
		}
	}
	
	public void onButtonClicked(int type)
	{
		if(type==0) {
			this.nowPage--;
		}else if(type==1) {
			this.nowPage++;
//			System.out.println(this.nowPage);
		}else if(type==2) {
			this.buyGood();
		}
	}
	
	public void buyGood()
	{
		Goods good=this.goods[this.nowPage];
		int cost=ShopUtil.getGoodPrice(good);
		PlayerUtil.addPlayerMoney(this.costomer, -cost);
		this.outputSlot.setInventorySlotContents(0, new ItemStack(ShopUtil.getGoodByEnumName(good)));
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(IContainerListener listener:listeners) {
		    listener.sendWindowProperty(this, 0, this.nowPage);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if(id==0) {
			this.nowPage=data;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		if(playerIn.getRidingEntity() instanceof EntityPanney) {
			return true;
		}
		return false;
	}
}
