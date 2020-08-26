package com.hungteen.pvz.utils.enums;

public enum GuildTopics {

	PLAYER_STATS(2),
	EVENTS(1),
	ESSENCES(2),
	SUMMON_CARDS(1),
	ZEN_GARDEN(1),
	ZOMBIE_HOUSE(1),
	PLANT_OBTAIN(2);
	
	public int pageNum;
	
	GuildTopics(int num) {
		this.pageNum = num;
	}
}
