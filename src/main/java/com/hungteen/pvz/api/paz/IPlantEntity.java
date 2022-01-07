package com.hungteen.pvz.api.paz;

import com.hungteen.pvz.api.types.IEssenceType;

public interface IPlantEntity extends IPAZEntity {

    /**
     * place pumpkin on plant entity.
     */
    void setPumpkin(boolean is);

    /**
     * which essence does it belong to.
     */
    IEssenceType getPlantEssenceType();
}
