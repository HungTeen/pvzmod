package com.hungteen.pvz.biome;

import net.minecraft.world.biome.Biome;

public abstract class PVZBiome extends Biome{

	protected PVZBiome(Builder biomeBuilder) {
		super(biomeBuilder);
	}
	
	public abstract void addFeatures();

	public abstract void addSpawns();
	
}
