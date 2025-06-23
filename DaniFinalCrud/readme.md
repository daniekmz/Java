# Sistem Manajemen Perpustakaan (Database Perpustakaan)

Sistem manajemen perpustakaan berbasis konsol yang dibuat dengan Java untuk mengelola data buku melalui operasi CRUD (Create, Read, Update, Delete).

## Informasi Pembuat
- **Nama Lengkap**: Muhamad Ramdani
- **NIM**: 202422027
- **Kelompok**: Kelas Sore

## Fitur Utama

Sistem ini menyediakan fungsionalitas berikut:

1. **Lihat Seluruh Buku** - Menampilkan semua buku dalam database
2. **Cari Data Buku** - Mencari buku menggunakan kata kunci
3. **Tambah Data Buku** - Menambahkan entri buku baru ke database
4. **Ubah Data Buku** - Memodifikasi informasi buku yang sudah ada
5. **Hapus Data Buku** - Menghapus buku dari database
6. **Keluar** - Menutup aplikasi

## Struktur Proyek

```
├── DaniMenu.java          # Kelas utama dengan antarmuka menu
├── crud/
│   ├── DaniOperasi.java   # Implementasi operasi CRUD
│   └── DaniUtility.java   # Fungsi-fungsi utilitas
└── database.txt           # File database berbasis teks
```

## Format Database

Sistem menggunakan file teks sederhana (`database.txt`) untuk menyimpan data buku dengan format:
```
PrimaryKey,Tahun,Penulis,Penerbit,Judul
```

Contoh:
```
Dani_2025_1,2025,Dani,Danie,sang penakluk bumi
Rika_2024_1,2024,Rika Sari,Petualangan Nusantara,kisah perjalanan melintasi kepulauan indonesia
```

## Cara Menjalankan Program

1. Pastikan Java sudah terinstal di sistem Anda
2. Kompilasi file Java:
   ```bash
   javac DaniMenu.java
   javac crud/*.java
   ```
3. Jalankan program utama:
   ```bash
   java DaniMenu
   ```

## Panduan Penggunaan

### Menu Utama

Saat menjalankan program, Anda akan melihat menu utama:

```
Database Perpustakaan

1.    Lihat Seluruh Buku
2.    Cari Data Buku
3.    Tambah Data Buku
4.    Ubah Data Buku
5.    Hapus Data Buku
6.    Keluar

Pilihan anda: 
```

### 1. Lihat Seluruh Buku
- Menampilkan semua buku dalam format tabel
- Menunjukkan: Nomor, Tahun, Penulis, Penerbit, Judul
- Jika database tidak ada, menampilkan pesan error

### 2. Cari Data Buku
- Masukkan kata kunci untuk mencari buku
- Pencarian dilakukan di semua field (tahun, penulis, penerbit, judul)
- Menampilkan hasil yang cocok dalam format tabel
- Pencarian tidak peka huruf besar/kecil

### 3. Tambah Data Buku
- Meminta detail buku:
  - Nama penulis
  - Judul buku
  - Nama penerbit
  - Tahun terbit (format YYYY)
- Mengecek duplikasi sebelum menambahkan
- Menghasilkan primary key unik secara otomatis
- Konfirmasi sebelum menyimpan ke database

### 4. Ubah Data Buku
- Menampilkan daftar semua buku dengan nomor
- Pilih nomor buku yang akan diupdate
- Pilih field mana yang akan dimodifikasi:
  - Tahun
  - Penulis
  - Penerbit
  - Judul
- Validasi data yang diupdate sebelum menyimpan
- Mencegah entri duplikat

### 5. Hapus Data Buku
- Menampilkan daftar semua buku dengan nomor
- Pilih nomor buku yang akan dihapus
- Konfirmasi penghapusan sebelum menghapus
- Memperbarui file database

### 6. Keluar
- Konfirmasi sebelum menutup aplikasi
- Menutup semua resource file dengan benar

## Fitur Unggulan

### Validasi Data
- **Validasi tahun**: Memastikan format YYYY yang benar
- **Pencegahan duplikasi**: Mengecek buku yang sudah ada sebelum menambah/mengupdate
- **Validasi input**: Memvalidasi pilihan dan input pengguna

### Operasi File
- **Penanganan file yang aman**: Membuka dan menutup resource file dengan benar
- **Sistem backup**: Menggunakan file sementara selama update/penghapusan
- **Penanganan error**: Menangani file database yang hilang dengan baik

### Antarmuka Pengguna
- **Bersihkan layar**: Membersihkan konsol untuk pengalaman pengguna yang lebih baik (Windows/Unix)
- **Output terformat**: Tampilan tabel yang terorganisir dengan baik
- **Konfirmasi pengguna**: Mengkonfirmasi operasi penting
- **Pesan error**: Pesan error yang jelas untuk operasi yang tidak valid

## Detail Teknis

### Pembuatan Primary Key
- Format: `NamaPenulis_Tahun_NomorEntri`
- Contoh: `Dani_2025_1`
- Otomatis menambah nomor entri untuk kombinasi penulis/tahun yang sama

### Struktur File
- Menggunakan format comma-separated values (CSV)
- Penyimpanan berbasis teks untuk kesederhanaan dan portabilitas
- Tidak memerlukan dependensi database eksternal

### Penanganan Error
- Menangani file database yang hilang dengan baik
- Memvalidasi input pengguna di berbagai level
- Menyediakan pesan error yang jelas dan opsi pemulihan

## Kebutuhan Sistem

- Java 8 atau lebih tinggi
- Akses konsol/terminal
- Izin baca/tulis di direktori aplikasi

## Keterbatasan

- Sistem pengguna tunggal (tidak ada akses bersamaan)
- Penyimpanan file teks (tidak cocok untuk dataset besar)
- Tidak ada fitur enkripsi atau keamanan data
- Antarmuka berbasis konsol saja

## Pengembangan Masa Depan

Perbaikan yang mungkin dilakukan:
- Antarmuka GUI
- Integrasi database (MySQL, PostgreSQL)
- Dukungan multi-user
- Fungsionalitas peminjaman/pengembalian buku
- Validasi ISBN
- Fitur ekspor/impor
- Filter pencarian dan opsi pengurutan

## Cara Kerja Program

### Alur Kerja Utama
1. Program dimulai dari `DaniMenu.java`
2. Menampilkan menu utama dalam loop
3. Memproses pilihan pengguna
4. Memanggil method yang sesuai dari `DaniOperasi.java`
5. Menggunakan utilitas dari `DaniUtility.java`
6. Menyimpan/membaca data dari `database.txt`

### Pengelolaan Data
- Data disimpan dalam format CSV di file teks
- Setiap baris mewakili satu buku
- Primary key dibuat otomatis untuk setiap entri
- File sementara digunakan untuk operasi update dan delete

## Lisensi

Ini adalah proyek edukatif yang dibuat untuk tujuan akademik.

---

*Dibuat oleh Muhamad Ramdani - NIM: 202422027 - Kelas Sore*
