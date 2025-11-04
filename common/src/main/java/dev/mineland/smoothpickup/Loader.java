package dev.mineland.smoothpickup;

public class Loader {
    private static LoaderUtils loader;


    public static void setLoader(LoaderUtils specificLoader) {
        loader = specificLoader;
    }

    public static boolean isModLoaded(String modId) {
        return loader.isModLoaded(modId);
    }

    public static boolean isFabric() {
        return loader.isFabric();
    }

    public static boolean isNeoForge() {
        return loader.isNeoForge();
    }


    public static String getMinecraftVersion() {
        return loader.getMinecraftVersion();
    }

    public interface LoaderUtils {
        boolean isModLoaded(String modId);
        boolean isFabric();
        boolean isNeoForge();
        String getMinecraftVersion();
    }
}
