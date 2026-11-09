public class Computer implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

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
            status = "IN_USE";
            return true;
        } else {
            return false;
        }
    }

    public boolean closeComputer() {
        if (status.equals("IN_USE")) {
            status = "AVAILABLE";
            return true;
        } else {
            return false;
        }
    }

    public String getComputerName() {
        return computerName;
    }

    public String getComputerId() {
        return computerId;
    }

    public String getStatus() {
        return status;
    }
}
