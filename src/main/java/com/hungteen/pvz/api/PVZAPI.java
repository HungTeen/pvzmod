package com.hungteen.pvz.api;

import com.google.common.base.Suppliers;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.type.IEssenceType;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Constructor;
import java.util.List;
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

    public interface IPVZAPI {

        /**
         * register essence type.
         */
        void registerEssenceType(IEssenceType type);

        /**
         * get all registered essence types.
         */
        List<IEssenceType> getEssences();

    }

}
