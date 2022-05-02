package quarris.qlib.api.client.helper;

public class ColorHelper {

    /**
     * Converts the default Minecraft color format (AARRGGBB) int into an array int[r, g, b, a]
     * @param color the color as int in format ARGB
     * @return An int array of individual r, g, b, a values
     */
    public static int[] fromColori(int color) {
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
    public static float[] fromColorf(int color) {
        float a = ((color >> 24) & 0xFF) / 255F; // alpha
        float r = ((color >> 16) & 0xFF) / 255F; // red
        float g = ((color >> 8) & 0xFF) / 255F; // green
        float b = ((color >> 0) & 0xFF) / 255F; // blue

        return new float[] {r, g, b, a};
    }

    /**
     * Converts the given colors into Minecrafts default color format, ARGB.
     * @return the ARGB representation of the given color components
     */
    public static int toColor(int r, int b, int g, int a) {
        return a << 24 | r << 16 | b << 8 | g;
    }

    public static int toColor(float r, float b, float g, float a) {
        return toColor((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
    }
}
