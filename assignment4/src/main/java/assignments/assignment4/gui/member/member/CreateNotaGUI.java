package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private final String[] paketList = {"Express", "Fast", "Reguler"};
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    // Constructor dari createNotaGUi
    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up panel
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // Membuat Component yang diperlukan
        paketLabel = new JLabel("Paket Laundry:");
        paketComboBox = new JComboBox<String>(paketList);
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(e -> showPaket());    // Menambahkan event untuk menampilkan info paket

        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratTextField = new JTextField(20);

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)");
        
        createNotaButton = new JButton("Buat Nota");
        createNotaButton.addActionListener(e -> createNota());  // Menambahkan event untuk pembuatan nota
        
        backButton = new JButton("Kembali");
        backButton.addActionListener(e -> handleBack());        // Menambahkan event untuk kembali
        
        // Menata dan merapikan letak component di panel
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(paketLabel, gbc);
        
        gbc.gridx = 1;
        add(paketComboBox, gbc);
        
        gbc.gridx = 2;
        add(showPaketButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(beratLabel, gbc);
        
        gbc.gridx = 1;
        add(beratTextField, gbc);
       
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(setrikaCheckBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(antarCheckBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(createNotaButton, gbc);
       
        gbc.gridy = 5;
        add(backButton, gbc);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // Mendapatkan member dan input berat
        Member member = memberSystemGUI.getLoggedInMember();
        String beratCucian = beratTextField.getText();

        // Validasi berat Cucian
        if (!beratCucian.matches("^\\d+$") || beratCucian.equals("0")) {
            beratTextField.setText("");
            JOptionPane.showMessageDialog(this, "Berat cucian harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int berat = Integer.parseInt(beratCucian);
        if (berat < 2) {
            berat = 2;
            JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        String paket = paketComboBox.getSelectedItem().toString(); // Mendapatkan jenis paket

        Nota nota = new Nota(member, berat, paket, fmt.format(cal.getTime()));  // Membuat Nota baru
        
        // Menambahkan service jika checkbox di checklist
        if (setrikaCheckBox.isSelected()) {
            nota.addService(new SetrikaService());
        }
        if (antarCheckBox.isSelected()) {
            nota.addService(new AntarService());
        }

        // Menambahkan nota tersebut ke member dan NotaManager
        member.addNota(nota);
        NotaManager.addNota(nota);
        JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Reset semua value yang telah terisi
        resetFields();
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
        resetFields();
    }

    private void resetFields() {
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0);
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }
}
