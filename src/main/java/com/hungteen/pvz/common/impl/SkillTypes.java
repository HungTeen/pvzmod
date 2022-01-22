package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.utils.ArrayUtil;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.*;

public class SkillTypes {

    private static final Map<String, ISkillType> SKILL_MAP = new HashMap<>();
    public static final String SKILL_TAG = "paz_skill_tag";
    public static final int COOL_DOWN_LEVEL = 5;

    /*
    Skills for both plants and zombies.
     */
    public static final ISkillType FAST_CD = new SkillType("fast_cd",
            ArrayUtil.getAverageArray(COOL_DOWN_LEVEL + 1, 0F, 5F),
            Arrays.asList(1, 2, 3, 3, 4)
    );

    public static final ISkillType LESS_SUN = new SkillType("less_sun",
            ArrayUtil.getAverageArray(4, 0F, 75F),
            Arrays.asList(3, 5, 7)
    );

    /*
    Skills of plants.
     */

    //peashooter.
    public static final ISkillType PLANT_MORE_LIFE = new SkillType("plant_more_life",
            ArrayUtil.getAverageArray(4, 20F, 50F),
            Arrays.asList(2, 3, 4)
    );

    //peashooter.
    public static final ISkillType PEA_DAMAGE = new SkillType("pea_damage",
            ArrayUtil.getAverageArray(5, 1.5F, 2.5F),
            Arrays.asList(2, 4, 5, 6)
    );

    //cherry bomb.
    public static final ISkillType NORMAL_BOMB_DAMAGE = new SkillType("normal_bomb_damage",
            ArrayUtil.getAverageArray(6, 150F, 300F),
            Arrays.asList(2, 3, 3, 4, 5)
    );

    //wall nut.
    public static final ISkillType NUT_MORE_LIFE = new SkillType("nut_more_life",
            ArrayUtil.getAverageArray(6, 300F, 500F),
            Arrays.asList(3, 4, 5, 6, 8)
    );

    //potato mine.
    public static final ISkillType MINE_FAST_PREPARE = new SkillType("mine_fast_prepare",
            ArrayUtil.getAverageArray(5, 240F, 40F),
            Arrays.asList(3, 3, 4, 6)
    );

    //chomper.
    public static final ISkillType NORMAL_ENHANCE_STRENGTH = new SkillType("normal_enhance_strength",
            ArrayUtil.getAverageArray(5, 140F, 300F),
            Arrays.asList(3, 4, 6, 7)
    );

    //puff shroom.
    public static final ISkillType SPORE_DAMAGE = new SkillType("spore_damage",
            ArrayUtil.getAverageArray(5, 1.25F, 2.25F),
            Arrays.asList(2, 4, 5, 6)
    );

    //doom shroom.
    public static final ISkillType HIGH_EXPLODE_DAMAGE = new SkillType("high_explode_damage",
            ArrayUtil.getAverageArray(7, 200F, 500F),
            Arrays.asList(3, 3, 4, 5, 6, 8)
    );

    //squash.
    public static final ISkillType SQUASH_AGAIN = new SkillType("squash_again",
            ArrayUtil.getAverageArray(4, 0.1F, 0.4F),
            Arrays.asList(1, 2, 4)
    );

    //spike weed
    public static final ISkillType SPIKE_DAMAGE = new SkillType("spike_damage",
            ArrayUtil.getAverageArray(5, 2F, 3.6F),
            Arrays.asList(2, 3, 5, 7)
    );

    //spike rock
    public static final ISkillType MORE_SPIKE = new SkillType("more_spike",
            ArrayUtil.getAverageArray(4, 4F, 13F),
            Arrays.asList(1, 3, 5)
    );

    //torch wood
    public static final ISkillType HEAT_PEA_RANGE = new SkillType("heat_pea_range",
            ArrayUtil.getAverageArray(4, 1.5F, 3F),
            Arrays.asList(2, 4, 6)
    );

    //torch wood
    public static final ISkillType WOOD_MORE_LIFE = new SkillType("wood_more_life",
            ArrayUtil.getAverageArray(6, 50F, 100F),
            Arrays.asList(1, 2, 3, 5, 7)
    );

    //plantern
    public static final ISkillType MORE_LIGHT_RANGE = new SkillType("more_light_range",
            ArrayUtil.getAverageArray(3, 20F, 40F),
            Arrays.asList(3, 4)
    );

    //cactus
    public static final ISkillType MORE_THORN_DAMAGE = new SkillType("more_thorn_damage",
            ArrayUtil.getAverageArray(6, 2F, 3.5F),
            Arrays.asList(2, 3, 5, 6, 9)
    );

    //blover.
    public static final ISkillType BLOW_STRENGTH = new SkillType("blow_strength",
            ArrayUtil.getAverageArray(5, 10F, 50F),
            Arrays.asList(2, 3, 5, 7)
    );

    //split pea.
    public static final ISkillType SPLIT_DOUBLE_CHANCE = new SkillType("split_double_chance",
            ArrayUtil.getAverageArray(4, 0.2F, 0.8F),
            Arrays.asList(2, 3, 5, 7)
    );

    //star fruit.
    public static final ISkillType MORE_STAR_DAMAGE = new SkillType("more_star_damage",
            ArrayUtil.getAverageArray(6, 1.75F, 3.75F),
            Arrays.asList(3, 5, 6, 8, 10)
    );

    //magnet shroom.
    public static final ISkillType LESS_WORK_CD = new SkillType("less_work_cd",
            ArrayUtil.getAverageArray(4, 600F, 300F),
            Arrays.asList(2, 3, 6)
    );

    //cabbage pult
    public static final ISkillType MORE_CABBAGE_DAMAGE = new SkillType("more_cabbage_damage",
            ArrayUtil.getAverageArray(5, 3F, 5F),
            Arrays.asList(2, 3, 6, 7)
    );

    //kernel pult
    public static final ISkillType MORE_KERNEL_DAMAGE = new SkillType("more_kernel_damage",
            ArrayUtil.getAverageArray(4, 1.5F, 3F),
            Arrays.asList(3, 5, 8)
    );

    //garlic
    public static final ISkillType MORE_GARLIC_LIFE = new SkillType("more_garlic_life",
            ArrayUtil.getAverageArray(5, 100F, 200F),
            Arrays.asList(2, 3, 5, 6)
    );

    //melon pult
    public static final ISkillType MORE_MELON_DAMAGE = new SkillType("more_melon_damage",
            ArrayUtil.getAverageArray(6, 6F, 12F),
            Arrays.asList(3, 5, 6, 7, 8)
    );

    //bonk choy
    public static final ISkillType MORE_SWING_DAMAGE = new SkillType("more_swing_damage",
            ArrayUtil.getAverageArray(6, 3F, 6F),
            Arrays.asList(2, 3, 4, 6, 8)
    );

    //gold leaf
    public static final ISkillType ADVANCE_GOLD = new SkillType("advance_gold",
            ArrayUtil.getAverageArray(2, 1F, 3F),
            Arrays.asList(6, 12)
    );

    //angle star fruit
    public static final ISkillType TEN_STARS = new SkillType("ten_stars",
            ArrayUtil.getAverageArray(5, 0.2F, 0.6F),
            Arrays.asList(1, 3, 5, 7)
    );

    //bamboo lord
    public static final ISkillType SMALL_BOMB_DAMAGE = new SkillType("small_bomb_damage",
            ArrayUtil.getAverageArray(5, 60F, 120F),
            Arrays.asList(2, 3, 4, 5)
    );

    //water guard
    public static final ISkillType MORE_GUARD_LIFE = new SkillType("more_guard_life",
            ArrayUtil.getAverageArray(6, 150F, 300F),
            Arrays.asList(2, 3, 4, 4, 5)
    );

    //butter pult
    public static final ISkillType MORE_BUTTER_DAMAGE = new SkillType("more_butter_damage",
            ArrayUtil.getAverageArray(6, 0.1F, 1.1F),
            Arrays.asList(1, 3, 4, 6, 10)
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

    /*
    Skills of zombies.
     */

    //normal zombie.
    public static final ISkillType ZOMBIE_FAST_MOVE = new SkillType("zombie_fast_move",
            ArrayUtil.getAverageArray(4, 0F, 0.3F),
            Arrays.asList(2, 5, 8)
    );

    //normal zombie.
    public static final ISkillType HIGH_EAT_DAMAGE = new SkillType("high_eat_damage",
            ArrayUtil.getAverageArray(5, 5F, 15F),
            Arrays.asList(2, 3, 5, 6)
    );

    //normal zombie.
    public static final ISkillType TOUGH_BODY = new SkillType("tough_body",
            ArrayUtil.getAverageArray(6, 0F, 15F),
            Arrays.asList(2, 4, 5, 7, 8)
    );

    public static void registerSkillType(ISkillType type){
        SKILL_MAP.put(type.getIdentity(), type);
    }

    public static int getSkillLevel(CompoundNBT nbt, ISkillType type){
        if(nbt.contains(type.getIdentity())){
            return nbt.getInt(type.getIdentity());
        }
        return 0;
    }
    
    public static int getSkillLevel(ItemStack stack, ISkillType type){
        if(stack.getOrCreateTag().contains(SKILL_TAG)) {
        	final CompoundNBT tmp = stack.getOrCreateTag().getCompound(SKILL_TAG);
        	if(tmp.contains(type.getIdentity())){
        		return tmp.getInt(type.getIdentity());
        	}
        }
        return 0;
    }
    
    public static void addSkillLevel(ItemStack stack, ISkillType type, int lvl){
    	if(stack.getOrCreateTag().contains(SKILL_TAG)) {
    		final CompoundNBT tmp = stack.getOrCreateTag().getCompound(SKILL_TAG);
    		tmp.putInt(type.getIdentity(), lvl);
    		stack.getOrCreateTag().put(SKILL_TAG, tmp);
    	} else {
    		final CompoundNBT tmp = new CompoundNBT();
    		tmp.putInt(type.getIdentity(), lvl);
    		stack.getOrCreateTag().put(SKILL_TAG, tmp);
    	}
    }

    public static void addSkillLevel(CompoundNBT nbt, ISkillType type, int lvl){
    	if(nbt.contains(SKILL_TAG)) {
    		final CompoundNBT tmp = nbt.getCompound(SKILL_TAG);
    		tmp.putInt(type.getIdentity(), lvl);
    		nbt.put(SKILL_TAG, tmp);
    	} else {
    		final CompoundNBT tmp = new CompoundNBT();
    		tmp.putInt(type.getIdentity(), lvl);
    		nbt.put(SKILL_TAG, tmp);
    	}
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
        public IFormattableTextComponent getDescription() {
            return new TranslationTextComponent("skill." + this.getModID() + "." + this.toString() + ".desc");
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
