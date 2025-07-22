package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.usecases.user.User;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findById(Long id);

    Optional<User> findByCpf_Cpf(String cpf);

    User save(User user);

    PagePort<User> findAll(int page, int size);
}
