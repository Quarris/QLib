package quarris.qlib.api.client.helper;

public class ColorHelper {

    /**
     * Converts the default Minecraft color format (AARRGGBB) int into an array int[r, g, b, a]
     * @param color the color as int in format ARGB
     * @return An int array of individual r, g, b, a values
     */
    public static int[] getColori(int color) {
        int a = ((color >> 24) & 0xFF); // alpha
        int r = ((color >> 16) & 0xFF); // red
        int g = ((color >> 8) & 0xFF); // green
        int b = ((color >> 0) & 0xFF); // blue

        return new int[] {r, g, b, a};
    }

    /**
     * Converts the default Minecraft color format (AARRGGBB) int into an array float[r, g, b, a]
     * @param color the color as int in format ARGB
     * @return A float array of individual r, g, b, a values (in range [0,1])
     */
    public static float[] getColorf(int color) {
        float a = ((color >> 24) & 0xFF) / 255F; // alpha
        float r = ((color >> 16) & 0xFF) / 255F; // red
        float g = ((color >> 8) & 0xFF) / 255F; // green
        float b = ((color >> 0) & 0xFF) / 255F; // blue

        return new float[] {r, g, b, a};
    }
}
