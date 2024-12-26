package cm.ftg.bookingImage.util;

import org.springframework.util.StringUtils;

public class FileUtils {
    public static String cleanFileName(String fileName) {
        return StringUtils.cleanPath(fileName);
    }
}
