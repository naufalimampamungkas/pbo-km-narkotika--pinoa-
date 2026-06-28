package model;

import java.util.ArrayList;

/**
 * Repository utama penyimpanan data putusan.
 * Mengimplementasikan interface Searchable.
 *
 * Menangani:
 * - CRUD
 * - Search
 * - Filter
 */
public class KnowledgeRepository implements Searchable {

    private ArrayList<Putusan> daftarPutusan;

    public KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    /**
     * CREATE
     */
    public void simpan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }

    /**
     * READ ALL
     */
    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    /**
     * TOTAL DATA
     */
    public int getTotalData() {
        return daftarPutusan.size();
    }

    /**
     * SEARCH BY NOMOR
     */
    @Override
    public Putusan cariByNomor(String nomor) {

        for (Putusan p : daftarPutusan) {

            if (p.getNomorPerkara()
                    .equalsIgnoreCase(nomor)) {

                return p;
            }
        }

        return null;
    }

    /**
     * SEARCH BY NAMA
     */
    @Override
    public ArrayList<Putusan> cariByNama(String nama) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getNamaTerdakwa()
                    .toLowerCase()
                    .contains(nama.toLowerCase())) {

                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * FILTER JENIS NARKOTIKA
     */
    @Override
    public ArrayList<Putusan> filterByJenis(String jenis) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getJenisNarkotika()
                    .equalsIgnoreCase(jenis)) {

                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * FILTER PENGADILAN
     */
    @Override
    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getPengadilan()
                    .equalsIgnoreCase(pengadilan)) {

                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * FILTER RENTANG VONIS
     */
    @Override
    public ArrayList<Putusan> filterByRentangVonis(
            int minBulan,
            int maxBulan) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getVonisHukuman() >= minBulan &&
                    p.getVonisHukuman() <= maxBulan) {

                hasil.add(p);
            }
        }

        return hasil;
    }

    /**
     * DELETE
     */
    public boolean hapus(String nomorPerkara) {

        Putusan putusan = cariByNomor(nomorPerkara);

        if (putusan != null) {
            daftarPutusan.remove(putusan);
            return true;
        }

        return false;
    }
}