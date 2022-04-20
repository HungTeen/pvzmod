package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.common.tag.PVZEntityTags;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 16:27
 **/
public class EntityGroupHandler {

    /**
     * get entity's group.
     * specially mention : multipart entity's group the same as its owner.
     * others entity is group 0.
     */
    public static PVZGroupType getEntityGroupType(Entity entity){
        final EntityType<?> entityType = entity.getType();
        if(entity instanceof Player){
            return getPlayerGroup((Player) entity);
        }
        // entity in plant group tag is in PLANTS.
        if(entityType.is(PVZEntityTags.PVZ_PLANT_GROUP_ENTITIES)){
            return PVZGroupType.PLANTS;
        }
        //entity in zombie group tag is in ZOMBIES.
        if(entityType.is(PVZEntityTags.PVZ_ZOMBIE_GROUP_ENTITIES)){
            return PVZGroupType.ZOMBIES;
        }
        //this type is a monster or in monster tag (can not be banned).
        if((entityType.getCategory() == MobCategory.MONSTER || entityType.is(PVZEntityTags.PVZ_OTHER_MONSTERS)) && ! entityType.is(PVZEntityTags.PVZ_NOT_MONSTERS)){
            return PVZGroupType.OTHER_MONSTERS;
        }
        //this type is a tamable entity or in guardian tag (can not be banned).
        if((entity instanceof TamableAnimal || entityType.is(PVZEntityTags.PVZ_OTHER_GUARDIANS)) && ! entityType.is(PVZEntityTags.PVZ_NOT_GUARDIANS)){
            return PVZGroupType.OTHER_GUARDIANS;
        }
        return PVZGroupType.NEUTRALS;
    }

    public static PVZGroupType getPlayerGroup(Player player){
        final int group = PlayerUtil.getResource(player, Resources.GROUP_TYPE);
        return getGroup(group);
    }

    public static PVZGroupType getGroup(int type){
        final int group = Mth.clamp(type, -2, 2);
        switch (group){
            case 2: return PVZGroupType.OTHER_GUARDIANS;
            case 1: return PVZGroupType.PLANTS;
            case 0: return PVZGroupType.NEUTRALS;
            case -1: return PVZGroupType.ZOMBIES;
            case -2: return PVZGroupType.OTHER_MONSTERS;
        }
        //impossible to run here.
        return PVZGroupType.NEUTRALS;
    }

    public static boolean isGuardianGroup(PVZGroupType groupType){
        return groupType.type > 0;
    }

    public static boolean isMonsterGroup(PVZGroupType groupType){
        return groupType.type < 0;
    }

    /**
     * whether entities belong to two group attack each other.
     */
    private static boolean checkCanAttack(PVZGroupType group1, PVZGroupType group2) {
        return group1.type != group2.type;
    }

    /**
     * whether entities belong to two group target each other first.
     */
    private static boolean checkCanTarget(PVZGroupType group1, PVZGroupType group2) {
        return group1.type * group2.type < 0;
    }

    /**
     * whether entities belong to two group attack each other.
     */
    public static boolean checkCanAttack(Entity entity, Entity entity1) {
        return checkCanAttack(getEntityGroupType(entity), getEntityGroupType(entity1));
    }

    /**
     * whether entities belong to two group target each other first.
     */
    public static boolean checkCanTarget(Entity entity, Entity entity1) {
        return checkCanTarget(getEntityGroupType(entity), getEntityGroupType(entity1));
    }

}
