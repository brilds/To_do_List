package br.com.brunomurilo.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

    /*
     * ID
     * usuario - id_user
     * title
     * date inicio
     * date fim
     * priority
     * 
     */
@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    
    @Id //primary Key
    @GeneratedValue(generator = "UUID") 
    private UUID id;
    private String description;
    
    
    @Column(length = 50) //Limitar tamanho do title char
    private String title;
    private LocalDateTime startAt; //localdatetime = pode-se colocar data e horario
    private LocalDateTime endAt;
    private String priority;
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    
}
