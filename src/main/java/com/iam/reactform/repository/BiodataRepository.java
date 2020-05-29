package com.iam.reactform.repository;

import com.iam.reactform.model.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiodataRepository extends JpaRepository<Biodata,Integer> {
}
