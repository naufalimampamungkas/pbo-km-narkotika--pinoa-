package model;

/**
 * Representasi satu putusan pengadilan pidana narkotika.
 *
 * <p>Class ini mendemonstrasikan konsep OOP berikut:</p>
 * <ul>
 *   <li><b>Inheritance</b>     : extends {@link DataEntity}</li>
 *   <li><b>Encapsulation</b>   : semua field {@code private}, akses via getter/setter</li>
 *   <li><b>Overriding</b>      : {@link #toString()} dan {@link #displayInfo()}</li>
 *   <li><b>Overloading</b>     : {@link #tampilkan()} dan {@link #tampilkan(boolean)}</li>
 *   <li><b>Static</b>          : {@link #jumlahDibuat} dan {@link #getJumlahDibuat()}</li>
 * </ul>
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public class Putusan extends DataEntity {

    /**
     * Menghitung total objek {@link Putusan} yang pernah diinstansiasi
     * selama program berjalan. Bertambah setiap kali constructor dipanggil.
     * Demonstrasi penggunaan {@code static field}.
     */
    private static int jumlahDibuat = 0;

    // ======================== Private Fields ========================

    /** Nomor perkara, contoh: "1001/Pid.Sus/2024/PN Sby" */
    private String nomorPerkara;

    /** Nama Pengadilan Negeri tempat perkara disidangkan */
    private String pengadilan;

    /** Tanggal putusan dijatuhkan, contoh: "15 Januari 2024" */
    private String tanggalPutusan;

    /** Nama lengkap terdakwa */
    private String namaTerdakwa;

    /** Usia terdakwa dalam tahun */
    private int umurTerdakwa;

    /** Jenis narkotika, contoh: "Sabu", "Ganja", "Ekstasi" */
    private String jenisNarkotika;

    /** Berat atau jumlah barang bukti dalam gram */
    private double beratBarangBukti;

    /** Pasal UU No.35/2009 yang dilanggar */
    private String pasalDilanggar;

    /** Peran terdakwa: Bandar, Kurir, Pengguna, atau Penyimpan */
    private String peranTerdakwa;

    /** Lama hukuman penjara dalam bulan */
    private int vonisHukuman;

    /** Besaran denda dalam Rupiah */
    private double vonisDenda;

    /** Nama hakim ketua yang memimpin persidangan */
    private String namaHakim;

    // ======================== Constructors ========================

    /**
     * No-arg constructor — menginisialisasi semua field dengan nilai default.
     * Setiap pemanggilan constructor menambah {@link #jumlahDibuat}.
     */
    public Putusan() {
        super("", "");
        this.nomorPerkara     = "";
        this.pengadilan       = "";
        this.tanggalPutusan   = "";
        this.namaTerdakwa     = "";
        this.umurTerdakwa     = 0;
        this.jenisNarkotika   = "";
        this.beratBarangBukti = 0.0;
        this.pasalDilanggar   = "";
        this.peranTerdakwa    = "";
        this.vonisHukuman     = 0;
        this.vonisDenda       = 0.0;
        this.namaHakim        = "";
        jumlahDibuat++;
    }

    /**
     * Parameterized constructor — membuat objek Putusan dengan semua field terisi.
     * Setiap pemanggilan constructor menambah {@link #jumlahDibuat}.
     *
     * @param nomorPerkara     nomor perkara (contoh: "1001/Pid.Sus/2024")
     * @param pengadilan       nama pengadilan negeri
     * @param tanggalPutusan   tanggal putusan dijatuhkan
     * @param namaTerdakwa     nama lengkap terdakwa
     * @param umurTerdakwa     usia terdakwa dalam tahun
     * @param jenisNarkotika   jenis narkotika yang terlibat
     * @param beratBarangBukti berat barang bukti dalam gram
     * @param pasalDilanggar   pasal UU yang dilanggar
     * @param peranTerdakwa    peran terdakwa dalam kasus
     * @param vonisHukuman     lama hukuman dalam bulan
     * @param vonisDenda       besaran denda dalam Rupiah
     * @param namaHakim        nama hakim ketua
     */
    public Putusan(String nomorPerkara, String pengadilan, String tanggalPutusan,
                   String namaTerdakwa, int umurTerdakwa, String jenisNarkotika,
                   double beratBarangBukti, String pasalDilanggar, String peranTerdakwa,
                   int vonisHukuman, double vonisDenda, String namaHakim) {
        super(nomorPerkara, tanggalPutusan);
        this.nomorPerkara     = nomorPerkara;
        this.pengadilan       = pengadilan;
        this.tanggalPutusan   = tanggalPutusan;
        this.namaTerdakwa     = namaTerdakwa;
        this.umurTerdakwa     = umurTerdakwa;
        this.jenisNarkotika   = jenisNarkotika;
        this.beratBarangBukti = beratBarangBukti;
        this.pasalDilanggar   = pasalDilanggar;
        this.peranTerdakwa    = peranTerdakwa;
        this.vonisHukuman     = vonisHukuman;
        this.vonisDenda       = vonisDenda;
        this.namaHakim        = namaHakim;
        jumlahDibuat++;
    }

    // ======================== Static Method ========================

    /**
     * Mengembalikan total objek {@link Putusan} yang pernah dibuat.
     * Demonstrasi penggunaan {@code static method}.
     *
     * @return total objek Putusan yang diinstansiasi sejak program berjalan
     */
    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

    // ======================== Method Overloading ========================

    /**
     * Menampilkan ringkasan satu baris putusan ini ke konsol.
     * Versi tanpa parameter dari method overloading {@code tampilkan}.
     */
    public void tampilkan() {
        System.out.printf("%-25s | %-25s | %-12s | %d bulan%n",
                nomorPerkara, namaTerdakwa, jenisNarkotika, vonisHukuman);
    }

    /**
     * Menampilkan putusan ini dalam format ringkas atau detail.
     * Versi dengan parameter boolean dari method overloading {@code tampilkan}.
     *
     * @param detail jika {@code true} tampilkan semua field secara lengkap,
     *               jika {@code false} tampilkan ringkasan satu baris
     */
    public void tampilkan(boolean detail) {
        if (!detail) {
            tampilkan();
            return;
        }
        System.out.println("=".repeat(60));
        System.out.println("  DETAIL PUTUSAN PENGADILAN");
        System.out.println("=".repeat(60));
        System.out.printf("  Nomor Perkara   : %s%n", nomorPerkara);
        System.out.printf("  Pengadilan      : %s%n", pengadilan);
        System.out.printf("  Tanggal Putusan : %s%n", tanggalPutusan);
        System.out.printf("  Nama Terdakwa   : %s%n", namaTerdakwa);
        System.out.printf("  Umur Terdakwa   : %d tahun%n", umurTerdakwa);
        System.out.printf("  Jenis Narkotika : %s%n", jenisNarkotika);
        System.out.printf("  Berat Barang    : %.2f gram%n", beratBarangBukti);
        System.out.printf("  Pasal Dilanggar : %s%n", pasalDilanggar);
        System.out.printf("  Peran Terdakwa  : %s%n", peranTerdakwa);
        System.out.printf("  Vonis Hukuman   : %d bulan%n", vonisHukuman);
        System.out.printf("  Vonis Denda     : Rp %,.0f%n", vonisDenda);
        System.out.printf("  Nama Hakim      : %s%n", namaHakim);
        System.out.printf("  Kategori        : %s%n", getKategoriHukuman());
        System.out.println("=".repeat(60));
    }

    /**
     * Mengkategorikan berat ringannya hukuman berdasarkan lama vonis.
     *
     * @return "Ringan" jika vonis kurang dari 24 bulan,
     *         "Sedang" jika 24–60 bulan,
     *         "Berat" jika lebih dari 60 bulan
     */
    public String getKategoriHukuman() {
        if (vonisHukuman < 24) {
            return "Ringan";
        } else if (vonisHukuman <= 60) {
            return "Sedang";
        } else {
            return "Berat";
        }
    }

    // ======================== Method Overriding ========================

    /**
     * Override {@link DataEntity#displayInfo()} untuk menampilkan
     * informasi ringkas putusan dalam satu baris.
     * Demonstrasi method overriding (polimorfisme).
     */
    @Override
    public void displayInfo() {
        System.out.printf("[Putusan] %s - %s (%s) | Vonis: %d bln | Kategori: %s%n",
                nomorPerkara, namaTerdakwa, jenisNarkotika,
                vonisHukuman, getKategoriHukuman());
    }

    /**
     * Override {@link Object#toString()} untuk representasi String
     * dari objek Putusan ini.
     * Demonstrasi method overriding.
     *
     * @return String berformat ringkas berisi field utama putusan
     */
    @Override
    public String toString() {
        return String.format(
                "Putusan{nomorPerkara='%s', namaTerdakwa='%s', jenis='%s', vonis=%d bln}",
                nomorPerkara, namaTerdakwa, jenisNarkotika, vonisHukuman);
    }

    // ======================== Getters & Setters ========================

    /** @return nomor perkara */
    public String getNomorPerkara() { return nomorPerkara; }
    /** @param nomorPerkara nomor perkara yang akan diset */
    public void setNomorPerkara(String nomorPerkara) { this.nomorPerkara = nomorPerkara; }

    /** @return nama pengadilan */
    public String getPengadilan() { return pengadilan; }
    /** @param pengadilan nama pengadilan yang akan diset */
    public void setPengadilan(String pengadilan) { this.pengadilan = pengadilan; }

    /** @return tanggal putusan */
    public String getTanggalPutusan() { return tanggalPutusan; }
    /** @param tanggalPutusan tanggal putusan yang akan diset */
    public void setTanggalPutusan(String tanggalPutusan) { this.tanggalPutusan = tanggalPutusan; }

    /** @return nama terdakwa */
    public String getNamaTerdakwa() { return namaTerdakwa; }
    /** @param namaTerdakwa nama terdakwa yang akan diset */
    public void setNamaTerdakwa(String namaTerdakwa) { this.namaTerdakwa = namaTerdakwa; }

    /** @return umur terdakwa dalam tahun */
    public int getUmurTerdakwa() { return umurTerdakwa; }
    /**
     * Mengeset umur terdakwa dengan validasi — hanya diset jika lebih dari 0.
     * @param umurTerdakwa umur dalam tahun (harus positif)
     */
    public void setUmurTerdakwa(int umurTerdakwa) {
        if (umurTerdakwa > 0) this.umurTerdakwa = umurTerdakwa;
    }

    /** @return jenis narkotika */
    public String getJenisNarkotika() { return jenisNarkotika; }
    /** @param jenisNarkotika jenis narkotika yang akan diset */
    public void setJenisNarkotika(String jenisNarkotika) { this.jenisNarkotika = jenisNarkotika; }

    /** @return berat barang bukti dalam gram */
    public double getBeratBarangBukti() { return beratBarangBukti; }
    /**
     * Mengeset berat barang bukti dengan validasi — hanya diset jika lebih dari 0.
     * @param beratBarangBukti berat dalam gram (harus positif)
     */
    public void setBeratBarangBukti(double beratBarangBukti) {
        if (beratBarangBukti > 0) this.beratBarangBukti = beratBarangBukti;
    }

    /** @return pasal yang dilanggar */
    public String getPasalDilanggar() { return pasalDilanggar; }
    /** @param pasalDilanggar pasal yang akan diset */
    public void setPasalDilanggar(String pasalDilanggar) { this.pasalDilanggar = pasalDilanggar; }

    /** @return peran terdakwa */
    public String getPeranTerdakwa() { return peranTerdakwa; }
    /** @param peranTerdakwa peran terdakwa yang akan diset */
    public void setPeranTerdakwa(String peranTerdakwa) { this.peranTerdakwa = peranTerdakwa; }

    /** @return lama vonis hukuman dalam bulan */
    public int getVonisHukuman() { return vonisHukuman; }
    /**
     * Mengeset vonis hukuman dengan validasi — hanya diset jika tidak negatif.
     * @param vonisHukuman lama vonis dalam bulan (harus >= 0)
     */
    public void setVonisHukuman(int vonisHukuman) {
        if (vonisHukuman >= 0) this.vonisHukuman = vonisHukuman;
    }

    /** @return besaran denda dalam Rupiah */
    public double getVonisDenda() { return vonisDenda; }
    /**
     * Mengeset vonis denda dengan validasi — hanya diset jika tidak negatif.
     * @param vonisDenda besaran denda dalam Rupiah (harus >= 0)
     */
    public void setVonisDenda(double vonisDenda) {
        if (vonisDenda >= 0) this.vonisDenda = vonisDenda;
    }

    /** @return nama hakim ketua */
    public String getNamaHakim() { return namaHakim; }
    /** @param namaHakim nama hakim yang akan diset */
    public void setNamaHakim(String namaHakim) { this.namaHakim = namaHakim; }
}