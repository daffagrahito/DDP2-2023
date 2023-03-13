package assignments.assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    // Fields yang diperlukan untuk class Nota
    private int idNota;
    private String paket;
    private int berat;
    private Member member;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;

    // Constructor dari class Nota
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.isReady = false;
        // Untuk this.sisaHariPengerjaan melakukan pengecekan berdasarkan this.paket dan set dengan value yang sesuai
        if (this.paket.equalsIgnoreCase("express")) {
            this.sisaHariPengerjaan = 1;
        } else if (this.paket.equalsIgnoreCase("fast")) {
            this.sisaHariPengerjaan = 2;
        } else {
            this.sisaHariPengerjaan = 3;
        }
    }

    // Getter untuk mendapatkan data member dari nota
    public Member getMember() { 
        return this.member;
    }

    // Getter untuk mendapatkan data paket dari nota
    public String getPaket() {
        return this.paket;
    }

    // Getter untuk mendapatkan data berat dari nota
    public int getBerat() {
        return this.berat;
    }

    // Getter untuk mendapatkan data tanggal dipesan dari nota
    public String getTanggalMasuk() {
        return this.tanggalMasuk;
    }

    // Getter untuk mendapatkan data idNota yang masuk dari nota
    public int getIdNota() {
        return this.idNota;
    }
    
    // Setter untuk menggasign idNota sesuai dengan berapa kali perintah Generate Nota di jalankan
    public void setIdNota(int id) {
        this.idNota = id;
    }
    
    // Getter untuk mendapatkan sisaHariPengerjaan supaya bisa mengupdate status pesanan dengan yang sesuai
    public int getSisaHariPengerjaan() {
        return this.sisaHariPengerjaan;
    }
    
    // Setter untuk mengupdate sisaHariPengerjaan sesuai dengan berapa kali bertambahnya hari
    public void setSisaHariPengerjaan(int days) {
        this.sisaHariPengerjaan += days;
        if (this.sisaHariPengerjaan <= 0) {
            this.isReady = true;
        }
    }
    
    // Getter untuk mendapatkan isReady
    public boolean getIsReady() {
        return this.isReady;
    }
    
    // Method untuk selalu update apakah Laundry sudah bisa diambil atau tidak setiap penambahan hari
    public void checkSisaHariPengerjaan() {
        if (this.isReady) {
            System.out.println("Laundry dengan nota ID " + this.getIdNota() + " sudah dapat diambil!");
        } 
    }
    
    // Hanya getStatus method juga untuk mereturn status yang sesuai berdasarkan sisaHariPengerjaan saja
    public String checkStatus(int sisaHariPengerjaan) {
        if (sisaHariPengerjaan <= 0) {
            return "Sudah dapat diambil!";
        } else {
            return "Belum bisa diambil :(";
        }
    }


    // Method generateNota dari TP 1 yang sudah diupdate bila ada diskon
    public static String generateNota(Member member, String paket, int berat, String tanggalTerima){
        int hargaPaket = 0;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        LocalDate tanggalTerimaFormatted = LocalDate.parse(tanggalTerima, dateFormat);
        LocalDate tanggalSelesai;
        
        // Assign value untuk setiap case dan menambah day pada tanggal
        if (paket.equalsIgnoreCase("express")) {
            hargaPaket = 12000;
            tanggalSelesai = tanggalTerimaFormatted.plusDays(1);
        } else if (paket.equalsIgnoreCase("fast")) {
            hargaPaket = 10000;
            tanggalSelesai = tanggalTerimaFormatted.plusDays(2);
        } else {
            hargaPaket = 7000;
            tanggalSelesai = tanggalTerimaFormatted.plusDays(3);
        } String tanggalSelesaiString = tanggalSelesai.format(dateFormat);
        
        member.setBonusCounter();   // Mengupdate bonusCounter dari member setiap member tersebut menjalankan perintah Generate Nota
        
        String nota = "ID    : "+ member.getId() +"\n" +
                "Paket : "+ paket +"\n" +
                "Harga :\n" +
                berat + " kg x "+ hargaPaket +" = "+(hargaPaket*berat)+ checkDiskon(hargaPaket*berat, member.getBonusCounter())+ "\n" +
                "Tanggal Terima  : "+ tanggalTerima +"\n" +
                "Tanggal Selesai : " + tanggalSelesaiString;
        return nota;
    }

    // Pengecekan bila ketentuan diskon terpenuhi dan mengupdate harga
    public static String checkDiskon(int totalHarga, int bonusCount) {
        if (bonusCount == 0) {
        return " = " + totalHarga/2 + " (Discount member 50%!!!)";
        } else {
            return "";
        }
    }
}
