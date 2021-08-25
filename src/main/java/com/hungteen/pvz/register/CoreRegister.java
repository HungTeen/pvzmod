package com.hungteen.pvz.register;

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
//      ModList.get().getAllScanData().forEach(scan -> {
//			scan.getAnnotations().forEach(a -> {
//				if (a.getAnnotationType().getClassName().equals(PlantType.class.getName())) {
//					String required = (String) a.getAnnotationData().getOrDefault("value", "");
//					if (required.isEmpty() || ModList.get().isLoaded(required)) {
//						try {
//							Class<?> clazz = Class.forName(a.getMemberName());
//							if (PlantType.class.isAssignableFrom(clazz)) {
//								clazz.getMethod("register").invoke(null);
//								PVZMod.LOGGER.info("Registered plant types at {}", a.getMemberName());
//							}
//						} catch (SecurityException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
//							PVZMod.LOGGER.error("Error loading plant types at {}", a.getMemberName(), e);
//						}
//					}
//				}
//			});
//		});
		PVZPlants.register();
		CustomPlants.register();
		MemePlants.register();
		OtherPlants.register();
		GrassZombies.register();
		PoolZombies.register();
		RoofZombies.register();
		CustomZombies.register();
		Zombotanies.register();
		OtherZombies.register();
	}
}
