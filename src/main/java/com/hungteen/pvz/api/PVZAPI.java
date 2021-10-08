package com.hungteen.pvz.api;

import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;

import com.google.common.base.Suppliers;

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
		
	}
	
}