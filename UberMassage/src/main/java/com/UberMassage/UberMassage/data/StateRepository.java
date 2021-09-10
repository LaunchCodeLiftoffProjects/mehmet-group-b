package com.UberMassage.UberMassage.data;

import com.UberMassage.UberMassage.models.Appointment;
import com.UberMassage.UberMassage.models.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
}
