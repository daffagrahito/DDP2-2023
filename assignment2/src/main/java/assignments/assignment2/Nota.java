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
        if (this.paket.equalsIgnoreCase("express")) {
            this.sisaHariPengerjaan = 1;
        } else if (this.paket.equalsIgnoreCase("fast")) {
            this.sisaHariPengerjaan = 2;
        } else {
            this.sisaHariPengerjaan = 3;
        }
    }

    public Member getMember(){ 
        return this.member;
    }

    public String getPaket() {
        return this.paket;
    }

    public int getBerat() {
        return this.berat;
    }

    public String getTanggalMasuk() {
        return this.tanggalMasuk;
    }

    // public int updateSisaHariPengerjaan() {
        
    // }
}
