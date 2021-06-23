package com.hungteen.pvz.utils.interfaces;

public interface IPult {

	/**
	 * check attacker's pult condition.
	 */
	boolean shouldPult();
	
	/**
	 * attack interval.
	 */
	int getPultCD();
	
	/**
	 * when tick reach the CD, start attack.
	 */
	void startPultAttack();
	
	/**
	 * pulter attack range.
	 */
	float getPultRange();
	
	/**
	 * shoot entity out.
	 */
	void pultBullet();
}
