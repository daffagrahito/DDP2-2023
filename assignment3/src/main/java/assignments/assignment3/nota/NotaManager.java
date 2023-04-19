package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay() {
        for (Nota nota : notaList) {
            if (nota == null) {
                continue;
            }
            else nota.toNextDay(); // kalo ternyata yang diminta adalah employee harus kerja dulu baru sisaHariPengerjaan berkurang, berarti ini dan yang ada di class Nota dan NotaManager salah
        }        

        cal.add(Calendar.DATE, 1);
        }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        Nota[] newNotaList = new Nota[notaList.length + 1];

        for (int i = 0; i < notaList.length; i++) {
            newNotaList[i] = notaList[i];
        }

        notaList = newNotaList;
        notaList[notaList.length - 1] = nota;
    }
}

