package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.api.enums.PVZGroupType;

public interface IHasGroup {

	/**
	 * which group the entity in.
	 */
	PVZGroupType getEntityGroupType();
}
