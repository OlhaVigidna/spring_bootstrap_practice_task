package ua.com.ouw.spring_bootstrap_practice_task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.ouw.spring_bootstrap_practice_task.models.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

}
