package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

import assignments.assignment1.NotaGenerator;

public class MainMenu {
    // Defining fields pada class MainMenu
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static int notaIdNumber;            // Untuk generate nota ID

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
        input.close();  // Close Input
    }

    private static void handleGenerateUser() {
        // Meminta input nama dan noHp untuk mengenerate ID
        System.out.println("Masukan nama Anda: ");
        String nama = input.nextLine();

        System.out.println("Masukan nomor handphone Anda: ");
        String noHp = input.nextLine();
        noHp = NotaGenerator.validatePhoneNumber(noHp);

        // Membuat object Member yang memiliki ID juga
        Member currentMember = new Member(nama, noHp);

        // Mengecek apakah ID Member yang akan dibuat sudah ada di dalam ArrayList memberList
        for (Member i : memberList) {
            if (i.getId().equals(currentMember.getId())) {
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", currentMember.getNama(), currentMember.getNoHp());
                return;
            }
        }
        // Bila belum ada maka akan ditambahkan dalam ArrayList memberList
        memberList.add(currentMember);
        System.out.println("Berhasil membuat member dengan ID " + NotaGenerator.generateId(nama, noHp) + "!");
    }

    private static void handleGenerateNota() {
        // Meminta input ID Member
        System.out.println("Masukkan ID member: ");
        String idMember = input.nextLine();
        
        Member targetMember = null;                 // membuat variable class Member yang null (tidak merefer apapun)
        
        // Mengiterate memberList dan memindahkan reference targetMember ke ID yang sama
        for (Member i : memberList) {
            if ((i.getId()).equals(idMember)) {
                targetMember = i;
                break;
            }
        }
        
        // Untuk mengecek apabila tidak ditemukan id yang sama
        if (targetMember == null) {
            System.out.println("Member dengan ID " + idMember + " tidak ditemukan!");
            return;
        }
        
        // Input Paket
        System.out.println("Masukkan paket laundry: ");
        String paket = input.nextLine();
        
        // Memvalidasi input paket sesuai ketentuan yang diminta
        while (!paket.equalsIgnoreCase("express") && !paket.equalsIgnoreCase("fast") && !paket.equalsIgnoreCase("reguler")) {
            if (paket.equals("?")) {
                showPaket();
            } else {
                System.out.println("Paket " + paket + " tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
            }
            System.out.println("Masukkan paket laundry: ");
            paket = input.nextLine();
        }

        // Input berat cucian
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        String berat = input.nextLine();

        // Mengecek apabila input adalah bilangan integer positif dengan regex
        while (!berat.matches("^\\d+$") || berat.equals("0")) { 
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            berat = input.nextLine();
        }
        // Mengecek apabila berat yang diinput dibawah 2 kg
        if (Integer.parseInt(berat) < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = "2";
        }

        // Membuat Nota berdasarkan targetMember
        Nota notaTargetMember = new Nota(targetMember, paket, Integer.parseInt(berat), fmt.format(cal.getTime()));

        // Penambahan nota ke List nota dan print nota yang di generate
        System.out.println("Berhasil menambahkan nota!");
        notaTargetMember.setIdNota(notaIdNumber);
        notaList.add(notaTargetMember);
        System.out.println("[ID Nota = " + notaIdNumber + "]");
        notaIdNumber++;                         // Increment notaIdNumber setelah berhasil menambahkan nota
        System.out.println(Nota.generateNota(targetMember, paket, Integer.parseInt(berat), fmt.format(cal.getTime())));
        System.out.println("Status\t\t: " + notaTargetMember.checkStatus(notaTargetMember.getSisaHariPengerjaan()));
    }

    private static void handleListNota() {
        // Mengecek isi dari notaList dan mengiteratenya jika terisi
        System.out.println("Terdaftar " + notaList.size() + " nota dalam sistem.");

        if (notaList.isEmpty()) {             
            return;
        } else {
            for (int i = 0; i < notaList.size(); i++) {
                System.out.println("- [" + notaList.get(i).getIdNota() + "] Status\t\t: " + notaList.get(i).checkStatus(notaList.get(i).getSisaHariPengerjaan()));
            }
        } return;
    }

    private static void handleListUser() {
        // Mengecek isi dari memberList dan mengiteratenya jika terisi
        System.out.println("Terdaftar " + memberList.size() + " member dalam sistem.");

        if (memberList.isEmpty()) {             
            return;
        } else {
            for (int i = 0; i < memberList.size(); i++) {
                System.out.println("- " + memberList.get(i).getId() + ": " + memberList.get(i).getNama());
            }
        } return;
    }

    private static void handleAmbilCucian() {
        // Meminta input ID Nota
        System.out.println("Masukkan ID nota yang akan diambil: ");
        String idNota = input.nextLine();

        // Validasi ID Nota dengan hanya angka integer non-negatif saja yang dibolehkan
        while (!idNota.matches("^\\d+$")) { 
            System.out.println("ID nota berbentuk angka!");
            idNota = input.nextLine();
        }

        boolean idNotaNotFound = true;

        // Mengiterate dan mencari Nota yang sesuai dengan input idNota setelah tervalidasi
        for (Nota id : notaList) {
            if (id.getIdNota() == Integer.parseInt(idNota)) {
                idNotaNotFound = false;
                if (id.getIsReady()) {  // Kasus bila Laundry bisa diambil
                    System.out.println("Nota dengan ID " + idNota + " berhasil diambil!");
                    notaList.remove(id);
                    break;
                } else {                // Kasus bila Laundry belum bisa diambil
                    System.out.println("Nota dengan ID " + idNota + " gagal diambil!");
                    break;
                }
            }
        }
        
        // Kasus bila idNota tidak ditemukan
        if (idNotaNotFound) {
            System.out.println("Nota dengan ID " + idNota + " tidak ditemukan!");
            }
    }

    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");

        // Menambahkan current date dengan 1 hari
        cal.add(Calendar.DAY_OF_MONTH, 1);

        // Pengecekan jika notaList berisi dan mengupdate status dari setiap nota
        if (!notaList.isEmpty()) {
            for (Nota nota : notaList) {
                nota.setSisaHariPengerjaan(-1);
                nota.checkSisaHariPengerjaan();
            }
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    // Method untuk menampilkan paket.
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }
}

