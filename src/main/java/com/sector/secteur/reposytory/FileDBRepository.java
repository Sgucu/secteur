package com.sector.secteur.reposytory;

import com.sector.secteur.model.Secteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<Secteur, Long> {
}
