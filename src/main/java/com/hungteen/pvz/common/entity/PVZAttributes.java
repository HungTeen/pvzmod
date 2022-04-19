package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZMod;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 14:26
 **/
public class PVZAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, PVZMod.MOD_ID);

    public static final RegistryObject<Attribute> WORK_CD = ATTRIBUTES.register("work_cd", () -> new RangedAttribute("attribute.name.pvz.work_cd", 100D, 1D, 1000000D).setSyncable(true));
//    public static final RegistryObject<Attribute> INNER_DEFENCE_HP = ATTRIBUTES.register("inner_defence_hp", () -> new RangedAttribute("attribute.name.pvz.inner_defence_hp", 0D, 0D, 1000000D).setSyncable(true));
//    public static final RegistryObject<Attribute> OUTER_DEFENCE_HP = ATTRIBUTES.register("outer_defence_hp", () -> new RangedAttribute("attribute.name.pvz.outer_defence_hp", 0D, 0D, 1000000D).setSyncable(true));

}
