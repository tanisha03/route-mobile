package com.example.CustomerService.repository;

import com.example.CustomerService.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Integer> {
    boolean existsByCustEmailAndMerchantUsername(String custEmail,String merchUsername);
}
