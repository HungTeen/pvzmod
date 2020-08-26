package com.hungteen.pvz;

import com.hungteen.pvz.register.KeyBindRegister;

public class ClientProxy extends CommonProxy{

	public void preInit() {};
	
	public void init() {
		KeyBindRegister.init();
	};
	
	public void postInit() {
	};
}
