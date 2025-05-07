package net.anse.callthembytheirname;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

/**
 * Manejador de eventos para los aldeanos
 */
public class VillagerHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            Entity entity = event.getEntity();

            if (entity instanceof Villager villager) {
                // Solo aplicar nombres a aldeanos sin nombre personalizado
                VillagerCapability capability = VillagerCapability.getOrCreate(villager);

                if (!villager.hasCustomName() && (capability.getName() != null && !capability.getName().isEmpty())) {
                    villager.setCustomName(Component.literal(capability.getName()));
                    villager.setCustomNameVisible(true);

                    LOGGER.info("Named villager: {} ({} {})",
                            capability.getName(),
                            capability.isMale() ? "Male" : "Female",
                            capability.isLegendary() ? "Legendary" : "Normal");
                } else {
                    LOGGER.info("Aldeano ya tenía nombre: {} ({} {})",
                            capability.getName(),
                            capability.isMale() ? "Male" : "Female",
                            capability.isLegendary() ? "Legendary" : "Normal");
                }
            }
        }
    }
}