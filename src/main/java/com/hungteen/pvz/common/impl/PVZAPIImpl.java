package com.hungteen.pvz.common.impl;

import java.util.*;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ICardType;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.ISkillType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.utils.Util;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 20:53
 *
 * the real api for pvz mod.
 **/
public class PVZAPIImpl implements PVZAPI.IPVZAPI {

    private static final List<IEssenceType> ESSENCES = new ArrayList<>();
    private static final List<IRankType> RANKS = new ArrayList<>();
    private static final List<ICardType> CARDS = new ArrayList<>();
    private static final List<ISkillType> SKILLS = new ArrayList<>();

    @Override
    public void registerPAZType(IPAZType type) {
        PAZTypes.registerPAZType(type);
    }

    @Override
    public Optional<IPAZType> getPAZType(String identity) {
        return PAZTypes.getPAZType(identity);
    }

    @Override
    public List<IPAZType> getPAZTypes() {
        return PAZTypes.getPAZs();
    }

    @Override
    public void registerEssenceType(IEssenceType type) {
        if (! ESSENCES.contains(type)) {
            ESSENCES.add(type);
        } else{
            Util.warn("Essence Type Register : Duplicate Type !");
        }
    }

    @Override
    public List<IEssenceType> getEssenceTypes() {
        return Collections.unmodifiableList(ESSENCES);
    }

    @Override
    public void registerRankType(IRankType type) {
        if(! RANKS.contains(type)){
            RANKS.add(type);
        } else{
            Util.warn("Rank Type Register : Duplicate Type !");
        }
    }

    @Override
    public void registerCardType(ICardType type) {
        if(! CARDS.contains(type)){
            CARDS.add(type);
        } else{
            Util.warn("Card Type Register : Duplicate Type !");
        }
    }

    @Override
    public void registerSkillType(ISkillType type) {
        if(! SKILLS.contains(type)){
            SKILLS.add(type);
        } else{
            Util.warn("Skill Type Register : Duplicate Type !");
        }
    }

    @Override
    public List<IRankType> getRankTypes() {
        return Collections.unmodifiableList(RANKS);
    }

    @Override
    public List<ICardType> getCardTypes() {
        return Collections.unmodifiableList(CARDS);
    }

}
