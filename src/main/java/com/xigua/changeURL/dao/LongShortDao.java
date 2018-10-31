package com.xigua.changeURL.dao;

import com.xigua.changeURL.model.LongShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: miaomiao
 * @Date: 2018/10/30
 * @Description:
 **/
@Repository
public interface LongShortDao extends JpaRepository<LongShort,Integer> {

    LongShort findByShortUrl(String shortUrl);

    @Query("select accessCount from LongShort where shortUrl = ?1")
    int getCountByShortUrl(String shortUrl);

    @Modifying
    @Query("update LongShort set access_count = access_count + 1 where id = ?1")
    int updateCount(int id);

}
