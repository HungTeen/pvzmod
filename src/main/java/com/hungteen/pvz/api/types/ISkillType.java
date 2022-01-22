package com.hungteen.pvz.api.types;

import net.minecraft.util.text.IFormattableTextComponent;

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
     * conflict identification between skills.
     */
    String getConflictGroup();

    /**
     * the max level of the skill.
     */
    int getMaxLevel();

    /**
     * get description text.
     */
    IFormattableTextComponent getDescription();

    /**
     * if summon card got this skill, it will raise cost.
     */
    default int getExtraSun(){
        return 0;
    }

}
