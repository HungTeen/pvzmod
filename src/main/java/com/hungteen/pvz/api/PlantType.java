package com.hungteen.pvz.api;

import java.util.Optional;
import java.util.function.Supplier;

import com.hungteen.pvz.client.model.entity.plant.IPlantModel;
import com.hungteen.pvz.common.core.EssenceType;
import com.hungteen.pvz.common.core.ICardPlacement;
import com.hungteen.pvz.common.core.ICoolDown;
import com.hungteen.pvz.common.core.RankType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.PlantInfo;
import com.hungteen.pvz.common.impl.Essences;
import com.hungteen.pvz.common.impl.Placements;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeCallable;

public abstract class PlantType {

	protected final String plantName;
	protected final ResourceLocation plantEntityResource;
	protected int sunCost;
	protected int maxLevel;
	protected float renderScale;
	protected ICoolDown plantCD;
	protected EssenceType plantEssence;
	protected RankType plantRank;
	protected Supplier<EntityType<? extends PVZPlantEntity>> plantEntitySup;
	protected Supplier<? extends PlantCardItem> summonCardSup;
	protected Supplier<? extends PlantCardItem> enjoyCardSup;
	protected IPlantModel<? extends PVZPlantEntity> plantModel;
	protected Supplier<PlantType> upgradeFrom;
	protected Supplier<PlantType> upgradeTo;
	protected Supplier<Block> plantBlock;
	protected Supplier<PlantInfo> outerPlant;
	protected ICardPlacement cardPlacement;
	protected boolean isShroomPlant;
	protected boolean isWaterPlant;
	
	public PlantType(String name, PlantFeatures features) {
		this.plantName = name;
		this.sunCost = features.sunCost;
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
	}
	
	@Override
	public String toString() {
		return this.plantName;
	}
	
	/**
	 * use to specify plant store information.
	 */
	public String getIdentity() {
		return this.getModID() + ":" + this.toString();
	}
	
	/**
	 * get the sun cost of plant.
	 */
	public int getCost() {
		return this.sunCost;
	}
	
	/**
	 * get the max level of plant.
	 */
	public int getMaxLevel() {
		return this.maxLevel;
	}
	
	/**
	 * get the cool down of plant summon card.
	 */
	public ICoolDown getCD() {
		return this.plantCD;
	}
	
	/**
	 * get the essence type that the plant belongs to.
	 */
	public EssenceType getEssence() {
		return this.plantEssence;
	}
	
	/**
	 * get the rank of plants.
	 */
	public RankType getRank() {
		return this.plantRank;
	}
	
	/**
	 * get the entity type of current plant.
	 */
	public Optional<EntityType<? extends PVZPlantEntity>> getEntityType() {
		return this.plantEntitySup == null ? Optional.empty() : Optional.ofNullable(this.plantEntitySup.get());
	}
	
	/**
	 * get the summon card item of plant.
	 */
	public Optional<? extends PlantCardItem> getSummonCard() {
		return this.summonCardSup == null ? Optional.empty() : Optional.ofNullable(this.summonCardSup.get());
	}
	
	/**
	 * get the enjoy card item of plant.
	 */
	public Optional<? extends PlantCardItem> getEnjoyCard() {
		return this.enjoyCardSup == null ? Optional.empty() : Optional.ofNullable(this.enjoyCardSup.get());
	}
	
	/**
	 * get the entity model of plant (Client Side). 
	 */
	@OnlyIn(Dist.CLIENT)
	public Optional<IPlantModel<? extends PVZPlantEntity>> getPlantModel() {
		return Optional.ofNullable(this.plantModel);
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getRenderScale() {
		return this.renderScale;
	}
	
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getRenderResource() {
		return this.plantEntityResource;
	}
	
	/**
	 * what type the plant upgrade from.
	 */
	public Optional<PlantType> getUpgradeFrom() {
		return this.upgradeFrom == null ? Optional.empty() : Optional.ofNullable(this.upgradeFrom.get());
	}
	
	/**
	 * what type the plant upgrade to.
	 */
	public Optional<PlantType> getUpgradeTo() {
		return this.upgradeTo == null ? Optional.empty() : Optional.ofNullable(this.upgradeTo.get());
	}
	
	/**
	 * can plant card place on.
	 */
	public ICardPlacement getPlacement() {
		return this.cardPlacement;
	}
	
	/**
	 * the shroom type : it need sleep at night. <br>
	 * such as Puff Shroom.
	 */
	public boolean isShroomPlant() {
		return this.isShroomPlant;
	}
	
	/**
	 * the block type : it's not an entity, but a block. <br>
	 * such as Lily Pad.
	 */
	public boolean isBlockPlant() {
		return this.plantBlock != null;
	}
	
	/**
	 * get corresponding block.
	 */
	public Optional<Block> getPlantBlock() {
		return Optional.ofNullable(this.plantBlock.get());
	}
	
	/**
	 * the water type : it only lives in water. <br>
	 * such as Tangle Kelp.
	 */
	public boolean isWaterPlant() {
		return this.isWaterPlant;
	}
	
	/**
	 * the outer type : it's not an entity, but a render layer. <br>
	 * such as Pumpkin.
	 */
	public boolean isOuterPlant() {
		return this.outerPlant != null;
	}
	
	/**
	 * get corresponding block.
	 */
	public Optional<PlantInfo> getOuterPlant() {
		return Optional.ofNullable(this.outerPlant.get());
	}
	
	/**
	 * the resource to save entity render picture.
	 */
	protected ResourceLocation getEntityResource() {
		final String sep = this.getEssence().toString();
		return new ResourceLocation(this.getModID(), "textures/entity/plant/" + sep + "/" + this.toString() + ".png");
	}
//	
//	/**
//	 * get plant corresponding id in plant list.
//	 */
//	public final int getId() {
//		if(PLANTS_ID.containsKey(this)) {
//			return PLANTS_ID.get(this);
//		}
//		PVZMod.LOGGER.error("Plant Type : Missing Id for registered plant type [ " + this.toString() + " ]");
//		return 0;
//	}
	
	/**
	 * get plant translation text.
	 */
	public TranslationTextComponent getText() {
		return new TranslationTextComponent("entity." + this.getModID() + "." + this.toString());
	}
	
	/**
	 * the priority of this group to stay forward in list. <br>
	 * larger means higher priority. <br>
	 * such as show front in Almanac.
	 */
	public abstract int getSortPriority();
	
	/**
	 * category of the group of plants.
	 */
	public abstract String getCategoryName();
	
	/**
	 * specific mod id.
	 */
	public abstract String getModID();
	
	public static final class PlantFeatures{
		protected int sunCost = 9999;
		protected int maxLevel = PlantUtil.MAX_PLANT_LEVEL;
		protected float renderScale = 0.5F;
		protected ICoolDown plantCD = ICoolDown.DEFAULT;
		protected EssenceType plantEssence = Essences.ORIGIN;
		protected RankType plantRank = Ranks.GRAY;
		protected Supplier<EntityType<? extends PVZPlantEntity>> plantEntitySup;
		protected Supplier<? extends PlantCardItem> summonCardSup;
		protected Supplier<? extends PlantCardItem> enjoyCardSup;
		protected IPlantModel<? extends PVZPlantEntity> plantModel;
		protected Supplier<PlantType> upgradeFrom;
		protected Supplier<PlantType> upgradeTo;
		protected Supplier<Block> plantBlock;
		protected Supplier<PlantInfo> outerPlant;
		protected ICardPlacement cardPlacement = Placements.COMMON;
		protected boolean isShroomPlant = false;
		protected boolean isWaterPlant = false;
		
		/**
		 * set the sun cost of plant.
		 */
		public PlantFeatures cost(int cost) {
			this.sunCost = cost;
			return this;
		}
		
		/**
		 * set the max level of plant.
		 */
		public PlantFeatures level(int lvl) {
			this.maxLevel = lvl;
			return this;
		}
		
		/**
		 * set the cool down of plant summon card.
		 */
		public PlantFeatures cd(ICoolDown cd) {
			this.plantCD = cd;
			return this;
		}
		
		/**
		 * set the essence type that the plant belongs to.
		 */
		public PlantFeatures essence(EssenceType e) {
			this.plantEssence = e;
			return this;
		}
		
		/**
		 * set the rank of plants.
		 */
		public PlantFeatures rank(RankType r) {
			this.plantRank = r;
			return this;
		}
		
		/**
		 * set the entity type of current plant.
		 */
		public PlantFeatures entityType(Supplier<EntityType<? extends PVZPlantEntity>> sup) {
			this.plantEntitySup = sup;
			return this;
		}
		
		/**
		 * set the summon card item of plant.
		 */
		public PlantFeatures summonCard(Supplier<? extends PlantCardItem> sup) {
			this.summonCardSup = sup;
			return this;
		}
		
		/**
		 * set the enjoy card item of plant.
		 */
		public PlantFeatures enjoyCard(Supplier<? extends PlantCardItem> sup) {
			this.enjoyCardSup = sup;
			return this;
		}
		
		/**
		 * set the entity model of plant (Client Side). 
		 */
		public PlantFeatures plantModel(Supplier<SafeCallable<IPlantModel<? extends PVZPlantEntity>>> sup) {
			this.plantModel = DistExecutor.safeCallWhenOn(Dist.CLIENT, sup);
			return this;
		}
		
		/**
		 * set the plant's render scale (Client Side). 
		 */
		public PlantFeatures scale(float scale) {
			this.renderScale = scale;
			return this;
		}
		
		/**
		 * it's an upgrade plant from the specific type.
		 */
		public PlantFeatures upgradeFrom(Supplier<PlantType> sup) {
			this.upgradeFrom = sup;
			return this;
		}
		
		/**
		 * it's can upgrade to the specific type.
		 */
		public PlantFeatures upgradeTo(Supplier<PlantType> sup) {
			this.upgradeTo = sup;
			return this;
		}
		
		/**
		 * it's can upgrade to the specific type.
		 */
		public PlantFeatures placement(ICardPlacement placement) {
			this.cardPlacement = placement;
			return this;
		}
		
		/**
		 * it's a block type plant.
		 */
		public PlantFeatures plantBlock(Supplier<Block> plantBlock) {
			this.plantBlock = plantBlock;
			return this;
		}
		
		/**
		 * it's a block type plant.
		 */
		public PlantFeatures outerPlant(Supplier<PlantInfo> info) {
			this.outerPlant = info;
			this.cardPlacement = Placements.NONE;
			return this;
		}
		
		/**
		 * it's shroom type plant.
		 */
		public PlantFeatures isShroomPlant() {
			this.isShroomPlant = true;
			this.cardPlacement = Placements.SHROOM;
			return this;
		}
		
		/**
		 * it's a water only plant.
		 */
		public PlantFeatures isWaterPlant() {
			this.isWaterPlant = true;
			this.cardPlacement = Placements.NONE;
			return this;
		}
		
	}
}
