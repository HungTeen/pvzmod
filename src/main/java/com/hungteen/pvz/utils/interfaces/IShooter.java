package com.hungteen.pvz.utils.interfaces;

import net.minecraft.entity.Entity;

public interface IShooter {

	/**
	 * shoot bullet to attack
	 */
	void shootBullet();
	
	/**
	 * get current shoot CD
	 */
	int getShootCD();
	
	/**
	 * bullet initial move speed
	 */
	float getBulletSpeed();
	
	/**
	 * perform shoot attack
	 */
	void startShootAttack();
	
	/**
	 * is suitable angle
	 */
	boolean checkY(Entity target);
	
}
