package com.hungteen.pvz.register;

import com.hungteen.pvz.common.impl.EssenceType;
import com.hungteen.pvz.common.impl.plant.CustomPlants;
import com.hungteen.pvz.common.impl.plant.MemePlants;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.OtherZombies;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;

public class CoreRegister {

	public static void register() {
		//register essences.
		EssenceType.register();
		//register plants.
		PVZPlants.register();
		CustomPlants.register();
		MemePlants.register();
		OtherPlants.register();
		//register zombies.
		GrassZombies.register();
		PoolZombies.register();
		RoofZombies.register();
		CustomZombies.register();
		Zombotanies.register();
		OtherZombies.register();
	}
}
