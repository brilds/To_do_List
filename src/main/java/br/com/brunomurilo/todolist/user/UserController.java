package br.com.brunomurilo.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") //caminho | rota
public class UserController {

    @Autowired /*Gerencia todo ciclo */
    private IUserRepository userRepository;

    @PostMapping("/")
     /*ResponseEntity = Ter retornos diferentes na aplicaçao dentro da mesma requisiçao*/
    public ResponseEntity create(@RequestBody UserModel userModel) {
    /*Tratamento de usuario */
        var user = userRepository.findByUsername(userModel.getUsername());
        if (user != null) {
            System.out.println("Usuario já existe");

            //mensagem de erro ou Status Code "400 - erro"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var passwordHashed = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashed); //setando senha ***

        var userCreated = userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
