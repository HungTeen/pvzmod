package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.utils.ArrayUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 12:16
 **/
public class SkillTypes {

    public static final String SKILL_TAG = "PAZSkills";
    private static final List<ISkillType> SKILLS = new ArrayList<>();

    public static void addSkillLevel(ItemStack stack, ISkillType type, int lvl){
        if(stack.getOrCreateTag().contains(SKILL_TAG)) {
            final CompoundTag tmp = stack.getOrCreateTag().getCompound(SKILL_TAG);
            tmp.putInt(type.getIdentity(), lvl);
            stack.getOrCreateTag().put(SKILL_TAG, tmp);
        } else {
            final CompoundTag tmp = new CompoundTag();
            tmp.putInt(type.getIdentity(), lvl);
            stack.getOrCreateTag().put(SKILL_TAG, tmp);
        }
    }

    public static void addSkillLevel(CompoundTag nbt, ISkillType type, int lvl){
        if(nbt.contains(SKILL_TAG)) {
            final CompoundTag tmp = nbt.getCompound(SKILL_TAG);
            tmp.putInt(type.getIdentity(), lvl);
            nbt.put(SKILL_TAG, tmp);
        } else {
            final CompoundTag tmp = new CompoundTag();
            tmp.putInt(type.getIdentity(), lvl);
            nbt.put(SKILL_TAG, tmp);
        }
    }

    /**
     * get skill with paz entity skill tag.
     */
    public static int getSkillLevel(CompoundTag nbt, ISkillType type){
        if(nbt.contains(type.getIdentity())){
            return nbt.getInt(type.getIdentity());
        }
        return 0;
    }

    /**
     * get skill with item stack skill tag.
     */
    public static int getSkillLevel(ItemStack stack, ISkillType type){
        if(stack.getOrCreateTag().contains(SKILL_TAG)) {
            final CompoundTag tmp = stack.getOrCreateTag().getCompound(SKILL_TAG);
            if(tmp.contains(type.getIdentity())){
                return tmp.getInt(type.getIdentity());
            }
        }
        return 0;
    }

    /*
    Plant Skills.
     */

    //peashooter.
    public static final ISkillType PLANT_MORE_LIFE = new SkillType("plant_more_life",
            ArrayUtil.getAverageArray(4, 20F, 50F),
            Arrays.asList(2, 3, 4)
    );

    //peashooter.
    public static final ISkillType PEA_DAMAGE = new SkillType("pea_damage",
            ArrayUtil.getAverageArray(5, 1.5F, 2.5F),
            Arrays.asList(1, 2, 4, 6)
    );

    //peashooter.
    public static final ISkillType PEA_SPEED_UP = new SkillType("pea_speed_up",
            ArrayUtil.getAverageArray(3, 1.2F, 2F),
            Arrays.asList(3, 5)
    );

    //wall nut.
    public static final ISkillType NUT_MORE_LIFE = new SkillType("nut_more_life",
            ArrayUtil.getAverageArray(6, 300F, 500F),
            Arrays.asList(3, 4, 5, 6, 8)
    );

    //wall nut.
    public static final ISkillType EXPLOSION_NUT = new SkillType("explosion_nut",
            ArrayUtil.getAverageArray(3, 0, 200F),
            Arrays.asList(5, 10)
    );

    //wall nut.
    public static final ISkillType BOWLING_NUT = new SkillType("bowling_nut",
            ArrayUtil.getAverageArray(2, 0F, 1F),
            Arrays.asList(8),
            true
    );

    //potato mine.
    public static final ISkillType MINE_FAST_PREPARE = new SkillType("mine_fast_prepare",
            ArrayUtil.getAverageArray(5, 300F, 100F),
            Arrays.asList(1, 2, 4, 6)
    );

    //potato mine.
    public static final ISkillType POTATO_BOMB_DAMAGE = new SkillType("potato_bomb_damage",
            ArrayUtil.getAverageArray(6, 150F, 300F),
            Arrays.asList(2, 2, 3, 3, 5)
    );

    public static class SkillType implements ISkillType {

        private final List<Float> values = new ArrayList<>();
        private final List<Integer> costs = new ArrayList<>();
        private final String name;
        private final boolean isSpecial;

        public SkillType(String name, Collection<Float> values, Collection<Integer> costs){
            this(name, values, costs, false);
        }

        public SkillType(String name, Collection<Float> values, Collection<Integer> costs, boolean special){
            this.name = name;
            this.values.addAll(values);
            this.costs.addAll(costs);
            this.isSpecial = special;
            SKILLS.add(this);
        }

        public static void register(){
            SKILLS.forEach(skill -> PVZAPI.get().registerSkillType(skill));
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getIdentity() {
            return Util.identify(this.getModID(), this.toString());
        }


        @Override
        public boolean isSpecial() {
            return isSpecial;
        }

        @Override
        public MutableComponent getText() {
            return new TranslatableComponent("skill." + this.getModID() + "." + this.toString());
        }

        @Override
        public MutableComponent getDescription() {
            return new TranslatableComponent("skill." + this.getModID() + "." + this.toString() + ".desc");
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
        public int getMaxLevel() {
            return this.costs.size();
        }
    }
}
