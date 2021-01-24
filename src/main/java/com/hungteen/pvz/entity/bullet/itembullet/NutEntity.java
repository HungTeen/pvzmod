package com.hungteen.pvz.entity.bullet.itembullet;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class NutEntity extends PVZItemBulletEntity {

	public NutEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public NutEntity(EntityType<?> type, World worldIn, LivingEntity thrower) {
		super(type, worldIn, thrower);
	}
	
	public void shoot(double x, double y, double z) {
		this.setMotion(x, y, z);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ItemRegister.NUT.get());
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if(result.getType() == RayTraceResult.Type.BLOCK) {
//			System.out.println(this.getThrower());
			if(! world.isRemote && this.getThrower() != null && world.isAirBlock(this.getPosition().up()) && this.rand.nextInt(12) == 0) {
//				System.out.println("why");
				WallNutEntity nut = EntityRegister.WALL_NUT.get().create(world);
				nut.setOwnerUUID(this.getThrower().getUniqueID());
				EntityUtil.onMobEntitySpawn(world, nut, this.getPosition().up());
				flag = true;
			}
		} else if(result.getType() ==  RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				this.dealNutDamage(target); // attack 
				flag = true;
			}
		}
		if (! this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			if (flag) {
				this.remove();
			}
		}
	}
	
	private void dealNutDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), 2);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}

}
