package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Menghitung statistik data putusan.
 */
public class StatistikPutusan {

    private int totalPutusan;
    private double rataRataVonis;
    private double rataRataDenda;
    private String jenisNarkotikaTerbanyak;
    private String[] distribusiPeran;

    private ArrayList<Putusan> daftarPutusan;

    /**
     * Membuat objek statistik berdasarkan daftar putusan.
     *
     * @param daftarPutusan daftar data putusan
     */
    public StatistikPutusan(ArrayList<Putusan> daftarPutusan) {
        this.daftarPutusan = daftarPutusan;
        hitungSemua();
    }
    /**
     * Hitung seluruh statistik.
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

        HashMap<String, Integer> narkotikaMap = new HashMap<>();
        HashMap<String, Integer> peranMap = new HashMap<>();

        for (Putusan p : daftarPutusan) {

            totalVonis += p.getVonisHukuman();
            totalDenda += p.getVonisDenda();

            narkotikaMap.put(
                    p.getJenisNarkotika(),
                    narkotikaMap.getOrDefault(
                            p.getJenisNarkotika(), 0
                    ) + 1
            );

            peranMap.put(
                    p.getPeranTerdakwa(),
                    peranMap.getOrDefault(
                            p.getPeranTerdakwa(), 0
                    ) + 1
            );
        }

        rataRataVonis = (double) totalVonis / totalPutusan;
        rataRataDenda = totalDenda / totalPutusan;

        int terbesar = 0;
        jenisNarkotikaTerbanyak = "-";

        for (Map.Entry<String, Integer> entry :
                narkotikaMap.entrySet()) {

            if (entry.getValue() > terbesar) {

                terbesar = entry.getValue();
                jenisNarkotikaTerbanyak = entry.getKey();
            }
        }

        distribusiPeran = new String[peranMap.size()];

        int index = 0;

        for (Map.Entry<String, Integer> entry :
                peranMap.entrySet()) {

            distribusiPeran[index] =
                    String.format(
                            "%-12s : %d orang",
                            entry.getKey(),
                            entry.getValue()
                    );

            index++;
        }
    }

    /**
     * Menampilkan laporan statistik.
     */
    public void tampilkanLaporan() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       STATISTIK PUTUSAN");
        System.out.println("========================================");

        System.out.println("Total Putusan               : " + totalPutusan);

        System.out.printf(
                "Rata-rata Vonis             : %.2f bulan%n",
                rataRataVonis
        );

        System.out.printf(
                "Rata-rata Denda             : Rp %,.2f%n",
                rataRataDenda
        );

        System.out.println(
                "Jenis Narkotika Terbanyak   : "
                        + jenisNarkotikaTerbanyak
        );

        System.out.println();
        System.out.println("Distribusi Peran:");

        for (String peran : distribusiPeran) {
            System.out.println("- " + peran);
        }

        System.out.println("========================================");
    }

    // =========================
    // GETTER
    // =========================

    public int getTotalPutusan() {
        return totalPutusan;
    }

    public double getRataRataVonis() {
        return rataRataVonis;
    }

    public double getRataRataDenda() {
        return rataRataDenda;
    }

    public String getJenisNarkotikaTerbanyak() {
        return jenisNarkotikaTerbanyak;
    }

    public String[] getDistribusiPeran() {
        return distribusiPeran;
    }
}