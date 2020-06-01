package com.hungteen.pvzmod.util.interfaces;

public interface IShooter {

	/**
	 * 用于实体发射子弹
	 */
	void shootBullet();
	
	/**
	 * 获取攻击CD 一般为30
	 * 
	 */
	int getShootSpeed();
	
	/**
	 * 开始攻击
	 */
	void startShootAttack();
}
