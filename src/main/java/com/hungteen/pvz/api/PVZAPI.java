package com.hungteen.pvz.api;

import com.google.common.base.Suppliers;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class PVZAPI {
	
	private static final Supplier<IPVZAPI> LAZY_INSTANCE = Suppliers.memoize(() -> {
		try {
			return (IPVZAPI) Class.forName("com.hungteen.pvz.common.impl.PVZAPIImpl").newInstance();
		} catch (ReflectiveOperationException e) {
			LogManager.getLogger().warn("Unable to find PVZAPIImpl, using a dummy one");
			return DummyAPI.INSTANCE;
		}
	});

	/**
	 * Obtain the CustomRaid API, either a valid implementation if CustomRaid is present, else
	 * a dummy instance instead if CustomRaid is absent.
	 */
	public static IPVZAPI get() {
		return LAZY_INSTANCE.get();
	}
	
	public interface IPVZAPI {

		/* register stuffs */

		/**
		 * register single plant type.
		 */
		void registerPlantType(IPlantType type);
		
		/**
		 * register plant type list.
		 */
		void registerPlantTypes(Collection<IPlantType> types);
		
		/**
		 * register single zombie type.
		 */
		void registerZombieType(IZombieType type);
		
		/**
		 * register zombie type list.
		 */
		void registerZombieTypes(Collection<IZombieType> types);

		/* getting stuffs */

		/**
		 * get all registered plant types.
		 */
		List<IPlantType> getPlants();

		/**
		 * get all registered zombie types.
		 */
		List<IZombieType> getZombies();

		/**
		 * get all registered plant & zombie types.
		 */
		List<IPAZType> getPAZs();

		/**
		 * get type by specific identity.
		 */
		Optional<IPAZType> getTypeByID(String id);
		
	}
	
}