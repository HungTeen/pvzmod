package com.hungteen.pvz.utils.interfaces;

public interface IBomber {

	/**
	 * 开始爆炸
	 */
    void startBomb();
	
    /**
     * 准备爆炸的时间
     */
	int getReadyTime();
}
