package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Menghitung dan menyimpan hasil statistik dari seluruh data
 * {@link Putusan} yang ada di repository.
 *
 * <p>Statistik dihitung otomatis saat objek ini dibuat melalui
 * constructor, menggunakan {@link HashMap} untuk menghitung
 * frekuensi jenis narkotika dan peran terdakwa.</p>
 *
 * <p>Menggunakan {@code String[]} (array primitif) untuk menyimpan
 * distribusi peran terdakwa, sesuai requirement penggunaan array
 * dalam tugas besar ini.</p>
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public class StatistikPutusan {

    /** Total jumlah putusan yang dianalisis */
    private int totalPutusan;

    /** Rata-rata lama vonis hukuman dalam bulan */
    private double rataRataVonis;

    /** Rata-rata besaran denda dalam Rupiah */
    private double rataRataDenda;

    /** Jenis narkotika yang paling sering muncul di dataset */
    private String jenisNarkotikaTerbanyak;

    /**
     * Array distribusi peran terdakwa.
     * Setiap elemen berformat: "NamaPeran   : N orang"
     * Menggunakan array primitif {@code String[]} sesuai requirement OOP.
     */
    private String[] distribusiPeran;

    /** Dataset putusan yang digunakan untuk kalkulasi statistik */
    private ArrayList<Putusan> daftarPutusan;

    /**
     * Konstruktor yang menerima dataset dan langsung menghitung
     * seluruh statistik secara otomatis.
     *
     * @param daftarPutusan {@link ArrayList} berisi data putusan yang akan dianalisis
     */
    public StatistikPutusan(ArrayList<Putusan> daftarPutusan) {
        this.daftarPutusan = daftarPutusan;
        hitungSemua();
    }

    /**
     * Menghitung seluruh statistik dari dataset secara sekaligus.
     *
     * <p>Proses kalkulasi meliputi:</p>
     * <ol>
     *   <li>Total putusan</li>
     *   <li>Rata-rata vonis dan denda</li>
     *   <li>Jenis narkotika terbanyak (modus) menggunakan {@link HashMap}</li>
     *   <li>Distribusi peran terdakwa ke dalam {@code String[]}</li>
     * </ol>
     *
     * <p>Jika dataset kosong, semua nilai diset ke default (0 atau "-")
     * untuk menghindari pembagian dengan nol.</p>
     */
    public void hitungSemua() {

        totalPutusan = daftarPutusan.size();

        if (totalPutusan == 0) {
            rataRataVonis = 0;
            rataRataDenda = 0;
            jenisNarkotikaTerbanyak = "-";
            distribusiPeran = new String[0];
            return;
        }

        double totalDenda = 0;
        int totalVonis = 0;

        // HashMap untuk menghitung frekuensi jenis narkotika
        HashMap<String, Integer> narkotikaMap = new HashMap<>();

        // HashMap untuk menghitung frekuensi peran terdakwa
        HashMap<String, Integer> peranMap = new HashMap<>();

        for (Putusan p : daftarPutusan) {

            totalVonis += p.getVonisHukuman();
            totalDenda += p.getVonisDenda();

            // Tambah counter jenis narkotika
            narkotikaMap.put(
                    p.getJenisNarkotika(),
                    narkotikaMap.getOrDefault(
                            p.getJenisNarkotika(), 0) + 1
            );

            // Tambah counter peran terdakwa
            peranMap.put(
                    p.getPeranTerdakwa(),
                    peranMap.getOrDefault(
                            p.getPeranTerdakwa(), 0) + 1
            );
        }

        // Hitung rata-rata vonis dan denda
        rataRataVonis = (double) totalVonis / totalPutusan;
        rataRataDenda = totalDenda / totalPutusan;

        // Temukan jenis narkotika dengan frekuensi terbesar (modus)
        int terbesar = 0;
        jenisNarkotikaTerbanyak = "-";

        for (Map.Entry<String, Integer> entry : narkotikaMap.entrySet()) {
            if (entry.getValue() > terbesar) {
                terbesar = entry.getValue();
                jenisNarkotikaTerbanyak = entry.getKey();
            }
        }

        // Konversi distribusi peran ke String[] (array primitif)
        distribusiPeran = new String[peranMap.size()];
        int index = 0;

        for (Map.Entry<String, Integer> entry : peranMap.entrySet()) {
            distribusiPeran[index] = String.format(
                    "%-12s : %d orang",
                    entry.getKey(),
                    entry.getValue()
            );
            index++;
        }
    }

    /**
     * Menampilkan laporan statistik lengkap ke konsol dalam format
     * yang mudah dibaca, termasuk distribusi peran terdakwa.
     */
    public void tampilkanLaporan() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       STATISTIK PUTUSAN");
        System.out.println("========================================");
        System.out.println("Total Putusan               : " + totalPutusan);
        System.out.printf("Rata-rata Vonis             : %.2f bulan%n", rataRataVonis);
        System.out.printf("Rata-rata Denda             : Rp %,.2f%n", rataRataDenda);
        System.out.println("Jenis Narkotika Terbanyak   : " + jenisNarkotikaTerbanyak);
        System.out.println();
        System.out.println("Distribusi Peran:");

        for (String peran : distribusiPeran) {
            System.out.println("- " + peran);
        }

        System.out.println("========================================");
    }

    // ========================= GETTER =========================

    /**
     * Mengembalikan total jumlah putusan yang dianalisis.
     * @return total putusan
     */
    public int getTotalPutusan() { return totalPutusan; }

    /**
     * Mengembalikan rata-rata lama vonis hukuman dalam bulan.
     * @return rata-rata vonis dalam bulan
     */
    public double getRataRataVonis() { return rataRataVonis; }

    /**
     * Mengembalikan rata-rata besaran denda dalam Rupiah.
     * @return rata-rata denda dalam Rupiah
     */
    public double getRataRataDenda() { return rataRataDenda; }

    /**
     * Mengembalikan jenis narkotika yang paling sering muncul.
     * @return nama jenis narkotika terbanyak
     */
    public String getJenisNarkotikaTerbanyak() { return jenisNarkotikaTerbanyak; }

    /**
     * Mengembalikan array distribusi peran terdakwa.
     * Setiap elemen berformat "NamaPeran : N orang".
     * @retuRn {@code String[]} distribusi peran terdakwa
     */
    public String[] getDistribusiPeran() { return distribusiPeran; }
}