package vn.id.quanghuydevfs.drcomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.id.quanghuydevfs.drcomputer.model.evaluate.Evaluate;

@Repository
public interface EvaluateRepository extends JpaRepository<Evaluate, Long> {
}
