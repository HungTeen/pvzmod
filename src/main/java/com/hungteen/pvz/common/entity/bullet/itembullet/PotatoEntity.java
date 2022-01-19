package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class PotatoEntity extends PVZItemBulletEntity{

	public PotatoEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
		this.setNoGravity(false);
	}
	
	public PotatoEntity(World worldIn, LivingEntity thrower) {
		super(EntityRegister.POTATO.get(), worldIn, thrower);
		this.setNoGravity(false);
	}
	
	public void shoot(double x, double y, double z) {
		this.setDeltaMovement(x, y, z);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(Items.POTATO);
	}

	@Override
	public void onImpact(RayTraceResult result) {
		this.level.broadcastEntityEvent(this, (byte)3);
        if(! this.checkLive(result)) {
            if(! (this.getThrower() instanceof PVZPlantEntity)) {
            	PVZMod.LOGGER.warn("Who is shooting potato, there are some matter !");
            	return ;
            }
            PotatoMineEntity mine = EntityRegister.POTATO_MINE.get().create(level);
            PlantUtil.copyPlantData(mine, (PVZPlantEntity) getThrower());
            mine.setPos(this.getX(), this.getY(), this.getZ());
            mine.setRisingFromDirt();
            this.level.addFreshEntity(mine);
            this.remove();
        }
	}

	@Override
	protected int getMaxLiveTick() {
		return 120;
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}

}
