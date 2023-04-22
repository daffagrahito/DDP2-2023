package assignments.assignment3.nota.service;

public class CuciService implements LaundryService {
    
    // Boolean untuk mengecek apakah service ini sudah selesai atau belum
    private boolean isCuciDone = false;

    @Override
    public String doWork() {
        if (!isCuciDone) {
            isCuciDone = true;
        }
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        return isCuciDone;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
