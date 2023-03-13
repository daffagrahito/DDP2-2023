package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // Fields yang dibutuhkan oleh class Member
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;
    
    // Constructor dari Member
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp); // Membuat ID dengan menggunakan method dari NotaGenerator.java
        this.bonusCounter = 0;                          // Assign value bonusCounter untuk pengecekan diskon nanti dengan mengaplikasikan penggunakan setBonusCounter
    }
    
    // Getter untuk nama member
    public String getNama() {
        return this.nama;
    }
    
    // Getter untuk nomor hp member
    public String getNoHp() {
        return this.noHp;
    }

    // Getter untuk ID member 
    public String getId() {
        return this.id;
    }

    // Getter untuk bonusCounter
    public int getBonusCounter() {
        return this.bonusCounter;
    }

    // Setter untuk bonusCounter 
    public void setBonusCounter() {
        this.bonusCounter++;
        this.bonusCounter %= 3;
    }
}

