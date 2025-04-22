package net.anse.callthembytheirname;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.Villager;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * Capability para almacenar información adicional sobre los aldeanos
 */
public class VillagerCapability {
    private static final Random random = new Random();
    private static final float DEFAULT_LEGEND_CHANCE = 0.03f; // 3% de probabilidad de ser legendario

    private boolean isMale;
    private boolean isLegendary;
    private String name;

    // Constructor por defecto
    public VillagerCapability() {
        this.isMale = random.nextBoolean(); // 50% probabilidad de ser hombre
        this.isLegendary = NameGenerator.isLegendary(DEFAULT_LEGEND_CHANCE);

        // Generar nombre basado en género y si es legendario
        if (isLegendary) {
            this.name = NameGenerator.generateLegendName(isMale);
        } else {
            this.name = NameGenerator.generateRandomName(isMale);
        }
    }

    // Registro de capability usando el nuevo sistema de NeoForge
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, CallThemByTheirName.MODID);

    // Definición del serializador
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<VillagerCapability>> VILLAGER_INFO =
            ATTACHMENT_TYPES.register("villager_info", () ->
                    AttachmentType.builder(() -> new VillagerCapability())
                            .serialize(new VillagerAttachmentSerializer())
                            .copyOnDeath()
                            .build());

    // Getters y setters
    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Regenerar nombre basado en los atributos actuales
    public void regenerateName() {
        if (isLegendary) {
            this.name = NameGenerator.generateLegendName(isMale);
        } else {
            this.name = NameGenerator.generateRandomName(isMale);
        }
    }

    /**
     * Obtiene la capability de un aldeano, o crea una nueva si no existe
     */
    public static VillagerCapability getOrCreate(Villager villager) {
        return villager.getData(VILLAGER_INFO);
    }

    // Implementación del serializador como clase estática dentro de VillagerCapability
    public static class VillagerAttachmentSerializer implements IAttachmentSerializer<CompoundTag, VillagerCapability> {

        @Override
        public VillagerCapability read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
            VillagerCapability capability = new VillagerCapability();
            capability.isMale = tag.getBoolean("IsMale");
            capability.isLegendary = tag.getBoolean("IsLegendary");
            capability.name = tag.getString("Name");
            return capability;
        }

        @Override
        public @Nullable CompoundTag write(VillagerCapability attachment, HolderLookup.Provider provider) {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("IsMale", attachment.isMale);
            tag.putBoolean("IsLegendary", attachment.isLegendary);
            tag.putString("Name", attachment.name);
            return tag;
        }
    }
}