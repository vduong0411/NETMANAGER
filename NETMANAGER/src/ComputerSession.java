import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

public class ComputerSession {
    private String sessionId; // mã phiên
    private String computerId; // mã máy tính
    private LocalDateTime startTime; // thời gian bắt đầu phiên
    private LocalDateTime endTime; // thời gian kết thúc phiên
    private String customerId; // mã khách hàng
    private ArrayList<DichVu> dichVuDaGoi = new ArrayList<>();

    public ComputerSession(String sessionId, String computerId, String customerId) {
        this.sessionId = sessionId;
        this.computerId = computerId;
        this.customerId = customerId;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
    }

    public LocalDateTime getStartTime() { // ăắt đầu phiên
        return startTime;
    }

    public boolean endSession() { // kết thúc phiên làm việc
        if (endTime == null) { // nếu chưa có thời gian kết thúc
            endTime = LocalDateTime.now(); // gán thời gian kết thúc là thời điểm hiện tại
            return true;
        }

        return false;
    }

    public long getUsedMinutes() {
        if (endTime == null) {
            return 0;
        }

        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes(); // trả về số nguyên phút đã sử dụng
    }

    public boolean isActive() {
        return endTime == null;
    }

    public String getComputerId() {
        return computerId;
    }

    public long getUsedSeconds() {
        if (endTime == null) {
            return 0;
        }
        Duration duration = Duration.between(startTime, endTime);
        return duration.getSeconds();

    }

    public String getCustomerId() {
        return customerId;
    }

    public void goiDichVu(DichVu dichVu) {
        dichVuDaGoi.add(dichVu);
    }

    public long getTongTienDichVu() {
        long tongTien = 0;

        for (DichVu dv : dichVuDaGoi) {
            tongTien += dv.getGia();
        }

        return tongTien;
    }

    public ArrayList<DichVu> getDichVuDaGoi() {
        return new ArrayList<>(dichVuDaGoi);
    }

}
