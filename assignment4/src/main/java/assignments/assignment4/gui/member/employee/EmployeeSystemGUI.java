package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton nyuciButton = new JButton("It's Nyuci Time");
        JButton notaDisplayButton = new JButton("Display List Nota");
        return new JButton[]{nyuciButton, notaDisplayButton};
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void displayNota() {
        String statusNota = "";
            for (Nota nota : NotaManager.notaList) {
                statusNota += (nota.getNotaStatus() + "\n");
            }
            if (statusNota.length() == 0) { // Jika belum ada cucian sama sekali
                statusNota += "Belum ada Cucian";
                JOptionPane.showMessageDialog(this, statusNota, "List Nota", JOptionPane.ERROR_MESSAGE);
                return;
            }
        JOptionPane.showMessageDialog(this, statusNota, "List Nota", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void cuci() {
        JOptionPane.showMessageDialog(this, String.format("Stand back! %s beginning to nyuci!", loggedInMember.getNama()), "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
        if (NotaManager.notaList.length == 0) { // Jika belum ada cucian sama sekali
            JOptionPane.showMessageDialog(this, "Nothing to cuci here", "Nyuci Results", JOptionPane.ERROR_MESSAGE);
        } else {
            String kerjakanStatus = "";
            for (Nota nota : NotaManager.notaList) {
                kerjakanStatus += (nota.kerjakan() + "\n");
            } JOptionPane.showMessageDialog(this, kerjakanStatus, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
