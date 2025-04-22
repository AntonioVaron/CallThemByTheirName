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
        Entity entity = event.getEntity();

        if (entity instanceof Villager villager) {
            // Solo aplicar nombres a aldeanos sin nombre personalizado
            if (!villager.hasCustomName()) {
                // Obtener o crear la capability
                VillagerCapability capability = VillagerCapability.getOrCreate(villager);

                // Establecer el nombre personalizado
                villager.setCustomName(Component.literal(capability.getName()));
                villager.setCustomNameVisible(true);

                // Registrar información en el log
                String type = capability.isLegendary() ? "Legendary" : "Normal";
                String gender = capability.isMale() ? "Male" : "Female";
                LOGGER.info("Named villager: {} ({} {})", capability.getName(), gender, type);
            }
        }
    }
}