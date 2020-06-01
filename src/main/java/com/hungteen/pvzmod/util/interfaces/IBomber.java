package com.hungteen.pvzmod.util.interfaces;

public interface IBomber {

	/**
	 * 开始爆炸
	 */
    void startBoom();
	
    /**
     * 准备爆炸的时间
     */
	int getReadyTime();
}
