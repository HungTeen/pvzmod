package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.interfaces.ICanAttract;

/**
 * use to check can entity be attracted by Defence Plants like WallNut.
 */
public interface ICanBeAttracted {

	/**
	 * can be attracted by the specific defender.
	 */
	boolean canBeAttractedBy(ICanAttract defender);
	
	void attractBy(ICanAttract defender);
	
}
