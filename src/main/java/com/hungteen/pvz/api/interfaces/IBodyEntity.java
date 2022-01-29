package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.api.enums.BodyType;
import com.hungteen.pvz.api.types.IZombieType;

public interface IBodyEntity {

	IZombieType getZombieType();

	BodyType getBodyType();

	boolean hasHandDefence();
	
	boolean isMini();

	int getAnimTime();

}
