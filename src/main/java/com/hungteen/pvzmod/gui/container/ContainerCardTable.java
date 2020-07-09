package com.hungteen.pvzmod.gui.container;

import java.util.Random;

import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.CraftUtil;
import com.hungteen.pvzmod.util.ItemUtil;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.enums.Ranks;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCardTable extends Container{

	private IInventory craftSlots;
	private IInventory inputSlots;
	private IInventory outputSlot;
	private BlockPos pos;
	private World world;
	private EntityPlayer player;
	private Plants resultPlant;
	private int prop;
	private int bonusProp;
	private boolean canShrink;
	
	public ContainerCardTable(World world,InventoryPlayer player,BlockPos pos) {
		this.world=world;
		this.pos=pos;
		this.player=player.player;
		this.resultPlant=null;
		this.prop=0;
		this.bonusProp=0;
		this.canShrink=true;
		this.craftSlots=new InventoryCrafting(this,3,3);
		this.inputSlots=new InventoryBasic("input",true,2) {
			@Override
			public void markDirty() {
				super.markDirty();
				ContainerCardTable.this.onCraftMatrixChanged(this);
			}
		};
		this.outputSlot=new InventoryCraftResult();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				this.addSlotToContainer(new Slot(craftSlots,i*3+j,31+26*i,54+26*j));
			}
		}//0-8
		this.addSlotToContainer(new Slot(inputSlots, 0, 146,17) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem()==Item.getItemFromBlock(Blocks.LAPIS_BLOCK);
			}
			
			@Override
			public int getSlotStackLimit() {
				return 1;
			}
		});
		this.addSlotToContainer(new Slot(inputSlots, 1, 146,143){
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem()==Items.TOTEM_OF_UNDYING;
			}
			
			@Override
			public int getSlotStackLimit() {
				return 1;
			}
		});
        this.addSlotToContainer(new Slot(this.outputSlot,0,146,80) {
        	@Override
        	public boolean isItemValid(ItemStack stack) {
        		return false;
        	}
        });
        
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				this.addSlotToContainer(new Slot(player,j+i*9+9,48+18*j,174+18*i));
			}
		}
		
		for(int i=0;i<9;i++) {
			this.addSlotToContainer(new Slot(player,i,48+18*i,232));
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		super.onCraftMatrixChanged(inventory);
//		System.out.println("hhh");
//		if(this.world.isRemote) return ;
		if(inventory==this.craftSlots) {
			this.checkPlant();
			if(this.resultPlant!=null) {
				Ranks rank=PlantsUtil.getPlantRank(resultPlant);
//				System.out.println(rank);
				if(rank==null) {
					System.out.println("error plant rank!");
					return ;
				}
				this.prop=80-10*rank.ordinal();
			}else {
				this.prop=0;
			}
		}
		else if(inventory==this.inputSlots) {
			this.checkInput();
		}
	}
	
	/**
	 * 检查三个主位的东西放得对不对
	 */
	private void checkPlant()
	{
		ItemStack midStack=this.craftSlots.getStackInSlot(4);
//		System.out.println(midStack.getItem());
		Plants[] plants=CraftUtil.getMatchPlantByMidItem(midStack.getItem());
		if(plants==null) {//没有可匹配的配方
			return;
		}
//		System.out.println("have recipes!");
		boolean hasResult=false;
		for(Plants plant:plants) {
			boolean canMatch=true;
			ItemStack[] itemstacks=CraftUtil.getCardRecipesByPlant(plant);
//			System.out.println(plant);
			if(itemstacks==null||itemstacks.length!=9) {
				System.out.println("recipes get error!");
				continue;
			}
//			for(int i=0;i<9;i++) {
//				System.out.println(items[i].getRegistryName());
//			}
		    for(int i=0;i<9;i++) {
				ItemStack stack=this.craftSlots.getStackInSlot(i);
				System.out.println(i);
//				System.out.println(stack.getItem().getRegistryName()+" "+items[i].getRegistryName());
				if(!stack.isItemEqual(itemstacks[i])) {//匹配不上
				    canMatch=false;
				   	break;
				}
		    }
		    if(canMatch) {
		    	this.resultPlant=plant;
		    	hasResult=true;
//		    	System.out.println("Successful match!");
		    	break;
		    }
		}
		if(!hasResult) {
			this.resultPlant=null;
		}
	}
	
	private void checkInput()
	{
		ItemStack luckStack=this.inputSlots.getStackInSlot(0);
		ItemStack protectStack=this.inputSlots.getStackInSlot(1);
		this.bonusProp=luckStack.isEmpty()?0:20;
		this.canShrink=protectStack.isEmpty();
	}
	
	public void onButtonClicked()
	{
//		System.out.println("craft!");
		Random rand=new Random();
		if(this.resultPlant==null) {
			System.out.println("error plant!");
			return ;
		}
		if(rand.nextInt(100)<this.getCraftProp()) {//合成成功
			ItemStack result=new ItemStack(PlantsUtil.getItemFromPlant(this.resultPlant),1);
			this.outputSlot.setInventorySlotContents(0, result);
			this.clearCraftMatrix();
		}
		else {//合成失败
			if(this.canShrink) {
				this.clearCraftMatrix();
		    }
		}
		//宝石不管失败与否都消失
		if(!this.inputSlots.getStackInSlot(0).isEmpty()) this.inputSlots.getStackInSlot(0).shrink(1);
		if(!this.inputSlots.getStackInSlot(1).isEmpty()) this.inputSlots.getStackInSlot(1).shrink(1);
		this.inputSlots.markDirty();
	}
	
	private void clearCraftMatrix()
	{
		for(int i=0;i<9;i++) {
			this.craftSlots.getStackInSlot(i).shrink(1);
		}
		this.craftSlots.markDirty();
	}
	
//	@Override
//	public void addListener(IContainerListener listener) {
//		super.addListener(listener);
//		listener.sendWindowProperty(this, 0, this.resultPlant==null?-1:resultPlant.ordinal());
//		listener.sendWindowProperty(this, 1, this.prop);
//		listener.sendWindowProperty(this, 2, this.bonusProp);
//		listener.sendWindowProperty(this, 3, this.canShrink?1:0);
//	}
//	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void updateProgressBar(int id, int data) {
//		if(id==0) {
//			if(data==-1) this.resultPlant=null;
//			else this.resultPlant=Plants.values()[data];
//		}
//		else if(id==1) {
//			this.prop=data;
//		}
//		else if(id==2) {
//			this.bonusProp=data;
//		}
//		else if(id==3) {
//			this.canShrink=(data==1);
//		}
//	}
	
	public boolean getCanButtonPress()//配方有效并且 输出槽为空时 可合成
	{
		return this.resultPlant!=null&&this.outputSlot.getStackInSlot(0).isEmpty();
	}
	
	public int getCraftProp()
	{
		return this.prop+this.bonusProp;
	}
	
	public Plants getCraftPlant()
	{
		return this.resultPlant;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		Slot slot=inventorySlots.get(index);
		if(slot==null||!slot.getHasStack()) {
			return ItemStack.EMPTY;
		}
		ItemStack newStack =slot.getStack();
		ItemStack oldStack =newStack.copy();
		boolean isMerged=false;
		if(index>=0&&index<=11) {
			if(!mergeItemStack(newStack, 12, 48, true)) {
				return ItemStack.EMPTY;
			}
		}
		else if(index>=12&&index<=38) {
//			if(mergeItemStack(newStack,0,12,false)) {
//				isMerged=true;
//			}else 
			if(mergeItemStack(newStack, 39, 48, false)) {
				isMerged=true;
			}else return ItemStack.EMPTY;
		}else if(index>=39&&index<=47) {
//			if(mergeItemStack(newStack,0,12,false)) {
//				isMerged=true;
//			}else 
			if(mergeItemStack(newStack, 12, 39, false)) {
				isMerged=true;
			}else return ItemStack.EMPTY;
		}
		if(!isMerged) {
			return ItemStack.EMPTY;
		}
		if(newStack.isEmpty()) {
			slot.putStack(ItemStack.EMPTY);
		}
		else {
			slot.onSlotChanged();
		}
		return oldStack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            this.clearContainer(playerIn, this.world, this.inputSlots);
            this.clearContainer(playerIn, this.world, this.craftSlots);
            this.clearContainer(playerIn, this.world, this.outputSlot);
        }
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		if (this.world.getBlockState(this.pos).getBlock() != BlockRegister.CARD_TABLE)
        {
            return false;
        }
        else
        {
            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
	}

}
