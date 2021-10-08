package com.hungteen.pvz.api;

import com.hungteen.pvz.api.PVZAPI.IPVZAPI;

/**
 * fake dummy API when there is no Custom Raid mod.
 */
public class DummyAPI implements IPVZAPI {

	public static final IPVZAPI INSTANCE = new DummyAPI();

}
