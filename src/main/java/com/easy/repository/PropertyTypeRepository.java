package com.easy.repository;

import com.easy.entity.PropertyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyTypeEntity,Long> {
}
