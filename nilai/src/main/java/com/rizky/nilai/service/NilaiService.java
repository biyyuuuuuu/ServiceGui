/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rizky.nilai.service;

import com.rizky.nilai.entity.Nilai;
import com.rizky.nilai.repository.NilaiRepository;
import com.rizky.nilai.vo.Mahasiswa;
import com.rizky.nilai.vo.Matakuliah;
import com.rizky.nilai.vo.ResponseTemplate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class NilaiService {
    @Autowired
    private NilaiRepository nilaiRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Nilai> getAll(){
        return nilaiRepository.findAll();
    } 
    
    public void insert(Nilai nilai){
        Optional<Nilai> nilaiOptional = nilaiRepository.findById(nilai.getId());
        
        
        if (nilaiOptional.isPresent()) {
            throw new IllegalStateException("Nilai Mahasiswa Sudah Ada");
        }
        
       nilaiRepository.save(nilai);
    }
    
    public void delete(long id){
        boolean ada = nilaiRepository.existsById(id);
        if (!ada) {
            throw new IllegalStateException("Nilai ini tidak ada");
        }
        nilaiRepository.deleteById(id);
    }
    
    @Transactional
    public void update(Long id, Long idMahasiswa, Long idMatakuliah, Double nilai) {
        Nilai nilaiUpdate = nilaiRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Nilai tidak ada"));

        if (idMahasiswa != null && !Objects.equals(nilaiUpdate.getIdMahasiswa(), idMahasiswa)) {
            nilaiUpdate.setIdMahasiswa(idMahasiswa);
        }

        if (idMatakuliah != null && !Objects.equals(nilaiUpdate.getIdMatakuliah(), idMatakuliah)) {
            nilaiUpdate.setIdMatakuliah(idMatakuliah);
        }

        if (nilai != null && !Objects.equals(nilaiUpdate.getNilai(), nilai)) {
            nilaiUpdate.setNilai(nilai);
        }
    }

    
    
    public List<ResponseTemplate> getNilai(Long nilaiId){
        
        List<ResponseTemplate> responseList = new ArrayList<>();

        
          List<Nilai> nilaiList = nilaiRepository.findByIdMahasiswa(nilaiId);
          for (Nilai nilai : nilaiList) {
            Mahasiswa mahasiswa = restTemplate.getForObject("http://localhost:9001/api/v1/mahasiswa/"
                +nilai.getIdMahasiswa(),Mahasiswa.class);
        
            Matakuliah matakuliah = restTemplate.getForObject("http://localhost:9002/api/v1/matakuliah/"+nilai.getIdMatakuliah(),Matakuliah.class );
            ResponseTemplate vo = new ResponseTemplate();
            vo.setNilai(nilai);
            vo.setMahasiswa(mahasiswa);
            vo.setMatakuliah(matakuliah);
            responseList.add(vo);
          }
          
        return responseList;
    }
}