package com.hungteen.pvz.entity.bullet.itembullet;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.register.EntityRegister;
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
	}
	
	public PotatoEntity(World worldIn, LivingEntity thrower) {
		super(EntityRegister.POTATO.get(), worldIn, thrower);
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
		this.world.setEntityState(this, (byte)3);
        if(! this.checkLive(result)) {
            if(! (this.getThrower() instanceof PVZPlantEntity)) {
            	System.out.println("ERROR : Who is shooting potato, there are some matter !");
            	return ;
            }
            PotatoMineEntity mine = EntityRegister.POTATO_MINE.get().create(world);
            PlantUtil.copyPlantData(mine, (PVZPlantEntity) getThrower());
            mine.setMineReady(true);
            mine.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
            this.world.addEntity(mine);
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

	@Override
	protected float getAttackDamage() {
		return 0;
	}
	
}
