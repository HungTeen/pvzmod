package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.common.entity.PVZMultiPartEntity;

/**
 * for entity who need multipart collidor.
 */
public interface IMultiPartEntity {

	void resetParts();
	
	void removeParts();
	
	void updateParts();
	
	/**
	 * get all part entities the zombie own.
	 */
	PVZMultiPartEntity[] getMultiParts();
	
}
