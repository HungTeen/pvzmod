package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.IZombieEntity;
import com.hungteen.pvz.api.IZombieModel;
import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.misc.SpawnChecker;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PAZUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeCallable;

import java.util.*;
import java.util.function.Supplier;

/**
 * use to extend zombie Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class ZombieType extends PAZType implements IZombieType {

	/* all registered zombies */
	private static final List<IZombieType> ZOMBIES = new ArrayList<>();
	/* category -> zombie type list */
	private static final Map<String, List<IZombieType>> CATEGORY_MAP = new HashMap<>();
	/* use to confirm no duplicate */
	private static final Set<IZombieType> ZOMBIE_SET = new HashSet<>();
	/* zombie type -> unique id.(dynamic each loading) */
	private static final Map<IZombieType, Integer> BY_ID = new HashMap<>();
	/* get type by name */
	private static final Map<String, IZombieType> BY_NAME = new HashMap<>();
	/* zombie entity type -> type */
	private static final Map<EntityType<? extends CreatureEntity>, IZombieType> BY_ENTITY_TYPE = new HashMap<>();
	/* other data */
	protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel1;
	protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel2;
	
	protected ZombieType(String name, ZombieFeatures features) {
		super(name);
		// common.
		this.sunCost = features.sunCost;
		this.requiredLevel = features.requiredLevel;
		this.maxLevel = features.maxLevel;
		this.occurDifficulty = features.occurDifficulty;
		this.randomInvasionWeight = features.randomInvasionWeight;
		this.waveSpawnWeight = features.waveSpawnWeight;
		this.xpPoint = features.xpPoint;
		this.renderScale = features.renderScale;
		this.coolDown = features.coolDown;
		this.rankType = features.rankType;
		this.lootTable = features.lootTable;
		this.entitySup = features.entitySup;
		this.summonCardSup = features.summonCardSup;
		this.enjoyCardSup = features.enjoyCardSup;
		// unique.
		this.zombieModel1 = features.zombieModel1;
		this.zombieModel2 = features.zombieModel2;
		// last.
		this.entityRenderResource = this.getEntityResource();
	}

	/**
	 * get the entity model of zombie (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	public Optional<IZombieModel<? extends IZombieEntity>> getZombieModel1() {
		return this.zombieModel1;
	}
	
	/**
	 * get the entity model of zombie (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	public Optional<IZombieModel<? extends IZombieEntity>> getZombieModel2() {
		return this.zombieModel2;
	}
	
	/**
	 * the resource to save entity render picture.
	 */
	protected ResourceLocation getEntityResource() {
		return new ResourceLocation(this.getModID(), "textures/entity/zombie/" + this.getCategoryName() + "/" + this.toString() + ".png");
	}
	
	/**
	 * get zombie corresponding id in zombie list.
	 */
	public final int getId() {
		if(BY_ID.containsKey(this)) {
			return BY_ID.get(this);
		}
		return 0;
	}

    public static void initZombies() {
    	PVZMod.LOGGER.debug("ZombieManager : registered " + ZOMBIE_SET.size() + " zombies.");
		PAZType.initPAZs(ZOMBIES, CATEGORY_MAP, BY_ID, BY_NAME);
	}
    
    /**
	 * {@link EntityRegister#addEntityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent)}
	 */
    public static void postInitZombies() {
		postInit(ZOMBIES, BY_ENTITY_TYPE);
	}
    
	protected static void registerZombie(IZombieType zombie) {
		registerPAZType(ZOMBIE_SET, CATEGORY_MAP, zombie);
	}
	
	protected static void registerZombies(Collection<IZombieType> zombies) {
		zombies.forEach(type -> registerZombie(type));
	}
	
	public static List<IZombieType> getZombies(){
		return Collections.unmodifiableList(ZOMBIES);
	}
	
	public static Optional<IZombieType> getZombieByName(String name){
		if(BY_NAME.containsKey(name)) {
			return Optional.ofNullable(BY_NAME.get(name));
		}
		return Optional.empty();
	}
	
	/**
	 * {@link SpawnChecker}
	 */
	public static Optional<IZombieType> getByEntityType(EntityType<? extends IZombieEntity> type){
	    return Optional.ofNullable(BY_ENTITY_TYPE.getOrDefault(type, null));
	}
	
	public static int size(){
		return ZOMBIES.size();
	}
	
	/**
	 * get zombie translation text.
	 */
	public TranslationTextComponent getTranslateText() {
		return new TranslationTextComponent("entity." + this.getModID() + "." + this.toString());
	}
	
	/**
	 * specific mod id.
	 */
	public abstract String getModID();
	
	public static final class ZombieFeatures{
		//common.
		protected int sunCost = 9999;
		protected int requiredLevel = 100;
		protected int maxLevel = PAZUtil.DEFAULT_MAX_POINTS;
		protected int occurDifficulty = 0;
		protected int randomInvasionWeight = 0;
		protected int waveSpawnWeight = 0;
		protected int xpPoint = 0;
		protected float renderScale = 0.5F;
		protected ICoolDown coolDown = CoolDowns.DEFAULT;
		protected IRankType rankType = Ranks.GRAY;
		protected ResourceLocation entityRenderResource;
		protected ResourceLocation lootTable;
		protected Supplier<EntityType<? extends CreatureEntity>> entitySup;
		protected Supplier<? extends Item> summonCardSup;
		protected Supplier<? extends Item> enjoyCardSup;
		//unique.
		protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel1 = Optional.empty();
		protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel2 = Optional.empty();

		public ZombieFeatures cost(int cost) {
			this.sunCost = cost;
			return this;
		}

		public ZombieFeatures requiredLevel(int lvl) {
			this.requiredLevel = lvl;
			return this;
		}

		public ZombieFeatures maxLevel(int lvl) {
			this.maxLevel = lvl;
			return this;
		}

		public ZombieFeatures difficulty(int dif) {
			this.occurDifficulty = dif * ConfigUtil.getIncDifficulty();
			return this;
		}

		public ZombieFeatures invasionWeight(int weight) {
			this.randomInvasionWeight = weight;
			return this;
		}

		public ZombieFeatures waveWeight(int weight) {
			this.waveSpawnWeight = weight;
			return this;
		}

		public ZombieFeatures xp(int point) {
			this.xpPoint = point;
			return this;
		}

		public ZombieFeatures cd(ICoolDown cd) {
			this.coolDown = cd;
			return this;
		}

		public ZombieFeatures rank(IRankType r) {
			this.rankType = r;
			return this;
		}


		public ZombieFeatures loot(ResourceLocation loot){
			this.lootTable = loot;
			return this;
		}
		
		public ZombieFeatures summonCard(Supplier<? extends Item> sup) {
			this.summonCardSup = sup;
			return this;
		}
		
		public ZombieFeatures enjoyCard(Supplier<? extends Item> sup) {
			this.enjoyCardSup = sup;
			return this;
		}
		
		public ZombieFeatures entityType(Supplier<EntityType<? extends CreatureEntity>> sup) {
			this.entitySup = sup;
			return this;
		}
		
		public ZombieFeatures zombieModel(Supplier<SafeCallable<IZombieModel<? extends IZombieEntity>>> sup) {
			this.zombieModel1 = Optional.ofNullable(DistExecutor.safeCallWhenOn(Dist.CLIENT, sup));
			this.zombieModel2 = Optional.ofNullable(DistExecutor.safeCallWhenOn(Dist.CLIENT, sup));
			return this;
		}
		
		public ZombieFeatures scale(float scale) {	
			this.renderScale = scale;
			return this;
		}
		
	}
	
}
