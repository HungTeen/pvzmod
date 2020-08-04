package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

public interface IPVZPlant {

	Plants getPlantEnumName();
	
	Ranks getPlantRank(Plants plant);
	
	int getSunCost();
	
	int getCoolDownTime();
	
	float getLife();
	
	boolean hasSuperMode();
	
	int getSuperTimeLength();
	
}
