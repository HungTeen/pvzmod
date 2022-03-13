package com.hungteen.pvz.api.enums;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 15:57
 *
 * Use to specify creatures' group. <br>
 * Plants will attack zombies and other monsters firstly. <br>
 * If there is no target for plants to attack and they are attacked by other guards or creatures, they will attack back too. <br>
 * Zombies will attack plants and other guards firstly. <br>
 * If there is no target for zombies to attack and they are attacked by other monsters or creatures, they will attack back too. <br>
 * By the way, entities in plant group will never attack each other, zombie group too. <br>
 **/
public enum PVZGroupType {

    /**
     * monster group except pvz zombies.
     */
    OTHER_MONSTERS(-2),

    /**
     * zombies' group.
     */
    ZOMBIES(-1),

    /**
     * plants' group.
     */
    PLANTS(1),

    /**
     * guardian group except pvz plants.
     */
    OTHER_GUARDIANS(2),

    /**
     * entities not belong to the above groups.
     */
    NEUTRALS(0);

    public final int type;

    PVZGroupType(int type){
        this.type = type;
    }

}
