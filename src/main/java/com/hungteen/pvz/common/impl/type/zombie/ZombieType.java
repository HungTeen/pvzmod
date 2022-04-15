package com.hungteen.pvz.common.impl.type.zombie;

import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.common.impl.type.plant.PlantType;
import com.hungteen.pvz.utils.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-04 19:22
 **/
public abstract class ZombieType extends PAZTypes.PAZType implements IZombieType {

    protected IEssenceType plantEssence = EssenceTypes.APPEASE;

    protected ZombieType(String name) {
        super(name);
    }

    @Override
    public IEssenceType getEssenceType() {
        return this.plantEssence;
    }


    @Override
    public ResourceLocation getDefaultResource() {
        return Util.prefix("textures/entity/zombie/" + this.name + "/" + this.name + ".png");
    }

    /*
    Set Methods.
    */

    public ZombieType sunCost(int cost){
        this.sunCost = cost;
        return this;
    }

    public ZombieType xp(int xp){
        this.xpPoint = xp;
        return this;
    }

    public ZombieType cd(int cd) {
        this.coolDown = cd;
        return this;
    }

    public ZombieType rank(IRankType type) {
        this.rankType = type;
        return this;
    }

    public ZombieType entity(Supplier<EntityType<? extends PathfinderMob>> supplier) {
        this.entitySup = supplier;
        return this;
    }

    public ZombieType summonCard(Supplier<? extends Item> supplier) {
        this.summonCardSup = supplier;
        return this;
    }

    public ZombieType enjoyCard(Supplier<? extends Item> supplier) {
        this.enjoyCardSup = supplier;
        return this;
    }

    public ZombieType skill(ISkillType skillType){
        this.skills.add(skillType);
        return this;
    }

    public ZombieType skills(List<ISkillType> skills){
        this.skills = skills;
        return this;
    }

    public ZombieType loot(ResourceLocation lootTable){
        this.lootTable = lootTable;
        return this;
    }

    public ZombieType upgradeFrom(Supplier<IPAZType> supplier) {
        this.upgradeFrom = supplier;
        return this;
    }

    public ZombieType upgradeTo(Supplier<IPAZType> supplier) {
        this.upgradeTo = supplier;
        return this;
    }

    /*
    Plant Only.
     */

    public ZombieType essence(IEssenceType essenceType){
        this.plantEssence = essenceType;
        return this;
    }

}
