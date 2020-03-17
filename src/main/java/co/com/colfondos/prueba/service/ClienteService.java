package co.com.colfondos.prueba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.colfondos.prueba.persistencia.Cliente;
import co.com.colfondos.prueba.persistencia.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        clienteRepository.findAll().forEach(cliente -> clientes.add(cliente));
        return clientes;
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).get();
    }

    public void saveOrUpdate(Cliente person) {
        clienteRepository.save(person);
    }

    public void delete(int id) {
        clienteRepository.deleteById(id);
    }

}
