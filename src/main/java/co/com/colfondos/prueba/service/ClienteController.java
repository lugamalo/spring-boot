package co.com.colfondos.prueba.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.colfondos.prueba.excepciones.ResourceNotFoundException;
import co.com.colfondos.prueba.persistencia.Cliente;
import co.com.colfondos.prueba.persistencia.ClienteRepository;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteRepository clienteRepository;

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

	@PutMapping("/clientes/{id}") 
	public ResponseEntity < Cliente > updateCliente(@PathVariable(value = "id") int id,
	  @Valid @RequestBody Cliente infoCliente) throws ResourceNotFoundException {
	  Cliente cliente = clienteRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("El cliente con este id no fue encontrado :: " + id));

	  cliente.setNumeroIdentificacion(infoCliente.getNumeroIdentificacion());
	  cliente.setTipoIdentificacion(infoCliente.getTipoIdentificacion());
	  cliente.setCorreo(infoCliente.getCorreo());
	  cliente.setCelular(infoCliente.getCelular());
	  
	  final Cliente updateCliente = clienteRepository.save(cliente); 
	  return ResponseEntity.ok(updateCliente); }

}
