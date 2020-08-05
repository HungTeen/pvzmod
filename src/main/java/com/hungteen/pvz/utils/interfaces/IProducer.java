package com.hungteen.pvz.utils.interfaces;

public interface IProducer {

	/**
	 * 制造什么东西，比如向日葵生产阳光
	 */
	void genSomething();
	
	/**
	 * 大招制造啥，比如向日葵制造很多阳光
	 */
	void genSuper();
	
	/**
	 * 制造的冷却时间
	 */
	int getGenCD();
}
