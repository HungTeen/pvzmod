package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.api.types.ISkillType;
import net.minecraft.nbt.CompoundTag;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 12:16
 **/
public class SkillTypes {

    public static final String SKILL_TAG = "PAZSkills";

    /**
     * get skill with paz entity skill tag.
     */
    public static int getSkillLevel(CompoundTag nbt, ISkillType type){
        if(nbt.contains(type.getIdentity())){
            return nbt.getInt(type.getIdentity());
        }
        return 0;
    }
}
