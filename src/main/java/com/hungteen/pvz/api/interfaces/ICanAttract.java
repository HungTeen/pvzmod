package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.interfaces.IDefender;

/**
 * use to check can entity be attracted by Defence Plants like WallNut.
 */
public interface ICanAttract {

	/**
	 * can be attracted by the specific defender.
	 */
	boolean canBeAttractedBy(IDefender defender);
}
