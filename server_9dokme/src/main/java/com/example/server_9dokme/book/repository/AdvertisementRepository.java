package com.example.server_9dokme.book.repository;

import com.example.server_9dokme.book.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement,Integer> {

    List<Advertisement> findAll();

}
