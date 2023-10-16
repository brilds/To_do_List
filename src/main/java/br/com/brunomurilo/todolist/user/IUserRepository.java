package br.com.brunomurilo.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
/*INTERFACE = MODELO/CONTRATO - REPRESENTAÇAO DOS METODOS */
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username); /*Dessa forma fica possivel passar o FindByUsername na userController*/
}
