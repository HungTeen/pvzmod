package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.common.entity.bullet.itembullet.FireCrackerEntity;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FireCrackerItem extends Item {

	private static final int CD = 10;
	
	public FireCrackerItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_MISC));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if(! worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
			FireCrackerEntity entity = new FireCrackerEntity(worldIn, playerIn);
			Vector3d vec = playerIn.getLookAngle();
			entity.setPos(playerIn.getX() + vec.x, playerIn.getY() + playerIn.getEyeHeight() + vec.y, playerIn.getZ() + vec.z);
			entity.summonByOwner(playerIn);
			entity.shoot(vec);
			entity.setAttackDamage(4F);
			worldIn.addFreshEntity(entity);
			EntityUtil.playSound(playerIn, SoundEvents.SNOWBALL_THROW);
			if(! playerIn.abilities.instabuild) {
				stack.shrink(1);
			}
			playerIn.getCooldowns().addCooldown(this, CD);
		}
		return ActionResult.success(stack);
	}

}
