package br.com.brunomurilo.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/*INTERFACE = MODELO/CONTRATO - REPRESENTAÇAO DOS METODOS */
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username); /*Dessa forma fica possivel passar o FindByUsername na userController*/
    
}