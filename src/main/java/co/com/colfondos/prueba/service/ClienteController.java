package co.com.colfondos.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.colfondos.prueba.persistencia.Cliente;

@RestController
public class ClienteController {
	
	@Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    private List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/clientes/{id}")
    private Cliente getCliente(@PathVariable("id") int id) {
        return clienteService.getClienteById(id);
    }

    @DeleteMapping("/clientes/{id}")
    private void deleteCliente(@PathVariable("id") int id) {
        clienteService.delete(id);
    }

    @PostMapping("/clientes")
    private int saveCliente(@RequestBody Cliente cliente) {
        clienteService.saveOrUpdate(cliente);
        return cliente.getId();
    }
    
	/*
	 * @PutMapping("/clientes/{id}") public ResponseEntity < Cliente > updateUser(
	 * 
	 * @PathVariable(value = "id") int id,
	 * 
	 * @Valid @RequestBody Cliente userDetails) throws ResourceNotFoundException {
	 * Cliente cliente = clienteService.getClienteById(id) .orElseThrow(() - > new
	 * ResourceNotFoundException("Instructor not found :: " + id));
	 * user.setEmail(userDetails.getEmail()); final Cliente updateCliente =
	 * clienteService.saveOrUpdate(user); return ResponseEntity.ok(updatedUser); }
	 */

}
