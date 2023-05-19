package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private JToggleButton showPasswordButton;   // Atribut bonus untuk button yang bisa menampilkan character pada password
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout
        this.loginManager = loginManager;

        // Set up main panel
        mainPanel = new JPanel(new GridBagLayout());

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // Set up Component and adding ActionListener
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField(48);
        showPasswordButton = new JToggleButton("Show Password");
        showPasswordButton.addActionListener(e -> handleShowPassword());
        
        // Membuat panel untuk bagian passwordField dan button
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(showPasswordButton, BorderLayout.EAST);

        idLabel = new JLabel("Masukkan ID Anda:");
        idTextField = new JTextField(60);
        idTextField.setPreferredSize(showPasswordButton.getPreferredSize());

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());

        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());

        // Menata dan merapikan letak component di panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(idLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(idTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 20, 0);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 20, 0);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(passwordPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton" dan mengkosongkan kembali isi dari JTextField
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        resetFields();
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String id = idTextField.getText();
        String pass = new String(passwordField.getPassword());

        // Condition apabila field ada yang kosong
        if (id.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field di atas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        } else { // Check apakah attempt loginnya berhasil
            boolean loginAttempt = MainFrame.getInstance().login(id, pass);
            if (!loginAttempt) {
                JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
            }
            resetFields();
        }
    }

    // Method action bila button Show Password ditekan user
    private void handleShowPassword() {
        boolean showPassword = showPasswordButton.isSelected();
        if (showPassword) {
            passwordField.setEchoChar((char) 0); // Show password
        } else {
            passwordField.setEchoChar('\u2022'); // Hide password and display bullet character
        }
    }

    // Method untuk mereset fields apabila suatu event telah terjadi
    private void resetFields() {
        idTextField.setText("");
        passwordField.setText("");
        showPasswordButton.setSelected(true);
        showPasswordButton.doClick();
    }
}
