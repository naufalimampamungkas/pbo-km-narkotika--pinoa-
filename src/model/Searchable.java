package model;

import java.util.ArrayList;

/**
 * Interface yang mendefinisikan kemampuan pencarian dan filter
 * untuk repository yang mengelola data Putusan.
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public interface Searchable {

    /**
     * Mencari putusan berdasarkan nomor perkara.
     *
     * @param nomor nomor perkara
     * @return data putusan jika ditemukan
     */
    Putusan cariByNomor(String nomor);

    ArrayList<Putusan> cariByNama(String nama);

    ArrayList<Putusan> filterByJenis(String jenis);

    ArrayList<Putusan> filterByPengadilan(String pengadilan);

    ArrayList<Putusan> filterByRentangVonis(int minBulan, int maxBulan);
}