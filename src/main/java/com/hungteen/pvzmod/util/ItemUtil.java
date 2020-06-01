package com.hungteen.pvzmod.util;

import javax.annotation.Nonnull;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ItemUtil {

	public static final int MELON_AMOUNT = 2;
	public static final int APPLE_AMOUNT = 4;
	public static final int BREAD_AMOUNT = 5;
	public static final int CHICKEN_AMOUNT = 6;
	public static final int PIG_AMOUNT = 8;
	
	public static final float COOKIE_SATURATION = 0.1f;
	public static final float APPLE_SATURATION = 0.3f;
	public static final float BREAD_SATURATION = 0.6f;
	public static final float PIG_SATURATION = 0.8f;
	public static final float GOLDEN_SATURATION = 1.2f;
	
//	Æ»¹û	4	0.3F
//	Ãæ°ü	5	0.6F
//	ÉúÖíÅÅ	3	0.3F
//	ÊìÖíÅÅ	8	0.8F
//	ÇúÆæ	2	0.1F
//	Î÷¹ÏÆ¬	2	0.3F
//	ÉúÅ£Èâ	3	0.3F
//	Å£ÅÅ	8	0.8F
//	Éú¼¦Èâ	2	0.3F
//	Êì¼¦Èâ	6	0.6F
//	¸¯Èâ	4	0.1F
//	Ö©ÖëÑÛ	2	0.8F
//	¿¾ÂíÁåÊí	5	0.6F
//	¶¾ÂíÁåÊí	2	0.3F
//	½ğÂÜ²·	6	1.2F
//	ÄÏ¹ÏÅÉ	8	0.3F
	
	public static NBTTagCompound getItemTag(@Nonnull ItemStack stack) {
		NBTTagCompound result = stack.getTagCompound();
		if (result == null) {
			result = new NBTTagCompound();
			stack.setTagCompound(result);
		}
		return result;
	}
	
	public static void restoreFromItemStack(ItemStack stack,IInventory backpack)
	{
		final NBTTagCompound tag=stack.getTagCompound();
		if(tag!=null) {
			final NBTTagList list=tag.getTagList("items", Constants.NBT.TAG_COMPOUND);
			backpack.clear();
			for(int i=0;i<list.tagCount();i++) {
				NBTTagCompound stackTag=list.getCompoundTagAt(i);
				int id=stackTag.getInteger("slot");
				if(id>=0&&id<backpack.getSizeInventory()) {
					final ItemStack itemstack=new ItemStack(stackTag);
					if(!itemstack.isEmpty()) {
						backpack.setInventorySlotContents(id, itemstack);
					}
				}
			}
		}
	}
	
	public static void convertToItemStack(ItemStack stack,IInventory backpack)
	{
		NBTTagCompound tag=new NBTTagCompound();
		NBTTagList list=new NBTTagList();
		for(int i=0;i<backpack.getSizeInventory();i++) {
			final ItemStack itemstack=backpack.getStackInSlot(i);
			if(!itemstack.isEmpty()) {
				NBTTagCompound stackTag=new NBTTagCompound();
				itemstack.writeToNBT(stackTag);
				stackTag.setInteger("slot", i);
				list.appendTag(stackTag);
			}
		}
		tag.setTag("items", list);
		stack.setTagCompound(tag);
	}
}
