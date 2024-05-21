# inflearn_mtcoding_shop_bank

## 스프링부트 JUnit 테스트

[참고 GitHub](https://github.com/codingspecialist/junit-bank-class)

## 정리


## DB

### user_tb (유저)



### account_tb (계좌)

- id
- user_id
- number
- balance

### transaction_tb(거래 내역 히스토리)

- id 아이디
- amount 금액
- gubun 구분
- withraw_account_id 출금계좌
- deposit_account_id 입금계좌
- withraw_account_balance 출금 후 금액
- deposit_account_balance 입금 후 금액

히스토리를 이렇게 설계하면 출금기록과 출금기록을 구분해서 가져올 수 있다.

## 메모

### Jpa LocalDateTime 자동으로 생성하는 법

- @EnableJpaAuditing (Main 클래스)
- @EntityListeners(AuditingEntityListener.class) (Entity 클래스)
```java
@CreatedDate
@Column(nullable = false)
private LocalDateTime createdAt;

@LastModifiedDate
@Column(nullable = false)
private LocalDateTime updatedAt;
```
