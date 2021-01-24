package com.hungteen.pvz.utils.interfaces;

public interface IPult {

	void startPultAttack();
	
	int getPultCD();
	
	float getPultRange();
	
	boolean shouldPult();
	
	void pultBullet();
}
