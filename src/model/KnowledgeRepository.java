package model;

import java.util.ArrayList;

/**
 * Repository utama yang mengelola koleksi data {@link Putusan}
 * menggunakan {@code ArrayList} sebagai struktur data utama.
 *
 * <p>Mengimplementasikan interface {@link Searchable} untuk memenuhi
 * kontrak pencarian dan filter data putusan.</p>
 *
 * <p>Menangani operasi:</p>
 * <ul>
 *   <li><b>Create</b> — menyimpan putusan baru via {@link #simpan(Putusan)}</li>
 *   <li><b>Read</b>   — mengambil semua data via {@link #getDaftarSemua()}</li>
 *   <li><b>Delete</b> — menghapus data via {@link #hapus(String)}</li>
 *   <li><b>Search</b> — mencari by nomor/nama via metode Searchable</li>
 *   <li><b>Filter</b> — filter by jenis/pengadilan/rentang vonis</li>
 * </ul>
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public class KnowledgeRepository implements Searchable {

    /**
     * Struktur data utama penyimpanan seluruh objek {@link Putusan}.
     * Dipilih ArrayList karena ukurannya dinamis dan mendukung
     * operasi iterasi, tambah, dan hapus dengan mudah.
     */
    private ArrayList<Putusan> daftarPutusan;

    /**
     * Konstruktor default yang menginisialisasi ArrayList kosong
     * sebagai wadah penyimpanan data putusan.
     */
    public KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    /**
     * Menyimpan (CREATE) satu objek {@link Putusan} ke dalam ArrayList.
     *
     * @param putusan objek Putusan yang akan disimpan, tidak boleh {@code null}
     */
    /**
     * Menyimpan data putusan ke dalam repository.
     *
     * @param putusan objek putusan yang akan disimpan
     */
    public void simpan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }
    /**
     * Mengambil (READ ALL) seluruh data putusan yang tersimpan.
     *
     * @return {@link ArrayList} berisi semua objek {@link Putusan},
     *         atau ArrayList kosong jika belum ada data
     */
    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    /**
     * Menghitung total jumlah data putusan yang tersimpan di repository.
     *
     * @return jumlah elemen dalam {@code daftarPutusan}
     */
    public int getTotalData() {
        return daftarPutusan.size();
    }

    /**
     * Mencari satu putusan berdasarkan nomor perkara (exact match,
     * case-insensitive).
     *
     * <p>Mengiterasi seluruh ArrayList dan membandingkan
     * {@code nomorPerkara} menggunakan {@code equalsIgnoreCase}.</p>
     *
     * @param nomor nomor perkara yang dicari (contoh: "1001/Pid.Sus/2024")
     * @return objek {@link Putusan} jika ditemukan, atau {@code null} jika tidak ada
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
     * Mencari semua putusan yang nama terdakwanya mengandung
     * keyword tertentu (partial match, case-insensitive).
     *
     * <p>Menggunakan {@code contains} + {@code toLowerCase} agar
     * pencarian tidak sensitif terhadap huruf besar/kecil.</p>
     *
     * @param nama keyword nama terdakwa yang dicari
     * @return {@link ArrayList} berisi semua putusan yang cocok,
     *         atau ArrayList kosong jika tidak ada yang cocok
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
     * Memfilter putusan berdasarkan jenis narkotika (exact match,
     * case-insensitive).
     *
     * @param jenis jenis narkotika yang difilter (contoh: "Sabu", "Ganja")
     * @return {@link ArrayList} berisi putusan dengan jenis narkotika yang sesuai
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
     * Memfilter putusan berdasarkan nama pengadilan (exact match,
     * case-insensitive).
     *
     * @param pengadilan nama pengadilan yang difilter (contoh: "PN Surabaya")
     * @return {@link ArrayList} berisi putusan dari pengadilan yang sesuai
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
     * Memfilter putusan berdasarkan rentang lama vonis hukuman (inklusif).
     *
     * <p>Menggunakan operator {@code >=} dan {@code <=} sehingga
     * batas bawah dan batas atas termasuk dalam hasil filter.</p>
     *
     * @param minBulan batas bawah vonis dalam bulan (inklusif)
     * @param maxBulan batas atas vonis dalam bulan (inklusif)
     * @return {@link ArrayList} berisi putusan dalam rentang vonis tersebut
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
     * Menghapus (DELETE) satu putusan berdasarkan nomor perkara.
     *
     * <p>Mencari terlebih dahulu menggunakan {@link #cariByNomor(String)},
     * kemudian menghapus dari ArrayList jika ditemukan.</p>
     *
     * @param nomorPerkara nomor perkara yang akan dihapus
     * @return {@code true} jika berhasil dihapus,
     *         {@code false} jika nomor perkara tidak ditemukan
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