package assignments.assignment3.nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int idNota;
    private String tanggalMasuk;
    private boolean isDone = false;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.idNota = totalNota;
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = NotaGenerator.toHariPaket(this.paket);
        totalNota++;
    }

    public void addService(LaundryService service){
        //TODO
    }

    public String kerjakan(){
        // TODO
        return "";
    }
    public void toNextDay() {
        this.sisaHariPengerjaan--;

    }

    public long calculateHarga(){
        // TODO
        return -1;
    }

    public String getNotaStatus(){
        // TODO
        return "";
    }

    @Override
    public String toString(){
        String formattedToString =  String.format("[ID Nota = %d]%n", idNota) + 
                                    NotaGenerator.generateNota(member.getId(), paket, berat, paket) + 
                                    "\n--- SERVICE LIST ---\n";
        return formattedToString;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public int getIdNota() {
        return this.idNota;
    }

}
