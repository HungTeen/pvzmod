package com.hungteen.pvz.common.command;

import com.hungteen.pvz.common.world.challenge.ChallengeManager;
import com.hungteen.pvz.common.world.invasion.InvasionManager;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.SuggestionProviders;

public class PVZSuggestionProviders {

    public static final SuggestionProvider<CommandSource> ALL_INVASIONS = SuggestionProviders.register(StringUtil.prefix("all_invasion"), (commandContext, builder) -> {
        return ISuggestionProvider.suggestResource(InvasionManager.getIds(), builder);
    });

    public static final SuggestionProvider<CommandSource> ALL_CHALLENGES = SuggestionProviders.register(StringUtil.prefix("all_challenge"), (commandContext, builder) -> {
        return ISuggestionProvider.suggestResource(ChallengeManager.getIds(), builder);
    });
}
