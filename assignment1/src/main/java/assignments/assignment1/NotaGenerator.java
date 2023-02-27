package assignments.assignment1;

// Mengimport module
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);
    public static boolean looping = true;
    
    // Method main, program utama kalian berjalan disini.
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        while (looping) {
            printMenu();
            System.out.print("Pilihan: ");
            String pilihan = input.nextLine();
            System.out.println("================================");
            
            // input.nextLine(); // hilangkan nanti, untuk sekarang save saja dulu

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan nama Anda: ");
                    String nama = input.next();
                    input.nextLine();
                    System.out.print("Masukkan nomor handphone Anda: ");
                    String nomorHP = input.nextLine();
                    while (!nomorHP.matches("\\d+")) { // Checking if input is digit with regex
                        System.out.println("Nomor hp hanya menerima digit");
                        nomorHP = input.nextLine();
                    }
                    String id = generateId(nama, nomorHP);
                    System.out.println("ID Anda : " + id);
                    break;
                case "2":
                    // generateNota(null, null, pilihan, null);
                    break;
                case "0":
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    looping = false;
                    break;
                default:
                    System.out.println("Perintah tidak diketahui, silahkan periksa kembali.");
                    

            }
            
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }
    
    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
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
        }
        if (checksum < 10) {
            idAfter = idBefore + "-0" + checksum;
        } else {
            idAfter = idBefore + "-" + checksum;
        }
        return idAfter;
    }

    /**
     *
     * Method untuk membuat Nota.
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        return null;
    }
}