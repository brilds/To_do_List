package br.com.brunomurilo.todolist.user;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data /*dependencia lombok, gerando os getters e setters debaixo dos panos, deixando o codigo mais limpo*/
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true) /*Setando uma coluna com restrição de atributo unico (para que nao existam cadastros com usuarios iguais)*/
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt; /*Data Quando o atributo foi criado no BD */
}
