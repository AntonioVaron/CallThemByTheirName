package net.anse.callthembytheirname;

import java.util.Random;

public class NameGenerator {

    // Prefijos comunes para nombres
    private static final String[] COMMON_PREFIXES = {
            "Al", "Bel", "Cor", "Dar", "El", "Fa", "Gal", "Har", "Ian", "Jar", "Kel", "Lor", "Mor", "Nor", "Or", "Per", "Quin", "Rin", "Sor", "Tor", "Ul", "Var", "Wil", "Xan", "Yor", "Zan"
    };

    // Sufijos para nombres masculinos
    private static final String[] MALE_SUFFIXES = {
            "amir", "bryn", "cis", "dan", "en", "far", "gorn", "helm", "ian", "kor", "lor", "mir", "nor", "or", "phas", "rath", "sor", "tur", "us", "ver", "wyn", "zar"
    };

    // Sufijos para nombres femeninos
    private static final String[] FEMALE_SUFFIXES = {
            "ara", "beth", "cia", "dra", "ella", "fra", "gia", "hia", "ira", "jia", "kira", "lia", "mia", "nya", "ora", "pia", "ria", "sia", "tia", "una", "via", "wina", "yra", "zina"
    };

    // Nombres completos masculinos
    private static final String[] MALE_FULL_NAMES = {
            "Tharion", "Cedric", "Kaelen", "Alaric", "Rowan", "Tiberius", "Dorian", "Balin", "Gideon", "Orion",
            "Magnus", "Gareth", "Thorne", "Lucius", "Darian", "Roran", "Cyrus", "Elric", "Gavin", "Ronan"
    };

    // Nombres completos femeninos
    private static final String[] FEMALE_FULL_NAMES = {
            "Elaria", "Brunhild", "Luthien", "Nymeria", "Isolde", "Seraphina", "Aurelia", "Freya", "Elowen", "Mireille",
            "Lyra", "Ariadne", "Thalia", "Elysia", "Fiona", "Gwendolyn", "Iris", "Selene", "Arianna", "Celia"
    };

    // Títulos para leyendas masculinas
    private static final String[] MALE_LEGEND_TITLES = {
            "the Bonebreaker", "the Flameborn", "the Stormcaller", "the Beastslayer",
            "the Undying", "the Iron Sentinel", "the Mountain", "the Dragonslayer",
            "the Wise", "the Blacksmith", "Thunderfist", "the Wanderer"
    };

    // Títulos para leyendas femeninas
    private static final String[] FEMALE_LEGEND_TITLES = {
            "the Whispering Shadow", "the Moonlit Warden", "the Enchantress", "the Healer",
            "the Swift", "the Oracle", "the Valkyrie", "the Spellweaver",
            "the Merciful", "the Nightingale", "the Emerald Eye", "the Protector"
    };

    // Títulos para leyendas de género neutro
    private static final String[] NEUTRAL_LEGEND_TITLES = {
            "the Elder", "the Formidable", "the Mystical", "the Eternal",
            "the Unseen", "the Savior", "the Fearless", "the Indomitable"
    };

    private static final Random random = new Random();

    /**
     * Genera un nombre aleatorio basado en el género especificado
     * @param isMale true para nombres masculinos, false para femeninos
     * @return Un nombre aleatorio
     */
    public static String generateRandomName(boolean isMale) {
        int choice = random.nextInt(3); // Eliminamos la opción de leyenda, ya que se maneja separadamente

        if (isMale) {
            switch (choice) {
                case 0:
                    return MALE_FULL_NAMES[random.nextInt(MALE_FULL_NAMES.length)];
                case 1:
                    return COMMON_PREFIXES[random.nextInt(COMMON_PREFIXES.length)] +
                            MALE_SUFFIXES[random.nextInt(MALE_SUFFIXES.length)];
                case 2:
                    return COMMON_PREFIXES[random.nextInt(COMMON_PREFIXES.length)] +
                            MALE_SUFFIXES[random.nextInt(MALE_SUFFIXES.length)] +
                            MALE_SUFFIXES[random.nextInt(MALE_SUFFIXES.length)];
                default:
                    return "Nameless";
            }
        } else {
            switch (choice) {
                case 0:
                    return FEMALE_FULL_NAMES[random.nextInt(FEMALE_FULL_NAMES.length)];
                case 1:
                    return COMMON_PREFIXES[random.nextInt(COMMON_PREFIXES.length)] +
                            FEMALE_SUFFIXES[random.nextInt(FEMALE_SUFFIXES.length)];
                case 2:
                    return COMMON_PREFIXES[random.nextInt(COMMON_PREFIXES.length)] +
                            FEMALE_SUFFIXES[random.nextInt(FEMALE_SUFFIXES.length)] +
                            FEMALE_SUFFIXES[random.nextInt(FEMALE_SUFFIXES.length)];
                default:
                    return "Nameless";
            }
        }
    }

    /**
     * Genera un nombre de leyenda basado en el género
     * @param isMale true para nombres masculinos, false para femeninos
     * @return Un nombre de leyenda
     */
    public static String generateLegendName(boolean isMale) {
        String baseName = generateRandomName(isMale);
        String title;

        if (isMale) {
            title = MALE_LEGEND_TITLES[random.nextInt(MALE_LEGEND_TITLES.length)];
        } else {
            title = FEMALE_LEGEND_TITLES[random.nextInt(FEMALE_LEGEND_TITLES.length)];
        }

        // 20% de probabilidad de usar un título neutro
        if (random.nextFloat() < 0.2f) {
            title = NEUTRAL_LEGEND_TITLES[random.nextInt(NEUTRAL_LEGEND_TITLES.length)];
        }

        return baseName + ", " + title;
    }

    /**
     * Determina aleatoriamente si una entidad es legendaria
     * @param legendChance la probabilidad (entre 0.0 y 1.0) de que sea una leyenda
     * @return true si es una leyenda, false en caso contrario
     */
    public static boolean isLegendary(float legendChance) {
        return random.nextFloat() < legendChance;
    }
}