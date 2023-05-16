package assignments.assignment4.gui.member;

// Interface Loginable untuk penentuan login
public interface Loginable {
    boolean login(String id, String password);
    void logout();
    String getPageName();
}
