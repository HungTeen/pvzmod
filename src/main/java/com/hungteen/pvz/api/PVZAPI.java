package com.hungteen.pvz.api;

import com.google.common.base.Suppliers;
import com.hungteen.pvz.api.raid.*;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.impl.challenge.SpawnComponent;
import com.hungteen.pvz.common.world.challenge.Challenge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
		 * register single rank type.
		 */
		void registerRankType(IRankType type);

		/**
		 * register single skill type.
		 */
		void registerSkillType(ISkillType type);

		/**
		 * register skill type list.
		 */
		void registerSkillTypes(Collection<ISkillType> types);

		/**
		 * register single cool down type.
		 */
		void registerCD(ICoolDown type);

		/**
		 * register several cds list.
		 */
		void registerCDs(Collection<ICoolDown> types);
		
		/**
		 * register pea gun shooting mode.
		 */
		void registerPeaGunMode(IPlantType type);
		
		/**
		 * register bowling gloves mode, entity type should extends {@link AbstractBowlingEntity}.
		 */
		void registerBowlingMode(IPlantType type, Supplier<EntityType<? extends Entity>> supplier, float size);

		/**
		 * register new spawn amount getter used in {@link SpawnComponent}
		 */
		void registerSpawnAmount(String name, Class<? extends IAmountComponent> c);

		/**
		 * register new spawn position getter used in {@link SpawnComponent}
		 */
		void registerSpawnPlacement(String name, Class<? extends IPlacementComponent> c);

		/**
		 * register new raid type, so that u can read your own json.
		 */
		void registerRaidType(String name, Class<? extends IChallengeComponent> c);

		/**
		 * register new wave type, so that u can read your own json.
		 */
		void registerWaveType(String name, Class<? extends IWaveComponent> c);

		/**
		 * register new spawn type, so that u can read your own json.
		 */
		void registerSpawnType(String name, Class<? extends ISpawnComponent> c);

		/**
		 * register new reward type, so that u can read your own json.
		 */
		void registerReward(String name, Class<? extends IRewardComponent> c);

		/**
		 * create a raid event at pos with specific type.
		 */
		boolean createRaid(ServerWorld world, ResourceLocation res, BlockPos pos);

		/**
		 * it is a raider entity or not.
		 */
		boolean isRaider(ServerWorld world, Entity entity);

		/**
		 * get the challenge of an entity. if not in any, return null.
		 */
		Challenge getEntityChallenge(ServerWorld world, Entity entity);

		/**
		 * get a nearby raid.<br>
		 * NOTE : there won't have more than one raid in a suitable range.
		 */
		Optional<Challenge> getNearByRaid(ServerWorld world, BlockPos pos);

		/**
		 * get all res -> raid component map.
		 */
		Map<ResourceLocation, IChallengeComponent> getRaidTypes();

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