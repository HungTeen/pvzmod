package com.hungteen.pvz.api.types;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;

/**
 * used for both plants and zombies type interfaces.
 */
public interface IPAZType extends IIDType {

	/**
	 * get the sun cost of role.
	 * how many sun amount will cost when use its card.
	 */
	int getSunCost();
	
	/**
	 * get limit maxLevel to use its card.
	 * if tree maxLevel doesn't reach requirement, then players can not use its card.
	 */
	int getRequiredLevel();

	/**
	 * get the experience point of role.
	 */
	int getXpPoint();

	/**
	 * get the cool down of summon card of current type.
	 */
	ICoolDown getCoolDown();
	
	/**
	 * get the rank of type.
	 */
	IRankType getRank();

	/**
	 * get the essence type that the plant belongs to.
	 */
	IEssenceType getEssence();

	/**
	 * get the entity type of current type.
	 */
	Optional<EntityType<? extends CreatureEntity>> getEntityType();
	
	/**
	 * get summon card item of type.
	 */
	Optional<? extends Item> getSummonCard();
	
	/**
	 * get enjoy card item of type.
	 */
	Optional<? extends Item> getEnjoyCard();

	List<ISkillType> getSkills();
	
	/**
	 * get type corresponding id in type list.
	 * used to sort card item list.
	 */
	int getId();
	
	/**
	 * the priority of this group to stay forward in list. <br>
	 * larger means higher priority. <br>
	 * such as show front in Almanac.
	 */
	int getSortPriority();
	
	/**
	 * category of the group of type.
	 */
	String getCategoryName();
	
	/**
	 * default render scale of model.
	 */
	@OnlyIn(Dist.CLIENT)
	float getRenderScale();
	
	/**
	 * render texture of type.
	 */
	@OnlyIn(Dist.CLIENT)
	ResourceLocation getRenderResource();

	ResourceLocation getLootTable();
	
}
