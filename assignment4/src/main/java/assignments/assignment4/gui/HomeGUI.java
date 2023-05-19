package assignments.assignment4.gui;

import java.awt.*;

import javax.swing.*;

import assignments.assignment3.nota.NotaManager;
import static assignments.assignment3.nota.NotaManager.toNextDay;
import assignments.assignment4.MainFrame;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout

        // Set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
    titleLabel = new JLabel("Selamat Datang di CuciCuci System!", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

    dateLabel = new JLabel("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));

    loginButton = new JButton("Login");
    loginButton.addActionListener(e -> handleToLogin());
    
    registerButton = new JButton("Register");
    registerButton.addActionListener(e -> handleToRegister());
    toNextDayButton = new JButton("Next Day");
    toNextDayButton.addActionListener(e -> handleNextDay());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 10, 40, 10); 
    gbc.anchor = GridBagConstraints.CENTER;
    mainPanel.add(titleLabel, gbc);

    gbc.gridy = 1;
    gbc.insets = new Insets(30, 0, 30, 0); 
    mainPanel.add(loginButton, gbc);

    gbc.gridy = 2;
    gbc.insets = new Insets(30, 0, 30, 0); 
    mainPanel.add(registerButton, gbc);

    gbc.gridy = 3;
    gbc.insets = new Insets(30, 0, 30, 0); 
    mainPanel.add(toNextDayButton, gbc);

    gbc.gridy = 4;
    gbc.insets = new Insets(40, 0, 0, 0);
    mainPanel.add(dateLabel, gbc);
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();
        dateLabel.setText("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));
        JOptionPane.showMessageDialog(this, String.format("Kamu tidur hari ini... zzz..."), "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
