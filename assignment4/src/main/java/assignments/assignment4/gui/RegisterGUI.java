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
    private JToggleButton showPasswordButton;   // Atribut bonus untuk button yang bisa menampilkan character pada password
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        // Set up component
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField(48);
        showPasswordButton = new JToggleButton("Show Password");
        showPasswordButton.addActionListener(e -> handleShowPassword());

        nameLabel = new JLabel("Masukkan nama Anda:");
        nameTextField = new JTextField(60);
        nameTextField.setPreferredSize(showPasswordButton.getPreferredSize());
    
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneTextField = new JTextField(60);
        phoneTextField.setPreferredSize(showPasswordButton.getPreferredSize());
    
        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegister());
    
        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(showPasswordButton, BorderLayout.EAST);
    
        // Menata dan merapikan letak component di panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nameLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(nameTextField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(phoneLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(phoneTextField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(passwordLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(passwordPanel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        resetFields();
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
                resetFields();
                return;
            }
            JOptionPane.showMessageDialog(this, String.format("Berhasil membuat user dengan ID %s!", member.getId()), "Success", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
            return;
        }
    } 

    // Method action bila button Show Password ditekan user
    private void handleShowPassword() {
        boolean showPassword = showPasswordButton.isSelected();
        if (showPassword) {
            passwordField.setEchoChar((char) 0); // Show password
        } else {
            passwordField.setEchoChar('\u2022'); // Hide password dan display bullet character
        }
    }

    // Method untuk mereset fields apabila suatu event telah terjadi
    private void resetFields() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        showPasswordButton.setSelected(true);
        showPasswordButton.doClick();
    }
}
