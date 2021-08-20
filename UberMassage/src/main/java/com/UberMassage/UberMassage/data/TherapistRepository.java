package com.UberMassage.UberMassage.data;

import com.UberMassage.UberMassage.models.Therapist;
import org.springframework.data.repository.CrudRepository;

public interface TherapistRepository extends CrudRepository<Therapist, Integer> {
    //Therapist findByUsername(String username);
}
