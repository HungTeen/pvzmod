package com.hungteen.pvz.api;

import com.hungteen.pvz.api.interfaces.ICanBeCharmed;
import com.hungteen.pvz.api.interfaces.IGroupEntity;
import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

public interface IPlantEntity extends IHasOwner, IGroupEntity, ICanBeCharmed, IHasMetal {

}
