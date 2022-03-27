package com.hungteen.pvz.api;

import com.google.common.base.Suppliers;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.ICDType;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.base.IPAZType;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 20:44
 **/
public class PVZAPI {

    private static final Supplier<IPVZAPI> LAZY_INSTANCE = Suppliers.memoize(() -> {
        try {
            Class<?> classes = Class.forName("com.hungteen.pvz.common.impl.PVZAPIImpl");
            Constructor<?> constructor = classes.getDeclaredConstructor();
            return (IPVZAPI) constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            PVZMod.LOGGER.warn("Unable to find PVZAPIImpl, using a dummy one");
            return DummyAPI.INSTANCE;
        }
    });

    /**
     * Obtain the CustomRaid API, either a valid implementation if CustomRaid is present, else
     * a dummy instance instead if CustomRaid is absent.
     */
    public static IPVZAPI get() {
        return LAZY_INSTANCE.get();
    }

    /**
     * pvz mod has two implemented API. <br>
     * a dummy one {@link DummyAPI} and a implemented one {@link com.hungteen.pvz.common.impl.PVZAPIImpl} <br>
     * all implement code are below impl package.
     */
    public interface IPVZAPI {

        /**
         * register plant or zombie type.
         */
        void registerPAZType(IPAZType type);

        Optional<IPAZType> getPAZType(String identity);

        /**
         * get all registered plants and zombies types.
         */
        List<IPAZType> getPAZTypes();

        /**
         * register essence type.
         */
        void registerEssenceType(IEssenceType type);

        /**
         * get all registered essence types.
         */
        List<IEssenceType> getEssences();

        /**
         * register rank type.
         */
        void registerRankType(IRankType type);

        /**
         * get all registered rank types.
         */
        List<IRankType> getRanks();

        /**
         * register CD.
         */
        void registerCoolDown(ICDType type);

        /**
         * get CD by name.
         */
        Optional<ICDType> getCoolDown(String name);

    }

}
