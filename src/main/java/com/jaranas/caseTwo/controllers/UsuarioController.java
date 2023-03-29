package com.jaranas.caseTwo.controllers;

import com.jaranas.caseTwo.dao.UsuarioDao;
import com.jaranas.caseTwo.models.Usuario;
import com.jaranas.caseTwo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token){
        return (jwtUtil.getKey(token)!= null);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> listarUsuariosBD(@RequestHeader(value="Authorization") String token){
        if(!validarToken(token)) return null;
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash= argon2.hash(1,1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrarUsuario(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value="Authorization") String token,
                                @PathVariable Long id){
        if(!validarToken(token)) {return;}
        usuarioDao.eliminar(id);
    }

//////////////////////////////////////////////////////////////////
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable String id){
        return findUsuario(variosUsuarios(), Long.valueOf(id));
    }


    @RequestMapping(value = "api/inicio")
    public List<String> inicio(){
        return List.of("Manzana", "Banana", "Durazno");
    }

    @RequestMapping(value = "editar")
    public void editarUsuario(){

    }

    @RequestMapping(value = "api/listar")
    public List<Usuario> listarUsuarios(){
        return variosUsuarios();
    }

    public Usuario findUsuario(List<Usuario> listaUsuarios, Long id) {
        Usuario usEncontrado = null, usTemp = null;
        Iterator<Usuario> it = listaUsuarios.iterator();
        while (it.hasNext() && usEncontrado==null){
            usTemp = it.next();
            if(usTemp.getId()==id)
                usEncontrado = usTemp;
        }
        return usEncontrado;
    }

    private Usuario unUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Maria");
        usuario.setApellido("Perez");
        usuario.setTelefono("123456");
        usuario.setEmail("mp@gmail.com");
        usuario.setPassword("qwerty");
        return usuario;
    }

    private List<Usuario> variosUsuarios(){
        Usuario usuario0 = new Usuario();
        usuario0.setId(10L);
        usuario0.setNombre("Maria0");
        usuario0.setApellido("Perez0");
        usuario0.setTelefono("1234560");
        usuario0.setEmail("mp@gmail.com0");
        usuario0.setPassword("qwerty0");

        Usuario usuario1 = new Usuario();
        usuario1.setId(11L);
        usuario1.setNombre("Maria1");
        usuario1.setApellido("Perez1");
        usuario1.setTelefono("1234561");
        usuario1.setEmail("mp@gmail.com1");
        usuario1.setPassword("qwerty1");

        Usuario usuario2 = new Usuario();
        usuario2.setId(12L);
        usuario2.setNombre("Maria2");
        usuario2.setApellido("Perez2");
        usuario2.setTelefono("1234562");
        usuario2.setEmail("mp@gmail.com2");
        usuario2.setPassword("qwerty2");

        Usuario usuario3 = new Usuario();
        usuario3.setId(13L);
        usuario3.setNombre("Maria3");
        usuario3.setApellido("Perez3");
        usuario3.setTelefono("1234563");
        usuario3.setEmail("mp@gmail.com3");
        usuario3.setPassword("qwerty3");

        Usuario usuario4 = new Usuario();
        usuario4.setId(14L);
        usuario4.setNombre("Maria4");
        usuario4.setApellido("Perez4");
        usuario4.setTelefono("1234564");
        usuario4.setEmail("mp@gmail.com4");
        usuario4.setPassword("qwerty4");
        List<Usuario> llist = new LinkedList<Usuario>(){{ add(usuario0); add(usuario1);add(usuario2);add(usuario3);add(usuario4);}};
        return llist;
    }

    /**
     * Datos de Prueba para insertar en la tabla usuarios
     * INSERT INTO USUARIOS VALUES(2,'Garcia','garcia_23@gmail.com','Ana','Xyz123','555-1234')
     *
     * INSERT INTO USUARIOS VALUES(3,'Perez','perez.juan@hotmail.com','Juan','Abc987','555-4321');
     *
     * INSERT INTO USUARIOS VALUES(4,'Rodriguez','rodriguez.maria@yahoo.com','Maria','Pass1234','555-5555')
     */

}

