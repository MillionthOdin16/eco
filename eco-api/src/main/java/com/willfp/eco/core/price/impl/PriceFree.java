package com.willfp.eco.core.price.impl;

import com.willfp.eco.core.price.Price;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Free (default) price.
 */
public final class PriceFree implements Price {
    /**
     * Create a new free price.
     */
    public PriceFree() {

    }

    @Override
    public boolean canAfford(@NotNull final Player player) {
        return true;
    }

    @Override
    public void pay(@NotNull final Player player) {
        // Do nothing.
    }
}
