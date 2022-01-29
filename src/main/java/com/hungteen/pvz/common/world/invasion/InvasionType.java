package com.hungteen.pvz.common.world.invasion;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * use to extend Invasion Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public class InvasionType {

	protected final ResourceLocation resourceLocation;
	protected final List<SpawnType> spawns = new ArrayList<>();
	private final boolean isAssistInvasion;
	protected int triggerChance = 100;
	protected ResourceLocation bonusResource;
	protected TextFormatting displayColor;
	private int requireDifficulty = 0x7fffffff;

	public InvasionType(ResourceLocation resourceLocation, boolean is){
		this.resourceLocation = resourceLocation;
		this.isAssistInvasion = is;
	}

	public void addSpawn(SpawnType type){
		this.spawns.add(type);
		this.requireDifficulty = Math.min(this.requireDifficulty, type.getInvasionLevel());
	}

	public List<SpawnType> getSpawns() {
		return spawns;
	}

	public void setTriggerChance(int triggerChance) {
		this.triggerChance = triggerChance;
	}

	public int getTriggerChance() {
		return triggerChance;
	}

	public void setBonusResource(ResourceLocation bonusResource) {
		this.bonusResource = bonusResource;
	}

	@Nullable
	public ResourceLocation getBonusResource() {
		return bonusResource;
	}

	public void setDisplayColor(TextFormatting displayColor) {
		this.displayColor = displayColor;
	}

	public TextFormatting getDisplayColor() {
		return displayColor;
	}

	public boolean isAssistInvasion() {
		return isAssistInvasion;
	}

	public int getRequireDifficulty() {
		return requireDifficulty;
	}

	public void setRequireDifficulty(int requireDay) {
		this.requireDifficulty = requireDay;
	}

	public ITextComponent getText(){
		return new TranslationTextComponent("invasion.pvz." + this.resourceLocation.getPath()).withStyle(this.getDisplayColor());
	}
}
