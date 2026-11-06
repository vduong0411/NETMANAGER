public class Computer {
    private String computerId;
    private String computerName;
    private String status;

    public Computer(String computerId, String computerName) {
        this.computerId = computerId;
        this.computerName = computerName;
        this.status = "AVAILABLE";
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean openComputer() {
        if (status.equals("AVAILABLE")) {
            status = "IN USE";
            return true;
        } else {
            return false;
        }
    }
    public boolean closeComputer() {
        if (status.equals("IN USE")) {
            status = "AVAILABLE";
            return true;
        } else {
            return false;
        }
    }
    public String getComputerName() {
        return computerName;
    }
    public String getStatus() {
        return status;
    }
}

