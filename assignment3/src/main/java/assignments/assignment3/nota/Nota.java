package assignments.assignment3.nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[0];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int idNota;
    private String tanggalMasuk;
    private boolean isDone = false;
    static public int totalNota;

    // Atribut tambahan untuk menyimpan value apabila telat
    private boolean telat = false;
    private int amountDayTelat = 0;

    // Parameterized Constructor dari Member
    public Nota(Member member, int berat, String paket, String tanggal) {
        this.idNota = totalNota;
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = NotaGenerator.toHariPaket(this.paket);
        this.baseHarga = NotaGenerator.toHargaPaket(this.paket);
        totalNota++;
        addService(new CuciService());
    }

    // Menambahkan service ke array milik masing-masing objek Nota
    public void addService(LaundryService service){
        LaundryService[] newServices = new LaundryService[services.length + 1];

        for (int i = 0; i < services.length; i++) {
            newServices[i] = services[i];
        }

        services = newServices;
        services[services.length - 1] = service;
    }

    // Mengerjakan service yang ada di nota
    public String kerjakan(){
        for (LaundryService service : services) {
            if (!service.isDone()) {
                String dikerjakan = "Nota " + getIdNota() + " : " + service.doWork();
                isDone = areAllServicesDone();
                return dikerjakan;
            }
        }
        return "Nota "+ getIdNota() + " : " + "Sudah selesai.";
    }

    // Mengupdate nota saat next day
    public void toNextDay() {
        if (!isDone) {
            this.sisaHariPengerjaan--;
        }
        
        if (getSisaHariPengerjaan() < 0 & !isDone()) {
            telat = true;
            setAmountDayTelat(getSisaHariPengerjaan());
        }
    }

    // Perhitungan harga nota
    public long calculateHarga(){
        if (member != null) {
            long harga = this.baseHarga * getBerat();
            for (LaundryService service : services) {
                harga += service.getHarga(getBerat());
            }
            if (telat) {
                harga += (getAmountDayTelat() * 2000);
                if (harga <= 0) {
                    harga = 0;
                    return harga;
                }
                return harga;
            } else {
                return harga;
            }
        } return -1;
    }

    // Mendapatkan status selesainya nota
    public String getNotaStatus() {
        String status = "";
        if (isDone()) {
            status += "Nota " + getIdNota() + " : Sudah selesai.";
        } else {
            status += "Nota " + getIdNota() + " : Belum selesai.";
        }
        return status;
    }

    // toString yang merupakan Override untuk mengeprint informasi nota yang diperlukan
    @Override
    public String toString(){
        String formattedToString =  String.format("[ID Nota = %d]%n", idNota) + 
                                    NotaGenerator.generateNota(member.getId(), this.paket, this.berat, this.tanggalMasuk) + 
                                    "\n--- SERVICE LIST ---\n";
        for (LaundryService serviceDipilih : services) {
            formattedToString += ("-" + serviceDipilih.getServiceName() + " @ Rp." + serviceDipilih.getHarga(this.berat) + "\n");
        }
        formattedToString += ("Harga Akhir:" + calculateHarga());

        if (telat) {
            formattedToString += " Ada kompensasi keterlambatan " + (getAmountDayTelat() * -1) + " * 2000 hari";
        }
        return formattedToString;
    }

    // Dibawah ini adalah getter dan setter

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
    
    public void setAmountDayTelat(int day) {
        this.amountDayTelat = day;
    }
    
    public int getAmountDayTelat() {
        return amountDayTelat;
    }

    // Method untuk mengupdate nilai isDone sesuai dengan sudah selesai atau belumnya service
    public boolean areAllServicesDone() {
        boolean temp = true;
        for (LaundryService service : services) {
            temp &= (service.isDone());
        }
        return temp;
    }
}
