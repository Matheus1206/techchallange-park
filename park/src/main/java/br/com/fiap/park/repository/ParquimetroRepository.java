package br.com.fiap.park.repository;

import br.com.fiap.park.model.Parquimetro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParquimetroRepository extends JpaRepository<Parquimetro, Long> {
}
