package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.common.misc.tag.PVZEntityTypeTags;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class EntityGroupHander {

    /**
     * get entity's group.
     * specially mention : multiparts' group the same as its owner.
     * others entity is group 0.
     */
    public static PVZGroupType getEntityGroupType(Entity entity){
        final EntityType<?> entityType = entity.getType();
        if(PVZEntityTypeTags.PVZ_PLANT_GROUP_ENTITIES.contains(entityType)){
            return PVZGroupType.PLANTS;
        }
        if(PVZEntityTypeTags.PVZ_ZOMBIE_GROUP_ENTITIES.contains(entityType)){
            return PVZGroupType.ZOMBIES;
        }
        //this type is a monster or in monster tag (can not be banned).
        if((entityType.getCategory() == EntityClassification.MONSTER || PVZEntityTypeTags.PVZ_OTHER_MONSTERS.contains(entityType)) && ! PVZEntityTypeTags.PVZ_NOT_MONSTERS.contains(entityType)){
            return PVZGroupType.OTHER_MONSTERS;
        }
        //this type is a tamable entity or in guardian tag (can not be banned).
        if((entity instanceof TameableEntity || PVZEntityTypeTags.PVZ_OTHER_GUARDIANS.contains(entityType)) && ! PVZEntityTypeTags.PVZ_NOT_GUARDIANS.contains(entityType)){
            return PVZGroupType.OTHER_GUARDIANS;
        }
        return PVZGroupType.NEUTRALS;
    }

    public static PVZGroupType getPlayerGroup(PlayerEntity player){
        final int group = PlayerUtil.getResource(player, Resources.GROUP_TYPE);
        return getGroup(group);
    }

    public static PVZGroupType getGroup(int type){
        final int group = MathHelper.clamp(type, Resources.GROUP_TYPE.min, Resources.GROUP_TYPE.max);
        return PVZGroupType.values()[group + 2];
    }

    public static boolean checkCanAttack(PVZGroupType g1, PVZGroupType g2) {
		return g1 != g2;
	}

	public static boolean checkCanTarget(PVZGroupType g1, PVZGroupType g2) {
		return (g1.ordinal() - 2) * (g2.ordinal() - 2) < 0;
	}
}
