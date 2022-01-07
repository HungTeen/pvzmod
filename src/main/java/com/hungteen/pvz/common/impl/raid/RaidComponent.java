package com.hungteen.pvz.common.impl.raid;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.*;
import com.hungteen.pvz.common.world.raid.RaidManager;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.Map.Entry;

public class RaidComponent implements IRaidComponent {

	public static final String NAME = "default";
	private List<IWaveComponent> waves = new ArrayList<>();
	private List<IRewardComponent> rewards = new ArrayList<>();
	private Set<String> tags = new HashSet<>();
	private Set<String> dimensions = new HashSet<>();
	private List<String> authors = new ArrayList<>();
	private IPlacementComponent placement;
	private ITextComponent raidTitle = new TranslationTextComponent("raid.craid.title");
	private ITextComponent winTitle = new TranslationTextComponent("raid.craid.win_title");
	private ITextComponent lossTitle = new TranslationTextComponent("raid.craid.loss_title");
	private Color barColor = Color.WHITE;
	private SoundEvent preSound;
	private SoundEvent waveSound;
	private SoundEvent winSound;
	private SoundEvent lossSound;
	private int winCD;
	private int lossCD;
	
	@Override
	public boolean readJson(JsonObject json) {
		/* titles */
		{
			ITextComponent text = ITextComponent.Serializer.fromJson(json.get(StringUtil.RAID_TITLE));
		    if(text != null) {
			    this.raidTitle = text;
		    }
		}
		{
			ITextComponent text = ITextComponent.Serializer.fromJson(json.get(StringUtil.WIN_TITLE));
		    if(text != null) {
			    this.winTitle = text;
		    }
		}
		{
			ITextComponent text = ITextComponent.Serializer.fromJson(json.get(StringUtil.LOSS_TITLE));
		    if(text != null) {
			    this.lossTitle = text;
		    }
		}
		
		
		/* authors */
		{
			final JsonArray array = JSONUtils.getAsJsonArray(json, StringUtil.AUTHORS, new JsonArray());
			if(array != null) {
				for(int i = 0; i < array.size(); ++ i) {
					final JsonElement e = array.get(i);
					if(e.isJsonPrimitive()) {
						this.authors.add(e.getAsString());
					}
				}
			}
		}
		
		/* tags */
		{
			final JsonArray array = JSONUtils.getAsJsonArray(json, StringUtil.TAGS, new JsonArray());
			if(array != null) {
				for(int i = 0; i < array.size(); ++ i) {
					final JsonElement e = array.get(i);
					if(e.isJsonPrimitive()) {
						this.tags.add(e.getAsString());
					}
				}
			}
		}

		/* dimensions */
		{
			final JsonArray array = JSONUtils.getAsJsonArray(json, StringUtil.DIMENSIONS, new JsonArray());
			if(array != null) {
				for(int i = 0; i < array.size(); ++ i) {
					final JsonElement e = array.get(i);
					if(e.isJsonPrimitive()) {
						this.dimensions.add(e.getAsString());
					}
				}
			}
		}
		
		/* raid cd */
		{
		    this.winCD = JSONUtils.getAsInt(json, StringUtil.WIN_CD, 200);
		    this.lossCD = JSONUtils.getAsInt(json, StringUtil.LOSS_CD, 100);
		}
		
		/* bar color */
		this.barColor = Color.byName(JSONUtils.getAsString(json, StringUtil.BAR_COLOR, "red"));
		
		/* sounds */
		{
			JsonObject obj = JSONUtils.getAsJsonObject(json, StringUtil.SOUNDS, null);
			if(obj != null) {
				this.preSound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, StringUtil.PRE_SOUND, "")));
			    this.waveSound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, StringUtil.WAVE_SOUND, "")));
			    this.winSound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, StringUtil.WIN_SOUND, "")));
			    this.lossSound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, StringUtil.LOSS_SOUND, "")));
			}
		}
		
		/* spawn placement */
		this.placement = RaidManager.readPlacement(json, true);
		
		/* waves */
		JsonArray jsonWaves = JSONUtils.getAsJsonArray(json, StringUtil.WAVES, new JsonArray());
		if(jsonWaves != null) {
			for(int i = 0; i < jsonWaves.size(); ++ i) {
			    JsonObject obj = jsonWaves.get(i).getAsJsonObject();
			    if(obj != null) {
			    	String type = JSONUtils.getAsString(obj, StringUtil.TYPE, "");
		            IWaveComponent wave = RaidManager.getWaveType(type);
		            if(! wave.readJson(obj)) {
		            	return false;
		            }
		            //by tick order.
		            wave.getSpawns().sort(new Sorter());
				    this.waves.add(wave);
			    }
			}
		}
	    if(this.waves.isEmpty()) {// mandatory !
		    throw new JsonSyntaxException("Wave list cannot be empty");
	    }
	    
	    /* rewards */
	    {
	    	JsonObject obj = JSONUtils.getAsJsonObject(json, StringUtil.REWARDS, null);
		    if(obj != null && ! obj.entrySet().isEmpty()) {
		       for(Entry<String, JsonElement> entry : obj.entrySet()) {
		  		    final IRewardComponent tmp = RaidManager.getReward(entry.getKey());
		    	    if(tmp != null) {
		    		    tmp.readJson(entry.getValue());
		    		    this.rewards.add(tmp);
		    	    } else {
		    		    PVZMod.LOGGER.warn("Placement Component : Read Spawn Placement Wrongly");
		    	    }
		   	    }
		    }
	    }
	    
	    return true;
	}
	
	@Override
	public List<ISpawnComponent> getSpawns(int wavePos) {
		return this.waves.get(this.wavePos(wavePos)).getSpawns();
	}
	
	@Override
	public List<IRewardComponent> getRewards() {
		return this.rewards;
	}
	
	@Override
	public List<String> getAuthors() {
		return this.authors;
	}
	
	@Override
	public boolean hasTag(String tag) {
		return this.tags.contains(tag);
	}

	@Override
	public boolean isSuitableDimension(RegistryKey<World> type) {
		return this.dimensions.isEmpty() || this.dimensions.contains(type.getRegistryName().toString());
	}

	@Override
	public int getPrepareCD(int wavePos) {
		return this.waves.get(this.wavePos(wavePos)).getPrepareCD();
	}
	
	@Override
	public int getLastDuration(int wavePos) {
		return this.waves.get(this.wavePos(wavePos)).getLastDuration();
	}
	
	@Override
	public boolean isWaveFinish(int wavePos, int spawnPos) {
		return spawnPos >= this.waves.get(this.wavePos(wavePos)).getSpawns().size();
	}
	
	@Override
	public int getMaxWaveCount() {
		return this.waves.size();
	}
	
	@Override
	public int getWinCD() {
		return this.winCD;
	}
	
	@Override
	public int getLossCD() {
		return this.lossCD;
	}

	@Override
	public SoundEvent getPrepareSound() {
		return this.preSound;
	}

	@Override
	public SoundEvent getStartWaveSound() {
		return this.waveSound;
	}

	@Override
	public SoundEvent getWinSound() {
		return this.winSound;
	}

	@Override
	public SoundEvent getLossSound() {
		return this.lossSound;
	}
	
	@Override
	public IPlacementComponent getPlacement(int wavePos) {
		final IPlacementComponent p = this.waves.get(this.wavePos(wavePos)).getPlacement();
		return p == null ? this.placement : p;
	}
	
	@Override
	public ITextComponent getRaidTitle() {
		return this.raidTitle;
	}
	
	@Override
	public ITextComponent getWinTitle() {
		return this.winTitle;
	}
	
	@Override
	public ITextComponent getLossTitle() {
		return this.lossTitle;
	}
	
	@Override
	public Color getBarColor() {
		return this.barColor;
	}
	
	private int wavePos(int pos) {
		return MathHelper.clamp(pos, 0, this.waves.size() - 1);
	}
	
	private static class Sorter implements Comparator<ISpawnComponent> {

		public int compare(ISpawnComponent a, ISpawnComponent b) {
			final double d0 = a.getSpawnTick();
			final double d1 = b.getSpawnTick();
			return d0 < d1 ? -1 : d0 > d1 ? 1 : 0;
		}
		
	}
	
}
	
