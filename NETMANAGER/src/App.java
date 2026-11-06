import java.util.ArrayList;
public class App {
    public static void main(String[] args) {
        System.out.println("===== NETMANAGER =====");
        System.out.println("He thong quan ly quan NET");
        System.out.println("1. Quan ly khach hang");
        System.out.println("2. Quan ly dich vu");
        System.out.println("3. Thoat");

        ArrayList<Computer> computers = new ArrayList<>();

computers.add(new Computer("NET01", "May 01"));
computers.add(new Computer("NET02", "May 02"));
computers.add(new Computer("NET03", "May 03"));

for (int i = 0; i < computers.size(); i++) {
    System.out.println(computers.get(i).getComputerName() + " - " + computers.get(i).getStatus());
}
System.out.println("So may: " + computers.size());

       
Computer computer1 = computers.get(0);

System.out.println(computer1.getComputerName());
System.out.println(computer1.getStatus());

boolean opened = computer1.openComputer();

if (opened) {
    System.out.println("Mo may thanh cong");
} else {
    System.out.println("May khong trong");
}

boolean closed = computer1.closeComputer();

if (closed) {
    System.out.println("Dong may thanh cong");
} else {
    System.out.println("Khong the dong may");
}

System.out.println(computer1.getStatus());

    }
}