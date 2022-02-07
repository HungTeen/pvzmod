package com.hungteen.pvz.common.impl.challenge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.raid.*;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.Map.Entry;

public class ChallengeComponent implements IChallengeComponent {

	public static final String NAME = "default";
	private List<IWaveComponent> waves = new ArrayList<>();
	private List<IRewardComponent> rewards = new ArrayList<>();
	private Set<String> tags = new HashSet<>();
	private Set<String> dimensions = new HashSet<>();
	private List<String> authors = new ArrayList<>();
	private final List<Pair<IFormattableTextComponent, Integer>> messages = new ArrayList<>();
	private IPlacementComponent placement;
	private ITextComponent title = new TranslationTextComponent("challenge.pvz.title");
	private ITextComponent winTitle = new TranslationTextComponent("challenge.pvz.win_title");
	private ITextComponent lossTitle = new TranslationTextComponent("challenge.pvz.loss_title");
	private Color barColor = Color.WHITE;
	private SoundEvent preSound = SoundRegister.READY.get();
	private SoundEvent waveSound = SoundRegister.HUGE_WAVE.get();
	private SoundEvent winSound = SoundRegister.WIN_MUSIC.get();
	private SoundEvent lossSound = SoundRegister.LOSE_MUSIC.get();
	private int winTick;
	private int lossTick;
	/* for trade */
	private boolean canTrade;
	private int tradePrice;
	private int tradeWeight;
	/* misc */
	private boolean showRound;
	private int recommendLevel;
	private boolean shouldCloseToCenter;
	
	@Override
	public boolean readJson(JsonObject json) {
		/* titles */
		{
			final ITextComponent text = ITextComponent.Serializer.fromJson(json.get("title"));
		    if(text != null) {
			    this.title = text;
		    }
		}
		{
			final ITextComponent text = ITextComponent.Serializer.fromJson(json.get("win_title"));
		    if(text != null) {
			    this.winTitle = text;
		    }
		}
		{
			final ITextComponent text = ITextComponent.Serializer.fromJson(json.get("loss_title"));
		    if(text != null) {
			    this.lossTitle = text;
		    }
		}
		/* authors */
		{
			final JsonArray array = JSONUtils.getAsJsonArray(json, "authors", new JsonArray());
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
			final JsonArray array = JSONUtils.getAsJsonArray(json, "tags", new JsonArray());
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
			final JsonArray array = JSONUtils.getAsJsonArray(json, "dimensions", new JsonArray());
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
		    this.winTick = JSONUtils.getAsInt(json, "win_tick", 400);
		    this.lossTick = JSONUtils.getAsInt(json, "loss_tick", 200);
		}
		/* bar color */
		{
			this.barColor = Color.byName(JSONUtils.getAsString(json, "bar_color", "red"));
		}
		{/* trade */
			this.canTrade = JSONUtils.getAsBoolean(json, "can_trade", true);
			this.tradePrice = JSONUtils.getAsInt(json, "trade_price", 100);
			this.tradeWeight = JSONUtils.getAsInt(json, "trade_weight", 100);
		}
		{/* misc */
			this.showRound = JSONUtils.getAsBoolean(json, "show_round", true);
			this.recommendLevel = JSONUtils.getAsInt(json, "recommend_level", 1);
			this.shouldCloseToCenter = JSONUtils.getAsBoolean(json, "close_to_center", true);
		}
		/* sounds */
		{
			JsonObject obj = JSONUtils.getAsJsonObject(json, "sounds", null);
			if(obj != null) {
				{
					final SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, "pre_sound", "")));
					if(sound != null){
						this.preSound = sound;
					}
				}
				{
					final SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, "wave_sound", "")));
					if(sound != null){
						this.waveSound = sound;
					}
				}
				{
					final SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, "win_sound", "")));
					if(sound != null){
						this.winSound = sound;
					}
				}
				{
					final SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(JSONUtils.getAsString(obj, "loss_sound", "")));
					if(sound != null){
						this.lossSound = sound;
					}
				}
			}
		}
		/* spawn placement */
		{
			this.placement = ChallengeManager.readPlacement(json, true);
		}
		/* waves */
		JsonArray jsonWaves = JSONUtils.getAsJsonArray(json, "waves", new JsonArray());
		if(jsonWaves != null) {
			for(int i = 0; i < jsonWaves.size(); ++ i) {
			    JsonObject obj = jsonWaves.get(i).getAsJsonObject();
			    if(obj != null) {
			    	String type = JSONUtils.getAsString(obj, "type", "");
		            IWaveComponent wave = ChallengeManager.getWaveComponent(type);
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
	    	JsonObject obj = JSONUtils.getAsJsonObject(json, "rewards", null);
		    if(obj != null && ! obj.entrySet().isEmpty()) {
		       for(Entry<String, JsonElement> entry : obj.entrySet()) {
		  		    final IRewardComponent tmp = ChallengeManager.getRewardComponent(entry.getKey());
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

	public List<IWaveComponent> getWaves() {
		return waves;
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
	public int getTotalWaveCount() {
		return this.waves.size();
	}
	
	@Override
	public int getWinTick() {
		return this.winTick;
	}
	
	@Override
	public int getLossTick() {
		return this.lossTick;
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
	public ITextComponent getTitle() {
		return this.title;
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

	@Override
	public IFormattableTextComponent getChallengeName(){
		final ResourceLocation resourceLocation = ChallengeManager.getResourceByChallenge(this);
		return new TranslationTextComponent("challenge." + resourceLocation.getNamespace() + "." + resourceLocation.getPath() + ".name");
	}

	@Override
	public void setMessages(List<Pair<IFormattableTextComponent, Integer>> list) {
		this.messages.clear();
		list.forEach(p -> this.messages.add(p));
	}

	@Override
	public List<Pair<IFormattableTextComponent, Integer>> getMessages(){
		return Collections.unmodifiableList(this.messages);
	}

	@Override
	public int getRecommendLevel() {
		return recommendLevel;
	}

	@Override
	public boolean canTrade() {
		return canTrade;
	}

	@Override
	public int getTradeWeight() {
		return tradeWeight;
	}

	@Override
	public int getTradePrice() {
		return tradePrice;
	}

	@Override
	public boolean showRoundTitle() {
		return this.showRound;
	}

	@Override
	public boolean shouldCloseToCenter() {
		return this.shouldCloseToCenter;
	}

	private static class Sorter implements Comparator<ISpawnComponent> {

		public int compare(ISpawnComponent a, ISpawnComponent b) {
			final double d0 = a.getSpawnTick();
			final double d1 = b.getSpawnTick();
			return d0 < d1 ? -1 : d0 > d1 ? 1 : 0;
		}
		
	}
	
}
	
