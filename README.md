Knowledge Management System (KMS) — Putusan Pengadilan Narkotika

Aplikasi Java berbasis arsitektur MVC (Model-View-Controller) untuk mengelola data putusan pengadilan pidana narkotika, dengan penyimpanan data menggunakan `ArrayList<Putusan>` di memory selama program berjalan.

Tugas Besar Mata Kuliah Pemrograman Berorientasi Object (PBO) — Semester Genap 2025/2026

---

Deskripsi Proyek:

Aplikasi ini memungkinkan pengguna untuk mengelola, mencari, memfilter, dan menganalisis data putusan pengadilan narkotika dari berbagai Pengadilan Negeri di Indonesia. Dataset disimpan dalam struktur data `ArrayList<Putusan>` selama aplikasi berjalan, dan diisi otomatis dengan 50 data dummy setiap kali program dijalankan.

Dibangun dengan menerapkan prinsip-prinsip OOP murni: Encapsulation, Inheritance, Polymorphism (Overloading & Overriding), Interface, Static Member, dan Exception Handling.

---

Fitur:

| No |         Fitur          |                                      Deskripsi                                            |
|----|------------------------|-------------------------------------------------------------------------------------------|
| 1  | Tambah Putusan         | Input data putusan baru dengan validasi lengkap, tersimpan ke `ArrayList<Putusan>`        |
| 2  | Tampilkan Semua        | Menampilkan seluruh data putusan dalam format tabel rapi                                  |
| 3  | Cari by Nomor Perkara  | Pencarian exact match berdasarkan nomor perkara                                           |
| 4  | Cari by Nama Terdakwa  | Pencarian partial match (mengandung kata kunci)                                           |
| 5  | Filter Jenis Narkotika | Filter data berdasarkan jenis narkotika                                                   |
| 6  | Filter Pengadilan      | Filter data berdasarkan nama Pengadilan Negeri                                            |
| 7  | Filter Rentang Vonis   | Filter berdasarkan rentang lama hukuman (bulan)                                           |
| 8  | Statistik Ringkasan    | Total data, rata-rata vonis & denda, jenis narkotika terbanyak, distribusi peran terdakwa |
| 9  | Hapus Putusan          | Menghapus data dari `ArrayList<Putusan>` (dengan konfirmasi)                              |

---

Arsitektur MVC:

```
kms_java/
├── README.md
├── .gitignore
└── src/
    ├── app/
    │   └── Main.java                   ← Entry point, inisialisasi Controller & View, loop menu
    ├── controller/
    │   └── KnowledgeController.java    ← Mediator antara View dan Model
    ├── model/
    │   ├── DataEntity.java             ← Abstract parent class (inheritance base)
    │   ├── Searchable.java             ← Interface kontrak pencarian/filter
    │   ├── Putusan.java                ← Entity utama (extends DataEntity)
    │   ├── KnowledgeRepository.java    ← Implements Searchable, menyimpan data di ArrayList<Putusan>
    │   └── StatistikPutusan.java       ← Kalkulasi statistik dari data
    ├── view/
    │   └── ConsoleView.java            ← Tampilan menu, tabel, form input
    └── util/
        └── InputHandler.java           ← Validasi input + exception handling
```

---

Alur Data MVC:

```
[View] ConsoleView
   ↕ (input mentah / output terformat)
[Controller] KnowledgeController
   ↕ (objek Putusan / hasil operasi)
[Model] KnowledgeRepository
   ↕ (ArrayList<Putusan> sebagai struktur data utama di memory)
```

---

Konsep OOP yang Diimplementasikan:

|        Konsep         |                                             Implementasi                                                      |
|-----------------------|---------------------------------------------------------------------------------------------------------------|
| Encapsulation         | Semua field `Putusan` bersifat `private`, diakses lewat getter/setter                                         |
| Inheritance           | `Putusan extends DataEntity`                                                                                  |
| Interface             | `KnowledgeRepository implements Searchable`                                                                   |
| Method Overloading    | `tampilkan()` dan `tampilkan(boolean detail)`                                                                 |
| Method Overriding     | `toString()` dan `displayInfo()` pada `Putusan`, serta seluruh method `Searchable` pada `KnowledgeRepository` |
| Static Field & Method | `jumlahDibuat` (field) dan `getJumlahDibuat()` (method)                                                       |
| ArrayList             | `ArrayList<Putusan>` sebagai struktur data utama di `KnowledgeRepository`                                     |
| Exception Handling    | `try-catch` di `InputHandler` dan `KnowledgeController`                                                       |

---

Cara Menjalankan:

Prasyarat:
- Java JDK 11 atau lebih baru
- IntelliJ IDEA (atau Eclipse/NetBeans), opsional — bisa juga lewat terminal

Langkah Setup:

**1. Clone repository**
```bash
git clone https://github.com/naufalimampamungkas/pbo-km-narkotika--pinoa-.git
cd pbo-km-narkotika--pinoa-
```

**2. Kompilasi**
```bash
javac -d out -encoding UTF-8 src/app/*.java src/controller/*.java src/model/*.java src/view/*.java src/util/*.java
```

**3. Jalankan**
```bash
java -cp out app.Main
```

Atau jika menggunakan IntelliJ IDEA: buka folder hasil clone, tandai `src` sebagai Sources Root, lalu klik kanan `src/app/Main.java` → **Run 'Main.main()'**.

Program akan otomatis mengisi 50 data dummy putusan setiap kali dijalankan (data bersifat sementara, tersimpan di memory selama program berjalan).

---

Tampilan Aplikasi:

Menu Utama:
```
============================================================
        KNOWLEDGE MANAGEMENT SYSTEM PUTUSAN NARKOTIKA
============================================================
Total Data Saat Ini   : 50
Total Objek Dibuat     : 50
============================================================

============== MENU UTAMA ==============
1. Tambah Putusan
2. Tampilkan Semua
3. Cari Berdasarkan Nomor
4. Cari Berdasarkan Nama
5. Filter Jenis Narkotika
6. Filter Pengadilan
7. Filter Rentang Vonis
8. Statistik
9. Hapus Putusan
0. Keluar
=========================================
Pilih Menu :
```

---

Struktur Branch Git:

```
main                → branch produksi (kode final)
develop             → branch integrasi sebelum ke main
feature/model       → pengembangan layer Model
feature/view        → pengembangan layer View
feature/controller  → pengembangan layer Controller
```

---

Anggota Kelompok:

| No |         Nama          |       NIM       | Kelas |                Peran                    |        Branch        |
|----|-----------------------|-----------------|-------|-----------------------------------------|----------------------|
| 1  | Naufal Imam Pamungkas | 202510370110028 |   B   | Backend Developer / Controller Engineer | `feature/controller` |
| 2  | Dewangga              | 202510370110031 |   B   | Knowledge/DB Engineer                   | `feature/model`      |
| 3  | Rafi Nasywa Pratama   | 202510370110024 |   B   | GUI Designer / View Developer           | `feature/view`       |

---

Video Demo:

> Link video demo (YouTube): [ISI LINK VIDEO]  //ISI INI

Video mencakup:
- Penjelasan arsitektur MVC dan pembagian tugas tim
- Demo alur Knowledge Management (tambah, validasi, exception handling)
- Demo pencarian & filter data
- Code walkthrough layer Model (encapsulation, constructor, overloading, static)
- Demo statistik
- Demo tampilan & CRUD akhir
- Bukti aktivitas Git (commit history, branch)

---

Lisensi

Proyek akademik — dibuat untuk keperluan Tugas Besar mata kuliah PBO, tidak untuk distribusi komersial.
