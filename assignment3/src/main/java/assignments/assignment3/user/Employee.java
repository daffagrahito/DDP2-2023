package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;    // Counting banyaknya employee yang tersedia

    // Parameterized Constructor dari Employee
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        nama = nama.replaceAll("\\s.*", ""); //  Mengambil kata sebelum whitespace pertama saja menggunakan regex
        String idEmployee = nama.toUpperCase() + "-" + employeeCount;
        employeeCount++;
        return idEmployee;
    }
}
