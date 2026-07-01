package model;

import java.util.ArrayList;

/**
 * Interface yang mendefinisikan kontrak kemampuan pencarian dan filter
 * untuk class repository yang mengelola koleksi data {@link Putusan}.
 *
 * <p>Setiap class yang mengimplementasikan interface ini wajib
 * menyediakan implementasi konkret untuk semua method yang didefinisikan
 * di sini. Saat ini diimplementasikan oleh {@link KnowledgeRepository}.</p>
 *
 * <p>Demonstrasi konsep OOP:</p>
 * <ul>
 *   <li><b>Interface</b>     : mendefinisikan kontrak tanpa implementasi</li>
 *   <li><b>Polymorphism</b>  : implementasi berbeda bisa digunakan secara bergantian</li>
 *   <li><b>Abstraksi</b>     : memisahkan "apa yang bisa dilakukan" dari "bagaimana caranya"</li>
 * </ul>
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public interface Searchable {

    /**
     * Mencari satu putusan berdasarkan nomor perkara (exact match).
     *
     * @param nomor nomor perkara yang dicari, contoh: "1001/Pid.Sus/2024"
     * @return objek {@link Putusan} jika ditemukan, atau {@code null} jika tidak ada
     */
    Putusan cariByNomor(String nomor);

    /**
     * Mencari semua putusan yang nama terdakwanya mengandung keyword
     * tertentu (partial match, case-insensitive).
     *
     * @param nama keyword nama terdakwa yang dicari
     * @return {@link ArrayList} berisi semua putusan yang cocok,
     *         atau ArrayList kosong jika tidak ada yang cocok
     */
    ArrayList<Putusan> cariByNama(String nama);

    /**
     * Memfilter putusan berdasarkan jenis narkotika yang terlibat.
     *
     * @param jenis jenis narkotika yang difilter, contoh: "Sabu", "Ganja"
     * @return {@link ArrayList} berisi putusan dengan jenis narkotika yang sesuai
     */
    ArrayList<Putusan> filterByJenis(String jenis);

    /**
     * Memfilter putusan berdasarkan nama pengadilan tempat perkara disidangkan.
     *
     * @param pengadilan nama pengadilan yang difilter, contoh: "PN Surabaya"
     * @return {@link ArrayList} berisi putusan dari pengadilan yang sesuai
     */
    ArrayList<Putusan> filterByPengadilan(String pengadilan);

    /**
     * Memfilter putusan berdasarkan rentang lama vonis hukuman dalam bulan
     * (batas bawah dan batas atas inklusif).
     *
     * @param minBulan batas bawah vonis dalam bulan (inklusif)
     * @param maxBulan batas atas vonis dalam bulan (inklusif)
     * @return {@link ArrayList} berisi putusan dalam rentang vonis tersebut
     */
    ArrayList<Putusan> filterByRentangVonis(int minBulan, int maxBulan);
}