package com.hungteen.pvz.utils.interfaces;

public interface IBomber {

	/**
	 * start explosion
	 */
    void startBomb();
	
    /**
     * explosion pre time
     */
	int getReadyTime();
}
