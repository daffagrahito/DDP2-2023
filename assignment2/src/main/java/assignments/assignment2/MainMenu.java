package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

import assignments.assignment1.NotaGenerator;


import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();

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
    }

    private static void handleGenerateUser() {
        System.out.println("Masukan nama Anda: ");
        String nama = input.nextLine();

        System.out.println("Masukan nomor handphone Anda: ");
        String noHp = input.nextLine();
        noHp = NotaGenerator.validatePhoneNumber(noHp);

        Member currentMember = new Member(nama, noHp);

        // Mengecek apakah object Member yang akan dibuat sudah ada di dalam ArrayList
        for (Member i : memberList) {
            if (i.equals(currentMember)) { // Equals yang digunakan adalah method overriding dari class Member
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", currentMember.getNama(), currentMember.getNoHp());
                return;
            } 
        }
        // Bila belum ada maka akan ditambahkan dalam ArrayList
        memberList.add(currentMember);
        System.out.println("Berhasil membuat member dengan ID " + NotaGenerator.generateId(nama, noHp) + "!");
        
    }

    private static void handleGenerateNota() {
        System.out.println("Masukkan ID member: ");
        String idMember = input.nextLine();

        // Input Paket
        System.out.println("Masukkan paket laundry: ");
        String paket = input.nextLine();
        
        // Memvalidasi input paket sesuai ketentuan yang diminta
        while (!paket.equalsIgnoreCase("express") && !paket.equalsIgnoreCase("fast") && !paket.equalsIgnoreCase("reguler")) {
            if (paket.equals("?")) {
                showPaket();
                System.out.println("Masukkan paket laundry: ");
                paket = input.nextLine();
            } else {
                System.out.println("Paket " + paket + " tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
                System.out.println("Masukkan paket laundry: ");
                paket = input.nextLine();
            }
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
    }

    private static void handleListNota() {
        System.out.println("Terdaftar" + notaList.size() + " nota dalam sistem.");
    }

    private static void handleListUser() {
        System.out.println("Terdaftar " + memberList.size() + " member dalam sistem.");
        if (memberList.isEmpty()) { // Mengecek apakah memberList kosong atau tidak
            return;
        } else {
            for (int i = 0; i < memberList.size(); i++) {
                System.out.print("- " + memberList.get(i).getId() + ": " + memberList.get(i).getNama() + "\n");
            }
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
    }

    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");
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

