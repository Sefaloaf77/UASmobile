package com.example.praktikum_psi_final.repository

class ModelBarang(
    val idBarang: String,
    val namaBarang: String,
    val hargaBarang: Int,
    val kategori: String,
    val deskripsi: String,
    val fotoProduk: String
) {
    constructor() : this("", "", 0, "", "", "")
}