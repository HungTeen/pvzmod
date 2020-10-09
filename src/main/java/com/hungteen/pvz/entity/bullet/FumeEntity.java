package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FumeEntity extends PVZThrowableEntity{

	public FumeEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public FumeEntity(EntityType<? extends ThrowableEntity> type, World worldIn, LivingEntity living) {
		super(type, worldIn, living);
	}
	
	@Override
	public void tick() {
		super.tick();
		
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.FUME.get());
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				this.dealFumeDamage(target); // attack 
			}
		}
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			if (flag || !this.checkLive(result)) {
				this.remove();
			}
		}
	}
	
	@Override
	protected boolean checkLive(RayTraceResult result) {
		if(result.getType() == RayTraceResult.Type.BLOCK) {
    		Block block = world.getBlockState(((BlockRayTraceResult)result).getPos()).getBlock();
    		if(block instanceof BushBlock) {
    			return true;
    		}
    	}
    	return false;
	}
	
	private void dealFumeDamage(Entity target) {
		
	}

}
