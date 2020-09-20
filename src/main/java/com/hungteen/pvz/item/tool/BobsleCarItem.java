package com.hungteen.pvz.item.tool;

import com.hungteen.pvz.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.GroupRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BobsleCarItem extends Item{

	public BobsleCarItem() {
		super(new Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1));
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		Hand hand = context.getHand();
		PlayerEntity player = context.getPlayer();
		ItemStack stack=player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		World world = context.getWorld();
		if(hand==Hand.OFF_HAND) {//only use right hand can plant 
			return ActionResultType.FAIL;
		}
		if(!world.isRemote&&context.getFace()==Direction.UP&&world.isAirBlock(pos.up())) {//can plant here
			stack.shrink(1);
			BobsleCarEntity car = EntityRegister.BOBSLE_CAR.get().create(world);
			car.rotationYaw = player.rotationYaw;
			car.setPosition(pos.getX()+0.5D,pos.getY()+1,pos.getZ()+0.5D);
			car.setIsZombieType(false);
			world.addEntity(car);
			player.addStat(Stats.ITEM_USED.get(this));
		}
		return ActionResultType.SUCCESS;
	}
	
}
