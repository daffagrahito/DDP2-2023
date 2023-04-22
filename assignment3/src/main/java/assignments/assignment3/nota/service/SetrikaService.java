package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService {
    
    // Boolean untuk mengecek apakah service ini sudah selesai atau belum
    private boolean isSetrikaDone = false;
    
    @Override
    public String doWork() {
        if (!isSetrikaDone) {
            isSetrikaDone = true;
        }
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return isSetrikaDone;
    }

    @Override
    public long getHarga(int berat) {
        long totalHargaSetrika = 1000 * berat;
        return totalHargaSetrika;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
