public class App {
    public static void main(String[] args) {
        System.out.println("===== NETMANAGER =====");
        System.out.println("He thong quan ly quan NET");
        System.out.println("1. Quan ly khach hang");
        System.out.println("2. Quan ly dich vu");
        System.out.println("3. Thoat");

        Computer computer1 = new Computer("NET01", "May 01");
        System.out.println(computer1.getComputerName());
        System.out.println(computer1.getStatus());
    }
}