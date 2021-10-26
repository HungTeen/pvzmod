package com.hungteen.pvz.api.types;

import net.minecraft.world.World;

import java.util.List;

public interface IInvasionType extends IIDType {

	/**
	 * get spawn zombies of this invasion. 
	 */
	List<IZombieType> getSpawnZombies(World world);
	
	/**
	 * check can be chosen or not.
	 */
	boolean isAvailable(World world);
	
	/**
	 * not a spawn event, but assist one.
	 */
	boolean isAssistEvent();
	
	/**
	 * choose weight to occur.
	 */
	int getChance();
	
	/**
	 * bonus when finish invasion.
	 */
	IBundleType getBundle();
	
}
