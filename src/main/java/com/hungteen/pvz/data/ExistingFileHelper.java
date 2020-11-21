package com.hungteen.pvz.data;

import java.nio.file.Path;
import java.util.Collection;

import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;

public class ExistingFileHelper extends net.minecraftforge.client.model.generators.ExistingFileHelper{

	public ExistingFileHelper(Collection<Path> existingPacks, boolean enable) {
		super(existingPacks, enable);
	}

	@Override
	public boolean exists(ResourceLocation loc, ResourcePackType type, String pathSuffix, String pathPrefix) {
		return true;
	}
	
}
