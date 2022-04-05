package com.hungteen.pvz.common.impl.type.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.type.CDTypes;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.impl.type.RankTypes;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;

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
            .xp(10)
            .rank(RankTypes.WHITE)
            .entity(() -> PVZEntities.NORMAL_ZOMBIE.get())
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
