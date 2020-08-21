package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class PotatoEntity extends PVZThrowableEntity{

	public PotatoEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public PotatoEntity(EntityType<? extends ThrowableEntity> type, World worldIn, LivingEntity thrower) {
		super(type, worldIn, thrower);
	}
	
	public void shoot(double x, double y, double z) {
		this.setMotion(x, y, z);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(Items.POTATO);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(!this.world.isRemote) {
			this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) {
            	this.remove();
            	if(!(this.getThrower() instanceof PVZPlantEntity)) {
            		System.out.println("who is shooting potato,there are some matter!");
            		return ;
            	}
            	PotatoMineEntity mine= EntityRegister.POTATO_MINE.get().create(world);
                PlantUtil.copyPlantData((PVZPlantEntity) getThrower(), mine);
                mine.setIsMineReady(true);
            	mine.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
            	this.world.addEntity(mine);
            }
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.06f;
	}
	
}
