public class Computer {
    private String computerId;
    private String computerName;
    private String status;

    public Computer(String computerId, String computerName) {
        this.computerId = computerId;
        this.computerName = computerName;
        this.status = "AVAILABLE";
    }
    public String getComputerName() {
        return computerName;
    }
    public String getStatus() {
        return status;
    }
}

