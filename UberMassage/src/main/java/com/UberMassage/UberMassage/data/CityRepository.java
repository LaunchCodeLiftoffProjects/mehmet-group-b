package com.UberMassage.UberMassage.data;

import com.UberMassage.UberMassage.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository  extends CrudRepository<City, Integer> {
}
