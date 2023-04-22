/* Nama : Muhammad Daffa Grahito Triharsanto
 * NPM  : 2206820075
 * Tugas Pemrograman 1
 */
package assignments.assignment1;

// Mengimport module
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    // Initializing static fields
    private static final Scanner input = new Scanner(System.in);
    public static boolean looping = true;
    
    // Method main, program utama
    public static void main(String[] args) {
        // Looping interface menu utama
        while (looping) {
            printMenu();
            System.out.print("Pilihan: ");
            String pilihan = input.nextLine();
            System.out.println("================================");

            // Switch case untuk setiap pilihan
            switch (pilihan) {
                case "1":
                    // Input Nama
                    System.out.println("Masukkan nama Anda: ");
                    String nama1 = input.nextLine();

                    // Input Nomor HP
                    System.out.println("Masukkan nomor handphone Anda: ");
                    String nomorHP1 = input.nextLine();
                    nomorHP1 = validatePhoneNumber(nomorHP1);

                    // Generate ID
                    String id1 = generateId(nama1, nomorHP1);
                    System.out.println("ID Anda : " + id1);
                    break;
                case "2":
                    // Input Nama
                    System.out.println("Masukkan nama Anda: ");
                    String nama2 = input.nextLine();

                    // Input Nomor HP
                    System.out.println("Masukkan nomor handphone Anda: ");
                    String nomorHP2 = input.nextLine();
                    nomorHP2 = validatePhoneNumber(nomorHP2);

                    // Generate ID
                    String id2 = generateId(nama2, nomorHP2);

                    // Input Tanggal
                    System.out.println("Masukkan tanggal terima: ");
                    String tanggalTerima = input.nextLine();

                    // Input Paket
                    System.out.println("Masukkan paket laundry: ");
                    String paketLaundry = input.nextLine();
                    
                    // Memvalidasi input paket sesuai ketentuan yang diminta
                    while (!paketLaundry.equalsIgnoreCase("express") && !paketLaundry.equalsIgnoreCase("fast") && !paketLaundry.equalsIgnoreCase("reguler")) {
                        if (paketLaundry.equals("?")) {
                            showPaket();
                            System.out.println("Masukkan paket laundry: ");
                            paketLaundry = input.nextLine();
                        } else {
                            System.out.println("Paket " + paketLaundry + " tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
                            System.out.println("Masukkan paket laundry: ");
                            paketLaundry = input.nextLine();
                        }
                    }

                    // Input berat cucian
                    System.out.println("Masukkan berat cucian Anda [Kg]: ");
                    String beratCucian = input.nextLine();

                    // Mengecek apabila input adalah bilangan integer positif dengan regex
                    while (!beratCucian.matches("^\\d+$") || beratCucian.equals("0")) { 
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        beratCucian = input.nextLine();
                    }
                    // Mengecek apabila berat yang diinput dibawah 2 kg
                    if (Integer.parseInt(beratCucian) < 2) {
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                        beratCucian = "2";
                    }
                    // Mengeprint nota
                    System.out.println("Nota Laundry");
                    System.out.println(generateNota(id2, paketLaundry, Integer.parseInt(beratCucian), tanggalTerima));
                    break;
                case "0":
                    // Exiting from while loop and exit the program
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    looping = false;
                    break;
                
                // Case apabila input bukan 1,2, atau 0
                default:
                    System.out.println("Perintah tidak diketahui, silahkan periksa kembali.");
            }
        }
    }

    // Method untuk memvalidasi apakah nomorHP inputnya digit semua atau bukan
    public static String validatePhoneNumber(String nomorHP) {
        while (!nomorHP.matches("\\d+")) { // Mengecek bila input adalah digit dengan regex
            System.out.println("Field nomor hp hanya menerima digit.");
            nomorHP = input.nextLine();
        } return nomorHP;
    }

    // Method untuk interface menu awal
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    // Method untuk menampilkan paket.
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }
    
    // Method untuk membuat ID dari nama dan nomor handphone serta ditambah checksum di akhir
    public static String generateId(String nama, String nomorHP) {
        nama = nama.replaceAll("\\s.*", ""); //  Mengambil kata sebelum whitespace pertama saja menggunakan regex
        String namaKapital = nama.toUpperCase();
        String idBefore = namaKapital + "-" + nomorHP;
        String idAfter = "";
        int checksum = 0;
        for (int i = 0; i < idBefore.length(); i++) {
            char letter = idBefore.charAt(i);
            if (Character.isUpperCase(letter)) {
                int position = (letter - 'A') + 1;
                checksum += position;
            } else if (Character.isDigit(letter)) {
                checksum += Character.getNumericValue(letter);
            } else {
                checksum += 7;
            }
            
        // Membuat checksum tidak lebih dari 2 digit
        checksum %= 100;
        }
        // Mengecek apabila checksum single digit atau bukan
        if (checksum < 10) {
            idAfter = idBefore + "-0" + checksum;
        } else {
            idAfter = idBefore + "-" + checksum;
        }
        return idAfter;
    }

    // Method untuk membuat Nota.
    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        LocalDate tanggalTerimaFormatted = LocalDate.parse(tanggalTerima, dateFormat);
        LocalDate tanggalSelesai;

        // Assign value untuk setiap case dan menambah day pada tanggal
        if (toHariPaket(paket) == 1) {
            tanggalSelesai = tanggalTerimaFormatted.plusDays(1);
        } else if (toHariPaket(paket) == 2) {
            tanggalSelesai = tanggalTerimaFormatted.plusDays(2);
        } else {
            tanggalSelesai = tanggalTerimaFormatted.plusDays(3);
        } String tanggalSelesaiString = tanggalSelesai.format(dateFormat);

        String nota = "ID    : "+ id +"\n" +
                "Paket : "+ paket +"\n" +
                "Harga :\n" +
                berat + " kg x "+ toHargaPaket(paket) +" = "+(toHargaPaket(paket)*berat)+"\n" +
                "tanggal terima  : "+ tanggalTerima +"\n" +
                "tanggal selesai : " + tanggalSelesaiString;
        return nota;
    }

    // Method untuk mendapatkan banyak hari selesai pada paket yang sesuai (dari solusi TP1)
    public static int toHariPaket(String paket) {
        paket = paket.toLowerCase();
        if (paket.equals("express"))
            return 1;
        if (paket.equals("fast"))
            return 2;
        if (paket.equals("reguler"))
            return 3;
        return -1;
    }

    // Method untuk mengubah paket menjadi harga paket yang sesuai (dari solusi TP1)
    public static long toHargaPaket(String paket) {
        paket = paket.toLowerCase();
        if (paket.equals("express"))
            return 12000;
        if (paket.equals("fast"))
            return 10000;
        if (paket.equals("reguler"))
            return 7000;
        return -1;
    }

    // Method untuk mengecek apakah String numerik atau tidak (dari solusi TP1)
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    // Method untuk mendapatkan input berat (dari solusi TP1)
    public static int getBerat(Scanner input) {
        System.out.println("Masukan berat cucian Anda [Kg]: ");
        String beratInput = input.nextLine();
        while (!isNumeric(beratInput) || Integer.parseInt(beratInput) < 1) {
            System.out.println("Harap masukan berat cucian Anda dalam bentuk bilangan positif.");
            beratInput = input.nextLine();
        }
        int berat = Integer.parseInt(beratInput);

        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }

        return berat;
    }

    // Method untuk mendapatkan input paket (dari solusi TP1)
    public static String getPaket(Scanner input) {
        String paket = "";
        showPaket();
        while (true) {
            System.out.println("Masukan paket laundry:");
            paket = input.nextLine();

            if (paket.equals("?")) {
                showPaket();
                continue;
            }

            if (toHargaPaket(paket) < 0) {
                System.out.printf("Paket %s tidak diketahui\n", paket);
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            } else {
                break;
            }
        }
        return paket;
    }
}