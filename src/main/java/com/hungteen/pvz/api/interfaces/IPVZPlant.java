package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

public interface IPVZPlant extends IHasOwner, IGroupEntity, ICanCharm, IHasMetal{

	Plants getPlantEnumName();
	
	Ranks getPlantRank(Plants plant);
	
	Essences getPlantEssenceType();
	
	int getSunCost();
	
	int getCoolDownTime();
	
	float getPlantHealth();
	
	int getSuperTimeLength();
	
}
