package com.example.cloudrundemo;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class CloudRunDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudRunDemoApplication.class, args);
	}

}

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
class Contact { 

	@Id @GeneratedValue
	Long id;
	String name; 
	String phone;

}

interface ContactRepository extends JpaRepository<Contact, Long>{

}

@RestController
class ContactController{
	@Autowired
	ContactRepository contactRepository;

	@GetMapping("/contacts")
	public List<Contact> getAllContacts(){
		return contactRepository.findAll();
	}

	@GetMapping("/contact/{id}")
	public Contact getContactById(@PathVariable Long id){
		return contactRepository.getById(id);
	}

	@PostMapping("/contact")
	public Contact addContact(@RequestBody Contact contact){
		return contactRepository.save(contact);
	}
}