
import java.util.ArrayList;

public class HoaDon {
    private String invoiceId;
    private String computerId;
    private String customerId;
    private long usedSeconds;
    private long tienMay;
    private ArrayList<DichVu> dichVuDaGoi;

    public HoaDon(String invoiceId, String computerId,
            String customerId, long usedSeconds,
            long tienMay, ArrayList<DichVu> dichVuDaGoi) {
        this.invoiceId = invoiceId;
        this.computerId = computerId;
        this.customerId = customerId;
        this.usedSeconds = usedSeconds;
        this.tienMay = tienMay;
        this.dichVuDaGoi = new ArrayList<>(dichVuDaGoi);
    }

    public void printBill() {
        System.out.println("===== HOA DON NETMANAGER =====");
        System.out.println("Ma hoa don: " + invoiceId);
        System.out.println("Ma may: " + computerId);
        System.out.println("Ma khach hang: " + customerId);
        System.out.println("Thoi gian: " + usedSeconds + " giay");
        System.out.println("Tien may: " + tienMay + " VND");

        System.out.println("----- DICH VU DA GOI -----");

        long tongTienDichVu = 0;

        for (DichVu dv : dichVuDaGoi) {
            System.out.println(dv.getTenDichVu()
                    + ": " + dv.getGia() + " VND");

            tongTienDichVu += dv.getGia();
        }

        long tongThanhToan = tienMay + tongTienDichVu;

        System.out.println("Tien dich vu: "
                + tongTienDichVu + " VND");
        System.out.println("Tong thanh toan: "
                + tongThanhToan + " VND");
        System.out.println("============================");
    }
}
