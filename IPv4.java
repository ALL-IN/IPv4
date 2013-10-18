package EpamTest;

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
    public String relation(String firstIPv4, String secondIPv4) {
        String firstIP = firstIPv4.split("\\/")[0];
        String secondIP = secondIPv4.split("\\/")[0];
        int firstPrefix = Integer.parseInt(firstIPv4.split("\\/")[1]);
        int secondPrefix = Integer.parseInt(secondIPv4.split("\\/")[1]);
        int firstIntIP = getIntFromIp(firstIP);
        int secondIntIp = getIntFromIp(secondIP);
        int n = Math.min(firstPrefix, secondPrefix);

        if (firstIntIP == secondIntIp) {
            return "EQUALS";
        } else if (firstPrefix > secondPrefix & firstIntIP >>> (32 - n) == secondIntIp >>> (32 - n)) {
            return "SUBSET";
        } else if (firstPrefix < secondPrefix & firstIntIP >> (32 - n) == secondIntIp >>> (32 - n)) {
            return "SUPERSET";
        } else {
            return "DISJOINT";
        }
    }

    /**
     * Return int value of String IP
     * @param stringIp
     * @return int value
     */
    private int getIntFromIp(String stringIp) {
        int[] ip = new int[4];
        String[] parts = stringIp.split("\\.");
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(parts[i]);
        }
        int intIP = 0;
        for (int i = 0; i < 4; i++) {
            intIP += ip[i] << (24 - (8 * i));
        }
        return intIP;
    }
}
