# Retailer Rewards
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.

# Technologies 
- Java 8, Spring Boot 2.7.6
- Spring MVC,Spring Data JPA
- Junit, Mockito
- Embedded H2 DB

# APIs
TransactionController
- /transaction/create : Create new transaction

RewardController
- /reward/monthly/{customerId} : Get monthly reward points for the past three-month by customer id
- /reward/total/{customerId} : Get total reward points for the past three-month by customer id

# Unit Test
Test all methods in repository, service and controller layers.

