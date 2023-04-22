package assignments.assignment3.user.menu;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        if (choice == 3) {
            logout = true;                      
        } else if (choice == 2) {
            Nota[] currMemberNotaList = loginMember.getNotaList();
            for (Nota nota : currMemberNotaList) {
                System.out.println(nota);
                System.out.println();
            }
        } else if (choice == 1) {
            /*System.out.println("Masukkan paket laundry:");
            NotaGenerator.showPaket();
            String paketLaundry = in.nextLine();*/
            /*System.out.println("Masukkan berat cucian Anda [Kg]: ");
            int berat = Integer.parseInt(in.nextLine());
            
            // Mengecek apabila berat yang diinput dibawah 2 kg
            if (berat < 2) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }*/
            
            // Meminta input yang sesuai dengan asumsi input valid
            String paketLaundry = NotaGenerator.getPaket(in);   // Terdapat parameter Scanner input agar Scanner input yang dipakai
            int berat = NotaGenerator.getBerat(in);             // sama saat menggunakan method tersebut dan tidak error
            
            Nota notaBaru = new Nota(loginMember, berat, paketLaundry, fmt.format(cal.getTime()));

            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0");
            System.out.print("[Ketik x untuk tidak mau]: ");
            boolean booleanSetrika = !in.nextLine().equalsIgnoreCase("x");
            if (booleanSetrika) {
                SetrikaService setrika = new SetrikaService();
                notaBaru.addService(setrika);
            }

            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            boolean booleanAntar = !in.nextLine().equalsIgnoreCase("x");
            if (booleanAntar) {
                AntarService antar = new AntarService();
                notaBaru.addService(antar);
            }

            loginMember.addNota(notaBaru);
            NotaManager.addNota(notaBaru);

            System.out.println("Nota berhasil dibuat!");
            System.out.println();
        } else {
            System.out.println("Pilihan tidak valid, silahkan coba lagi.");
            System.out.println();
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        Member[] newMemberList = new Member[memberList.length + 1];

        for (int i = 0; i < memberList.length; i++) {
            newMemberList[i] = memberList[i];
        }

        memberList = newMemberList;
        memberList[memberList.length - 1] = member;
    }
}