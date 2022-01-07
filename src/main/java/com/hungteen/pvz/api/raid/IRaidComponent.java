package com.hungteen.pvz.api.raid;

import com.google.gson.JsonObject;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import java.util.List;

public interface IRaidComponent {

	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	boolean readJson(JsonObject json);
	
	/**
	 * get how many ticks needed for players to prepare the wave.
	 */
	int getPrepareCD(int wavePos);
	
	/**
	 * get how many ticks will this wave last.
	 */
	int getLastDuration(int wavePos);
	
	/**
	 * how many waves is there.
	 */
	int getMaxWaveCount();
	
	/**
	 * how long will win state last.
	 */
	int getWinCD();
	
	/**
	 * how long will loss state last.
	 */
	int getLossCD();
	
	boolean isWaveFinish(int wavePos, int spawnPos);
	
	boolean hasTag(String tag);

	boolean isSuitableDimension(RegistryKey<World> type);
	
	List<String> getAuthors();
	
	/**
	 * get spawn list of current wave.
	 */
	List<ISpawnComponent> getSpawns(int wavePos);
	
	List<IRewardComponent> getRewards();
	
	IPlacementComponent getPlacement(int wavePos);
	
	ITextComponent getRaidTitle();
	
	ITextComponent getWinTitle();
	
	ITextComponent getLossTitle();
	
	BossInfo.Color getBarColor();
	
	SoundEvent getPrepareSound();
	
	SoundEvent getStartWaveSound();
	
	SoundEvent getWinSound();
	
	SoundEvent getLossSound();

}
