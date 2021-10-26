package com.hungteen.pvz.common.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.IPlantEntity;
import com.hungteen.pvz.api.IPlantInfo;
import com.hungteen.pvz.api.IPlantModel;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeCallable;

import java.util.*;
import java.util.function.Supplier;

/**
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class PlantType implements IPlantType {

	/* all registered plants */
	private static final List<IPlantType> PLANTS = new ArrayList<>();
	/* category -> plant type list */
	private static final Map<String, List<IPlantType>> CATEGORY_LISTS = new HashMap<>();
	/* use to confirm no duplicate */
	private static final Set<IPlantType> PLANT_SET = new HashSet<>();
	/* plant type -> unique id.(dynamic each loading) */
	private static final Map<IPlantType, Integer> PLANTS_ID = new HashMap<>();
	/* entity type -> plant type */
	private static final Map<EntityType<? extends LivingEntity>, IPlantType> ENTITY_TYPE = new HashMap<>();
	/* get type by name */
	private static final Map<String, IPlantType> TYPE_BY_NAME = new HashMap<>();
	/* other data */
	protected final String plantName;
	protected int sunCost;
	protected int limitLevel;
	protected int maxLevel;
	protected float renderScale;
	protected ICoolDown plantCD;
	protected IEssenceType plantEssence;
	protected IRankType plantRank;
	protected final ResourceLocation plantEntityResource;
	protected final ResourceLocation lootTable;
	protected Supplier<EntityType<? extends CreatureEntity>> plantEntitySup;
	protected Supplier<? extends Item> summonCardSup;
	protected Supplier<? extends Item> enjoyCardSup;
	protected IPlantModel<? extends IPlantEntity> plantModel;
	protected Supplier<IPlantType> upgradeFrom;
	protected Supplier<IPlantType> upgradeTo;
	protected Supplier<Block> plantBlock;
	protected Supplier<IPlantInfo> outerPlant;
	protected ICardPlacement cardPlacement;
	protected boolean isShroomPlant;
	protected boolean isWaterPlant;
	
	public PlantType(String name, PlantFeatures features) {
		this.plantName = name;
		this.sunCost = features.sunCost;
		this.limitLevel = features.limitLevel;
		this.maxLevel = features.maxLevel;
		this.renderScale = features.renderScale;
		this.plantCD = features.plantCD;
		this.plantEssence = features.plantEssence;
		this.plantRank = features.plantRank;
		this.plantEntitySup = features.plantEntitySup;
		this.summonCardSup = features.summonCardSup;
		this.enjoyCardSup = features.enjoyCardSup;
		this.plantModel = features.plantModel;
		this.upgradeFrom = features.upgradeFrom;
		this.upgradeTo = features.upgradeTo;
		this.plantBlock = features.plantBlock;
		this.outerPlant = features.outerPlant;
		this.cardPlacement = features.cardPlacement;
		this.isShroomPlant = features.isShroomPlant;
		this.isWaterPlant = features.isWaterPlant;
		this.plantEntityResource = this.getEntityResource();
		this.lootTable = features.lootTable;
	}
	
	@Override
	public String toString() {
		return this.plantName;
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
	public int getLimitedLevel() {
		return this.limitLevel;
	}

	@Override
	public int getXp() {
		return 0;
	}

	@Override
	public int getDifficulty() {
		return 0;
	}

	@Override
	public int getInvasionWeight() {
		return 0;
	}

	@Override
	public int getWaveWeight() {
		return 0;
	}

	@Override
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	@Override
	public ICoolDown getCD() {
		return this.plantCD;
	}
	
	@Override
	public IEssenceType getEssence() {
		return this.plantEssence;
	}
	
	@Override
	public IRankType getRank() {
		return this.plantRank;
	}
	
	@Override
	public Optional<EntityType<? extends CreatureEntity>> getEntityType() {
		return this.plantEntitySup == null ? Optional.empty() : Optional.ofNullable(this.plantEntitySup.get());
	}
	
	@Override
	public Optional<? extends Item> getSummonCard() {
		return this.summonCardSup == null ? Optional.empty() : Optional.ofNullable(this.summonCardSup.get());
	}
	
	@Override
	public Optional<? extends Item> getEnjoyCard() {
		return this.enjoyCardSup == null ? Optional.empty() : Optional.ofNullable(this.enjoyCardSup.get());
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public Optional<IPlantModel<? extends IPlantEntity>> getPlantModel() {
		return Optional.ofNullable(this.plantModel);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public float getRenderScale() {
		return this.renderScale;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getRenderResource() {
		return this.plantEntityResource;
	}

	@Override
	public ResourceLocation getLootTable() {
		return lootTable;
	}

	@Override
	public Optional<IPlantType> getUpgradeFrom() {
		return this.upgradeFrom == null ? Optional.empty() : Optional.ofNullable(this.upgradeFrom.get());
	}
	
	@Override
	public Optional<IPlantType> getUpgradeTo() {
		return this.upgradeTo == null ? Optional.empty() : Optional.ofNullable(this.upgradeTo.get());
	}
	
	@Override
	public ICardPlacement getPlacement() {
		return this.cardPlacement;
	}
	
	@Override
	public boolean isShroomPlant() {
		return this.isShroomPlant;
	}
	
	@Override
	public boolean isBlockPlant() {
		return this.plantBlock != null;
	}
	
	@Override
	public Optional<Block> getPlantBlock() {
		return Optional.ofNullable(this.plantBlock.get());
	}
	
	@Override
	public boolean isWaterPlant() {
		return this.isWaterPlant;
	}
	
	@Override
	public boolean isOuterPlant() {
		return this.outerPlant != null;
	}
	
	@Override
	public Optional<IPlantInfo> getOuterPlant() {
		return Optional.ofNullable(this.outerPlant.get());
	}
	
	/**
	 * the resource to save entity render picture.
	 */
	protected ResourceLocation getEntityResource() {
		final String sep = this.getEssence().toString();
		return new ResourceLocation(this.getModID(), "textures/entity/plant/" + sep + "/" + this.toString() + ".png");
	}
	
	@Override
	public TranslationTextComponent getText() {
		return new TranslationTextComponent("entity." + this.getModID() + "." + this.toString());
	}
	
	@Override
	public final int getId() {
		if(PLANTS_ID.containsKey(this)) {
			return PLANTS_ID.get(this);
		}
		PVZMod.LOGGER.error("Plant Type : Missing Id for registered plant type [ " + this.toString() + " ]");
		return 0;
	}
	
	/**
	 * {@link PVZMod#PVZMod()}
	 */
    public static void initPlants() {
    	PVZMod.LOGGER.debug("PlantManager : registered " + PLANT_SET.size() + " plants.");
    	PLANTS.clear();
    	//get priority category list.
    	List<Pair<String, Integer>> categoryList = new ArrayList<>();
    	CATEGORY_LISTS.keySet().forEach(l -> {
    		final IPlantType tmp = CATEGORY_LISTS.get(l).get(0);
    		categoryList.add(Pair.of(l, tmp.getSortPriority()));
    	});
    	//sort category by priority.
    	Collections.sort(categoryList, new AlgorithmUtil.PairSorter<>());
    	//deal with each category list one by one.
    	for(Pair<String, Integer> category : categoryList) {
    		//get priority category list.
    		final List<Pair<IPlantType, Integer>> tmp = new ArrayList<>();
    		CATEGORY_LISTS.get(category.getFirst()).forEach(l -> tmp.add(Pair.of(l, l.getSortPriority())));
    		//sort list by priority.
    		Collections.sort(tmp, new AlgorithmUtil.PairSorter<>());
    		PVZMod.LOGGER.debug("PlantManager : sort category [" + category.getFirst() + "] found " + tmp.size() + " plants.");
    		//add to the final result list.
    		tmp.forEach(pair -> PLANTS.add(pair.getFirst()));
    	}
    	for(int i = 0; i < PLANTS.size(); ++ i) {
    		PLANTS_ID.put(PLANTS.get(i), i);
    		TYPE_BY_NAME.put(PLANTS.get(i).getIdentity(), PLANTS.get(i));
    	}
	}
    
    /**
	 * {@link EntityRegister#addEntityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent)}
	 */
    public static void postInitPlants() {
    	PLANTS.forEach(type -> {
    		type.getEntityType().ifPresent(l -> {
    			ENTITY_TYPE.put(l, type);
    		});
    	});
	}
    
	public static void registerPlant(IPlantType plant) {
		if(! PLANT_SET.contains(plant)) {
		    PLANT_SET.add(plant);
		    if(CATEGORY_LISTS.containsKey(plant.getCategoryName())) {
		    	CATEGORY_LISTS.get(plant.getCategoryName()).add(plant);
		    } else {
		    	CATEGORY_LISTS.put(plant.getCategoryName(), new ArrayList<>(Arrays.asList(plant)));
		    }
		} else {
			PVZMod.LOGGER.warn("PlantManager : already add " + plant.toString());
		}
	}
	
	public static void registerPlants(Collection<IPlantType> plants) {
		plants.forEach(type -> registerPlant(type));
	}
	
	public static List<IPlantType> getPlants(){
		return Collections.unmodifiableList(PLANTS);
	}
	
	public static Optional<IPlantType> getPlantByName(String name){
		if(TYPE_BY_NAME.containsKey(name)) {
			return Optional.ofNullable(TYPE_BY_NAME.get(name));
		}
		return Optional.empty();
	}
	
	public static int size(){
		return PLANTS.size();
	}
	
	public static final class PlantFeatures{
		protected int sunCost = 9999;
		protected int limitLevel = 100;
		protected int maxLevel = PlantUtil.MAX_PLANT_LEVEL;
		protected float renderScale = 0.5F;
		protected ICoolDown plantCD = ICoolDown.DEFAULT;
		protected IEssenceType plantEssence = EssenceType.ORIGIN;
		protected IRankType plantRank = Ranks.GRAY;
		protected Supplier<EntityType<? extends CreatureEntity>> plantEntitySup;
		protected Supplier<? extends Item> summonCardSup;
		protected Supplier<? extends Item> enjoyCardSup;
		protected IPlantModel<? extends IPlantEntity> plantModel;
		protected Supplier<IPlantType> upgradeFrom;
		protected Supplier<IPlantType> upgradeTo;
		protected Supplier<Block> plantBlock;
		protected Supplier<IPlantInfo> outerPlant;
		protected ICardPlacement cardPlacement = Placements.COMMON;
		protected ResourceLocation lootTable;
		protected boolean isShroomPlant = false;
		protected boolean isWaterPlant = false;
		
		public PlantFeatures cost(int cost) {
			this.sunCost = cost;
			return this;
		}
		
		public PlantFeatures limitLevel(int lvl) {
			this.limitLevel = lvl;
			return this;
		}
		
		public PlantFeatures level(int lvl) {
			this.maxLevel = lvl;
			return this;
		}
		
		public PlantFeatures cd(ICoolDown cd) {
			this.plantCD = cd;
			return this;
		}
		
		public PlantFeatures essence(IEssenceType e) {
			this.plantEssence = e;
			return this;
		}
		
		public PlantFeatures rank(IRankType r) {
			this.plantRank = r;
			return this;
		}
		
		public PlantFeatures entityType(Supplier<EntityType<? extends CreatureEntity>> sup) {
			this.plantEntitySup = sup;
			return this;
		}
		
		public PlantFeatures summonCard(Supplier<? extends Item> sup) {
			this.summonCardSup = sup;
			return this;
		}
		
		public PlantFeatures enjoyCard(Supplier<? extends Item> sup) {
			this.enjoyCardSup = sup;
			return this;
		}
		
		public PlantFeatures plantModel(Supplier<SafeCallable<IPlantModel<? extends IPlantEntity>>> sup) {
			this.plantModel = DistExecutor.safeCallWhenOn(Dist.CLIENT, sup);
			return this;
		}
		
		public PlantFeatures scale(float scale) {
			this.renderScale = scale;
			return this;
		}
		
		public PlantFeatures upgradeFrom(Supplier<IPlantType> sup) {
			this.upgradeFrom = sup;
			return this;
		}
		
		public PlantFeatures upgradeTo(Supplier<IPlantType> sup) {
			this.upgradeTo = sup;
			return this;
		}

		public PlantFeatures placement(ICardPlacement placement) {
			this.cardPlacement = placement;
			return this;
		}

		public PlantFeatures loot(ResourceLocation loot){
			this.lootTable = loot;
			return this;
		}
		
		public PlantFeatures plantBlock(Supplier<Block> plantBlock) {
			this.plantBlock = plantBlock;
			return this;
		}
		
		public PlantFeatures outerPlant(Supplier<IPlantInfo> info) {
			this.outerPlant = info;
			this.cardPlacement = Placements.NONE;
			return this;
		}
		
		public PlantFeatures isShroomPlant() {
			this.isShroomPlant = true;
			this.cardPlacement = Placements.SHROOM;
			return this;
		}
		
		public PlantFeatures isWaterPlant() {
			this.isWaterPlant = true;
			this.cardPlacement = Placements.NONE;
			return this;
		}
		
	}

	public static class PlantTypeLoader extends JsonReloadListener {

		public static final String NAME = "plant";
		private static final Gson GSON = (new GsonBuilder()).create();

		public PlantTypeLoader() {
			super(GSON, NAME + "s");
		}

		@Override
		protected void apply(Map<ResourceLocation, JsonElement> map, IResourceManager manager, IProfiler profiler) {
//			/* refresh */
//			RAID_MAP.clear();
//
//			/* load */
//			map.forEach((res, jsonElement) -> {
//				try {
//					JsonObject jsonObject = JSONUtils.convertToJsonObject(jsonElement, NAME);
//					String type = JSONUtils.getAsString(jsonObject, StringUtil.TYPE, "");
//					IRaidComponent raid = RaidManager.getRaidType(type);
//					if(! raid.readJson(jsonObject)) {
//						CRaid.LOGGER.debug("Skipping loading custom raid {} as it's conditions were not met", res);
//						return;
//					}
//					RAID_MAP.put(res, raid);
//				} catch (IllegalArgumentException | JsonParseException e) {
//					CRaid.LOGGER.error("Parsing error loading custom raid {}: {}", res, e.getMessage());
//				}
//			});
//
//			/* finish */
//			RaidManager.finishRaidMap(RAID_MAP);
//
//			CRaid.LOGGER.info("Loaded {} Custom Raids", RAID_MAP.size());
		}
	}
	
}
