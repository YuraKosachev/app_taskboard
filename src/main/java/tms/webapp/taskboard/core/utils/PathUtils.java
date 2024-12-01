package tms.webapp.taskboard.core.utils;

public final class PathUtils {
    public static String getFileName(String filename, boolean removeAllExtensions) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }
        String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
        return filename.replaceAll(extPattern, "");

    }
}
