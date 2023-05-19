package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton createNotaButton = new JButton("Saya ingin laundry");
        JButton detailNotaButton = new JButton("Lihat detail nota saya");
        return new JButton[]{createNotaButton, detailNotaButton};
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
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void showDetailNota() {
        String detailNota = "";
        Nota[] notaListMember = getLoggedInMember().getNotaList();
        for (Nota eachNota : notaListMember) {
            detailNota += (eachNota.toString() + "\n\n");
        }

        if (detailNota.length() == 0) {
            detailNota += ("Belum pernah laundry di CuciCuci, hiks :'(");
        }
        
        // Display Text Nota
        JTextArea textArea = new JTextArea(detailNota);
        textArea.setEditable(false);
        textArea.setRows(2); // Set banyak rows visible
        textArea.setColumns(5); // Set banyak columns visible

        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JOptionPane optionPane = new JOptionPane(scrollableTextArea, JOptionPane.INFORMATION_MESSAGE);
        optionPane.setPreferredSize(new Dimension(460, 360)); // Set size yang diinginkan

        // Show JOptionPane dialog
        JDialog dialog = optionPane.createDialog(this, "Detail Nota");
        dialog.setVisible(true);
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void createNota() {
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}
