package com.rizky.mahasiswa.service;


import com.rizky.mahasiswa.entity.Mahasiswa;
import com.rizky.mahasiswa.repository.MahasiswaRepository;
import com.rizky.mahasiswa.repository.MahasiswaRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
@Service
public class Mahasiswaservice {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    
    public List<Mahasiswa> getAll(){
        return mahasiswaRepository.findAll();
    }
    
    public void insert(Mahasiswa mahasiswa){
        Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findMahasiswaByEmail(mahasiswa.getEmail());
        
        if(mahasiswaOptional.isPresent()){
            throw new IllegalStateException("Email Sudah Ada");
        }
        
        mahasiswaRepository.save(mahasiswa);
    }
    
    public Mahasiswa getMahasiswa(Long id){
        Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findById(id);
        return mahasiswaOptional.get();
    }
    
    public void delete(Long id){
        boolean ada = mahasiswaRepository.existsById(id);
        if (!ada) {
            throw new IllegalStateException("Mahasiswa ini tidak ada");
        }
        mahasiswaRepository.deleteById(id);
    }
    
    @Transactional
    public void update(Long id, String nama, String email,LocalDate tglLahir){
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
        .orElseThrow(()-> new IllegalStateException("Mahasiswa tidak ada"));
        
        if (nama != null && nama.length()>0 && !Objects.equals(mahasiswa.getNama(), nama)) {
            mahasiswa.setNama(nama);
        }
        
        if (email != null && email.length()>0 && !Objects.equals(mahasiswa.getEmail(), email)) {
            mahasiswa.setEmail(email);
        }
        
        if (tglLahir != null && !Objects.equals(mahasiswa.getTglLahir(), tglLahir)) {
            mahasiswa.setEmail(email);
        }
        
    }
}