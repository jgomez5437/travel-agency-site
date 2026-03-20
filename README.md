# E-Commerce Travel Application: Backend Architecture & API Integration

## Executive Summary
Engineered and debugged a robust Spring Boot backend for an enterprise-level travel booking web application. This project focused on building a seamless bridge between a strict Java/Hibernate backend and a dynamic Angular frontend, ensuring data integrity, transaction safety, and precise REST API serialization.

## Tech Stack
* **Backend:** Java, Spring Boot, Spring Data REST, Hibernate / JPA
* **Frontend:** Angular, TypeScript, HTML/CSS
* **Database:** MySQL
* **Tools:** Maven, Jackson (JSON Serialization), Chrome DevTools

## Key Engineering Achievements

1. **JPA Entity Lifecycle & Transaction Management**
    * **Resolved Detached Entity Collisions:** Diagnosed and fixed critical `ObjectOptimisticLockingFailureException` and `StaleObjectStateException` crashes. Intercepted `id: 0` payloads sent by the frontend, dynamically nullifying them in the service layer to force Hibernate into `INSERT` operations rather than failing `UPDATE` attempts.
    * **Bi-Directional Cascade Persistence:** Orchestrated complex entity relationships (`Customer -> Cart -> CartItem`) within `@Transactional` boundaries, ensuring child objects were fully linked via helper methods (`addCartItem()`) before saving the aggregate root to prevent data integrity violations.
    * **UUID Order Tracking:** Implemented server-side generation of universally unique order tracking numbers, safely persisting them upon successful transaction commits.

2. **REST API Payload Serialization & Data Binding**
    * **Jackson Payload Reconciliation:** Audited and corrected severe mismatches between the Angular JSON payload and Java POJOs. Repaired case-sensitive mapping errors (`image_URL`), pluralization mismatches (`cartItems`), and nullability constraints to eliminate `400 Bad Request` validation walls.
    * **Foreign Key Exposure for UI Filtering:** Overcame Spring Data REST's default HATEOAS link encapsulation by utilizing `@JoinColumn(insertable = false, updatable = false)` alongside specific `@JsonProperty` scalars. This safely exposed `country_id` data to the frontend, unblocking the Angular UI's geographic cascading dropdown filters.

3. **Spring Data REST Configuration**
    * **Endpoint Hijacking Resolution:** Identified and neutralized a routing collision where a misconfigured `@RepositoryRestResource` hijacked the `/api/countries` path, routing traffic to the `carts` table.
    * **Custom Search Endpoints:** Engineered custom Spring Data JPA repository queries (`findByCountry_Id`) to automatically generate the nested search endpoints required by the frontend geographic components.
    * **Cross-Origin & ID Unmasking:** Configured global CORS policies (`@CrossOrigin`) and implemented a `RepositoryRestConfigurer` to explicitly expose database IDs, allowing the frontend dropdowns to successfully bind to backend record references.

4. **Deterministic Database Bootstrapping**
    * **Constraint-Aware Initialization:** Built a robust `CommandLineRunner` script to seed the database with initial users. Engineered the logic to navigate strict environmental constraints (`ddl-auto=none`), satisfy all `@NotBlank` backend validation rules, and dynamically fetch necessary foreign keys (`Division`) before executing `saveAll()` operations.
