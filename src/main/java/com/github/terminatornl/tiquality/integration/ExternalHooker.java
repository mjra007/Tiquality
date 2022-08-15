package com.github.terminatornl.tiquality.integration;

import com.github.terminatornl.tiquality.Tiquality;
import com.github.terminatornl.tiquality.integration.griefdefender.GriefDefenderHook;
import com.griefdefender.api.GriefDefender;
import net.minecraftforge.fml.common.Loader;

import java.util.HashSet;
import java.util.Map;

public class ExternalHooker {

    public static final HashSet<String> LOADED_HOOKS = new HashSet<>();

    public static void init() {

        if (Loader.isModLoaded("griefdefender")) {
            try {
                //noinspection ResultOfMethodCallIgnored
                GriefDefender.getCore();
                /*
                    GriefPrevention API loaded successfully
                 */
                Tiquality.LOGGER.info("GriefDefender detected. Adding hooks...");
                LOADED_HOOKS.add("griefdefender");
                GriefDefenderHook.init();
                Tiquality.LOGGER.info("Done.");
            } catch (IllegalStateException e) {
                Tiquality.LOGGER.info("The griefdefender API is not loaded, and therefore, we cannot hook into it.");
            }
        }

    }
}
