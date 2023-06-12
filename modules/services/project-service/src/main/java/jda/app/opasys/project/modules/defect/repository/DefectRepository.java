package jda.app.opasys.project.modules.defect.repository;

import org.springframework.stereotype.Repository;

import jda.app.opasys.project.modules.defect.model.Defect;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface DefectRepository extends PagingAndSortingRepository<Defect, Integer>{
}
