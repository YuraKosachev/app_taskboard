package tms.webapp.taskboard.core.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class UuidUtils {

    public static byte[] uuidToBytes(UUID uuid) {
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long mostSignificantBits = uuid.getMostSignificantBits();
        byte[] leastSignificantBytes = ByteBuffer.allocate(8).putLong(leastSignificantBits).array();
        byte[] mostSignificantBytes = ByteBuffer.allocate(8).putLong(mostSignificantBits).array();
        byte[] uuidBytes = new byte[16];
        System.arraycopy(leastSignificantBytes, 0, uuidBytes, 0, 8);
        System.arraycopy(mostSignificantBytes, 0, uuidBytes, 8, 8);
        return uuidBytes;
    }

    public static UUID bytesToUuid(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}
