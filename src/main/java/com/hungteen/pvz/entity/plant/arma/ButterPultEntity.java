package com.hungteen.pvz.entity.plant.arma;

import java.util.Optional;

import com.hungteen.pvz.entity.bullet.ButterEntity;
import com.hungteen.pvz.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ButterPultEntity extends KernelPultEntity {

	public ButterPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setCurrentBullet(CornTypes.BUTTER);
	}
	
	protected Optional<PultBulletEntity> pultKernel(LivingEntity target, boolean isSuper) {
		if(target == null) return Optional.empty();
		PultBulletEntity bullet = new ButterEntity(world, this);
		bullet.setPosition(this.getPosX(), this.getPosY() + 1.7f, this.getPosZ());
		bullet.shootPultBullet(target);
        this.world.addEntity(bullet);
        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
        this.setCurrentBullet(CornTypes.KERNEL); 
        return Optional.of(bullet);
	}
	
	@Override
	protected void changeBullet() {
		this.setCurrentBullet(CornTypes.BUTTER);
	}

	@Override
	public float getSuperRange() {
		if(this.isPlantInStage(1)) return 15;
		if(this.isPlantInStage(2)) return 20;
		return 25;
	}
	
	@Override
	public float getButterDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) return 0.1F * lvl;
		return 2;
	}
	
	@Override
	public int getButterDuration() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 72 + 8 * lvl;
		return 240;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.BUTTER_PULT;
	}
	
}
