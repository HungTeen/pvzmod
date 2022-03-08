package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.paz.IZombieEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.impl.*;
import com.hungteen.pvz.common.world.spawn.SpawnChecker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import java.util.*;
import java.util.concurrent.Callable;
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
		this.xpPoint = features.xpPoint;
		this.renderScale = features.renderScale;
		this.coolDown = features.coolDown;
		this.rankType = features.rankType;
		this.lootTable = features.lootTable;
		this.entitySup = features.entitySup;
		this.summonCardSup = features.summonCardSup;
		this.enjoyCardSup = features.enjoyCardSup;
		this.skills = features.skillTypes;
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
	
	@Override
	public IEssenceType getEssence() {
		//TODO Essence of Zombies.
		return EssenceTypes.ORIGIN;
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
    
	public static void registerZombie(IZombieType zombie) {
		registerPAZType(ZOMBIE_SET, CATEGORY_MAP, zombie);
	}
	
	public static void registerZombies(Collection<IZombieType> zombies) {
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
	public TranslatableComponent getTranslateText() {
		return new TranslatableComponent("entity." + this.getModID() + "." + this.toString());
	}
	
	/**
	 * specific mod id.
	 */
	public abstract String getModID();
	
	public static final class ZombieFeatures{
		//common.
		protected int sunCost = 9999;
		protected int requiredLevel = 100;
		protected int xpPoint = 0;
		protected float renderScale = 0.5F;
		protected ICoolDown coolDown = CoolDowns.DEFAULT;
		protected IRankType rankType = RankTypes.GRAY;
		protected ResourceLocation entityRenderResource;
		protected ResourceLocation lootTable;
		protected Supplier<EntityType<? extends CreatureEntity>> entitySup;
		protected Supplier<? extends Item> summonCardSup;
		protected Supplier<? extends Item> enjoyCardSup;
		protected List<ISkillType> skillTypes = new ArrayList<>();
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

		public ZombieFeatures skill(Collection<ISkillType> skills){
			this.skillTypes.addAll(skills);
			return this;
		}

		public ZombieFeatures baseSkill(Collection<ISkillType> skills){
			this.skillTypes.addAll(Arrays.asList(SkillTypes.TOUGH_BODY, SkillTypes.FAST_CD));
			return this.skill(skills);
		}

		public ZombieFeatures commonSkill(Collection<ISkillType> skills){
			this.skillTypes.addAll(Arrays.asList( SkillTypes.FAST_CD, SkillTypes.ZOMBIE_FAST_MOVE));
			return this.baseSkill(skills);
		}

		public ZombieFeatures eatCommonSkill(Collection<ISkillType> skills){
			this.skillTypes.addAll(Arrays.asList(SkillTypes.HIGH_EAT_DAMAGE));
			return this.commonSkill(skills);
		}
		
		public ZombieFeatures zombieModel(Supplier<Callable<IZombieModel<? extends IZombieEntity>>> sup) {
			this.zombieModel1 = Optional.ofNullable(DistExecutor.unsafeCallWhenOn(Dist.CLIENT, sup));
			this.zombieModel2 = Optional.ofNullable(DistExecutor.unsafeCallWhenOn(Dist.CLIENT, sup));
			return this;
		}
		
		public ZombieFeatures scale(float scale) {	
			this.renderScale = scale;
			return this;
		}
		
	}
	
}
