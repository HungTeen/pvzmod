package com.hungteen.pvz.item.tool;

import com.hungteen.pvz.entity.bullet.itembullet.FireCrackerEntity;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireCrackerItem extends Item {

	private static final int CD = 10;
	
	public FireCrackerItem() {
		super(new Item.Properties().group(GroupRegister.PVZ_MISC));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(! worldIn.isRemote && handIn == Hand.MAIN_HAND) {
			FireCrackerEntity entity = new FireCrackerEntity(worldIn, playerIn);
			Vec3d vec = playerIn.getLookVec();
			entity.setPosition(playerIn.getPosX() + vec.x, playerIn.getPosY() + playerIn.getEyeHeight() + vec.y, playerIn.getPosZ() + vec.z);
			entity.shoot(vec);
			worldIn.addEntity(entity);
			EntityUtil.playSound(playerIn, SoundEvents.ENTITY_SNOWBALL_THROW);
			if(! playerIn.abilities.isCreativeMode) {
				stack.shrink(1);
			}
			playerIn.getCooldownTracker().setCooldown(this, CD);
		}
		return ActionResult.resultSuccess(stack);
	}

}
