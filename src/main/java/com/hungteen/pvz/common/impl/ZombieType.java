package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.IZombieEntity;
import com.hungteen.pvz.api.IZombieModel;
import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.misc.SpawnChecker;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.ConfigUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
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
public abstract class ZombieType implements IZombieType {

	/* all registered zombies */
	private static final List<IZombieType> ZOMBIES = new ArrayList<>();
	/* category -> zombie type list */
	private static final Map<String, List<IZombieType>> CATEGORY_LISTS = new HashMap<>();
	/* use to confirm no duplicate */
	private static final Set<IZombieType> ZOMBIE_SET = new HashSet<>();
	/* zombie type -> unique id.(dynamic each loading) */
	private static final Map<IZombieType, Integer> ZOMBIES_ID = new HashMap<>();
	/* zombie entity type -> type */
	private static final Map<EntityType<? extends LivingEntity>, IZombieType> ENTITY_TYPE = new HashMap<>();
	protected final String zombieName;
	protected final ResourceLocation zombieEntityResource;
	protected final ResourceLocation lootTable;
	protected final int sunCost;
	protected int limitLevel;
	protected int maxLevel;
	protected float renderScale;
	protected ICoolDown zombieCD;
	protected IRankType zombieRank;
	protected final int difficulty;
	protected final int invasionWeight;
	protected final int waveWeight;
	protected final int zombieXp;
	protected Supplier<? extends Item> summonCardSup;
	protected Supplier<? extends Item> enjoyCardSup;
	protected Supplier<EntityType<? extends CreatureEntity>> zombieEntitySup;
	protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel1;
	protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel2;
	
	public ZombieType(String name, ZombieFeatures features) {
		this.zombieName = name;
		this.sunCost = features.sunCost;
		this.limitLevel = features.limitLevel;
		this.maxLevel = features.maxLevel;
		this.zombieCD = features.zombieCD;
		this.difficulty = features.difficulty;
		this.invasionWeight = features.invasionWeight;
		this.waveWeight = features.waveWeight;
		this.renderScale = features.renderScale;
		this.zombieXp = features.zombieXp;
		this.zombieRank = features.zombieRank;
		this.summonCardSup = features.summonCardSup;
		this.enjoyCardSup = features.enjoyCardSup;
		this.zombieEntitySup = features.zombieEntitySup;
		this.zombieModel1 = features.zombieModel1;
		this.zombieModel2 = features.zombieModel2;
		this.zombieEntityResource = this.getEntityResource();
		this.lootTable = features.lootTable;
	}
	
	@Override
	public String toString() {
		return this.zombieName;
	}
	
	@Override
	public String getIdentity() {
		return this.getModID() + ":" + this.toString();
	}
	
	@Override
	public int getSunCost() {
		return this.sunCost;
	}
	
	@Override
	public ICoolDown getCD() {
		return this.zombieCD;
	}
	
	@Override
	public int getLimitedLevel() {
		return this.limitLevel;
	}
	
	@Override
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	@Override
	public Optional<? extends Item> getSummonCard() {
		return this.summonCardSup == null ? Optional.empty() : Optional.ofNullable(this.summonCardSup.get());
	}
	
	@Override
	public Optional<? extends Item> getEnjoyCard() {
		return this.enjoyCardSup == null ? Optional.empty() : Optional.ofNullable(this.enjoyCardSup.get());
	}
	
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
	
	/**
	 * get the rank of zombies.
	 */
	public IRankType getRank() {
		return this.zombieRank;
	}
	
	/**
	 * get the entity type of current zombie.
	 */
	public Optional<EntityType<? extends CreatureEntity>> getEntityType() {
		return this.zombieEntitySup == null ? Optional.empty() : Optional.ofNullable(this.zombieEntitySup.get());
	}
	
	@Override
	public IFormattableTextComponent getText() {
		return new TranslationTextComponent("entity." + this.getModID() + "." + this.toString());
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
	
	@OnlyIn(Dist.CLIENT)
	public float getRenderScale() {
		return this.renderScale;
	}
	
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getRenderResource() {
		return this.zombieEntityResource;
	}

	@Override
	public ResourceLocation getLootTable() {
		return lootTable;
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
    		final IZombieType tmp = CATEGORY_LISTS.get(l).get(0);
    		categoryList.add(Pair.of(l, tmp.getSortPriority()));
    	});
    	//sort category by priority.
    	Collections.sort(categoryList, new AlgorithmUtil.PairSorter<>());
    	//deal with each category list one by one.
    	for(Pair<String, Integer> category : categoryList) {
    		//get priority category list.
    		final List<Pair<IZombieType, Integer>> tmp = new ArrayList<>();
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
    
	protected static void registerZombie(IZombieType zombie) {
		if(! ZOMBIE_SET.contains(zombie)) {
		    ZOMBIE_SET.add(zombie);
		    if(CATEGORY_LISTS.containsKey(zombie.getCategoryName())) {
		    	CATEGORY_LISTS.get(zombie.getCategoryName()).add(zombie);
		    } else {
		    	CATEGORY_LISTS.put(zombie.getCategoryName(), new ArrayList<>(Arrays.asList(zombie)));
		    }
		} else {
			PVZMod.LOGGER.warn("ZombieManager : already add " + zombie.toString());
		}
	}
	
	protected static void registerZombies(Collection<IZombieType> zombies) {
		zombies.forEach(type -> registerZombie(type));
	}
	
	public static List<IZombieType> getZombies(){
		return Collections.unmodifiableList(ZOMBIES);
	}
	
//	public static Optional<IZombieType> getZombieByName(String name){
//		for(IZombieType zombie : ZOMBIES) {
//			if(zombie.getIdentity().equals(name)) {
//				return Optional.ofNullable(zombie);
//			}
//		}
//		return Optional.empty();
//	}
	
	/**
	 * {@link SpawnChecker}
	 */
	public static Optional<IZombieType> getByEntityType(EntityType<? extends IZombieEntity> type){
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
	 * specific mod id.
	 */
	public abstract String getModID();
	
	public static final class ZombieFeatures{
		protected int sunCost = 9999;
		protected int limitLevel = 100;
		protected int maxLevel = 20;
		protected ICoolDown zombieCD = ICoolDown.DEFAULT;
		protected int difficulty = 0;
		protected int invasionWeight = 0;
		protected int waveWeight = 0;
		protected float renderScale = 0.5F;
		protected int zombieXp = 1;
		protected IRankType zombieRank = Ranks.GRAY;
		protected ResourceLocation lootTable;
		protected Supplier<? extends Item> summonCardSup;
		protected Supplier<? extends Item> enjoyCardSup;
		protected Supplier<EntityType<? extends CreatureEntity>> zombieEntitySup;
		protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel1 = Optional.empty();
		protected Optional<IZombieModel<? extends IZombieEntity>> zombieModel2 = Optional.empty();
		
		public ZombieFeatures cost(int cost) {
			this.sunCost = cost;
			return this;
		}
		
		public ZombieFeatures limitLevel(int lvl) {
			this.limitLevel = lvl;
			return this;
		}
		
		public ZombieFeatures level(int lvl) {
			this.maxLevel = lvl;
			return this;
		}
		
		public ZombieFeatures cd(ICoolDown cd) {
			this.zombieCD = cd;
			return this;
		}
		
		public ZombieFeatures dif(int dif) {
			this.difficulty = dif * ConfigUtil.getIncDifficulty();
			return this;
		}
		
		public ZombieFeatures invasion(int weight) {
			this.invasionWeight = weight;
			return this;
		}
		
		public ZombieFeatures wave(int weight) {
			this.waveWeight = weight;
			return this;
		}
		
		public ZombieFeatures rank(IRankType r) {
			this.zombieRank = r;
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
			this.zombieEntitySup = sup;
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
		
		public ZombieFeatures xp(int point) {
			this.zombieXp = point;
			return this;
		}
		
	}
	
}
