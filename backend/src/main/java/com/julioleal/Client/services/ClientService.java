package com.julioleal.client.services;




import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julioleal.client.entities.Client;
import com.julioleal.client.entities.ClientDTO;
import com.julioleal.client.repositories.ClientRepository;
import com.julioleal.client.services.exceptions.DataBaseException;
import com.julioleal.client.services.exceptions.ResourceNotFoundExeption;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(PageRequest pageRequest){
		Page<Client> clientList =  repository.findAll(pageRequest);
		Page<ClientDTO> clientDTOList = clientList.map(x -> new ClientDTO(x));
		return clientDTOList;				
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return new ClientDTO(repository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Cliente não encontrado.")));
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client(dto);
		client = repository.save(client);
		return new ClientDTO(client);
		
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = repository.getReferenceById(id);
			client.setBirthDate(dto.getBirthDate());
			client.setChildren(dto.getChildren());
			client.setCpf(dto.getCpf());
			client.setIncome(dto.getIncome());
			client.setName(dto.getName());
			return new ClientDTO(client);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExeption("Id não encontrado " + id);
		}
		
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundExeption("Id não encontrado");
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
		
	}
}
