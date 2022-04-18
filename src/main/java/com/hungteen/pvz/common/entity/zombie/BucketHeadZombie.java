package com.hungteen.pvz.common.entity.zombie;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.PVZPAZ;
import com.hungteen.pvz.common.impl.type.zombie.PVZZombies;
import com.hungteen.pvz.common.item.PVZItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 18:21
 **/
public class BucketHeadZombie extends NormalZombie{

    public BucketHeadZombie(EntityType<? extends PVZPAZ> entityType, Level level) {
        super(entityType, level);
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(PVZItems.BUCKET_HEAD.get()));
    }

    @Override
    protected float getLife() {
        return 30;
    }

    @Override
    public IZombieType getZombieType() {
        return PVZZombies.BUCKET_HEAD_ZOMBIE;
    }

}
