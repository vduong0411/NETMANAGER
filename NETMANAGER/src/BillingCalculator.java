public class BillingCalculator {
    private static final long HOURLY_RATE = 6000;

    public static long calculateFee(long usedSeconds) {
        if (usedSeconds < 0) { //chặn thời gian âm
            throw new IllegalArgumentException("So giay khong duoc am!");
        }

        long numerator = usedSeconds * HOURLY_RATE;
        return (numerator + 1800) / 3600;
    }

}
