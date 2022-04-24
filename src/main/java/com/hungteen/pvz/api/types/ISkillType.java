package com.hungteen.pvz.api.types;

import com.hungteen.pvz.api.types.base.IIDType;
import net.minecraft.network.chat.MutableComponent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 15:25
 *
 * The differences between skill and enchantment are below : <br>
 * 1. skill affects entity, enchantment affects item stack. <br>
 * 2. enchantment used for common items, skill is more specific.
 **/
public interface ISkillType extends IIDType {

    /**
     * how many value can it get when the skill level reach {pos}.
     */
    float getValueAt(int pos);

    /**
     * how many points does it cost to update to level {pos}.
     */
    int getCostAt(int pos);

    /**
     * the max level of the skill.
     */
    int getMaxLevel();

    /**
     * get description text.
     */
    MutableComponent getDescription();

    /**
     * special skills, conflict with other special skills.
     */
    boolean isSpecial();

    /**
     * if summon card got this skill, it will raise cost.
     */
    default int getExtraSun(){
        return 0;
    }

}