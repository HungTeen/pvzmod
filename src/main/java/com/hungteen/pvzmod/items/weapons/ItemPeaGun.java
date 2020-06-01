package com.hungteen.pvzmod.items.weapons;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.gui.GuiHandler;
import com.hungteen.pvzmod.items.ItemBase;
import com.hungteen.pvzmod.registry.CreativeTabRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.ItemUtil;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemPeaGun extends ItemBase{

	private IInventory backpack=new InventoryBasic("back_pack", true, 28);
	private final float normalShootSpeed = 2.2f;
	
	public ItemPeaGun(String name) {
		super(name, CreativeTabRegister.TOOL_TAB);
		this.setMaxStackSize(1);
		setFull3D();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack=playerIn.getHeldItem(handIn);
		BlockPos pos=new BlockPos(playerIn);
		if(handIn==EnumHand.MAIN_HAND) {
			if(!worldIn.isRemote) {
				this.startAttack(worldIn, playerIn, stack);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
		}
		else if(handIn==EnumHand.OFF_HAND) {
			if(!worldIn.isRemote&&playerIn.isSneaking()) {
				playerIn.openGui(Main.instance, GuiHandler.PEA_GUN, worldIn, pos.getX(),pos.getY(),pos.getZ());
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	
	private void startAttack(World world,EntityPlayer player,ItemStack stack)
	{
		ItemUtil.restoreFromItemStack(stack, this.backpack);
		ItemStack spStack =this.backpack.getStackInSlot(0);
		for(int i=1;i<28;i++) {
			ItemStack newStack=this.backpack.getStackInSlot(i);
			if(canPlayerUseItemPea(player,newStack.getItem())) {
				if(this.itemRand.nextInt(2)==0) {
					this.specialShoot(world,player,newStack.getItem(),spStack.getItem());
				}else {
				    this.shoot(world,player,newStack.getItem(),normalShootSpeed,ShootType.NORMAL);
				}
//				player.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F);
				world.playSound(null, player.posX, player.posY, player.posZ,SoundEvents.ENTITY_SNOWMAN_SHOOT , SoundCategory.NEUTRAL, 1,1);
				newStack.shrink(1);
				player.getCooldownTracker().setCooldown(stack.getItem(), getCoolDownTime(player));
				break;
			}
		}
		ItemUtil.convertToItemStack(stack, this.backpack);
	}
	
	private int getCoolDownTime(EntityPlayer player)
	{
		if(PlayerUtil.getPlantLvl(player,Plants.GATLING_PEA)>=5) {
			return 7;
		}else if(PlayerUtil.getPlantLvl(player,Plants.THREE_PEATER)>=5) {
			return 10;
		}else if(PlayerUtil.getPlantLvl(player,Plants.DOUBLE_SHOOTER)>=5) {
			return 15;
		}else if(PlayerUtil.getPlantLvl(player,Plants.SPLIT_PEA)>=5) {
			return 20;
		}
		return 30;
	}
	
	private void shoot(World world,EntityPlayer player,Item item,double speed,ShootType type)
	{
		EntityPea pea=new EntityPea(world, player, EntityPea.Type.NORMAL,getPeaState(item));
		pea.setPosition(player.posX, player.posY+player.getEyeHeight(), player.posZ);
		Vec3d vec = player.getLookVec().scale(speed);
		if(type==ShootType.BACK) {
			vec=vec.scale(-1);
		}else if(type==ShootType.LEFT_FRONT) {
			pea.setPosition(player.posX-vec.z*item.itemRand.nextFloat(), player.posY+player.getEyeHeight(), player.posZ+vec.x*item.itemRand.nextFloat());
		}else if(type==ShootType.RIGHT_FRONT) {
			pea.setPosition(player.posX+vec.z*item.itemRand.nextFloat(), player.posY+player.getEyeHeight(), player.posZ-vec.x*item.itemRand.nextFloat());
		}else if(type==ShootType.UNSTABLE) {
			vec=vec.addVector((item.itemRand.nextFloat()-0.5f),(item.itemRand.nextFloat()-0.5f),(item.itemRand.nextFloat()-0.5f));
		}
		pea.addVelocity(vec.x,vec.y,vec.z);
		world.spawnEntity(pea);
	}
	
	private void specialShoot(World world,EntityPlayer player,Item item,Item spItem)
	{
		if(spItem==ItemRegister.PEA_SHOOTER_CARD) {
			this.peaShooterShoot(world, player, item);
		}else if(spItem==ItemRegister.SPLIT_PEA_CARD){
			this.splitPeaShoot(world, player, item);
		}else if(spItem==ItemRegister.DOUBLE_SHOOTER_CARD){
			this.doubleShooterShoot(world, player, item);
		}else if(spItem==ItemRegister.THREE_PEATER_CARD){
			this.threePeaterShoot(world, player, item);
		}else if(spItem==ItemRegister.GATLING_PEA_CARD){
			this.gatlingPeaShoot(world, player, item);
		}else {
			this.shoot(world,player,item,normalShootSpeed,ShootType.NORMAL);
		}
	}
	
	private void peaShooterShoot(World world,EntityPlayer player,Item item)
	{
		int cnt=4;
		for(int i=0;i<cnt;i++) {
			this.shoot(world,player,item,normalShootSpeed-0.1f*i,ShootType.NORMAL);
		}
	}
	
	private void splitPeaShoot(World world,EntityPlayer player,Item item)
	{
		int cnt=4;
		for(int i=0;i<cnt;i++) {
			this.shoot(world,player,item,normalShootSpeed-0.1f*i,ShootType.NORMAL);
		}
		for(int i=0;i<2*cnt;i++) {
			this.shoot(world,player,item,normalShootSpeed-0.05f*i,ShootType.BACK);
		}
	}
	
	private void doubleShooterShoot(World world,EntityPlayer player,Item item)
	{
		int cnt=8;
		for(int i=0;i<cnt;i++) {
			this.shoot(world,player,item,normalShootSpeed-0.05f*i,ShootType.NORMAL);
		}
	}
	
	private void threePeaterShoot(World world,EntityPlayer player,Item item)
	{
		int cnt=4;
		for(int i=0;i<cnt;i++) {
			this.shoot(world,player,item,normalShootSpeed-0.1f*i,ShootType.NORMAL);
			this.shoot(world,player,item,normalShootSpeed-0.1f*i,ShootType.LEFT_FRONT);
			this.shoot(world,player,item,normalShootSpeed-0.1f*i,ShootType.RIGHT_FRONT);
		}
	}
	
	private void gatlingPeaShoot(World world,EntityPlayer player,Item item)
	{
		int cnt=16;
		for(int i=0;i<cnt;i++) {
			this.shoot(world,player,item,normalShootSpeed-0.02f*i,ShootType.UNSTABLE);
		}
	}
	
	private EntityPea.State getPeaState(Item item)
	{
		if(item==ItemRegister.PEA) {
			return EntityPea.State.NORMAL;
		}else if(item==ItemRegister.SNOW_PEA) {
			return EntityPea.State.SNOW;
		}
		return EntityPea.State.NORMAL;
	}
	
	private boolean canPlayerUseItemPea(EntityPlayer player,Item item)
	{
		if(item==ItemRegister.PEA) {
			return PlayerUtil.getPlantLvl(player,Plants.PEA_SHOOTER)>=5;
		}else if(item==ItemRegister.SNOW_PEA) {
			return PlayerUtil.getPlantLvl(player,Plants.SNOW_PEA)>=5;
		}
		return false;
	}
	
	public enum ShootType
	{
		NORMAL,
		BACK,
		LEFT_FRONT,
		RIGHT_FRONT,
		UNSTABLE, 
	}
}
