package com.hungteen.pvz.api.types.base;

import net.minecraft.network.chat.MutableComponent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 21:26
 *
 * base type for those who need store information.
 **/
public interface IIDType {

    /**
     * get type name, such as pea_shooter.
     */
    String toString();

    /**
     * used to specify store information (nbt) of type. <br>
     * [mod id]:[type name], such as pvz:pea_shooter.
     */
    String getIdentity();

    /**
     * get translation text.
     */
    MutableComponent getText();

    /**
     * specific mod id.
     */
    String getModID();
}
