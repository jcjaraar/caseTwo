package com.jaranas.caseTwo.controllers;

import com.jaranas.caseTwo.dao.UsuarioDao;
import com.jaranas.caseTwo.models.Usuario;
import com.jaranas.caseTwo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuarioIntentandoLoguear){
        Usuario usuarioValidado = usuarioDao.obtenerUsuarioPorCredenciales(usuarioIntentandoLoguear);
        if(usuarioValidado != null) {
            return jwtUtil.create(String.valueOf(usuarioValidado.getId()), usuarioValidado.getEmail());
        }
        return "FAIL";
    }
}