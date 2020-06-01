package com.hungteen.pvzmod.blocks.tileentities;

import com.hungteen.pvzmod.blocks.special.BlockJuicer;
import com.hungteen.pvzmod.items.tools.ItemSummonCard;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.enums.Juices;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityJuicer extends TileEntity implements ITickable{

	private ItemStackHandler handler = new ItemStackHandler(3);
	private String customName;
	private final int MAX_V = 8;//达到8就满容量了
	private final int MAX_WORK_TIME=100;
	private final int MAX_FLOW_TIME=100;
	private int burnTime;//火力时间
	private int burnMaxTime;
	private int workTime;//搅拌时间
	private int progress;//当前容量
	private int flowTime;//产出时间
	private int type;
	//杯子+水果+红石===>果汁
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.burnMaxTime = compound.getInteger("BurnMaxTime");
		this.workTime = compound.getInteger("WorkTime");
		this.flowTime = compound.getInteger("FlowTime");
		this.progress = compound.getInteger("Progress");
		this.type = compound.getInteger("Type");
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("BurnMaxTime", this.burnMaxTime);
		compound.setInteger("WorkTime", this.workTime);
		compound.setInteger("FlowTime", this.flowTime);
		compound.setInteger("Progress", this.progress);
		compound.setInteger("Type", this.type);
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public void update() {
		boolean is_burn=this.isBurning();
		boolean change=false;
		if(!this.world.isRemote) {
			ItemStack fruit=this.handler.getStackInSlot(0);
		    ItemStack fuel=this.handler.getStackInSlot(1);
		    ItemStack bucket=this.handler.getStackInSlot(2);
		    if(this.isBurning()||(!fruit.isEmpty()&&!fuel.isEmpty())) {//在燃烧或者可燃烧
		    	if(!this.isBurning()&&this.progress<this.MAX_V) {//有燃料有水果就可以烧
		    	    int time=this.getItemBurnTime(fuel);
		    	    if(this.checkFruit(fruit)&&time!=0) {
		    	    	this.burnTime=time;
		    	    	this.burnMaxTime=time;
		    	    	if(this.isBurning()) {
		    	    		change=true;
		    	    	    fuel.shrink(1);
		    	    	}
		    	    }
		    	}
		    	if(this.isBurning()&&this.progress<this.MAX_V&&this.checkFruit(fruit)) {
		    		this.workTime++;
		    		if(this.workTime>=this.MAX_WORK_TIME) {//搅拌成功 果汁条+1
		    			this.workTime=0;
		    			if(this.type==0) {
		    				if(fruit.getItem()==Items.APPLE) {
		    				    this.type=Juices.APPLE_JUICE.ordinal();	
		    				}
		    				else if(fruit.getItem()==Items.MELON) {
		    					this.type=Juices.MELON_JUICE.ordinal();
		    				}
		    			}
		    			fruit.shrink(1);
		    			this.progress++;
		    			change=true;
		    		}
		    	}
		    	else {
		    		this.workTime=0;
		    	}
		    	if(this.isBurning()!=is_burn) {
		    		change=true;
		    		BlockJuicer.setState(this.isBurning(), world, pos);
		    	}
		    	if(this.isBurning()) {
		    		this.burnTime--;
		    	}
//		    	System.out.println(this.burnMaxTime+" "+this.burnTime);
		    }
		    if(this.progress>0&&!bucket.isEmpty()&&bucket.getItem()==ItemRegister.GLASS_CUP) {
	    		this.flowTime++;
	    		if(this.flowTime>=this.MAX_FLOW_TIME) {//flow成功 获得产物
	    			this.flowTime=0;
	    			this.progress--;
	    			if(this.progress==0) this.type=0;
	    			if(this.type==Juices.APPLE_JUICE.ordinal()) {
	    			    this.handler.setStackInSlot(2, new ItemStack(ItemRegister.APPLE_JUICE,1));
	    			}else if(this.type==Juices.MELON_JUICE.ordinal()) {
	    				this.handler.setStackInSlot(2, new ItemStack(ItemRegister.MELON_JUICE,1));
	    			}
	    			change=true;
	    		}
	    	}
	    	else {
	    		this.flowTime=0;
	    	}
		    if(change) this.markDirty();
		}
	}
	
	private boolean checkFruit(ItemStack stack)
	{
		if(stack.isEmpty()) return false;
		Item item=stack.getItem();
		Juices juice=Juices.values()[this.type];
		if(item==Items.APPLE&&(juice==Juices.NONE||juice==Juices.APPLE_JUICE)) return true;
		if(item==Items.MELON&&(juice==Juices.NONE||juice==Juices.MELON_JUICE)) return true;
		return false;
	}
	
	public static int getItemBurnTime(ItemStack stack)//燃料的燃烧时间【这里的燃料是红石电能】233
	{
		if(stack.isEmpty()) return 0;
		Item item=stack.getItem();
		if(item == Items.REDSTONE) return 200;
		if(item == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) return 2000;
		return 0;
	}
	
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public boolean isBurning() 
	{
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityJuicer te) 
	{
		return te.getField(0) > 0;
	}
	
	public int getField(int id) 
	{
		//if(id==5) System.out.println(this.burnMaxTime);
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.workTime;
		case 2:
			return this.flowTime;
		case 3:
			return this.progress;
		case 4:
			return this.type;
		case 5:
			return this.burnMaxTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.workTime= value;
			break;
		case 2:
			this.flowTime = value;
			break;
		case 3:
			this.progress = value;
			break;
		case 4:
			this.type=value;
			break;
		case 5:
			this.burnMaxTime=value;
			break;
		}
	}
	
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		 return oldState.getBlock() != newState.getBlock();
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.pvz_juicer");
	}
}
