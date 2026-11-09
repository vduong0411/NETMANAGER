import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        ArrayList<Computer> computers = new ArrayList<>();

        computers.add(new Computer("NET01", "May 01"));
        computers.add(new Computer("NET02", "May 02"));
        computers.add(new Computer("NET03", "May 03"));

        ArrayList<ComputerSession> sessions = new ArrayList<>(); // danh sách các phiên làm việc
        ArrayList<HoaDon> hoaDons = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();

        customers.add(new Customer("KH01", "Khach A"));
        customers.add(new Customer("KH02", "Khach B"));

        ArrayList<DichVu> dichVus = new ArrayList<>();

        dichVus.add(new DichVu("DV01", "Nuoc suoi", 10000));
        dichVus.add(new DichVu("DV02", "Mi tom", 15000));
        dichVus.add(new DichVu("DV03", "Sting", 12000));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== NETMANAGER =====");
            System.out.println("1. Quan ly may tinh");
            System.out.println("2. Quan ly khach hang");
            System.out.println("3. Tim may theo ma");
            System.out.println("4. Mo may");
            System.out.println("5. Dong may");
            System.out.println("6. Lich su hoa don");
            System.out.println("7. Them khach hang");
            System.out.println("8. Danh sach dich vu");
            System.out.println("9. Goi dich vu");
            System.out.println("10. Chuyen may");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            int choice = scanner.nextInt();

            System.out.println("Ban da chon: " + choice);
            switch (choice) {

                case 1:
                    showComputers(computers);
                    break;

                case 2:
                    System.out.println("===== DANH SACH KHACH HANG =====");

                    for (int i = 0; i < customers.size(); i++) {
                        Customer customer = customers.get(i);

                        System.out.println(
                                customer.getCustomerId() + " - " +
                                        customer.getCustomerName());
                    }
                    break;

                case 3:
                    System.out.print("Nhap ma may: ");
                    String targetId = scanner.next();
                    Computer result = findComputer(computers, targetId);

                    System.out.println("Ban muon tim: " + targetId);
                    boolean found = false;
                    for (int i = 0; i < computers.size(); i++) {
                        if (computers.get(i).getComputerId().equalsIgnoreCase(targetId)) {
                            System.out.println("Tim thay may!");
                            System.out.println("Ma may: " + computers.get(i).getComputerId());
                            System.out.println("Ten may: " + computers.get(i).getComputerName());
                            System.out.println("Trang thai: " + computers.get(i).getStatus());

                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Khong tim thay may!");
                    }
                    break;

                case 4:
                    System.out.print("Nhap ma may can mo: ");
                    String openId = scanner.next();

                    Computer computerToOpen = findComputer(computers, openId);

                    if (computerToOpen == null) {
                        System.out.println("Khong tim thay may!");
                        break;
                    }

                    if (!computerToOpen.getStatus().equals("AVAILABLE")) {
                        System.out.println("May dang ban!");
                        break;
                    }

                    System.out.print("Nhap ma khach hang: ");
                    String openCustomerId = scanner.next();

                    Customer openCustomer = findCustomer(customers, openCustomerId);

                    if (openCustomer == null) {
                        System.out.println("Khong tim thay khach hang!");
                        break;
                    }

                    if (computerToOpen.openComputer()) {
                        ComputerSession session = new ComputerSession(
                                "P" + (sessions.size() + 1),
                                openId,
                                openCustomer.getCustomerId());

                        sessions.add(session);

                        System.out.println("Mo may thanh cong!");
                        System.out.println("Khach hang: " + openCustomer.getCustomerName());
                    } else {
                        System.out.println("Khong the mo may!");
                    }
                    break;

                case 5:
                    System.out.print("Nhap ma may can dong: ");
                    String closeId = scanner.next();

                    Computer computerToClose = findComputer(computers, closeId);
                    ComputerSession activeSession = findActiveSession(sessions, closeId);

                    if (computerToClose != null && activeSession != null) {
                        boolean ended = activeSession.endSession();

                        if (ended) {
                            boolean closed = computerToClose.closeComputer();

                            if (closed) {
                                long seconds = activeSession.getUsedSeconds();
                                long soGiayTinhTien = activeSession.getSoGiayTinhTien();
                                long fee = BillingCalculator.calculateFee(soGiayTinhTien);

                                HoaDon hoaDon = new HoaDon(
                                        "HD" + (hoaDons.size() + 1),
                                        computerToClose.getComputerId(),
                                        activeSession.getCustomerId(),
                                        seconds,
                                        soGiayTinhTien,
                                        fee,
                                        activeSession.getDichVuDaGoi());

                                hoaDons.add(hoaDon);
                                System.out.println("Dong may thanh cong!");
                                hoaDon.printBill();

                            } else {
                                System.out.println("Khong the dong may!");
                            }

                        } else {
                            System.out.println("Phien da ket thuc!");
                        }

                    } else {
                        System.out.println("Khong tim thay may hoac phien dang hoat dong!");
                    }
                    break;

                case 6:
                    System.out.println("===== LICH SU HOA DON =====");

                    if (hoaDons.size() == 0) {
                        System.out.println("Chua co hoa don!");
                    } else {
                        for (int i = 0; i < hoaDons.size(); i++) {
                            hoaDons.get(i).printBill();
                        }
                    }
                    break;

                case 7:
                    scanner.nextLine(); // Doc bo ky tu xuong dong sau nextInt()

                    System.out.print("Nhap ten khach hang: ");
                    String customerName = scanner.nextLine().trim();

                    if (customerName.isEmpty()) {
                        System.out.println("Ten khach hang khong duoc rong!");
                    } else {
                        String customerId = "KH" + (customers.size() + 1);

                        Customer newCustomer = new Customer(customerId, customerName);
                        customers.add(newCustomer);

                        System.out.println("Them khach hang thanh cong!");
                        System.out.println("Ma khach hang: " + customerId);
                    }
                    break;
                case 8:
                    System.out.println("===== DANH SACH DICH VU =====");

                    for (int i = 0; i < dichVus.size(); i++) {
                        DichVu dv = dichVus.get(i);

                        System.out.println(
                                dv.getMaDichVu() + " - " +
                                        dv.getTenDichVu() + " - " +
                                        dv.getGia() + " VND");
                    }
                    break;

                case 9:
                    System.out.print("Nhap ma may dang choi: ");
                    String orderComputerId = scanner.next();

                    ComputerSession orderSession = findActiveSession(sessions, orderComputerId);

                    if (orderSession == null) {
                        System.out.println("May khong co phien dang hoat dong!");
                        break;
                    }

                    System.out.print("Nhap ma dich vu: ");
                    String orderServiceId = scanner.next();

                    DichVu selectedService = null;

                    for (DichVu dv : dichVus) {
                        if (dv.getMaDichVu().equalsIgnoreCase(orderServiceId)) {
                            selectedService = dv;
                            break;
                        }
                    }

                    if (selectedService == null) {
                        System.out.println("Khong tim thay dich vu!");
                        break;
                    }

                    orderSession.goiDichVu(selectedService);

                    System.out.println("Goi dich vu thanh cong!");
                    System.out.println("Ten: " + selectedService.getTenDichVu());
                    System.out.println("Gia: " + selectedService.getGia() + " VND");
                    System.out.println("Tong tien dich vu: "
                            + orderSession.getTongTienDichVu() + " VND");
                    break;

                case 10:
                    System.out.print("Nhap ma may hien tai: ");
                    String oldId = scanner.next();

                    System.out.print("Nhap ma may muon chuyen den: ");
                    String newId = scanner.next();

                    // Tim 2 may
                    Computer oldComputer = findComputer(computers, oldId);
                    Computer newComputer = findComputer(computers, newId);

                    // Kiem tra may co ton tai khong
                    if (oldComputer == null || newComputer == null) {
                        System.out.println("Khong tim thay may!");
                        break;
                    }

                    // Khong duoc chuyen den chinh may dang dung
                    if (oldComputer.getComputerId()
                            .equalsIgnoreCase(newComputer.getComputerId())) {
                        System.out.println("Khong the chuyen cung mot may!");
                        break;
                    }

                    // Tim phien dang hoat dong cua may cu
                    ComputerSession transferSession = findActiveSession(sessions, oldComputer.getComputerId());

                    if (transferSession == null) {
                        System.out.println("Khong tim thay phien dang choi cua may cu!");
                        break;
                    }

                    // Kiem tra trang thai may cu
                    if (!oldComputer.getStatus().equals("IN_USE")) {
                        System.out.println("Trang thai may cu khong hop le!");
                        System.out.println("Trang thai: " + oldComputer.getStatus());
                        break;
                    }

                    // May moi phai dang ranh
                    if (!newComputer.getStatus().equals("AVAILABLE")) {
                        System.out.println("May moi dang ban!");
                        break;
                    }

                    // Mo may moi
                    if (!newComputer.openComputer()) {
                        System.out.println("Khong the mo may moi!");
                        break;
                    }

                    // Dong may cu
                    if (!oldComputer.closeComputer()) {
                        newComputer.closeComputer();
                        System.out.println("Chuyen may that bai!");
                        break;
                    }

                    // Chuyen phien sang may moi
                    transferSession.transferToComputer(
                            newComputer.getComputerId());

                    transferSession.congThoiGianBu(2);
                    System.out.println("Da bu 2 phut chuyen may!");

                    System.out.println("Chuyen may thanh cong!");
                    System.out.println("Tu may: " + oldComputer.getComputerId());
                    System.out.println("Sang may: " + newComputer.getComputerId());
                    System.out.println("Khach hang: " + transferSession.getCustomerId());

                    break;

                case 0:
                    System.out.println("Thoat chuong trinh");
                    return;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        }

    }

    private static void showComputers(ArrayList<Computer> computers) {
        System.out.println("===== DANH SACH MAY =====");

        for (int i = 0; i < computers.size(); i++) {
            System.out.println(
                    computers.get(i).getComputerName()
                            + " - " + computers.get(i).getStatus());
        }
    }

    private static Computer findComputer(
            ArrayList<Computer> computers, String targetId) {

        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getComputerId().equalsIgnoreCase(targetId)) {
                return computers.get(i);
            }
        }

        return null;
    }

    private static ComputerSession findActiveSession(
            ArrayList<ComputerSession> sessions, String computerId) {

        for (int i = 0; i < sessions.size(); i++) {
            ComputerSession session = sessions.get(i);

            if (session.getComputerId().equalsIgnoreCase(computerId)
                    && session.isActive()) {
                return session;
            }
        }

        return null;
    }

    private static Customer findCustomer(
            ArrayList<Customer> customers, String customerId) {

        for (Customer customer : customers) {
            if (customer.getCustomerId().trim()
                    .equalsIgnoreCase(customerId.trim())) {
                return customer;
            }
        }

        return null;
    }

}