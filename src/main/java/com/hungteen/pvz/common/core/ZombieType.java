package com.hungteen.pvz.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.zombie.IZombieModel;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.common.misc.SpawnChecker;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.ConfigUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeCallable;

/**
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class ZombieType {

	//all registered zombies.
	private static final List<ZombieType> ZOMBIES = new ArrayList<>();
	//category -> zombie type list.
	private static final Map<String, List<ZombieType>> CATEGORY_LISTS = new HashMap<>();
	//use to confirm no duplicate.
	private static final Set<ZombieType> ZOMBIE_SET = new HashSet<>();
	//zombie type -> unique id.(dynamic each loading).
	private static final Map<ZombieType, Integer> ZOMBIES_ID = new HashMap<>();
	private static final Map<EntityType<? extends PVZZombieEntity>, ZombieType> ENTITY_TYPE = new HashMap<>();
	protected final String zombieName;
	protected final ResourceLocation zombieEntityResource;
//	protected int sunCost;
	protected final int difficulty;// spawn required difficulty.
	protected final int invasionWeight;//random invasion choose.
	protected final int waveWeight;//wave spawn choose.
	protected final float renderScale;
	protected final int zombieXp;
//	protected ICoolDown zombieCD;
	protected RankType zombieRank;
	protected Supplier<EntityType<? extends PVZZombieEntity>> zombieEntitySup;
//	protected Supplier<? extends PlantCardItem> summonCardSup;
//	protected Supplier<? extends PlantCardItem> enjoyCardSup;
	protected Optional<IZombieModel<? extends PVZZombieEntity>> zombieModel1;
	protected Optional<IZombieModel<? extends PVZZombieEntity>> zombieModel2;
	
	public ZombieType(String name, ZombieFeatures features) {
		this.zombieName = name;
//		this.sunCost = features.sunCost;
		this.difficulty = features.difficulty;
		this.invasionWeight = features.invasionWeight;
		this.waveWeight = features.waveWeight;
		this.renderScale = features.renderScale;
		this.zombieXp = features.zombieXp;
//		this.plantCD = features.plantCD;
		this.zombieRank = features.zombieRank;
		this.zombieEntitySup = features.zombieEntitySup;
//		this.summonCardSup = features.summonCardSup;
//		this.enjoyCardSup = features.enjoyCardSup;
		this.zombieModel1 = features.zombieModel1;
		this.zombieModel2 = features.zombieModel2;
		this.zombieEntityResource = this.getEntityResource();
	}
	
	@Override
	public String toString() {
		return this.zombieName;
	}
	
	/**
	 * use to specify zombie store information.
	 */
	public String getIdentity() {
		return this.getModID() + ":" + this.toString();
	}
	
//	/**
//	 * get the sun cost of plant.
//	 */
//	public int getCost() {
//		return this.sunCost;
//	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	public int getInvasionWeight() {
		return this.invasionWeight;
	}
	
	public int getWaveWeight() {
		return this.waveWeight;
	}
	
	/**
	 * get the experience point of zombie.
	 */
	public int getXp() {
		return this.zombieXp;
	}
	
//	
//	/**
//	 * get the cool down of plant summon card.
//	 */
//	public ICoolDown getCD() {
//		return this.plantCD;
//	}
	
	/**
	 * get the rank of zombies.
	 */
	public RankType getRank() {
		return this.zombieRank;
	}
	
	/**
	 * get the entity type of current plant.
	 */
	public Optional<EntityType<? extends PVZZombieEntity>> getEntityType() {
		return this.zombieEntitySup == null ? Optional.empty() : Optional.ofNullable(this.zombieEntitySup.get());
	}
	
//	/**
//	 * get the summon card item of plant.
//	 */
//	public Optional<? extends PlantCardItem> getSummonCard() {
//		return this.summonCardSup == null ? Optional.empty() : Optional.ofNullable(this.summonCardSup.get());
//	}
//	
//	/**
//	 * get the enjoy card item of plant.
//	 */
//	public Optional<? extends PlantCardItem> getEnjoyCard() {
//		return this.enjoyCardSup == null ? Optional.empty() : Optional.ofNullable(this.enjoyCardSup.get());
//	}
	
	/**
	 * get the entity model of plant (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	public Optional<IZombieModel<? extends PVZZombieEntity>> getZombieModel1() {
		return this.zombieModel1;
	}
	
	/**
	 * get the entity model of plant (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	public Optional<IZombieModel<? extends PVZZombieEntity>> getZombieModel2() {
		return this.zombieModel2;
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getRenderScale() {
		return this.renderScale;
	}
	
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getRenderResource() {
		return this.zombieEntityResource;
	}
	
	/**
	 * the resource to save entity render picture.
	 */
	protected ResourceLocation getEntityResource() {
		return new ResourceLocation(this.getModID(), "textures/entity/zombie/" + this.getCategoryName() + "/" + this.toString() + ".png");
	}
	
	/**
	 * get plant corresponding id in zombie list.
	 */
	public final int getId() {
		if(ZOMBIES_ID.containsKey(this)) {
			return ZOMBIES_ID.get(this);
		}
		PVZMod.LOGGER.error("Zombie Type : Missing Id for registered zombie type [ " + this.getIdentity() + " ]");
		return 0;
	}
	
	/**
	 * {@link PVZMod#PVZMod()}
	 */
    public static void initZombies() {
    	PVZMod.LOGGER.debug("ZombieManager : registered " + ZOMBIE_SET.size() + " zombies.");
    	ZOMBIES.clear();
    	//get priority category list.
    	List<Pair<String, Integer>> categoryList = new ArrayList<>();
    	CATEGORY_LISTS.keySet().forEach(l -> {
    		final ZombieType tmp = CATEGORY_LISTS.get(l).get(0);
    		categoryList.add(Pair.of(l, tmp.getSortPriority()));
    	});
    	//sort category by priority.
    	Collections.sort(categoryList, new AlgorithmUtil.PairSorter<>());
    	//deal with each category list one by one.
    	for(Pair<String, Integer> category : categoryList) {
    		//get priority category list.
    		final List<Pair<ZombieType, Integer>> tmp = new ArrayList<>();
    		CATEGORY_LISTS.get(category.getFirst()).forEach(l -> tmp.add(Pair.of(l, l.getSortPriority())));
    		//sort list by priority.
    		Collections.sort(tmp, new AlgorithmUtil.PairSorter<>());
    		PVZMod.LOGGER.debug("ZombieManager : sort category [" + category.getFirst() + "] found " + tmp.size() + " zombies.");
    		//add to the final result list.
    		tmp.forEach(pair -> ZOMBIES.add(pair.getFirst()));
    	}
    	for(int i = 0; i < ZOMBIES.size(); ++ i) {
    		ZOMBIES_ID.put(ZOMBIES.get(i), i);
    	}
    	ZOMBIES.forEach(type -> {
    		type.getEntityType().ifPresent(l -> {
    			ENTITY_TYPE.put(l, type);
    		});
    	});
	}
    
    /**
	 * {@link EntityRegister#addEntityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent)}
	 */
    public static void postInitZombies() {
    	ZOMBIES.forEach(type -> {
    		type.getEntityType().ifPresent(l -> {
    			ENTITY_TYPE.put(l, type);
    		});
    	});
	}
    
	protected static void registerZombie(ZombieType plant) {
		if(! ZOMBIE_SET.contains(plant)) {
		    ZOMBIE_SET.add(plant);
		    if(CATEGORY_LISTS.containsKey(plant.getCategoryName())) {
		    	CATEGORY_LISTS.get(plant.getCategoryName()).add(plant);
		    } else {
		    	CATEGORY_LISTS.put(plant.getCategoryName(), new ArrayList<>(Arrays.asList(plant)));
		    }
		} else {
			PVZMod.LOGGER.warn("ZombieManager : already add " + plant.toString());
		}
	}
	
	protected static void registerZombies(List<ZombieType> zombies) {
		zombies.forEach(type -> registerZombie(type));
	}
	
	public static List<ZombieType> getZombies(){
		return ZOMBIES;
	}
	
//	public static Optional<ZombieType> getZombieByName(String name){
//		for(ZombieType plant : ZOMBIES) {
//			if(plant.getIdentity().equals(name)) {
//				return Optional.ofNullable(plant);
//			}
//		}
//		return Optional.empty();
//	}
	
	/**
	 * {@link SpawnChecker}
	 */
	public static Optional<ZombieType> getByEntityType(EntityType<? extends PVZZombieEntity> type){
	    return Optional.ofNullable(ENTITY_TYPE.getOrDefault(type, null));
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
	 * the priority of this group to stay forward in list. <br>
	 * larger means higher priority. <br>
	 * such as show front in Almanac.
	 */
	public abstract int getSortPriority();
	
	/**
	 * category of the group of zombies.
	 */
	public abstract String getCategoryName();
	
	/**
	 * specific mod id.
	 */
	public abstract String getModID();
	
	public static final class ZombieFeatures{
//		protected int sunCost = 9999;
		protected int difficulty = 0;
		protected int invasionWeight = 0;
		protected int waveWeight = 0;
		protected float renderScale = 0.5F;
		protected int zombieXp = 1;
//		protected ICoolDown zombieCD = ICoolDown.DEFAULT;
		protected RankType zombieRank = Ranks.GRAY;
		protected Supplier<EntityType<? extends PVZZombieEntity>> zombieEntitySup;
//		protected Supplier<? extends PlantCardItem> summonCardSup;
//		protected Supplier<? extends PlantCardItem> enjoyCardSup;
		protected Optional<IZombieModel<? extends PVZZombieEntity>> zombieModel1;
		protected Optional<IZombieModel<? extends PVZZombieEntity>> zombieModel2;
		
//		/**
//		 * set the sun cost of plant.
//		 */
//		public ZombieFeatures cost(int cost) {
//			this.sunCost = cost;
//			return this;
//		}
		
		/**
		 * set zombie spawn difficulty.
		 */
		public ZombieFeatures dif(int dif) {
			this.difficulty = dif * ConfigUtil.getIncDifficulty();
			return this;
		}
		
		/**
		 * set choose invasion weight of random invasion.
		 */
		public ZombieFeatures invasion(int weight) {
			this.invasionWeight = weight;
			return this;
		}
		
		/**
		 * set choose wave weight.
		 */
		public ZombieFeatures wave(int weight) {
			this.waveWeight = weight;
			return this;
		}
		
//		/**
//		 * set the cool down of plant summon card.
//		 */
//		public ZombieFeatures cd(ICoolDown cd) {
//			this.plantCD = cd;
//			return this;
//		}
		
		/**
		 * set the rank of zombies.
		 */
		public ZombieFeatures rank(RankType r) {
			this.zombieRank = r;
			return this;
		}
		
		/**
		 * set the entity type of current zombie.
		 */
		public ZombieFeatures entityType(Supplier<EntityType<? extends PVZZombieEntity>> sup) {
			this.zombieEntitySup = sup;
			return this;
		}
		
//		/**
//		 * set the summon card item of plant.
//		 */
//		public ZombieFeatures summonCard(Supplier<? extends PlantCardItem> sup) {
//			this.summonCardSup = sup;
//			return this;
//		}
//		
//		/**
//		 * set the enjoy card item of plant.
//		 */
//		public ZombieFeatures enjoyCard(Supplier<? extends PlantCardItem> sup) {
//			this.enjoyCardSup = sup;
//			return this;
//		}
		
		/**
		 * set the entity models of zombie (Client Side). 
		 */
		public ZombieFeatures zombieModel(Supplier<SafeCallable<IZombieModel<? extends PVZZombieEntity>>> sup) {
			this.zombieModel1 = Optional.ofNullable(DistExecutor.safeCallWhenOn(Dist.CLIENT, sup));
			this.zombieModel2 = Optional.ofNullable(DistExecutor.safeCallWhenOn(Dist.CLIENT, sup));
			return this;
		}
		
		/**
		 * set the zombie's render scale (Client Side). 
		 */
		public ZombieFeatures scale(float scale) {
			this.renderScale = scale;
			return this;
		}
		
		/**
		 * set the zombie's render scale (Client Side). 
		 */
		public ZombieFeatures xp(int point) {
			this.zombieXp = point;
			return this;
		}
		
	}
	
}
