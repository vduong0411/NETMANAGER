
import java.util.ArrayList;

public class HoaDon {
    private String invoiceId;
    private String computerId;
    private String customerId;
    private long usedSeconds;
    private long tienMay;
    private ArrayList<DichVu> dichVuDaGoi;
    private long soGiayTinhTien;

    public HoaDon(String invoiceId, String computerId,
            String customerId, long usedSeconds,
            long soGiayTinhTien, long tienMay,
            ArrayList<DichVu> dichVuDaGoi) {
        this.invoiceId = invoiceId;
        this.computerId = computerId;
        this.customerId = customerId;
        this.usedSeconds = usedSeconds;
        this.soGiayTinhTien = soGiayTinhTien;
        this.tienMay = tienMay;
        this.dichVuDaGoi = new ArrayList<>(dichVuDaGoi);
    }

    public void printBill() {
        System.out.println("===== HOA DON NETMANAGER =====");
        System.out.println("Ma hoa don: " + invoiceId);
        System.out.println("Ma may: " + computerId);
        System.out.println("Ma khach hang: " + customerId);
        System.out.println("Thoi gian: " + usedSeconds + " giay");
        System.out.println("Thoi gian tinh phi: "
                + soGiayTinhTien + " giay");

        System.out.println("Thoi gian duoc mien phi: "
                + (usedSeconds - soGiayTinhTien) + " giay");

        System.out.println("Tien may: " + tienMay + " VND");

        System.out.println("----- DICH VU DA GOI -----");

        long tongTienDichVu = 0;

        for (DichVu dv : dichVuDaGoi) {
            System.out.println(dv.getTenDichVu()
                    + ": " + dv.getGia() + " VND");

            tongTienDichVu += dv.getGia();
        }

        long tamTinh = tienMay + tongTienDichVu;

        // VAT 10% gia dinh cho demo
        long vat = (tamTinh * 10 + 50) / 100;

        long tongThanhToan = tamTinh + vat;

        System.out.println("Tien dich vu: "
                + tongTienDichVu + " VND");

        System.out.println("Tam tinh: "
                + tamTinh + " VND");

        System.out.println("VAT (10%): "
                + vat + " VND");

        System.out.println("Tong thanh toan: "
                + tongThanhToan + " VND");

        System.out.println("============================");

    }
}
