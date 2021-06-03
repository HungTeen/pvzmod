package com.hungteen.pvz.client.model.entity.zombie;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.hungteen.pvz.client.model.entity.zombie.grassday.BucketHeadZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.ConeHeadZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.FlagZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.NormalZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.PoleZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.BackupDancerModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.DancingZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.FootballZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.GigaFootballZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.NewspaperZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.ScreenDoorZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.SundayEditionZombieModel;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieModelHandler {

	public static final Map<Zombies, PVZZombieModel<? extends PVZZombieEntity>> ZOMBIE_PART1_MODEL = new EnumMap<>(Zombies.class);
//	public static final Map<Zombies, PVZZombieModel<? extends PVZZombieEntity>> ZOMBIE_PART2_MODEL = new EnumMap<>(Zombies.class);
	
	static {
		//grass day
		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
		putModel(Zombies.FLAG_ZOMBIE, FlagZombieModel::new);
		putModel(Zombies.CONEHEAD_ZOMBIE, ConeHeadZombieModel::new);
		putModel(Zombies.POLE_ZOMBIE, PoleZombieModel::new);
		putModel(Zombies.BUCKETHEAD_ZOMBIE, BucketHeadZombieModel::new);
		//grass night(no tomb stone)
		putModel(Zombies.NEWSPAPER_ZOMBIE, NewspaperZombieModel::new);
		putModel(Zombies.SCREENDOOR_ZOMBIE, ScreenDoorZombieModel::new);
		putModel(Zombies.FOOTBALL_ZOMBIE, FootballZombieModel::new);
		putModel(Zombies.DANCING_ZOMBIE, DancingZombieModel::new);
		putModel(Zombies.BACKUP_DANCER, BackupDancerModel::new);
		putModel(Zombies.GIGA_FOOTBALL_ZOMBIE, GigaFootballZombieModel::new);
		putModel(Zombies.OLD_ZOMBIE, NormalZombieModel::new);
		putModel(Zombies.SUNDAY_EDITION_ZOMBIE, SundayEditionZombieModel::new);
//		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
//		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
//		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
//		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
//		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
//		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
	}
	
	public static Optional<PVZZombieModel<? extends PVZZombieEntity>> getPart1Model(Zombies zombie) {
		return Optional.ofNullable(ZOMBIE_PART1_MODEL.get(zombie));
	}
	
//	public static Optional<PVZZombieModel<? extends PVZZombieEntity>> getPart2Model(Zombies zombie) {
//		return Optional.ofNullable(ZOMBIE_PART2_MODEL.get(zombie));
//	}
	
	private static void putModel(Zombies zombie, Supplier<? extends PVZZombieModel<? extends PVZZombieEntity>> sup) {
		ZOMBIE_PART1_MODEL.put(zombie, sup.get());
//		ZOMBIE_PART2_MODEL.put(zombie, sup.get());
	}
}
