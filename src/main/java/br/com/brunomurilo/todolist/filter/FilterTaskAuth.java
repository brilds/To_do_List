package br.com.brunomurilo.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.brunomurilo.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component //toda classe para o string gerenciar, adicionar @componente / @restController
/*
 * Toda requisição, antes de ir de fato para a rota ira passar pelo Filter
 */
public class FilterTaskAuth  extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                var servletPath = request.getServletPath();
                if(servletPath.startsWith("/tasks/")){
                        //pegar a autenticação (user, senha)
                    var authorization = request.getHeader("Authorization");
                    
                    var authEncoded = authorization.substring("Basic".length()).trim(); //Calcular lenght do "Basic" / tirar os caracteres e espaços

                    byte[] authDecode = Base64.getDecoder().decode(authEncoded); // gera DeCode - array de bytes

                    var authString = new String(authDecode); //DeCode para String, assim ficando entendivel

                    
                    String[] credentials = authString.split(":");
                    String username = credentials[0]; //["user", "password"]
                    String password = credentials[1]; //["user", "password"]
                    

                    //validar user
                        var user = this.userRepository.findByUsername(username);
                        if(user == null){
                            response.sendError(401);
                        }else{
                            //validar senha
                        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                        if(passwordVerify.verified){

                            //segue fluxo
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);
                        }else{
                                response.sendError(401);
                        }

                        }
                }else{
                    filterChain.doFilter(request, response);
                }

                


                
                
    }

}
