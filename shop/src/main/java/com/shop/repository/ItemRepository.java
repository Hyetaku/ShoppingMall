package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.shop.entity.Item;
import org.hibernate.type.StringNVarcharType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

//엔티티 저장 및 수정 <S extends T> save(S entity)
public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {

//    상품명 조회 테스트
    List<Item> findByItemNm(String itemNm);
    //메소드에 이름(itemNm) 메소드명(findByItemNm)

//    상품명, 상품상세설명 or 테스트(OR 조건을 이용한 쿼리 메소드)
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

//    파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리 메소드
    List<Item> findByPriceLessThan(Integer price);

//    상품을 가격이 높은 순으로 조회하는 예제
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

//    Spring DATA JPA @Query: SQL 과 유사한 JPQL 이라는 객체지향 쿼리언어
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    //@Query 안에 JPQL 로 작성한 쿼리문을 넣어줌
    //from 뒤에는 엔티티 클래스로 작성한 Item 을 지정함, Item 으로부터 데이터를 select 하겠다는 의미임
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    //파라미터에 @Param 을 이용해 파라미터로 넘어온 값을 JPQL 에 들어갈 변수로 지정해줄 수 있음
    //itemDetail 변수를"like % %" 사이에 ":itemDetail"로 값이 들어가도록 작성했음

//    @Query-nativeQuery: 기존의 데이터베이스에서 사용하던 쿼리를 그대로 사용해야 할 때
    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    //value 안에 네이티브 쿼리문을 작성하고 nativeQuery=true 를 지정함
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);


}
