package br.com.brunomurilo.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users") //caminho | rota
public class UserController {
    
    @Autowired /*Gerencia todo ciclo */
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        /*ResponseEntity = Ter retornos diferentes na aplicaçao dentro da mesma requisiçao*/
        /*Tratamento de usuario */
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            //mensagem de erro ou Status Code "400 - erro"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente!"); //mostra diretamente no retorno do JSON
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()); //criptografia de senha

        userModel.setPassword(passwordHashred); //setando senha

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
