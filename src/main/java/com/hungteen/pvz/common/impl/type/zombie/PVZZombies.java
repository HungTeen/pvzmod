package com.hungteen.pvz.common.impl.type.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.type.RankTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-04 19:26
 **/
public class PVZZombies {

    private static final List<IPAZType> ZOMBIES = new ArrayList<>();

    public static final IZombieType NORMAL_ZOMBIE = new PVZZombies.PVZZombieType("normal_zombie")
            .rank(RankTypes.WHITE)
            .entity(() -> PVZEntities.NORMAL_ZOMBIE.get())
            .skills(new ArrayList<>());

    public static final IZombieType FLAG_ZOMBIE = new PVZZombies.PVZZombieType("flag_zombie")
            .rank(RankTypes.WHITE)
            .entity(() -> PVZEntities.FLAG_ZOMBIE.get())
            .res(NORMAL_ZOMBIE.getDefaultResource())
            .skills(new ArrayList<>());

    public static final IZombieType CONE_HEAD_ZOMBIE = new PVZZombies.PVZZombieType("cone_head_zombie")
            .rank(RankTypes.WHITE)
            .entity(() -> PVZEntities.CONE_HEAD_ZOMBIE.get())
            .res(NORMAL_ZOMBIE.getDefaultResource())
            .skills(new ArrayList<>());

    public static final IZombieType BUCKET_HEAD_ZOMBIE = new PVZZombies.PVZZombieType("bucket_head_zombie")
            .rank(RankTypes.GOLD)
            .entity(() -> PVZEntities.BUCKET_HEAD_ZOMBIE.get())
            .res(NORMAL_ZOMBIE.getDefaultResource())
            .skills(new ArrayList<>());

    public static final IZombieType LEADER_ZOMBIE = new PVZZombies.PVZZombieType("leader_zombie")
            .rank(RankTypes.GOLD)
            .entity(() -> PVZEntities.LEADER_ZOMBIE.get())
            .res(NORMAL_ZOMBIE.getDefaultResource())
            .skills(new ArrayList<>());

    public static class PVZZombieType extends ZombieType {

        protected PVZZombieType(String name) {
            super(name);
            ZOMBIES.add(this);
        }

        /**
         * {@link PVZMod#coreRegister()}
         */
        public static void register() {
            ZOMBIES.forEach(paz -> PVZAPI.get().registerPAZType(paz));
        }

        @Override
        public int getSortPriority() {
            return 100;
        }

        @Override
        public String getCategoryName() {
            return "pvz_zombie";
        }

        @Override
        public String getModID() {
            return PVZMod.MOD_ID;
        }

    }

}
