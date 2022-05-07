package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.api.types.ICardType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.Util;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import java.util.*;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 17:17
 *
 * handler plants and zombies type register.
 **/
public class PAZTypes {

    /* all registered types */
    private static final List<IPAZType> PAZS = new ArrayList<>();
    /* category -> paz type list */
    private static final Map<String, List<IPAZType>> CATEGORY_MAP = new HashMap<>();
    /* used to confirm no duplicate */
    private static final Set<IPAZType> PAZ_SET = new HashSet<>();
    /* get type by name */
    private static final Map<String, IPAZType> BY_NAME = new HashMap<>();
    /* entity type -> paz type */
    private static final Map<EntityType<? extends PathfinderMob>, IPAZType> BY_ENTITY_TYPE = new HashMap<>();

    public static List<IPAZType> getPAZs(){
        return Collections.unmodifiableList(PAZS);
    }

    public static Optional<IPAZType> getPAZType(String identity) {
        return Optional.ofNullable(BY_NAME.get(identity));
    }

    /**
     * register type.
     * put type into category.
     */
    public static void registerPAZType(IPAZType type) {
//        Util.debug("registering {}.", type);
        if(! PAZ_SET.contains(type)) {
            PAZ_SET.add(type);
            if(CATEGORY_MAP.containsKey(type.getCategoryName())) {
                CATEGORY_MAP.get(type.getCategoryName()).add(type);
            } else {
                CATEGORY_MAP.put(type.getCategoryName(), new ArrayList<>(Arrays.asList(type)));
            }
        } else {
            Util.warn("PAZ Register : already add {}.", type);
        }
    }

    /**
     * sort plants or zombies type by priority.
     * {@link com.hungteen.pvz.common.item.PVZItems#registerCards(RegistryEvent.Register)}
     */
    public static void initPAZs() {
        //clear list.
        PAZS.clear();
        //get priority category list.
        final List<Pair<String, Integer>> categoryList = new ArrayList<>();
        CATEGORY_MAP.keySet().forEach(l -> {
            final IPAZType tmp = CATEGORY_MAP.get(l).get(0);
            Util.debug("registering categoty {}.", tmp);
            categoryList.add(Pair.of(l, tmp.getSortPriority()));
        });
        //sort category by priority.
        Collections.sort(categoryList, new AlgorithmUtil.PairSorter<>());
        //deal with each category list one by one.
        for(Pair<String, Integer> category : categoryList) {
            //get priority category list.
            final List<Pair<IPAZType, Integer>> tmp = new ArrayList<>();
            CATEGORY_MAP.get(category.getFirst()).forEach(l -> tmp.add(Pair.of(l, l.getSortPriority())));
            //sort list by priority.
            Collections.sort(tmp, new AlgorithmUtil.PairSorter<>());
            Util.debug("PAZ Register : sort category [{}] found {} types.", category.getFirst(), tmp.size());
            //add to the final result list.
            tmp.forEach(pair -> PAZS.add(pair.getFirst()));
        }
        for(int i = 0; i < PAZS.size(); ++ i) {
            BY_NAME.put(PAZS.get(i).getIdentity(), PAZS.get(i));
        }
    }

    /**
     * to update the map from entity type to type.
     * {@link com.hungteen.pvz.common.entity.PVZEntities#addEntityAttributes(EntityAttributeCreationEvent)}
     */
    public static void postInit() {
        PAZS.forEach(type -> {
            type.getEntityType().ifPresent(l -> {
                BY_ENTITY_TYPE.put(l, type);
            });
        });
    }


    /**
     * @program: pvzmod-1.18.x
     * @author: HungTeen
     * @create: 2022-03-26 15:53
     **/
    public abstract static class PAZType implements IPAZType {

        protected final String name;
        protected int sunCost = 9999;
        protected int xpPoint = 0;
        protected int coolDown = 0;
        protected IRankType rankType = RankTypes.WHITE;
        protected ICardType cardType = CardTypes.SAPLING;
        protected ResourceLocation lootTable;
        protected Supplier<? extends Item> summonCardSup;
        protected Supplier<? extends Item> enjoyCardSup;
        protected Supplier<IPAZType> upgradeFrom;
        protected Supplier<IPAZType> upgradeTo;
        protected Supplier<EntityType<? extends PathfinderMob>> entitySup;
        protected List<ISkillType> skills = new ArrayList<>();

        protected PAZType(String name){
            this.name = name;
        }

        /*
        Attributes that can not modify.
         */

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getIdentity() {
            return Util.identify(this.getModID(), this.toString());
        }

        @Override
        public MutableComponent getText() {
            return new TranslatableComponent("entity." + this.getModID() + "." + this.toString());
        }

        /*
        Modifiable attributes.
         */

        @Override
        public int getSunCost() {
            return this.sunCost;
        }

        @Override
        public int getXpPoint() {
            return this.xpPoint;
        }

        @Override
        public int getCoolDown() {
            return this.coolDown;
        }

        @Override
        public IRankType getRankType() {
            return this.rankType;
        }

        @Override
        public ICardType getCardType() {
            return cardType;
        }

        @Override
        public Optional<EntityType<? extends PathfinderMob>> getEntityType() {
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
        public Optional<IPAZType> getUpgradeFrom() {
            return this.upgradeFrom == null ? Optional.empty() : Optional.ofNullable(this.upgradeFrom.get());
        }

        @Override
        public Optional<IPAZType> getUpgradeTo() {
            return this.upgradeTo == null ? Optional.empty() : Optional.ofNullable(this.upgradeTo.get());
        }

        @Override
        public List<ISkillType> getSkills() {
            return skills;
        }

        @Override
        public ResourceLocation getLootTable() {
            return lootTable;
        }

    }
}
