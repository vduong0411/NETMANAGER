public class DichVu implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String maDichVu;
    private String tenDichVu;
    private long gia;

    public DichVu(String maDichVu, String tenDichVu, long gia) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.gia = gia;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public long getGia() {
        return gia;
    }
}
