package com.hungteen.pvzmod.world.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.hungteen.pvzmod.util.enums.SpecialEvents;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class OverworldData extends WorldSavedData{
	
	private HashSet<SpecialEvents> events = new HashSet<SpecialEvents>(SpecialEvents.values().length);
	private boolean attack_now=false;
	private boolean changed=false;
	private boolean is_zomboss_defeated=false;
	
	public OverworldData(String name) {
		super(name);
	}
	
	public void setAttack(boolean is)
	{
		attack_now=is;
		this.markDirty();
	}
	
	public void setChanged(boolean is)
	{
		changed=is;
		this.markDirty();
	}
	
	public void setZombossDefeated(boolean is)
	{
		is_zomboss_defeated=is;
		this.markDirty();
	}
	
	public void add(SpecialEvents event)
	{
		events.add(event);
		this.markDirty();
	}
	
	public void remove(SpecialEvents event)
	{
		events.remove(event);
		this.markDirty();
	}
	
	public boolean hasEvent(SpecialEvents event)
	{
		return events.contains(event);
	}
	
	public boolean hasChanged()
	{
		return changed;
	}
	
	public boolean isAttack()
	{
		return attack_now;
	}
	
	public boolean isZombossDefeated()
	{
		return is_zomboss_defeated;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		events.clear();
		NBTTagList list=(NBTTagList) nbt.getTag("pvzEvents");
		if(list==null) {
			list=new NBTTagList();
		}
		for(int i=0;i<list.tagCount();i++) {
			NBTTagCompound compound=(NBTTagCompound) list.get(i);
			events.add(SpecialEvents.values()[compound.getInteger("id")]);
			//System.out.println("read_"+compound.getInteger("id"));
		}
		attack_now=nbt.getBoolean("attack_now");
		changed=nbt.getBoolean("has_changed");
		is_zomboss_defeated=nbt.getBoolean("is_zomboss_defeated");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for(SpecialEvents event:events) {
			NBTTagCompound compound=new NBTTagCompound();
			//System.out.println("write_"+event.getId());
			compound.setInteger("id", event.ordinal());
			list.appendTag(compound);
		}
		nbt.setTag("pvzEvents", list);
		nbt.setBoolean("attack_now", attack_now);
		nbt.setBoolean("has_changed", changed);
		nbt.setBoolean("is_zomboss_defeated", is_zomboss_defeated);
		return nbt;
	}
	
	public static OverworldData getGlobalData(World world)
	{
		WorldSavedData data = world.getPerWorldStorage().getOrLoadData(OverworldData.class, "SpecialDay");
        if (data == null)
        {
            data = new OverworldData("SpecialDay");
            world.getPerWorldStorage().setData("SpecialDay", data);
        }
        return (OverworldData) data;
	}
}
