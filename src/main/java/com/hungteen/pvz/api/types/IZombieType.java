package com.hungteen.pvz.api.types;

import com.hungteen.pvz.api.paz.IZombieEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

/**
 * define a zombie type.
 */
public interface IZombieType extends IPAZType {

	/**
	 * get (zombie type, zombie entity interface) pair.
	 */
//	Optional<Pair<MobEntity, IZombieEntity>> getZombieEntityType();

	/**
	 * get the entity model of plant (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	Optional<IZombieModel<? extends IZombieEntity>> getZombieModel1();
	
	/**
	 * get the entity model of plant (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	Optional<IZombieModel<? extends IZombieEntity>> getZombieModel2();
	
}
