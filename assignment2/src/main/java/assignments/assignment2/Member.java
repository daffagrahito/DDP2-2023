package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(nama, noHp);
        this.bonusCounter = addBonusCounter();
    }

    public Member() {}
    
    public String getNama() {
        return this.nama;
    }
    
    public String getNoHp() {
        return this.noHp;
    }

    public String getId() {
        return this.id;
    }

    public int getBonusCounter() {
        return this.bonusCounter;
    }

    public int addBonusCounter() {
        this.bonusCounter++;
        return this.bonusCounter % 3;
    }

    public int setBonusCounter(int num) {
        return this.bonusCounter = num;
    }
    // Method overriding equals untuk mengecek apakah kedua object memiliki konten yang sama
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Member)) {
            return false;
        }
        Member that = (Member) object;
        return this.nama.equals(that.nama) && this.noHp.equals(that.noHp);
    }
}

