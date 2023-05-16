package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import static assignments.assignment1.NotaGenerator.isNumeric;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
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
        // Set up component
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameTextField = new JTextField(60);
    
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneTextField = new JTextField(60);
    
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField(60);
    
        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegister());
    
        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());
    
        // Menata dan merapikan letak component di panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nameLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nameTextField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(phoneLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(phoneTextField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // Mendapatkan input dari user yang diisi pada TextField dan PasswordField
        String name = nameTextField.getText();
        String phoneNumber = phoneTextField.getText();
        String pass = new String(passwordField.getPassword());

        // Check Validasi yang diminta
        if (name.equals("") || phoneNumber.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field di atas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!isNumeric(phoneNumber)) {   // Validasi nomor telepon dengan isNumeric dari NotaGenerator
            JOptionPane.showMessageDialog(this, "Nomor telepon harus angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
            return;
        } else {
            // Register member 
            Member member = loginManager.register(name, phoneNumber, pass);
            if (member == null) {   // Pengecekan apabila member dengan id yang sama sudah pernah register
                JOptionPane.showMessageDialog(this, String.format("User dengan nama %s dan nomor hp %s sudah ada", name, phoneNumber), "Failed To Register", JOptionPane.ERROR_MESSAGE);
                MainFrame.getInstance().navigateTo(HomeGUI.KEY);
                nameTextField.setText("");
                phoneTextField.setText("");
                passwordField.setText("");
                return;
            }
            JOptionPane.showMessageDialog(this, String.format("Berhasil membuat user dengan ID %s!", member.getId()), "Success", JOptionPane.INFORMATION_MESSAGE);
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            return;
        }
    } 
}
