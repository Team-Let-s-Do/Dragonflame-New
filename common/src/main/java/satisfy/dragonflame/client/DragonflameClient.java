package satisfy.dragonflame.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import satisfy.dragonflame.client.gui.LootChestScreen;
import satisfy.dragonflame.client.model.*;
import satisfy.dragonflame.client.render.*;
import satisfy.dragonflame.entity.fire_dragon.FireDragonModel;
import satisfy.dragonflame.entity.fire_dragon.FireDragonRenderer;
import satisfy.dragonflame.registry.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public enum DragonflameClient {
    ;

    public static final Set<UUID> dragonRiders = new HashSet<>();

    public static boolean isYPressed;

        public static void onInitializeClient() {
                //EntityRendererRegistry.register(EntityRegistry.SKELETON_BSP, SkeletonRenderer::new);


                RenderTypeRegistry.register(RenderType.cutout(), ObjectRegistry.DRAGON_LEAVES.get(), ObjectRegistry.STATUE_ADOREDU.get(), ObjectRegistry.STATUE_LILITU.get(),
                        ObjectRegistry.DRAGON_DOOR.get(), ObjectRegistry.DRAGON_TRAPDOOR.get(), ObjectRegistry.BURNT_DOOR.get(), ObjectRegistry.BURNT_TRAPDOOR.get(),
                        ObjectRegistry.GRIM_ANVIL.get(), ObjectRegistry.DRAGON_SAPLING.get(), ObjectRegistry.DRAGON_WINDOW.get()
                );

                /*ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
                        if (tintIndex != 1 || world == null || pos == null) return -1;
                        BlockEntity blockEntity = world.getBlockEntity(pos);
                        if (!(blockEntity instanceof ArcaneBlockEntity ether)) return -1;
                        return ether.firstColorRGB;
                }, ARCANE_WALL_TORCH, ARCANE_GROUND_TORCH);*/

                MenuRegistry.registerScreenFactory(ScreenhandlerTypeRegistry.LOOTCHEST_SCREENHANDLER.get(), LootChestScreen::new);
                registerBlockEntityRenderers();

            initColorItems();
        }

        public static void preInitClient() {
                EntityModelLayerRegistry.register(FieryWarhorseModel.LAYER_LOCATION, FieryWarhorseModel::getTexturedModelData);
                EntityModelLayerRegistry.register(ArmoredPillagerDogModel.LAYER_LOCATION, ArmoredPillagerDogModel::getTexturedModelData);
                EntityModelLayerRegistry.register(ArmoredVindicatorModel.ARMORED_VINDICATOR_MODEL_LAYER, ArmoredVindicatorModel::getTexturedModelData);
                EntityModelLayerRegistry.register(ArmoredPillagerModel.ARMORED_PILLAGER_MODEL_LAYER, ArmoredPillagerModel::getTexturedModelData);
                EntityModelLayerRegistry.register(DragonWhelplingModel.LAYER_LOCATION, DragonWhelplingModel::getTexturedModelData);
                EntityModelLayerRegistry.register(LootChestRenderer.LAYER_LOCATION, LootChestRenderer::getTexturedModelData);
                EntityModelLayerRegistry.register(FireDragonModel.LAYER_LOCATION, FireDragonModel::getTexturedModelData);

                registerEntityModelLayers();
                registerEntityRenderers();
                //ParticleRegistry.registerFactories();
        }

    private static void registerEntityRenderers() {
                EntityRendererRegistry.register(EntityRegistry.FIERY_WARHORSE, FieryWarhorseRenderer::new);
                EntityRendererRegistry.register(EntityRegistry.ARMORED_PILLAGER_DOG, ArmoredPillagerDogRenderer::new);
                EntityRendererRegistry.register(EntityRegistry.ARMORED_VINDICATOR, ArmoredVindicatorRenderer::new);
                EntityRendererRegistry.register(EntityRegistry.ARMORED_PILLAGER, ArmoredPillagerRenderer::new);
                EntityRendererRegistry.register(EntityRegistry.FIREDRAGON, FireDragonRenderer::new);
                EntityRendererRegistry.register(EntityRegistry.DRAGON_WHELPLING, DragonWhelplingRenderer::new);
        }

    private static void registerBlockEntityRenderers() {
                BlockEntityRendererRegistry.register(BlockEntityRegistry.GRIM_ANVIL_BLOCK_ENTITY.get(), GrimAnvilRenderer::new);
                BlockEntityRendererRegistry.register(BlockEntityRegistry.LOOTCHEST_BLOCK_ENTITY.get(), LootChestRenderer::new);
        }

    private static void initColorItems() {
        ClientUtil.registerColorArmor(ObjectRegistry.REINFORCED_LEATHER_HELMET.get(), 0xFFADD8E6);
        ClientUtil.registerColorArmor(ObjectRegistry.REINFORCED_LEATHER_CHESTPLATE.get(), 0xFFADD8E6);
        ClientUtil.registerColorArmor(ObjectRegistry.REINFORCED_LEATHER_LEGGINGS.get(), 0xFFADD8E6);
        ClientUtil.registerColorArmor(ObjectRegistry.REINFORCED_LEATHER_BOOTS.get(), 0xFFADD8E6);
    }

    private static void registerEntityModelLayers() {
            ArmorRegistry.registerArmorModelLayers();
        }
}