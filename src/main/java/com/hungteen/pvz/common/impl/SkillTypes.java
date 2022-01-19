package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.utils.ArrayUtil;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.*;

public class SkillTypes {

    private static final Map<String, ISkillType> SKILL_MAP = new HashMap<>();
    public static final String SKILL_TAG = "paz_skill_tag";
    public static final int COOL_DOWN_LEVEL = 5;

    public static final ISkillType PLANT_MORE_LIFE = new SkillType("plant_more_life",
            ArrayUtil.getAverageArray(4, 20F, 50F),
            Arrays.asList(2, 3, 4)
    );

    public static final ISkillType FAST_CD = new SkillType("fast_cd",
            ArrayUtil.getAverageArray(COOL_DOWN_LEVEL + 1, 0F, 5F),
            Arrays.asList(1, 2, 3, 3, 4)
    );

    public static final ISkillType LESS_SUN = new SkillType("less_sun",
            ArrayUtil.getAverageArray(4, 0F, 75F),
            Arrays.asList(3, 5, 7)
    );

    public static final ISkillType PEA_DAMAGE = new SkillType("pea_damage",
            ArrayUtil.getAverageArray(5, 1.5F, 2.5F),
            Arrays.asList(2, 4, 5, 6)
    );

//    public static final ISkillType BIG_PEA = new SkillType("big_pea", "pvz:special_pea",
//            ArrayUtil.getAverageArray(6, 0F, 0.04F),
//            Arrays.asList(20, 30, 50, 75, 100)
//    );
//
//    public static final ISkillType KB_PEA = new SkillType("kb_pea", "pvz:special_pea",
//            ArrayUtil.getAverageArray(6, 0F, 0.1F),
//            Arrays.asList(20, 30, 50, 75, 100)
//    );


    public static void registerSkillType(ISkillType type){
        SKILL_MAP.put(type.getIdentity(), type);
    }

    public static int getSkillLevel(CompoundNBT nbt, ISkillType type){
        if(nbt.contains(type.getIdentity())){
            return nbt.getInt(type.getIdentity());
        }
        return 0;
    }

    public static ISkillType getSkillType(String name){
        return SKILL_MAP.get(name);
    }

    public static class SkillType implements ISkillType {

        private static final List<ISkillType> SKILLS = new ArrayList<>();
        private final List<Float> values = new ArrayList<>();
        private final List<Integer> costs = new ArrayList<>();
        private final String name;
        private final String group;

        public SkillType(String name, Collection<Float> values, Collection<Integer> costs){
            this.name = name;
            this.group = this.getIdentity();
            this.values.addAll(values);
            this.costs.addAll(costs);
            SKILLS.add(this);
        }

//        public SkillType(String name, String group, Collection<Float> values, Collection<Integer> costs){
//            this.name = name;
//            this.group = group;
//            this.values.addAll(values);
//            this.costs.addAll(costs);
//            SKILLS.add(this);
//        }

        public static void register(){
            PVZAPI.get().registerSkillTypes(SKILLS);
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
            return new TranslationTextComponent("skill." + this.getModID() + "." + this.toString());
        }

        @Override
        public String getModID() {
            return PVZMod.MOD_ID;
        }

        @Override
        public float getValueAt(int pos) {
            return this.values.get(pos);
        }

        @Override
        public int getCostAt(int pos) {
            return this.costs.get(pos);
        }

        @Override
        public String getConflictGroup() {
            return this.group;
        }

        @Override
        public int getMaxLevel() {
            return this.costs.size();
        }
    }
}
