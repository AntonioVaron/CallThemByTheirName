package net.anse.callthembytheirname;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue SHOW_VILLAGER_NAMES;
    public static final ModConfigSpec.DoubleValue LEGENDARY_VILLAGER_CHANCE;
    public static final ModConfigSpec.BooleanValue SHOW_GENDER_INDICATORS;

    static {
        BUILDER.comment("Configuración general para CallThemByTheirName").push("general");

        SHOW_VILLAGER_NAMES = BUILDER
                .comment("Mostrar nombres de aldeanos sobre sus cabezas")
                .define("showVillagerNames", true);

        LEGENDARY_VILLAGER_CHANCE = BUILDER
                .comment("Probabilidad de que un aldeano sea legendario (0.0 a 1.0)")
                .defineInRange("legendaryVillagerChance", 0.03, 0.0, 1.0);

        SHOW_GENDER_INDICATORS = BUILDER
                .comment("Mostrar indicadores de género en los nombres de los aldeanos")
                .define("showGenderIndicators", false);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}