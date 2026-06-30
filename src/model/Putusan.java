package model;

/**
 * Merepresentasikan data putusan pengadilan tindak pidana narkotika.
 * Class ini menyimpan seluruh informasi putusan yang digunakan
 * dalam sistem Knowledge Management.
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public class Putusan extends DataEntity {

    private static int jumlahDibuat = 0;

    private String nomorPerkara;
    private String pengadilan;
    private String tanggalPutusan;
    private String namaTerdakwa;
    private int umurTerdakwa;
    private String jenisNarkotika;
    private double beratBarangBukti;
    private String pasalDilanggar;
    private String peranTerdakwa;
    private int vonisHukuman;
    private double vonisDenda;
    private String namaHakim;

    /**
     * Membuat objek Putusan dengan data lengkap.
     *
     * @param nomorPerkara nomor perkara
     * @param pengadilan nama pengadilan
     * @param tanggalPutusan tanggal putusan
     * @param namaTerdakwa nama terdakwa
     * @param umurTerdakwa umur terdakwa
     * @param jenisNarkotika jenis narkotika
     * @param beratBarangBukti berat barang bukti
     * @param pasalDilanggar pasal yang dilanggar
     * @param peranTerdakwa peran terdakwa
     * @param vonisHukuman lama vonis
     * @param vonisDenda besar denda
     * @param namaHakim nama hakim
     */
    public Putusan() {
        super("", "");
        this.nomorPerkara   = "";
        this.pengadilan     = "";
        this.tanggalPutusan = "";
        this.namaTerdakwa   = "";
        this.umurTerdakwa   = 0;
        this.jenisNarkotika = "";
        this.beratBarangBukti = 0.0;
        this.pasalDilanggar = "";
        this.peranTerdakwa  = "";
        this.vonisHukuman   = 0;
        this.vonisDenda     = 0.0;
        this.namaHakim      = "";
        jumlahDibuat++;
    }

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

    /**
     * Mengembalikan jumlah objek Putusan yang telah dibuat.
     *
     * @return jumlah objek Putusan
     */
    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

    public void tampilkan() {
        System.out.printf("%-25s | %-25s | %-12s | %d bulan%n",
                nomorPerkara, namaTerdakwa, jenisNarkotika, vonisHukuman);
    }

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

    public String getKategoriHukuman() {
        if (vonisHukuman < 24) {
            return "Ringan";
        } else if (vonisHukuman <= 60) {
            return "Sedang";
        } else {
            return "Berat";
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("[Putusan] %s - %s (%s) | Vonis: %d bln | Kategori: %s%n",
                nomorPerkara, namaTerdakwa, jenisNarkotika, vonisHukuman, getKategoriHukuman());
    }

    @Override
    public String toString() {
        return String.format("Putusan{nomorPerkara='%s', namaTerdakwa='%s', jenis='%s', vonis=%d bln}",
                nomorPerkara, namaTerdakwa, jenisNarkotika, vonisHukuman);
    }

    public String getNomorPerkara() { return nomorPerkara; }
    public void setNomorPerkara(String nomorPerkara) { this.nomorPerkara = nomorPerkara; }

    public String getPengadilan() { return pengadilan; }
    public void setPengadilan(String pengadilan) { this.pengadilan = pengadilan; }

    public String getTanggalPutusan() { return tanggalPutusan; }
    public void setTanggalPutusan(String tanggalPutusan) { this.tanggalPutusan = tanggalPutusan; }

    public String getNamaTerdakwa() { return namaTerdakwa; }
    public void setNamaTerdakwa(String namaTerdakwa) { this.namaTerdakwa = namaTerdakwa; }

    public int getUmurTerdakwa() { return umurTerdakwa; }
    public void setUmurTerdakwa(int umurTerdakwa) {
        if (umurTerdakwa > 0) this.umurTerdakwa = umurTerdakwa;
    }

    public String getJenisNarkotika() { return jenisNarkotika; }
    public void setJenisNarkotika(String jenisNarkotika) { this.jenisNarkotika = jenisNarkotika; }

    public double getBeratBarangBukti() { return beratBarangBukti; }
    public void setBeratBarangBukti(double beratBarangBukti) {
        if (beratBarangBukti > 0) this.beratBarangBukti = beratBarangBukti;
    }

    public String getPasalDilanggar() { return pasalDilanggar; }
    public void setPasalDilanggar(String pasalDilanggar) { this.pasalDilanggar = pasalDilanggar; }

    public String getPeranTerdakwa() { return peranTerdakwa; }
    public void setPeranTerdakwa(String peranTerdakwa) { this.peranTerdakwa = peranTerdakwa; }

    public int getVonisHukuman() { return vonisHukuman; }
    public void setVonisHukuman(int vonisHukuman) {
        if (vonisHukuman >= 0) this.vonisHukuman = vonisHukuman;
    }

    public double getVonisDenda() { return vonisDenda; }
    public void setVonisDenda(double vonisDenda) {
        if (vonisDenda >= 0) this.vonisDenda = vonisDenda;
    }

    public String getNamaHakim() { return namaHakim; }
    public void setNamaHakim(String namaHakim) { this.namaHakim = namaHakim; }
}