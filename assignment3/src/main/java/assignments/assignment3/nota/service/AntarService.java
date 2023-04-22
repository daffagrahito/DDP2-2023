package assignments.assignment3.nota.service;

// import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean isAntarDone = false;
    @Override
    public String doWork() {
        if (!isAntarDone) {
            isAntarDone = true;
        }
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return isAntarDone;
    }

    @Override
    public long getHarga(int berat) {
        long hargaMinimumAntar = 2000;
        if (berat <= 4) {
            return hargaMinimumAntar;
        } else {
            return hargaMinimumAntar += ((berat-4) * 500);
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
