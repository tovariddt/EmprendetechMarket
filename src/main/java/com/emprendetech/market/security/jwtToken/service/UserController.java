package com.emprendetech.market.security.jwtToken.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.emprendetech.market.dao.PlataformaDao;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.UsuarioContrasenaDto;
import com.emprendetech.market.service.responseDto.CompCorreoRespDto;
import com.emprendetech.market.service.responseDto.PerfilesRespDto;
import com.emprendetech.market.service.responseDto.TokenRespDto;

@RestController
@RequestMapping("/auth")
public class UserController {

	private static final Log LOG = LogFactory.getLog(UserController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PlataformaDao idusarioDao;
    
    // Endpoint público (no seguro)
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome! This endpoint is not secure.";
    }

    // Autenticar y obtener un token JWT
    @PostMapping("/generateToken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody UsuarioContrasenaDto authRequest) {
		LOG.info("llegue a UserController datos "+ authRequest.getNombreusuario()+authRequest.getContrasena() );
         String idusuario=idusarioDao.getIdUsuario(authRequest.getNombreusuario());
		
 		ResponseEntity<?> responseToken = null;

        List<TokenRespDto> resultToken = new ArrayList<>();

            try {
            	String nombre="Bearer Token";	
            	String token=jwtService.generateToken(authRequest.getNombreusuario());
            	TokenRespDto bearerToken = new TokenRespDto();
            	bearerToken.setNombre(authRequest.getNombreusuario());
            	bearerToken.setAuth(nombre);
            	bearerToken.setToken(token);
            	
            	
            	resultToken.add(bearerToken) ;
                final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Token");
                responseContenido.setContenido(resultToken);
                responseToken = new ResponseEntity<>(responseContenido, HttpStatus.OK);
            } catch (Exception e) {
                LOG.error("error" + e.getStackTrace());
                final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Token");
                responseToken = new ResponseEntity<>(responseContenido, HttpStatus.INTERNAL_SERVER_ERROR);
            }            

        
        return responseToken;
    }
    
    
	// jwtService.generateToken(authRequest.getNombreusuario());
    
}
