package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ICDType;
import com.hungteen.pvz.common.enchantment.card.FastCDEnchantment;
import com.hungteen.pvz.utils.MathUtil;

import java.util.*;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 15:54
 *
 * card cool down types.
 **/
public abstract class CDTypes {

    /**
     * always 5s.
     */
    public static final ICDType DEFAULT = new CDType("default") {

        @Override
        public int getCD(int lvl) {
            return 100;
        }

    };

    /**
     * from 8s to 5s.
     */
    public static final ICDType SUPER_FAST = new CDType("super_fast", 100, 160);

    /**
     * from 12s to 8s.
     */
    public static final ICDType HUGE_FAST = new CDType("huge_fast", 160, 240);

    /**
     * from 15s to 10s.
     */
    public static final ICDType VERY_FAST = new CDType("very_fast", 200, 300);

    /**
     * from 20s to 15s.
     */
    public static final ICDType FAST = new CDType("fast", 300, 400);

    /**
     * from 25s to 18s.
     */
    public static final ICDType LITTLE_FAST = new CDType("little_fast", 360, 500);

    /**
     * from 32s to 24s.
     */
    public static final ICDType NORMAL = new CDType("normal", 480, 640);

    /**
     * from 40s to 30s.
     */
    public static final ICDType LITTLE_SLOW = new CDType("little_slow", 600, 800);

    /**
     * from 50s to 36s.
     */
    public static final ICDType SLOW = new CDType("slow", 720, 1000);

    /**
     * from 72s to 48s.
     */
    public static final ICDType VERY_SLOW = new CDType("very_slow", 960, 1440);

    /**
     * from 120s to 80s.
     */
    public static final ICDType HUGE_SLOW = new CDType("huge_slow", 2400, 1600);

    /**
     * from 240s to 180s.
     */
    public static final ICDType SUPER_SLOW = new CDType("super_slow", 3600, 4800);


    public static class CDType implements ICDType{

        private static final List<ICDType> CDS = new ArrayList<>();
        private final String name;
        private final int maxCD;
        private final int minCD;

        protected CDType(String name){
            this(name, 0, 0);
        }

        protected CDType(String name, int minCD, int maxCD){
            this.name = name;
            this.minCD = minCD;
            this.maxCD = maxCD;
            CDS.add(this);
        }

        /**
         * {@link PVZMod#coreRegister()}
         */
        public static void register(){
            CDS.forEach(cd -> PVZAPI.get().registerCoolDown(cd));
        }

        @Override
        public String getTranslateKey() {
            return "misc.pvz.cd." + this.name;
        }

        @Override
        public int getCD(int lvl) {
            return MathUtil.getIncreaseAverage(lvl, FastCDEnchantment.MAX_LEVEL, this.maxCD, this.minCD);
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
