package com.hungteen.pvz.api.interfaces;

import java.util.Optional;
import java.util.UUID;

public interface IHasOwner {

	Optional<UUID> getOwnerUUID();
}
