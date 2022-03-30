package com.hungteen.pvz.common.impl.type.plant;

import com.hungteen.pvz.api.interfaces.IPlantInfo;
import com.hungteen.pvz.api.types.*;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.common.impl.type.PlaceTypes;
import com.hungteen.pvz.utils.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:54
 *
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you want to show in front of that category, then override one with higher priority. <br>
 *
 * step 1. new your own plant type at {@link PVZPlants}
 * step 2. create and register plant entity at {@link com.hungteen.pvz.common.entity.PVZEntities}
 * step 3. put your entity texture at texture/entity/plant/{plant_name}/{plant_name}.png
 * step 4. put your item texture at texture/art/plant/{plant_name}.png
 */
public abstract class PlantType extends PAZTypes.PAZType implements IPlantType {

    protected IEssenceType plantEssence = EssenceTypes.APPEASE;
    protected Supplier<Block> plantBlock;
    protected Supplier<IPlantInfo> outerPlant;
    protected IPlaceType cardPlacement = PlaceTypes.COMMON;
    protected boolean isShroomPlant;
    protected boolean isWaterPlant;

    protected PlantType(String name) {
        super(name);
    }

    @Override
    public IPlaceType getPlacement() {
        return this.cardPlacement;
    }

    @Override
    public Optional<Block> getPlantBlock() {
        return this.plantBlock == null ? Optional.empty() : Optional.ofNullable(this.plantBlock.get());
    }

    @Override
    public boolean isWaterPlant() {
        return this.isWaterPlant;
    }

    @Override
    public boolean isShroomPlant() {
        return this.isShroomPlant;
    }

    @Override
    public Optional<IPlantInfo> getOuterPlant() {
        return this.outerPlant == null ? Optional.empty() : Optional.ofNullable(this.outerPlant.get());
    }

    @Override
    public IEssenceType getEssence() {
        return this.plantEssence;
    }

    @Override
    public ResourceLocation getDefaultResource() {
        return Util.prefix("textures/entity/plant/" + this.name + "/" + this.name + ".png");
    }

    /*
    Set Methods.
    */

    public PlantType sunCost(int cost){
        this.sunCost = cost;
        return this;
    }

    public PlantType xp(int xp){
        this.xpPoint = xp;
        return this;
    }

    public PlantType cd(int cd) {
        this.coolDown = cd;
        return this;
    }

    public PlantType rank(IRankType type) {
        this.rankType = type;
        return this;
    }

    public PlantType entity(Supplier<EntityType<? extends PathfinderMob>> supplier) {
        this.entitySup = supplier;
        return this;
    }

    public PlantType summonCard(Supplier<? extends Item> supplier) {
        this.summonCardSup = supplier;
        return this;
    }

    public PlantType enjoyCard(Supplier<? extends Item> supplier) {
        this.enjoyCardSup = supplier;
        return this;
    }

    public PlantType skill(ISkillType skillType){
        this.skills.add(skillType);
        return this;
    }

    public PlantType skills(List<ISkillType> skills){
        this.skills = skills;
        return this;
    }

    public PlantType loot(ResourceLocation lootTable){
        this.lootTable = lootTable;
        return this;
    }

    public PlantType upgradeFrom(Supplier<IPAZType> supplier) {
        this.upgradeFrom = supplier;
        return this;
    }

    public PlantType upgradeTo(Supplier<IPAZType> supplier) {
        this.upgradeTo = supplier;
        return this;
    }

    /*
    Plant Only.
     */

    public PlantType essence(IEssenceType essenceType){
        this.plantEssence = essenceType;
        return this;
    }

    public PlantType placement(IPlaceType placeType){
        this.cardPlacement = placeType;
        return this;
    }

}
