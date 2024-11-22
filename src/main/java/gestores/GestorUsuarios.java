package gestores;

import java.util.ArrayList;
import java.util.List;
import models.BilleteraCliente;
import models.Usuario;
import models.Cliente;

public class GestorUsuarios {
    private static GestorUsuarios singleton;
    private List<BilleteraCliente> cuentas;
    private List<Usuario> usuarios;
    private int ultimoId = 0; 

    private GestorUsuarios() {
        this.cuentas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public static synchronized GestorUsuarios getInstance() {
        if (singleton == null) {
            singleton = new GestorUsuarios();
        }
        return singleton;
    }

    public boolean registrarUsuario(Usuario usuario) {
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

        usuario.setIdUsuario(++ultimoId); 

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

    public Cliente buscarUsuarioPorDni(String dniCliente) {
        return usuarios.stream()
                .filter(u -> u instanceof Cliente && ((Cliente) u).getDniCliente().equals(dniCliente))
                .map(u -> (Cliente) u) 
                .findFirst()
                .orElse(null);
    }

    public Usuario autenticarUsuario(int idUsuario, String claveUsuario) {
        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == idUsuario && u.getClaveUsuario().equals(claveUsuario))
                .findFirst()
                .orElse(null);
    }
}
