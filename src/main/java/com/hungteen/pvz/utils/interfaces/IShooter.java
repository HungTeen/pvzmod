package com.hungteen.pvz.utils.interfaces;

import net.minecraft.entity.Entity;

public interface IShooter {

	void shootBullet();
	
	int getShootCD();
	
	float getBulletSpeed();
	
	void startShootAttack();
	
	/**
	 * is suitable angle
	 */
	boolean checkY(Entity target);
}
