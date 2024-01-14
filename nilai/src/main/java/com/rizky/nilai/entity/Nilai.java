/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.rizky.nilai.entity;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;

    /**
     *
     * @author ASUS
     */
    @Entity
    @Table
    public class Nilai {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private long idMahasiswa;
        private long idMatakuliah;
        private double nilai;

        public Nilai() {
        }

        public Nilai(long id, long idMahasiswa, long idMatakuliah, double nilai) {
            this.id = id;
            this.idMahasiswa = idMahasiswa;
            this.idMatakuliah = idMatakuliah;
            this.nilai = nilai;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getIdMahasiswa() {
            return idMahasiswa;
        }

        public void setIdMahasiswa(long idMahasiswa) {
            this.idMahasiswa = idMahasiswa;
        }

        public long getIdMatakuliah() {
            return idMatakuliah;
        }

        public void setIdMatakuliah(long idMatakuliah) {
            this.idMatakuliah = idMatakuliah;
        }

        public double getNilai() {
            return nilai;
        }

        public void setNilai(double nilai) {
            this.nilai = nilai;
        }

        @Override
        public String toString() {
            return "Nilai{" + "id=" + id + ", idMahasiswa=" + idMahasiswa + ", idMatakuliah=" + idMatakuliah + ", nilai=" + nilai + '}';
        }


    }