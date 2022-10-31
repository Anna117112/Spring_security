package com.geecbrains.repositories;



import com.geecbrains.entities.Autorites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoritesRepository extends CrudRepository<Autorites, Long> {
}
