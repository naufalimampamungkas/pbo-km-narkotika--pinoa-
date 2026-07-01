package model;

/**
 * Abstract parent class (kelas induk abstrak) untuk semua entitas domain
 * dalam sistem KMS Putusan Pengadilan Narkotika.
 *
 * <p>Menyediakan field dan behavior umum yang diwarisi oleh semua subclass.
 * Class ini tidak dapat diinstansiasi langsung — harus di-extend terlebih dahulu.</p>
 *
 * <p>Demonstrasi konsep OOP:</p>
 * <ul>
 *   <li><b>Abstraksi</b>   : class dideklarasikan {@code abstract}</li>
 *   <li><b>Inheritance</b> : menjadi parent class dari {@link Putusan}</li>
 *   <li><b>Encapsulation</b>: field {@code private} diakses via getter/setter</li>
 * </ul>
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public abstract class DataEntity {

    /** Identifikasi unik entitas, diisi dengan nomorPerkara pada subclass Putusan */
    private String id;

    /** Waktu pembuatan entitas, diisi dengan tanggalPutusan pada subclass Putusan */
    private String createdAt;

    /**
     * No-arg constructor — menginisialisasi semua field dengan String kosong.
     * Dipanggil oleh no-arg constructor subclass via {@code super("", "")}.
     */
    public DataEntity() {
        this.id = "";
        this.createdAt = "";
    }

    /**
     * Parameterized constructor — menginisialisasi field dengan nilai yang diberikan.
     * Dipanggil oleh parameterized constructor subclass via {@code super(id, createdAt)}.
     *
     * @param id        identifikasi unik entitas
     * @param createdAt waktu pembuatan entitas
     */
    public DataEntity(String id, String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    // ======================== Getters & Setters ========================

    /**
     * Mengembalikan identifikasi unik entitas ini.
     *
     * @return nilai field {@code id}
     */
    public String getId() {
        return id;
    }

    /**
     * Mengeset identifikasi unik entitas ini.
     *
     * @param id nilai id yang akan diset
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Mengembalikan waktu pembuatan entitas ini.
     *
     * @return nilai field {@code createdAt}
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Mengeset waktu pembuatan entitas ini.
     *
     * @param createdAt nilai createdAt yang akan diset
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // ======================== Abstract Methods ========================

    /**
     * Menampilkan informasi ringkas entitas ini ke konsol.
     *
     * <p>Wajib di-override oleh semua subclass — setiap subclass
     * menentukan sendiri format tampilan informasinya.</p>
     */
    public abstract void displayInfo();

    /**
     * Mengembalikan representasi String dari entitas ini.
     *
     * <p>Wajib di-override oleh semua subclass untuk menghasilkan
     * format String yang sesuai dengan domain masing-masing.</p>
     *
     * @return String Representsi objek ini
     */
    @Override
    public abstract String toString();
}