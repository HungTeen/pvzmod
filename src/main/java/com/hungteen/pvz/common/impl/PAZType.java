package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;
import java.util.function.Supplier;

public abstract class PAZType implements IPAZType {

    private final String name;
    protected int sunCost = 9999;
    protected int requiredLevel = 100;
    protected int xpPoint = 0;
    protected float renderScale = 0.5F;
    protected ICoolDown coolDown = CoolDowns.DEFAULT;
    protected IRankType rankType = RankTypes.WHITE;
    protected ResourceLocation entityRenderResource;
    protected ResourceLocation lootTable;
    protected Supplier<EntityType<? extends CreatureEntity>> entitySup;
    protected Supplier<? extends Item> summonCardSup;
    protected Supplier<? extends Item> enjoyCardSup;
    protected List<ISkillType> skills;

    protected PAZType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getIdentity() {
        return StringUtil.identify(this.getModID(), this.toString());
    }

    @Override
    public TranslationTextComponent getText() {
        return new TranslationTextComponent("entity." + this.getModID() + "." + this.toString());
    }

    @Override
    public int getSunCost() {
        return this.sunCost;
    }

    @Override
    public int getRequiredLevel() {
        return this.requiredLevel;
    }

    @Override
    public int getXpPoint() {
        return this.xpPoint;
    }

    @Override
    public ICoolDown getCoolDown() {
        return this.coolDown;
    }

    @Override
    public IRankType getRank() {
        return this.rankType;
    }

    @Override
    public Optional<EntityType<? extends CreatureEntity>> getEntityType() {
        return this.entitySup == null ? Optional.empty() : Optional.ofNullable(this.entitySup.get());
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
    public List<ISkillType> getSkills() {
        return skills;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getRenderScale() {
        return this.renderScale;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getRenderResource() {
        return this.entityRenderResource;
    }

    @Override
    public ResourceLocation getLootTable() {
        return lootTable;
    }

    /**
     * register type.
     */
    public static <T extends IPAZType> void registerPAZType(Set<T> set, Map<String, List<T>> map, T plant) {
        if(! set.contains(plant)) {
            set.add(plant);
            if(map.containsKey(plant.getCategoryName())) {
                map.get(plant.getCategoryName()).add(plant);
            } else {
                map.put(plant.getCategoryName(), new ArrayList<>(Arrays.asList(plant)));
            }
        } else {
            PVZMod.LOGGER.warn("PAZTypeRegister : already add {}.", plant.toString());
        }
    }

    /**
     * sort plants or zombies type by priority.
     */
    public static <T extends IPAZType> void initPAZs(List<T> list, Map<String, List<T>> categoryMap, Map<T, Integer> byId, Map<String, T> byName) {
        //clear list.
        list.clear();
        //get priority category list.
        final List<Pair<String, Integer>> categoryList = new ArrayList<>();
        categoryMap.keySet().forEach(l -> {
            final T tmp = categoryMap.get(l).get(0);
            categoryList.add(Pair.of(l, tmp.getSortPriority()));
        });
        //sort category by priority.
        Collections.sort(categoryList, new AlgorithmUtil.PairSorter<>());
        //deal with each category list one by one.
        for(Pair<String, Integer> category : categoryList) {
            //get priority category list.
            final List<Pair<T, Integer>> tmp = new ArrayList<>();
            categoryMap.get(category.getFirst()).forEach(l -> tmp.add(Pair.of(l, l.getSortPriority())));
            //sort list by priority.
            Collections.sort(tmp, new AlgorithmUtil.PairSorter<>());
            PVZMod.LOGGER.debug("PAZTypeRegister : sort category [{}] found {} {}.", category.getFirst(), tmp.size(), "types");
            //add to the final result list.
            tmp.forEach(pair -> list.add(pair.getFirst()));
        }
        for(int i = 0; i < list.size(); ++ i) {
            byId.put(list.get(i), i);
            byName.put(list.get(i).getIdentity(), list.get(i));
        }
    }

    /**
     * to update the map from entity type to type.
     */
    public static <T extends IPAZType> void postInit(List<T> list, Map<EntityType<? extends CreatureEntity>, T>  byEntityType) {
        list.forEach(type -> {
            type.getEntityType().ifPresent(l -> {
                byEntityType.put(l, type);
            });
        });
    }

}
