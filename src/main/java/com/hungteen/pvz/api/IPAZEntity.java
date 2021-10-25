package com.hungteen.pvz.api;

import com.hungteen.pvz.api.interfaces.ICanBeAttracted;
import com.hungteen.pvz.api.interfaces.ICanBeCharmed;
import com.hungteen.pvz.api.interfaces.IGroupEntity;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

/**
 * an abstract interface above on both plants and zombies entity.<br>
 * plants and zombies have something in common.<br>
 * they both have owner, group and so on.
 */
public interface IPAZEntity extends IHasOwner, IGroupEntity, ICanBeCharmed, ICanBeAttracted, IHasMetal {

    IPAZType getPAZType();

    boolean canBeCold();

    boolean canBeFrozen();

    boolean canBeButtered();

    boolean canBeCharmed();

    boolean canBeMini();

    boolean canBeInvisible();

}
