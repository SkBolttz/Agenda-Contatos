package br.com.Agenda.Contatos.Security;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import br.com.Agenda.Contatos.Entity.Usuario;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Acesso")
                    .withSubject(usuario.getNome())
                    .withClaim("email", usuario.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o Token! " + e.getMessage());
        }
    }

    private Instant getExpirationDate() {
        return Instant.now().plusSeconds(7200); // Token expira em 2 horas
    }

    public DecodedJWT validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Acesso") // Certifique-se de que bate com a geração do token
                    .build()
                    .verify(token);
        } catch (Exception e) {
            throw new RuntimeException("Token inválido! " + e.getMessage());
        }
    }
}
