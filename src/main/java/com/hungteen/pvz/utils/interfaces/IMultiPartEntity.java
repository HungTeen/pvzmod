package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.entity.PVZMultiPartEntity;

public interface IMultiPartEntity {

	void resetParts();
	
	void removeParts();
	
	void updateParts();
	
	PVZMultiPartEntity[] getMultiParts();
	
}
