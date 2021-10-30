package com.hungteen.pvz.api;

import com.google.common.base.Suppliers;
import com.hungteen.pvz.api.types.*;
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

		/**
		 * register single essence type.
		 */
		void registerEssenceType(IEssenceType type);

		/**
		 * register essence type list.
		 */
		void registerEssenceTypes(Collection<IEssenceType> types);

		/**
		 * register single cool down type.
		 */
		void registerCD(ICoolDown type);

		/**
		 * register several cds list.
		 */
		void registerCDs(Collection<ICoolDown> types);

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
		 * get all registered essence types.
		 */
		List<IEssenceType> getEssences();

		/**
		 * get type by specific identity.
		 */
		Optional<IPAZType> getTypeByID(String id);

		/**
		 * get type by specific identity.
		 */
		Optional<IPlantType> getPlantTypeByID(String id);

		/**
		 * get type by specific identity.
		 */
		Optional<IZombieType> getZombieTypeByID(String id);
		
	}
	
}