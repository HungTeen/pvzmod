package com.hungteen.pvz.utils.interfaces;

public interface IDefender {

	/**
	 * for extra life
	 */
	float getSuperLife();

	float getAttractRange();
	
	/**
	 * attract the attacker's target
	 */
	void attract();
}
