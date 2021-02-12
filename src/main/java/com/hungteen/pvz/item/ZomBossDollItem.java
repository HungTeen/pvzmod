package com.hungteen.pvz.item;

import com.hungteen.pvz.entity.zombie.roof.ZomBossEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ZomBossDollItem extends Item {

	public ZomBossDollItem() {
		super(new Item.Properties().group(GroupRegister.PVZ_MISC));
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		if(! world.isRemote && ! player.getCooldownTracker().hasCooldown(this) && context.getFace() == Direction.UP) {
			if(this.canSpawnHere(world, pos)) {
				ZomBossEntity zomboss = EntityRegister.ZOMBOSS.get().create(world);
				EntityUtil.onMobEntitySpawn(world, zomboss, pos.up());
			}
		}
		return super.onItemUse(context);
	}
	
	private boolean canSpawnHere(World world, BlockPos pos) {
		for(int i = - 5; i <= 5; ++ i) {
			for(int j = - 5; j <= 5; ++ j) {
				for(int k = 1; k <= 12; ++ k) {
					BlockPos tmp = pos.add(i, k, j);
					if(! world.getBlockState(tmp).isAir(world, pos)) {
						return false;
					}
				}
			}
		}
		return world.getBlockState(pos).isSolid();
	}

}
