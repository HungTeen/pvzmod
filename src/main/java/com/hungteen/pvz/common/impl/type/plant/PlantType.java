package com.hungteen.pvz.common.impl.type.plant;

import com.hungteen.pvz.api.misc.IPlantInfo;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IPlaceType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.impl.type.PAZType;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:54
 *
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you want to show in front of that category, then override one with higher priority. <br>
 */
public abstract class PlantType extends PAZType implements IPlantType {

    protected PlantType(String name) {
        super(name);
    }

    @Override
    public IPlaceType getPlacement() {
        return null;
    }

    @Override
    public Optional<Block> getPlantBlock() {
        return Optional.empty();
    }

    @Override
    public boolean isWaterPlant() {
        return false;
    }

    @Override
    public boolean isShroomPlant() {
        return false;
    }

    @Override
    public Optional<IPlantInfo> getOuterPlant() {
        return Optional.empty();
    }

    @Override
    public String getModID() {
        return null;
    }

    @Override
    public IEssenceType getEssence() {
        return null;
    }

    @Override
    public Optional<IPAZType> getUpgradeFrom() {
        return Optional.empty();
    }

    @Override
    public Optional<IPAZType> getUpgradeTo() {
        return Optional.empty();
    }

}
