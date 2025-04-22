package net.anse.callthembytheirname;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// Definición del mod usando la anotación Mod
@Mod(CallThemByTheirName.MODID)
public class CallThemByTheirName
{
    // Define el ID del mod en un lugar común para que todo pueda referenciarlo
    public static final String MODID = "callthembytheirname";
    // Referencia directa a un logger slf4j
    private static final Logger LOGGER = LogUtils.getLogger();

    // El constructor para la clase del mod es el primer código que se ejecuta cuando tu mod se carga.
    // FML reconoce algunos tipos de parámetros como IEventBus o ModContainer y los pasa automáticamente.
    public CallThemByTheirName(IEventBus modEventBus, ModContainer modContainer)
    {
        LOGGER.info("Initializing CallThemByTheirName mod");

        // Registra el método commonSetup para la carga del mod
        modEventBus.addListener(this::commonSetup);

        // Registra tus eventos de cliente directamente al IEventBus del mod
        if (Dist.CLIENT.isClient()) {
            modEventBus.addListener(ClientModEvents::onClientSetup);
        }

        // Registra el mod para eventos del servidor y otros eventos de juego en los que estamos interesados.
        NeoForge.EVENT_BUS.register(this);

        // Registra el evento para añadir elementos a las pestañas creativas
        modEventBus.addListener(this::addCreative);

        // Registra el ModConfigSpec del mod para que FML pueda crear y cargar el archivo de configuración
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Registra el tipo de attachment para los aldeanos
        VillagerCapability.ATTACHMENT_TYPES.register(modEventBus);

        // Registramos explícitamente los manejadores de eventos
        NeoForge.EVENT_BUS.register(VillagerHandler.class);

        LOGGER.info("CallThemByTheirName mod initialized");
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Common setup for CallThemByTheirName");
        // Aquí puedes inicializar componentes comunes de tu mod
    }

    // Añade elementos del mod a las pestañas creativas
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Si necesitas añadir elementos a las pestañas creativas, hazlo aquí
    }

    // Puedes usar SubscribeEvent y dejar que Event Bus descubra métodos para llamar
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Server starting for CallThemByTheirName");
    }

    // Puedes usar EventBusSubscriber para registrar automáticamente todos los métodos estáticos
    // de la clase anotados con @SubscribeEvent
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Client setup for CallThemByTheirName");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}