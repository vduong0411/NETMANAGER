import java.io.*;
import java.util.ArrayList;

public class LuuDuLieu {

    private static final String FILE_NAME = "netmanager.dat";

    public static class Data implements Serializable {
        private static final long serialVersionUID = 1L;

        public ArrayList<Computer> computers;
        public ArrayList<Customer> customers;
        public ArrayList<ComputerSession> sessions;
        public ArrayList<HoaDon> hoaDons;
        public ArrayList<DichVu> dichVus;

        public Data(ArrayList<Computer> computers,
                ArrayList<Customer> customers,
                ArrayList<ComputerSession> sessions,
                ArrayList<HoaDon> hoaDons,
                ArrayList<DichVu> dichVus) {

            this.computers = computers;
            this.customers = customers;
            this.sessions = sessions;
            this.hoaDons = hoaDons;
            this.dichVus = dichVus;
        }
    }

    public static void save(Data data) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            out.writeObject(data);
            System.out.println("Luu du lieu thanh cong!");

        } catch (IOException e) {
            System.out.println("Loi luu du lieu: "
                    + e.getMessage());
        }
    }

    public static Data load() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file))) {

            Data data = (Data) in.readObject();
            System.out.println("Doc du lieu thanh cong!");
            return data;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi doc du lieu: "
                    + e.getMessage());
            return null;
        }
    }
}
