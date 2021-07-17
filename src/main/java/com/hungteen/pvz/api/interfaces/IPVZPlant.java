package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

public interface IPVZPlant extends IHasOwner, IGroupEntity, ICanBeCharmed, IHasMetal{

	Plants getPlantEnumName();
	
	Ranks getPlantRank(Plants plant);
	
	Essences getPlantEssenceType();
	
	int getSunCost();
	
	int getCoolDownTime();
	
	/**
	 * get plant max health.
	 */
	float getPlantHealth();
	
	/**
	 * how many health does plant has.
	 * {@link EntityUtil#getCurrentHealth(net.minecraft.entity.LivingEntity)}
	 */
	float getCurrentHealth();
	
	/**
	 * how many max health does zombie have currently.
	 * {@link EntityUtil#getCurrentMaxHealth(net.minecraft.entity.LivingEntity)}
	 */
	float getCurrentMaxHealth();
	
	int getSuperTimeLength();
	
	
}
