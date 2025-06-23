//	* Nama Lengkap Anda:Muhamad Ramdani
//	* NIM: 202422027
//	* Kelompok: Kelas Sore

import java.io.IOException;
import java.util.Scanner;
import crud.DaniOperasi;
import crud.DaniUtility;

public class DaniMenu {

    public static void main(String[] args) throws IOException {

        Scanner DaniMenu = new Scanner(System.in);
        String DaniPilihan;
        boolean DaniLanjut = true;

        while (DaniLanjut) {
            DaniUtility.DaniHapusLayar();
            System.out.println("Database Perpustakaan\n");
            System.out.println("1.\tLihat Seluruh Buku");
            System.out.println("2.\tCari Data Buku");
            System.out.println("3.\tTambah Data Buku");
            System.out.println("4.\tUbah Data Buku");
            System.out.println("5.\tHapus Data Buku");
            System.out.println("6.\tKeluar");

            System.out.print("\nPilihan anda: ");
            DaniPilihan = DaniMenu.next();

            switch (DaniPilihan) {
                case "1":
                    System.out.println("\n==================");
                    System.out.println("LIST SELURUH BUKU");
                    System.out.println("==================");
                    DaniOperasi.DaniTampilkanData();
                    break;
                case "2":
                    System.out.println("\n==========");
                    System.out.println("CARI BUKU");
                    System.out.println("==========");
                    DaniOperasi.DaniCariData();
                    break;
                case "3":
                    System.out.println("\n==================");
                    System.out.println("TAMBAH DATA BUKU");
                    System.out.println("==================");
                    DaniOperasi.DaniTambahData();
                    break;
                case "4":
                    System.out.println("\n================");
                    System.out.println("UBAH DATA BUKU");
                    System.out.println("================");
                    DaniOperasi.DaniUpdateData();
                    break;
                case "5":
                    System.out.println("\n=================");
                    System.out.println("HAPUS DATA BUKU");
                    System.out.println("=================");
                    DaniOperasi.DaniHapusData();
                    break;
                case "6":
                    DaniLanjut = !DaniUtility.DaniYaAtauTidak("Apakah Anda Mau Keluar?");
                    break;
                default:
                    System.err.println("\nInput Anda Tidak Ditemukan\nSilahkan Pilih [1-6]");
            }

            if (DaniLanjut) {
                DaniLanjut = DaniUtility.DaniYaAtauTidak("Apakah Anda Ingin Melanjutkan?");
            }
        }
        DaniMenu.close();
    }
}