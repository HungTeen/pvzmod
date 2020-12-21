package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.utils.enums.MetalTypes;

public interface IHasMetal {

	boolean hasMetal();
	
	void decreaseMetal();
	
	void increaseMetal();
	
	MetalTypes getMetalType();
	
}
