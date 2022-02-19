package com.hungteen.pvz.api.paz;

import com.hungteen.pvz.api.interfaces.*;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import com.mojang.datafixers.util.Pair;

import java.util.List;

/**
 * an abstract interface above on both plants and zombies entity.<br>
 * plants and zombies have something in common.<br>
 * they both have owner, group and so on.
 */
public interface IPAZEntity extends IHasOwner, IHasGroup, ICanBeCharmed, ICanBeAttracted, IHasMetal {

    IPAZType getPAZType();

    /**
     * whether it can be slow down because of cold effect.
     */
    boolean canBeCold();

    /**
     * whether it can be too cold to move caused by frozen effect.
     */
    boolean canBeFrozen();

    /**
     * whether it can be stunned by butter bullet or not.
     */
    boolean canBeButtered();

    /**
     * whether it can change its group or not. {@link com.hungteen.pvz.api.enums.PVZGroupType}
     */
    boolean canBeCharmed();

    /**
     * it will be much smaller than before when mini invasion is coming.
     */
    boolean canBeMini();

    /**
     * players can not see it when invisible invasion is coming.
     */
    boolean canBeInvisible();
    
    boolean canBeStealByBungee();

    /**
     * display in almanac. {@link com.hungteen.pvz.common.item.display.AlmanacItem}
     */
    void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list);

    void setOuterDefenceLife(double life);

    void setInnerDefenceLife(double life);

    double getOuterDefenceLife();

    double getInnerDefenceLife();

}
