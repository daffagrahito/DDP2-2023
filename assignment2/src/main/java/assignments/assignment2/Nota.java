package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    private int idNota;
    private String paket;
    private int berat;
    private Member member;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.isReady = false;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}
