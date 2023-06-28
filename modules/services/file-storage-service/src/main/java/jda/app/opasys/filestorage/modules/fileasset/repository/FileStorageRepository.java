package jda.app.opasys.filestorage.modules.fileasset.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jda.app.opasys.filestorage.modules.fileasset.model.FileStorage;

@Repository
public interface FileStorageRepository extends PagingAndSortingRepository<FileStorage, Integer>{

}
