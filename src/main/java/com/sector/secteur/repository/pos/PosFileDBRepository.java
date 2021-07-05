package com.sector.secteur.repository.pos;

import com.sector.secteur.model.pos.SecteurP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosFileDBRepository extends JpaRepository<SecteurP, Long> {
}
