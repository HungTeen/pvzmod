package com.hungteen.pvz.api.interfaces;

import java.util.Optional;
import java.util.UUID;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:06
 **/
public interface IHasOwner {

    Optional<UUID> getOwnerUUID();
}
