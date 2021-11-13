package com.example.MerchantService.repository;

import com.example.MerchantService.entity.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface LendRepository extends JpaRepository<Lend,Integer> {

    @Query(value = "select * from lend where loan_requested_from=?1 ",nativeQuery = true)
    List<Lend> getAllLendsByMerchant(String merchantUsername);

    @Query(value = "select * from lend where loan_requested_by=?1 ",nativeQuery = true)
    List<Lend> getAllBorrowsByMerchant(String merchantUsername);

    @Modifying
    @Transactional
    @Query(value = "update lend set is_loan_given = true where id=?1",nativeQuery = true)
    void updateTheTransaction(int lendId);

}
