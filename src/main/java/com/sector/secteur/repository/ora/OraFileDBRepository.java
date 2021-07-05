package com.sector.secteur.repository.ora;

import com.sector.secteur.model.ora.SecteurO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OraFileDBRepository extends JpaRepository<SecteurO,Long> {
}
