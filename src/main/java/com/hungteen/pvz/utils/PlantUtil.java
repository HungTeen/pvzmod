package com.hungteen.pvz.utils;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;

public class PlantUtil {
	
                                                   //1  2  3  4  5  6   7   8   9   10  11  12  13   14   15   16   17   18   19   20
//	public static final int[] GRAY_XP = new int[] {0,10,15,25,40,60,100,140,200,280,400,560,800,1250,1700,2250,3000,4000,5400,7500,999999999};
//	public static final int[] WHITE_XP = new int[] {0,15,25,40,60,80,130,180,240,325,450,620,880,1400,1920,2500,3400,4500,6000,8400,999999999};
//	public static final int[] GREEN_XP = new int[] {0,20,35,50,75,105,175,235,300,400,540,720,1000,1600,2250,3000,4000,5200,7000,9600,999999999};
//	public static final int[] BLUE_XP = new int[] {0,25,50,70,100,135,200,270,350,450,600,800,1100,1800,2500,3400,4500,6000,8000,10800,999999999};
//	public static final int[] PURPLE_XP = new int[] {0,30,60,80,105,150,225,300,400,520,700,960,1300,2100,3000,4200,5600,7200,9600,12500,999999999};
//	public static final int[] GOLD_XP = new int[] {0,35,60,90,120,175,255,350,450,600,800,1080,1500,2500,3600,5000,6400,8100,10800,14000,999999999};
//	public static final int[] MEGA_XP = new int[] {0,40,70,100,150,210,300,400,520,700,960,1300,1800,3000,4200,6000,7800,9600,12500,16000,999999999};
	public static int CURRENT_PLANT_NUM = 0;
	public static final int MAX_PLANT_LEVEL = 20;
	public static final float PUMPKIN_LIFE = 400;
	public static final float PUMPKIN_SUPER_LIFE = 400;
	
	/**
	 * copy data from p1 to p2
	 */
	public static void copyPlantData(PVZPlantEntity p2, PVZPlantEntity p1) {
		p2.setSkills(p1.getSkills());
		p2.setCharmed(p1.isCharmed());
		p2.setOwnerUUID(p1.getOwnerUUID().orElse(null));
	}
	
}
