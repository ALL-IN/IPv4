
/**
 * Author: Aleksey Alekseenko
 * Date: 18.10.13
 */
public class IPv4 {
    /**
     * Return relation from two IPv4s
     *
     * @param firstIPv4  String value
     * @param secondIPv4 String value
     * @return String relation
     */
    public static String relation(String firstIPv4, String secondIPv4) {
        if (!(isValidData(firstIPv4) & isValidData(secondIPv4))) {
            return "Input valid data. Format: [0-255].[0-255].[0-255].[0-255]/[0-32]";
        }
        String firstIP = firstIPv4.split("\\/")[0];
        String secondIP = secondIPv4.split("\\/")[0];
        int firstPrefix = Integer.parseInt(firstIPv4.split("\\/")[1]);
        int secondPrefix = Integer.parseInt(secondIPv4.split("\\/")[1]);
        long firstLongIP = getLongFromIp(firstIP);
        long secondLongIp = getLongFromIp(secondIP);
        int n = Math.min(firstPrefix, secondPrefix);
        int firstNBits = (32 - n);
        if (firstPrefix == secondPrefix & firstLongIP >>> firstNBits == secondLongIp >>> firstNBits) {
            return "EQUALS";
        } else if (firstPrefix > secondPrefix & firstLongIP >>> firstNBits == secondLongIp >>> firstNBits) {
            return "SUBSET";
        } else if (firstPrefix < secondPrefix & firstLongIP >>> firstNBits == secondLongIp >>> firstNBits) {
            return "SUPERSET";
        } else {
            return "DISJOINT";
        }
    }

    /**
     * Checks data
     * @param firstIPv4
     * @return
     */
    private static boolean isValidData(String firstIPv4) {
        return new IPAddressValidator().validate(firstIPv4);
    }

    /**
     * Return long value of String IP
     *
     * @param stringIp
     * @return long value
     */
    private static long getLongFromIp(String stringIp) {
        long[] ip = new long[4];
        String[] parts = stringIp.split("\\.");
        for (int i = 0; i < 4; i++) {
            ip[i] = Long.parseLong(parts[i]);
        }
        long longIP = 0;
        for (int i = 0; i < 4; i++) {
            longIP += ip[i] << (24 - (8 * i));
        }
        return longIP;
    }
}
