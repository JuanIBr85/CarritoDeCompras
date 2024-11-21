package gestores;

import java.util.ArrayList;
import java.util.List;
import models.CuentaCliente;
import models.Usuario;
import models.Cliente;

public class GestorCuentaUsuario {
	// Reglas del singleton
		// 1- Constructor private 
		// 2- Que tenga un atributo estatico que haga referencia a la clase 
		// 3- metodo para obtener la instancia. GetInstance() 
	
    private static GestorCuentaUsuario singleton;
    private List<CuentaCliente> cuentas;
    private List<Usuario> usuarios;

    private GestorCuentaUsuario() {
        this.cuentas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
   
    //https://keepcoding.io/blog/que-es-el-patron-singleton-en-java/#:~:text=%C2%BFQu%C3%A9%20es%20el%20patr%C3%B3n%20Singleton%20en%20Java%3F%20El,te%20devolver%C3%A1%20el%20mismo%20objeto%20que%20ya%20existe.
    public static synchronized GestorCuentaUsuario getInstance() {
        if (singleton == null) {
        	singleton = new GestorCuentaUsuario();
        }
        return singleton;
    }

    public boolean registrarUsuario(Usuario usuario) { //logica del registro de usuario
        boolean existeUsuario = usuarios.stream()
                .anyMatch(u -> u.getIdUsuario() == usuario.getIdUsuario());
        
        boolean existeDni = false;
        if (usuario instanceof Cliente) {
            String dniCliente = ((Cliente) usuario).getDniCliente();
            existeDni = usuarios.stream()
                    .filter(u -> u instanceof Cliente)
                    .anyMatch(u -> ((Cliente) u).getDniCliente().equals(dniCliente));
        }

        if (existeUsuario) {
            System.out.println("El usuario ya existe.");
            return false;
        }
        if (existeDni) {
            System.out.println("El DNI ya está registrado.");
            return false;
        }

        usuarios.add(usuario);
        return true;
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == idUsuario)
                .findFirst()
                .orElse(null);
    }
    
    public Usuario autenticarUsuario(int idUsuario, String claveUsuario) { //logica del login
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == idUsuario && u.getClaveUsuario().equals(claveUsuario))
                .findFirst()
                .orElse(null);
    }
}
